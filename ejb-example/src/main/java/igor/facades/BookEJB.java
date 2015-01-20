package igor.facades;

import igor.Book;


import java.util.List;

import javax.ejb.*;
import javax.inject.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import igor.Em;

@Stateless
@Remote(BookEJBRemote.class)
@LocalBean
public class BookEJB implements BookEJBRemote {
	@Inject @Em
	private EntityManager em;

	@Override
	public List<Book> findBooks() {
		TypedQuery<Book> query = em.createNamedQuery(Book.FIND_ALL, Book.class);
		return query.getResultList();
	}

	@Override
	public @NotNull Book createBook(@NotNull Book book) {
		em.persist(book);
		return book;
	}

	@Override
	public void deleteBook(@NotNull Book book) {
		em.remove(em.merge(book));
	}

	@Override
	public @NotNull Book updateBook(@NotNull Book book) {
		em.merge(book);
		return book;
	}
}
