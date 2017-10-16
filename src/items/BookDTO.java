package items;

import java.sql.Date;

public class BookDTO {
	private Long ISBN;
	private String title;
	private Long authorID;
	private String publisher;
	private String publishDate;
	private double price;
	public void setISBN(Long ISBN) {
		this.ISBN=ISBN;
	}
	public void setTitle(String title) {
		this.title=title;
	}
	public void setAuthorID(Long ID) {
		this.authorID=ID;
	}
	public void setPublisher(String puber) {
		this.publisher=puber;
	}
	public void setPublishDate(String pubdate) {
		this.publishDate=pubdate;
	}
	public void setPrice(double price) {
		this.price=price;
	}
	public double getPrice() {
		return price;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public String getPublisher() {
		return publisher;
	}
	public Long getISBN() {
		return ISBN;
	}
	public String getTitle() {
		return title;
	}
	public Long getAuthorID() {
		return authorID;
	}
}
