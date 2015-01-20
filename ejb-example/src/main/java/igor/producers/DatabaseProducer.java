package igor.producers;
import javax.enterprise.inject.*;
import igor.Em;
//import javax.inject.*;
import javax.persistence.*;

public class DatabaseProducer {
	@Produces @Em
	@PersistenceContext(unitName="chapter08PU")
	private EntityManager em;
}
