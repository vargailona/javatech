package hu.uni.miskolc.javatechnologies.books.api.model;

import java.util.Date;

public class announcement {
private Book book;
private Price price;
private Date startDate;
private Date expirationDate;
private boolean sold;

public announcement () {
	super ();
}
public announcement (Book book, Price price, Date startDate, Date expirationDate, boolean isSold){
	super ();
	this.book=book;
	this.price=price;
	this.startDate=startDate;
	this.expirationDate=expirationDate;
	this.sold=isSold;
}


public Book getBook() {
	return book;
}
public void setBook(Book book) {
	this.book = book;
}
public Price getPrice() {
	return price;
}
public void setPrice(Price price) {
	this.price = price;
}
public Date getStartDate() {
	return startDate;
}
public void setStartDate(Date startDate) {
	this.startDate = startDate;
}
public Date getExpirationDate() {
	return expirationDate;
}
public void setExpirationDate(Date expirationDate) {
	this.expirationDate = expirationDate;
}
public boolean isSold() {
	return sold;
}
public void setSold(boolean sold) {
	this.sold = sold;
}

@Override
public String toString (){
	return "Announcement [book=" + book + ", price=" + price + ", startDate=" + startDate + ", expirationDate="
			+ expirationDate + ", sold=" + sold + "]";
}

}

