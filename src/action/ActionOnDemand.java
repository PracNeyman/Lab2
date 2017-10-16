package action;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import items.AuthorDTO;
import items.BookDTO;
import mysqlcon.SqlCon;

public class ActionOnDemand extends ActionSupport{
//	private Long ISBN;
//	public void setISBN(Long ISBN) {
//		System.out.println(ISBN);
//		this.ISBN=ISBN;
//	}
//	public Long getISBN() {
//		return ISBN;
//	}
	
	
	private AuthorDTO author;
	public void setAuthor(AuthorDTO author) {
		this.author=author;
	}
	public AuthorDTO getAuthor() {
		return author;
	}
	private BookDTO book;
	public void setBook(BookDTO book) {
		this.book=book;
	}
	public BookDTO getBook() {
		return book;
	}
	
	SqlCon myCon = new SqlCon();
	
	ActionOnBook actionOnBook=new ActionOnBook();
	ActionOnAuthor actionOnAuthor=new ActionOnAuthor();
	
	private List<BookDTO> bookList = new ArrayList<>();
	private List<AuthorDTO> authorList= new ArrayList<>();
	
	//根据作者姓名找书名
	//步骤：	1.根据姓名找出所有同名作者
	//		2.遍历同名作者，根据作者ID找书，书的作者可能不是同一个人，但是作者名字相同
	public String findTitleByAuthorName() throws SQLException {
		authorList=actionOnAuthor.retrieveByName(author.getName());
		ActionContext.getContext().put("authorList", authorList);
		bookList.clear();
		if (!authorList.isEmpty()) {
			for(AuthorDTO authorTmp:authorList) {
				bookList.addAll(actionOnBook.retrieveByAuthorID(authorTmp.getAuthorID()));
			}
			ActionContext.getContext().put("bookList", bookList);
			ActionContext.getContext().put("msg", "查找成功");
		} else {
			ActionContext.getContext().put("msg", "没有该作者");
		}
		return SUCCESS;
	}
	
	//根据书名找书和作者
	//步骤：	1.根据书名找出所有同名书
	//		2.遍历同名书，根据作者ID找作者，可能有多个作者
	public String findBookandAuthorByTitle() throws SQLException {
		bookList=actionOnBook.retrieveByTitle(book.getTitle());
		//没有找到书
		if (bookList.isEmpty()) {
			return ERROR;
		}
		authorList.clear();
		for(BookDTO bookTmp:bookList) {
			authorList.addAll(actionOnAuthor.retrieveByID(bookTmp.getAuthorID()));
		}
		//没有找到作者，其实找到书了，就肯定会有作者的
		if(authorList.isEmpty()) {
			return ERROR;
		}
		ActionContext.getContext().put("bookList", bookList);
		ActionContext.getContext().put("authorList", authorList);
		return SUCCESS;
	}
	
	//根据ISBN找书和作者
	public String findBookAndAuthorByISBN() throws SQLException {
		System.out.println("成功调用action");	
//		System.out.println(ISBN);
		System.out.println(book.getISBN());
		bookList=actionOnBook.retrieveByISBN(book.getISBN());
		if (bookList.isEmpty()) {
			return ERROR;
		}
		authorList=actionOnAuthor.retrieveByID(bookList.get(0).getAuthorID());
		ActionContext.getContext().put("bookList", bookList);
		ActionContext.getContext().put("authorList", authorList);
		return SUCCESS;
		
	}
	
	//删书,如果同一个作者没有写过其他书，那么把作者也删了
	public String removeBook() throws SQLException {
		//验证有没有这本书
		bookList=actionOnBook.retrieveByISBN(book.getISBN());
		if (bookList.isEmpty()) {
			ActionContext.getContext().put("msg", "没有这本书，删除失败");
			return ERROR;			
		}
		//验证有没有同一个作者写的其他书
		Long authorIDtmp=bookList.get(0).getAuthorID();
		
		actionOnBook.removeBook(book.getISBN());
		bookList=actionOnBook.retrieveByAuthorID(authorIDtmp);
		//如果这本书没有姊妹篇，好啦，那删了这个作者
		if (bookList.isEmpty()) {
			actionOnAuthor.removeAuthor(authorIDtmp);
			ActionContext.getContext().put("msg", "本书作者没有其他的书了，因此该作者也被删除了");
			
		}else {
			ActionContext.getContext().put("msg", "下面是本书作者的其他书籍");
			
		}
		ActionContext.getContext().put("bookList", bookList);
		return SUCCESS;
	}
	
	//增加作者
	public String insertAuthor() throws SQLException {
		authorList=actionOnAuthor.retrieveByID(author.getAuthorID());
		if (!authorList.isEmpty()) {
			ActionContext.getContext().put("msg", "添加失败，数据库中存在相同AuthorID，请更换一个作者ID");
			return ERROR;
		}
		actionOnAuthor.insertAuthor(author);
		ActionContext.getContext().put("authorID", author.getAuthorID());
		return SUCCESS;
	}
	
	//增加书
	public String insertBook() throws SQLException {
		//验证有没有这本书
		System.out.println("进入了action");
		bookList=actionOnBook.retrieveByISBN(book.getISBN());
		if (!bookList.isEmpty()) {
			ActionContext.getContext().put("msg", "添加失败，数据库中已经存在这本书了");
			return ERROR;			
		}	
		authorList=actionOnAuthor.retrieveByID(book.getAuthorID());
//		if (authorList.isEmpty()) {
//			actionOnAuthor.insertAuthor(author);
//		}
		actionOnBook.insertBook(book);
		ActionContext.getContext().put("book", book);
		ActionContext.getContext().put("author",authorList.get(0));
		return SUCCESS;
	}
	
	//更新
	//步骤：	1.book和author的id不同，返回
	//		2.书不存在，返回
	//		3.如果作者ID没有变，就更新作者，返回
	//		4.如果库中没有新作者，就增加该作者
	//		5.存在新作者，就更新作者
	//		6.更新书
	//		7.如果原作者没有其他书,那就删掉原作者
	public String update() throws SQLException {
		if (!book.getAuthorID().equals(author.getAuthorID())) {
			ActionContext.getContext().put("msg", "更新失败,请输入相同的AuthorID");
			return ERROR;
		}
		bookList=actionOnBook.retrieveByISBN(book.getISBN());
		if (bookList.isEmpty()) {
			ActionContext.getContext().put("msg", "更新失败,此书不存在");
			return ERROR;
		}
		Long oldID=bookList.get(0).getAuthorID();
		if (oldID.equals(author.getAuthorID())) {
			actionOnAuthor.updateAuthor(author);
			actionOnBook.updateBook(book);
			ActionContext.getContext().put("msg", "更新成功");
			ActionContext.getContext().put("book",book);
			ActionContext.getContext().put("author", author);
			return SUCCESS;
		}
		authorList=actionOnAuthor.retrieveByID(book.getAuthorID());
		boolean addAuthor=false;
		if (authorList.isEmpty()) {
			actionOnAuthor.insertAuthor(author);
			addAuthor=true;
		}else {
			actionOnAuthor.updateAuthor(author);
		}
		actionOnBook.updateBook(book);
		
		if (addAuthor) {
			ActionContext.getContext().put("msg", "更新成功，请注意：本来库中没有该作者，现在已经为您增加");
		}else {
			ActionContext.getContext().put("msg", "更新成功");
		}
		
		bookList=actionOnBook.retrieveByAuthorID(oldID);
		if(bookList.isEmpty()) {
			actionOnAuthor.removeAuthor(oldID);
			if (addAuthor) {
				ActionContext.getContext().put("msg", "更新成功，请注意：本来库中没有新作者，现在已经为您增加,原作者没有其他书，已经被删除");
			}else {
				ActionContext.getContext().put("msg", "更新成功，请注意：原作者没有其他书，已经被删除");
			}
		}
		
		ActionContext.getContext().put("book",book);
		ActionContext.getContext().put("author", author);
		return SUCCESS;
	}
	
	//根据书名查找书
	public String retrieveByTitle() throws SQLException {
		bookList=actionOnBook.retrieveByTitle(book.getTitle());
		if(bookList.isEmpty()) {
			ActionContext.getContext().put("msg", "查找失败，没有这本书");
			return ERROR;
		}
		ActionContext.getContext().put("bookList", bookList);
		return SUCCESS;
	}

}
