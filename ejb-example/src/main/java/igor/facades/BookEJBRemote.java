package igor.facades;
import javax.ejb.*;
import javax.validation.constraints.*;
import igor.Book;
import java.util.List;

@Remote
public interface BookEJBRemote {
	List<Book> findBooks();
	@NotNull Book createBook(@NotNull Book book);
	void deleteBook(@NotNull Book book);
	@NotNull Book updateBook(@NotNull Book book);
}
