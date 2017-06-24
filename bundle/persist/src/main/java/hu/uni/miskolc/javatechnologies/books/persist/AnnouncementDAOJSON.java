package hu.uni.miskolc.javatechnologies.books.persist;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import hu.uni.miskolc.javatechnologies.books.api.model.announcement;
import hu.uni.miskolc.javatechnologies.books.api.model.Book;
import hu.uni.miskolc.javatechnologies.book.service.dao.AnnouncementDAO;
import hu.uni.miskolc.javatechnologies.book.service.dao.BookDAO;

public class AnnouncementDAOJSON implements AnnouncementDAO {

	private static Logger LOG = LogManager.getLogger(AnnouncementDAOJSON.class);

	private final File database;

	public AnnouncementDAOJSON(String database) {
		super();
		this.database = new File(database);
	}

	public void createannouncement(announcement announcement) {
		Collection<announcement> announcements = new ArrayList<announcement>(readannouncements());
		announcements.add(announcement);
//		System.out.println(announcements);
		announcement[] announcementsArray = announcements.toArray(new announcement[announcements.size()]);
		ObjectMapper mapper = new ObjectMapper();
		mapper.addMixIn(announcement.class, AnnouncementMixIn.class);
		try {
			mapper.writeValue(database, announcementsArray);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Collection<announcement> readannouncements() {
		Collection<announcement> result;
		announcement[] announcements = new announcement[] {};
		try {
			ObjectMapper mapper = new ObjectMapper();
			announcements = mapper.readValue(database, announcement[].class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IOException e) {
			LOG.fatal(String.format("IOException occured due to %s", e.getMessage()));
		}
		result = new ArrayList<announcement>(Arrays.asList(announcements));
		return result;
	}

	private abstract class AnnouncementMixIn{
		@JsonIgnore abstract boolean openAnnouncement(); 
	}

	public void createAnnouncement(announcement announcement) {
		// TODO Auto-generated method stub
		
	}
}

