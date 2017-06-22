package hu.uni.miskolc.javatechnologies.books.api.service;



import java.util.Collection;

import hu.uni.miskolc.javatechnologies.books.api.model.Book;


public interface BookManagementService {

	Collection<Book> listBook();
	Book getBookByBookId(String BookId);
	void acquireCar(Book book);
	
}