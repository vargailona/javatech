package hu.uni.miskolc.javatechnologies.books.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Currency;
import java.util.Date;

import hu.uni.miskolc.javatechnologies.books.api.model.announcement;
import hu.uni.miskolc.javatechnologies.books.api.model.Book;
import hu.uni.miskolc.javatechnologies.books.api.model.Price;
import hu.uni.miskolc.javatechnologies.books.api.service.AnnouncementManagementService;
import hu.uni.miskolc.javatechnologies.books.api.service.BookManagementService;
import hu.uni.miskolc.javatechnologies.books.persist.AnnouncementDAOJSON;
import hu.uni.miskolc.javatechnologies.books.persist.BookDAOJSON;
import hu.uni.miskolc.javatechnologies.book.service.dao.AnnouncementDAO;
import hu.uni.miskolc.javatechnologies.book.service.dao.BookDAO;
import hu.uni.miskolc.javatechnologies.books.service.impl.AnnouncementManagementServiceImpl;
import hu.uni.miskolc.javatechnologies.books.service.impl.BookManagementServiceImpl;


public class App {
	private static BookManagementService bookManager;
	private static AnnouncementManagementService announcementManager;

	static {
		BookDAO BookDAO = new BookDAOJSON("store/book.json");
		AnnouncementDAO announcementDAO = new AnnouncementDAOJSON("store/announcement.json");
		BookManager = new BookManagementServiceImpl(bookDAO);
		announcementManager = new AnnouncementManagementServiceImpl(announcementDAO, bookDAO);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean run = true;
		while (run) {

			String line = br.readLine();
			if ("exit".equals(line)) {
				break;
			}
			if ("list books".equals(line)) {
				listBooks();
			}
			if ("insert book".equals(line)) {
				addBook();
			}
			if ("list announcements".equals(line)) {
				printannouncements(announcementManager.listannouncements());
			}
			if ("list open announcements".equals(line)) {
				printannouncements(announcementManager.listOpenannouncements());
			}
			if ("add announcement".equals(line)) {
				addAnnouncement();
			}
		}
	}
	private static void listBooks() {
		final int tableWidth = 30;
		printHorisontalLine(tableWidth);
		System.out.println("| PlateNo | Producer | Color | # Doors | Horse Power |");
		printHorisontalLine(tableWidth);
		for (Book book : bookManager.listBooks()) {
			System.out.println(String.format("| %1$7s | %2$8s | %3$5s | %4$7d |", book.getWriter(),
					book.getTitle(), book.getBookId(), book.getPages()));
			printHorisontalLine(tableWidth);
		}
	}

	private static void printHorisontalLine(int length) {
		for (int i = 0; i < length; i++) {
			System.out.print("-");
		}
		System.out.println();
	}

	private static void addCar() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Writer: ");
		String writer = br.readLine();
		System.out.println("Title: ");
		String title = br.readLine();
		System.out.println("Book ID: ");
		int bookId = Integer.parseInt(br.readLine());
		System.out.println("Pages: ");
		int pages = Integer.parseInt(br.readLine());
		Book book = new Book(writer, title, bookId, pages);
		bookManager.acquireBook(book);

	}

	private static void printannouncements(Collection<announcement> announcements) {
		final int tableWidth = 80;
		printHorisontalLine(tableWidth);
		System.out.println(
				"| 						Book							 | 	Price			 | Start Date | Expire Date | open  |");
		System.out.println(
				"| Writer | Title | Book ID | Pages |  Amount | Currency |			  |		        |       |");
		printHorisontalLine(tableWidth);
		for (announcement announcement : announcements) {
			Book book = announcement.getBook();
			Price price = announcement.getPrice();
			System.out.println(String.format(
					"| %1$7s | %2$8s | %3$5s | %4$7d | %5$11d | %6$5.2f | %7$8s | %8$10s | %9$5s |",
					book.getWriter(), book.getTitle(), book.getBookId(), book.getPages(),
					price.getAmount(), price.getCurrency().toString(), announcement.getStartDate().toString(),
					announcement.getExpirationDate().toString(), announcement.openannouncement()));
		}
	}

	private static void addannouncement() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Book ID: ");
		String bookId = br.readLine();
		System.out.println("Expiration Date (YYYY-MM-dd): ");
		String expireDateStr = br.readLine();
		System.out.println("Price (amount currency e.g. 1000 HUF): ");
		String priceStr = br.readLine();

		try {
			Book book = bookManager.getBookByBookId(bookId);
			DateFormat df = new SimpleDateFormat("YYYY-MM-dd");
			Date expireDate = df.parse(expireDateStr);
			Price price = new Price(Double.parseDouble(priceStr.split(" ")[0]),
					Currency.getInstance(priceStr.split(" ")[1]));

			announcementManager.announce(bookId, expireDate, price);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


