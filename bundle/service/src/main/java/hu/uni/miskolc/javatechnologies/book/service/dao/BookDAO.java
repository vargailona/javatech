package hu.uni.miskolc.javatechnologies.book.service.dao;

import java.util.Collection;

import hu.uni.miskolc.javatechnologies.books.api.model.Book;


public interface BookDAO {

	void createBook(Book book);
	
	Collection<Book> readBook();
	Book readBookBybookId(String bookId);
	
}