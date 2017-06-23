package hu.uni.miskolc.javatechnologies.books.service.impl;

import java.util.Collection;

import hu.uni.miskolc.javatechnologies.books.api.model.Book;
import hu.uni.miskolc.javatechnologies.books.api.service.BookManagementService;
import hu.uni.miskolc.javatechnologies.book.service.dao.BookDAO;


public class BookManagementServiceImpl implements BookManagementService {

	private BookDAO BookDAO;

	public BookManagementServiceImpl(BookDAO BookDAO) {
		super();
		this.BookDAO = BookDAO;
	}

	public Collection<Book> listBook() {
		return BookDAO.readBook();
	}

	public void acquireBook(Book book) {
		BookDAO.createBook(book);
	}
}

