package logik;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import domain.Postnummer;
import domain.PostnummerImpl;
import exceptions.PostnummerDoesNotExistException;

public class PostnummerLogikTest {
	
	private Postnummer pn;
	private PostnummerLogik pnl;

	@Before
	public void setUp() throws Exception {
		pn = new PostnummerImpl();
		pnl = new PostnummerLogikImpl();
	}

	@Test
	public void testListPostnummer() throws SQLException, PostnummerDoesNotExistException {
		pn = pnl.listPostnummer("7400");
		assertEquals("7400", pn.getPostnummer());
	}

}
