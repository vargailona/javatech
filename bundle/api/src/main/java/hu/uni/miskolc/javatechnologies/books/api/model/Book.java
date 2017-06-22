package hu.uni.miskolc.javatechnologies.books.api.model;

public class Book {

private String writer;
private String title;
private int bookId;
private int pages;

public Book (){
	super ();
}

public Book(String writer, String title, int bookId, int pages) {
	super();
	this.writer = writer;
	this.title = title;
	this.bookId = bookId;
	this.pages = pages;
}

public String getWriter() {
	return writer;
}

public void setWriter(String writer) {
	this.writer = writer;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public int getBookId() {
	return bookId;
}

public void setBookId(int bookId) {
	this.bookId = bookId;
}

public int getPages() {
	return pages;
}

public void setPages(int pages) {
	this.pages = pages;
}


}
