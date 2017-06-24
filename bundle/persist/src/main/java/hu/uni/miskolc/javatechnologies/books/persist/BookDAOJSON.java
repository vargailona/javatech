package hu.uni.miskolc.javatechnologies.books.persist;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import hu.uni.miskolc.javatechnologies.books.api.model.Book;
import hu.uni.miskolc.javatechnologies.book.service.dao.BookDAO;


public class BookDAOJSON implements BookDAO {

	private Logger LOGGER = LogManager.getLogger(BookDAOJSON.class);
	
	private File database;
	
	public BookDAOJSON(String databasePath) {
		this.database = new File(databasePath);
		LOGGER.debug(String.format("Book Database : %s", database.getAbsolutePath()));
	}

	public void createBook(Book book) {
		Collection<Book> allBooks = readBooks();
		allBooks.add(book);
		Book[] extendedDatabase = allBooks.toArray(new Book[allBooks.size()]);
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(database, extendedDatabase);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			LOGGER.fatal(String.format("IOException occured due to %s", e.getMessage()));
		}
		LOGGER.info(String.format("Book (%s) has been added!", book.getBookId()));
		

	}

	public Collection<Book> readBooks() {
		ObjectMapper mapper = new ObjectMapper();
		Book[] books = new Book[] {};
		try {
			books = mapper.readValue(database, Book[].class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			LOGGER.fatal(String.format("IOException occured due to %s", e.getMessage()));
		}
		Collection<Book> result = new ArrayList<Book>(Arrays.asList(books));
		return result;
	}

	public Book readBookByBookId(String bookId)  {
		for(Book book : readBooks()){
			if(bookId.equals(book.getBookId())){
				return book;
			}
		}

	}



	public Collection<Book> readBook() {
		// TODO Auto-generated method stub
		return null;
	}

	public Book readBookBybookId(String bookId) {
		// TODO Auto-generated method stub
		return null;
	}

}