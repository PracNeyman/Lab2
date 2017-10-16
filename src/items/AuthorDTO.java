package items;

public class AuthorDTO {
	private Long authorID;
	private String name;
	private int age;
	private String country;
	public void setCountry(String country) {
		this.country=country;
	}
	public void setAge(int age) {
		this.age=age;
	}
	public void setName(String name) {
		this.name=name;
	}
	public void setAuthorID(Long authorID) {
		this.authorID=authorID;
	}
	public Long getAuthorID() {
		return authorID;
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public String getCountry() {
		return country;
	}
}
