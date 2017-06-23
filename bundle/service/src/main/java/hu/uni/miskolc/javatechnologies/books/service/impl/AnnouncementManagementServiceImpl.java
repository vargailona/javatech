package hu.uni.miskolc.javatechnologies.books.service.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import hu.uni.miskolc.javatechnologies.books.api.model.announcement;
import hu.uni.miskolc.javatechnologies.books.api.model.Book;
import hu.uni.miskolc.javatechnologies.books.api.model.Price;
import hu.uni.miskolc.javatechnologies.books.api.service.AnnouncementManagementService;
import hu.uni.miskolc.javatechnologies.book.service.dao.AnnouncementDAO;
import hu.uni.miskolc.javatechnologies.book.service.dao.BookDAO;


public class AnnouncementManagementServiceImpl implements AnnouncementManagementService {
	
	private static Logger LOG = LogManager.getLogger(AnnouncementManagementServiceImpl.class);
	
	
	private AnnouncementDAO announcementDAO;
	private BookDAO BookDAO;
	
	

	public AnnouncementManagementServiceImpl(AnnouncementDAO announcementDAO, BookDAO BookDAO) {
		super();
		this.announcementDAO = announcementDAO;
		this.BookDAO = BookDAO;
	}

	public Collection<announcement> listannouncements() {
		return announcementDAO.readannouncements();
	}

	public Collection<announcement> listOpenannouncements() {
		Collection<announcement> result = new ArrayList<announcement>();
		for(announcement announcement : announcementDAO.readannouncements()){
			if(announcement.openannouncement()){
				result.add(announcement);
			}
		}
		return result;}
}
	