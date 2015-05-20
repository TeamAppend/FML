package logik;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.Kunde;
import domain.KundeImpl;
import exceptions.CPRAllreadyExists;
import exceptions.CPRDoesNotExists;
import exceptions.KundeAllreadyExists;
import exceptions.KundeDoesNotExists;
import exceptions.PostnummerDoesNotExist;

public class KundeControllerTest {

	public class KundeControllerStub extends KundeController {
	}
	
	private KundeController kcs;
	private KundeLogik kl;
	private CPRLogik cl;
	
	@After
	public void after() throws Exception {
		
	}
	
	@Before
	public void setUp() throws Exception {
		kcs = new KundeControllerStub();
		kl = new KundeLogikImpl();
		cl = new CPRLogikImpl();
	}

	@Test
	public void testOpretKunde() throws SQLException, CPRAllreadyExists, KundeAllreadyExists, PostnummerDoesNotExist, KundeDoesNotExists {
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
		} catch (CPRDoesNotExists e) {
			e.printStackTrace();
		}
	}
}
