package action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import items.BookDTO;
import mysqlcon.SqlCon;

public class ActionOnBook extends ActionSupport{
	SqlCon mSqlCon=new SqlCon();
	List<BookDTO> bookList = new ArrayList<>();
	
	public void retrieve(String sql) throws SQLException {
		bookList.clear();
		ResultSet resultSet = mSqlCon.executeQuery(sql);
		while (resultSet.next()) {
			BookDTO bookTmp = new BookDTO();
			bookTmp.setAuthorID(resultSet.getLong("authorID"));
			bookTmp.setISBN(resultSet.getLong("ISBN"));
			bookTmp.setTitle(resultSet.getString("title"));
			bookTmp.setPublisher(resultSet.getString("publisher"));
			bookTmp.setPublishDate(resultSet.getString("publishDate"));
			bookTmp.setPrice(resultSet.getDouble("price"));
			bookList.add(bookTmp);
			
			//这里是为了Git实验而进行的操作
		}
		resultSet.close();
	}
	
	//根据ISBN找到书
	public List<BookDTO> retrieveByISBN(Long ISBN) throws SQLException {
		System.out.println(ISBN);
		String sql = "select * from book where ISBN="+ISBN+";";
		retrieve(sql);
		return bookList;
	}
	
	//根据作者ID找到书
	public List<BookDTO> retrieveByAuthorID(Long authorID) throws SQLException {
		String sql = "select * from book where authorID="+authorID+";";
		retrieve(sql);
		return bookList;
	}
	
	//根据书名找书
	public List<BookDTO> retrieveByTitle(String title) throws SQLException {
		String sql = "select * from book where title=\""+title+"\";";
		retrieve(sql);
		return bookList;
	}
	
	//删除书
	public String removeBook(Long ISBN) {
		String sql = "delete from book where ISBN="+ISBN.toString()+";";
		mSqlCon.executeUpdate(sql);
		return SUCCESS;
	}
	
	//增加书
	public String insertBook(BookDTO bookDTO) {
		String sql = "insert into book values ("+bookDTO.getISBN().toString()+",\""+bookDTO.getTitle()+"\","+bookDTO.getAuthorID()
			+",\""+bookDTO.getPublisher()+"\",\""+bookDTO.getPublishDate()+"\","+bookDTO.getPrice()+");";
		mSqlCon.executeUpdate(sql);
		return SUCCESS;
	}
	
	//更新书
	public String updateBook(BookDTO bookDTO) {
		String sql ="update book set title=\""+bookDTO.getTitle()+"\",authorID="+bookDTO.getAuthorID()+",publisher=\""
				+bookDTO.getPublisher()+"\",publishDate=\""+bookDTO.getPublishDate()+"\",price="+bookDTO.getPrice()+" where "
						+ "ISBN="+bookDTO.getISBN()+";";
		mSqlCon.executeUpdate(sql);
		return SUCCESS;
	}
}
