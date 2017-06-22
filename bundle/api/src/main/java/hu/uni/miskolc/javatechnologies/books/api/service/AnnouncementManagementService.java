package hu.uni.miskolc.javatechnologies.books.api.service;


import java.util.Date;
import java.util.Collection;

import hu.uni.miskolc.javatechnologies.books.api.model.announcement;
import hu.uni.miskolc.javatechnologies.books.api.model.Book;
import hu.uni.miskolc.javatechnologies.books.api.model.Price;

public interface AnnouncementManagementService {

	Collection<announcement> listAnnouncements();
	Collection<announcement> listOpenAnnouncements();
	
	void announce(String bookId, Date expireDate, Price price);
}