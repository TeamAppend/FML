package logik;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.Kunde;
import domain.KundeImpl;
import exceptions.CPRAllreadyExistsException;
import exceptions.CPRDoesNotExistsException;
import exceptions.KundeAllreadyExistsException;
import exceptions.KundeDoesNotExistsException;
import exceptions.PostnummerDoesNotExistException;

public class KundeControllerTest {
	
	private KundeController kcs;
	private KundeLogik kl;
	private CPRLogik cl;
	
	@After
	public void after() throws Exception {
		
	}
	
	@Before
	public void setUp() throws Exception {
		kcs = KundeController.instance();
		kl = new KundeLogikImpl();
		cl = new CPRLogikImpl();
	}

	@Test
	public void testOpretKunde() throws SQLException, CPRAllreadyExistsException, KundeAllreadyExistsException, PostnummerDoesNotExistException, KundeDoesNotExistsException {
		kcs.opretKunde("55050601", "3006921811", "Michael bondom", "Idomvej 17", "7500");
		// Her finder vi kunden ved og søge på telefon nummeret.
		assertEquals("55050601", kcs.getKunde().getTelefon());
		//Her finder vi kunden ved og søge på kundenavn
		assertEquals("Michael bondom", kcs.getKunde().getKundenavn());
		//Her finder vi kunden ved og søge på adressen.
		assertEquals("Idomvej 17", kcs.getKunde().getAdresse());
		//Her finder vi kunden ved og søge på postnummer
		assertEquals("7500", kcs.getKunde().getPostnummer());
		
		Kunde kunde = new KundeImpl();
		kunde = kl.listKunde("55050601");
		try {
			cl.deleteCPR(kunde.getCPR_id());
		} catch (CPRDoesNotExistsException e) {
			e.printStackTrace();
		}
	}
}
