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
	
	//������������������
	//���裺	1.���������ҳ�����ͬ������
	//		2.����ͬ�����ߣ���������ID���飬������߿��ܲ���ͬһ���ˣ���������������ͬ
	public String findTitleByAuthorName() throws SQLException {
		authorList=actionOnAuthor.retrieveByName(author.getName());
		ActionContext.getContext().put("authorList", authorList);
		bookList.clear();
		if (!authorList.isEmpty()) {
			for(AuthorDTO authorTmp:authorList) {
				bookList.addAll(actionOnBook.retrieveByAuthorID(authorTmp.getAuthorID()));
			}
			ActionContext.getContext().put("bookList", bookList);
			ActionContext.getContext().put("msg", "���ҳɹ�");
		} else {
			ActionContext.getContext().put("msg", "û�и�����");
		}
		return SUCCESS;
	}
	
	//�����������������
	//���裺	1.���������ҳ�����ͬ����
	//		2.����ͬ���飬��������ID�����ߣ������ж������
	public String findBookandAuthorByTitle() throws SQLException {
		bookList=actionOnBook.retrieveByTitle(book.getTitle());
		//û���ҵ���
		if (bookList.isEmpty()) {
			return ERROR;
		}
		authorList.clear();
		for(BookDTO bookTmp:bookList) {
			authorList.addAll(actionOnAuthor.retrieveByID(bookTmp.getAuthorID()));
		}
		//û���ҵ����ߣ���ʵ�ҵ����ˣ��Ϳ϶��������ߵ�
		if(authorList.isEmpty()) {
			return ERROR;
		}
		ActionContext.getContext().put("bookList", bookList);
		ActionContext.getContext().put("authorList", authorList);
		return SUCCESS;
	}
	
	//����ISBN���������
	public String findBookAndAuthorByISBN() throws SQLException {
		System.out.println("�ɹ�����action");	
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
	
	//ɾ��,���ͬһ������û��д�������飬��ô������Ҳɾ��
	public String removeBook() throws SQLException {
		//��֤��û���Ȿ��
		bookList=actionOnBook.retrieveByISBN(book.getISBN());
		if (bookList.isEmpty()) {
			ActionContext.getContext().put("msg", "û���Ȿ�飬ɾ��ʧ��");
			return ERROR;			
		}
		//��֤��û��ͬһ������д��������
		Long authorIDtmp=bookList.get(0).getAuthorID();
		
		actionOnBook.removeBook(book.getISBN());
		bookList=actionOnBook.retrieveByAuthorID(authorIDtmp);
		//����Ȿ��û�����ƪ����������ɾ���������
		if (bookList.isEmpty()) {
			actionOnAuthor.removeAuthor(authorIDtmp);
			ActionContext.getContext().put("msg", "��������û�����������ˣ���˸�����Ҳ��ɾ����");
			
		}else {
			ActionContext.getContext().put("msg", "�����Ǳ������ߵ������鼮");
			
		}
		ActionContext.getContext().put("bookList", bookList);
		return SUCCESS;
	}
	
	//��������
	public String insertAuthor() throws SQLException {
		authorList=actionOnAuthor.retrieveByID(author.getAuthorID());
		if (!authorList.isEmpty()) {
			ActionContext.getContext().put("msg", "���ʧ�ܣ����ݿ��д�����ͬAuthorID�������һ������ID");
			return ERROR;
		}
		actionOnAuthor.insertAuthor(author);
		ActionContext.getContext().put("authorID", author.getAuthorID());
		return SUCCESS;
	}
	
	//������
	public String insertBook() throws SQLException {
		//��֤��û���Ȿ��
		System.out.println("������action");
		bookList=actionOnBook.retrieveByISBN(book.getISBN());
		if (!bookList.isEmpty()) {
			ActionContext.getContext().put("msg", "���ʧ�ܣ����ݿ����Ѿ������Ȿ����");
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
	
	//����
	//���裺	1.book��author��id��ͬ������
	//		2.�鲻���ڣ�����
	//		3.�������IDû�б䣬�͸������ߣ�����
	//		4.�������û�������ߣ������Ӹ�����
	//		5.���������ߣ��͸�������
	//		6.������
	//		7.���ԭ����û��������,�Ǿ�ɾ��ԭ����
	public String update() throws SQLException {
		if (!book.getAuthorID().equals(author.getAuthorID())) {
			ActionContext.getContext().put("msg", "����ʧ��,��������ͬ��AuthorID");
			return ERROR;
		}
		bookList=actionOnBook.retrieveByISBN(book.getISBN());
		if (bookList.isEmpty()) {
			ActionContext.getContext().put("msg", "����ʧ��,���鲻����");
			return ERROR;
		}
		Long oldID=bookList.get(0).getAuthorID();
		if (oldID.equals(author.getAuthorID())) {
			actionOnAuthor.updateAuthor(author);
			actionOnBook.updateBook(book);
			ActionContext.getContext().put("msg", "���³ɹ�");
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
			ActionContext.getContext().put("msg", "���³ɹ�����ע�⣺��������û�и����ߣ������Ѿ�Ϊ������");
		}else {
			ActionContext.getContext().put("msg", "���³ɹ�");
		}
		
		bookList=actionOnBook.retrieveByAuthorID(oldID);
		if(bookList.isEmpty()) {
			actionOnAuthor.removeAuthor(oldID);
			if (addAuthor) {
				ActionContext.getContext().put("msg", "���³ɹ�����ע�⣺��������û�������ߣ������Ѿ�Ϊ������,ԭ����û�������飬�Ѿ���ɾ��");
			}else {
				ActionContext.getContext().put("msg", "���³ɹ�����ע�⣺ԭ����û�������飬�Ѿ���ɾ��");
			}
		}
		
		ActionContext.getContext().put("book",book);
		ActionContext.getContext().put("author", author);
		return SUCCESS;
	}
	
	//��������������
	public String retrieveByTitle() throws SQLException {
		bookList=actionOnBook.retrieveByTitle(book.getTitle());
		if(bookList.isEmpty()) {
			ActionContext.getContext().put("msg", "����ʧ�ܣ�û���Ȿ��");
			return ERROR;
		}
		ActionContext.getContext().put("bookList", bookList);
		return SUCCESS;
	}

}
