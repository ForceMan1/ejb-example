package igor;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import igor.facades.*;

public class Main {
	public static void main(String[] args) throws NamingException {
		//Lookup the EJB
		Context ctx = new InitialContext();
		BookEJBRemote bookEJB = (BookEJBRemote) ctx.lookup(
				"java:global/ejb-example-1.0/BookEJB!igor.facades.BookEJBRemote");
		
		// Gets and display all the books from the database
		List<Book> books = bookEJB.findBooks();
		for(Book book : books){
			System.out.println(book);
		}
		
		// Creates an instance of book
		Book book = new Book("H2G2", 12.5F, "Scifi book", "1-24561-799-0", 354, false);
		
		book = bookEJB.createBook(book);
		System.out.println("Book created : " + book);
		
		book.setTitle("H2G2");
		book = bookEJB.updateBook(book);
		System.out.println("Book updated : " + book);
		
		bookEJB.deleteBook(book);
		System.out.println("Book deleted");
	}
	
}
