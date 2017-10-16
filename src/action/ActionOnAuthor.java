package action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import items.AuthorDTO;
import items.BookDTO;
import mysqlcon.SqlCon;

public class ActionOnAuthor extends ActionSupport{
	
	private SqlCon myCon = new SqlCon();
	private List<AuthorDTO> authorList = new ArrayList<>();
	
	//查询，改变链表 ,所有的查询都通过这个来生成一个链表，并返回链表
	public void retrieve(String sql) throws SQLException {
		authorList.clear();
		ResultSet result = myCon.executeQuery(sql);
		while(result.next()) {
			AuthorDTO authortmp = new AuthorDTO();
			authortmp.setAge(result.getInt("Age"));
			authortmp.setAuthorID(result.getLong("AuthorID"));
			authortmp.setCountry(result.getString("Country"));
			authortmp.setName(result.getString("Name"));
			authorList.add(authortmp);
		}
		result.close();
	}
	
	//根据姓名找作者
	public List<AuthorDTO> retrieveByName(String name) throws SQLException {
		String sql="select * from author where name=\""+name+"\";";
		retrieve(sql);
		return authorList;
	}
	
	//根据ID找作者
	public List<AuthorDTO> retrieveByID(Long ID) throws SQLException {
		String sql="select * from author where authorID="+ID+";";
		retrieve(sql);
		return authorList;
	}

	public List<AuthorDTO> retrieveAll() throws SQLException {

		String sql="select * from author";
		System.out.println(sql);
		retrieve(sql);
//		ActionContext.getContext().put("list", list);
		return authorList;
	}
	
	//增加作者
	public String insertAuthor(AuthorDTO author) {
		String sql="insert INTO author values ("+author.getAuthorID()+",\""+author.getName()+"\","+author.getAge()+",\""
				+author.getCountry()+"\");";
		System.out.println("sql是"+sql);
		myCon.executeUpdate(sql);
		return SUCCESS;
	}
	
	//删除作者
	public String removeAuthor(Long authorID) {
		String sql = "delete from author where authorID="+authorID.toString()+";";
		myCon.executeUpdate(sql);
		return SUCCESS;
	}
	
	//更新作者
	public String updateAuthor(AuthorDTO authorDTO) {
		String sql ="update author set name=\""+authorDTO.getName()+"\",age="+authorDTO.getAge()+",country=\""
				+authorDTO.getCountry()+"\" where authorID="+authorDTO.getAuthorID()+";";
		myCon.executeUpdate(sql);
		return SUCCESS;
	}
	
	private int jiashu;
	public void setJiashu(int jiashu) {
		this.jiashu=jiashu;
	}
	public int getJiashu() {
		return jiashu;
	}
	public String execution() {
		ActionContext.getContext().put("jiashu", getJiashu()+1);
		return "SUCCESS";
	}
	
}