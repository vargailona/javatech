package hu.uni.miskolc.javatechnologies.book.service.dao;

import java.util.Collection;

import hu.uni.miskolc.javatechnologies.books.api.model.announcement;

public interface AnnouncementDAO {

	void createAnnouncement(announcement announcement);

	Collection<announcement> readannouncements();

}