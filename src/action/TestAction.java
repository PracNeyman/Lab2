package action;

import mysqlcon.SqlCon;

public class TestAction {
    private String usrname;//�˴�����Ӧ��jsp����һ��
    private String pwd;
    public String getUsrname() {
        return usrname;
    }
    public void setUsrname(String usrname) {
        this.usrname = usrname;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    
    public String excute()
    {
        SqlCon mycon = new SqlCon();
        String insert = "insert into users values('"+getUsrname()+"','"+getPwd()+"');";
        mycon.executeUpdate(insert);
        return "SUCCESS";
    }
    public String exe()
    {
    	return "SUCCESS";
    }

}