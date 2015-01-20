package igor.test;
import static org.junit.Assert.*;

import org.junit.*;

import java.util.HashMap;
import java.util.Map;
import java.io.File;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;

import igor.facades.*;
import igor.Book;


public class BookEJBIT {
	@Test
	public void shouldCreateABook() throws Exception {
		Map<String, Object> properties = new HashMap<>();
		properties.put(EJBContainer.MODULES, new File[]{new File("target/classes"),
						new File("target/test-classes") 
		});
		try(EJBContainer ec = EJBContainer.createEJBContainer(properties)){
			Context ctx = ec.getContext();
			
			// Check JNDI dependencies (Datasource and EJBs)
			assertNotNull(ctx.lookup("java:global/jdbc/chapter08DS"));
			assertNotNull(ctx.lookup(
					"java:global/classes/BookEJB!igor.facades.BookEJBRemote"));
			assertNotNull(ctx.lookup(
					"java:global/classes/BookEJB!igor.facades.BookEJB"));
			
			// Looks up th EJB
			BookEJB bookEJB = (BookEJB)ctx.lookup(
					"java:global/classes/BookEJB!igor.facades.BookEJB");
			
			// Find All the books and make sure there two (inserted by DBPopulator)
			assertEquals(2, bookEJB.findBooks().size());
			
			// Creates an instance of book
			Book book = new Book("H2G2", 12.5F, "Scifi book", "1-24561-799-0", 354, false);
			
			// Persists the book to the database 
			book  = bookEJB.createBook(book);
			assertNotNull(book.getId());
			
			// Find all the books and sure there is extra one
			assertEquals(3, bookEJB.findBooks().size());
			
			// Removes the created book
			bookEJB.deleteBook(book);
			
			// Find all the books and make sure there is one less
			assertEquals(2, bookEJB.findBooks().size());
			
		}
	}
}
