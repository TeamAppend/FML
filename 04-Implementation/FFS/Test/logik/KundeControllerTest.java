package logik;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import exceptions.CPRAllreadyExists;
import exceptions.KundeAllreadyExists;
import exceptions.PostnummerDoesNotExist;

public class KundeControllerTest {

	public class KundeControllerStub extends KundeController {
	}
	
	private KundeController kcs;
	
	@Before
	public void setUp() throws Exception {
		kcs = new KundeControllerStub();
	}

	@Test
	public void testOpretKunde() throws SQLException, CPRAllreadyExists, KundeAllreadyExists, PostnummerDoesNotExist {
		kcs.opretKunde("55050601", "3006921811", "Michael bondom", "Idomvej 17", "7500");
		// Her finder vi kunden ved og søge på telefon nummeret.
		assertEquals("55050601", kcs.getKunde().getTelefon());
		//Her finder vi kunden ved og søge på kundenavn
		assertEquals("Michael bondom", kcs.getKunde().getKundenavn());
		//Her finder vi kunden ved og søge på adressen.
		assertEquals("Idomvej 17", kcs.getKunde().getAdresse());
		//Her finder vi kunden ved og søge på postnummer
		assertEquals("7500", kcs.getKunde().getPostnummer());
	}
	
	@Test
	public void testOpretKundeMedForkertTelefonInput() throws SQLException, CPRAllreadyExists, KundeAllreadyExists, PostnummerDoesNotExist {
		kcs.opretKunde("abcdaefs", "3006921811", "Thomas Borg Nielsen", "Holstebrovej 17", "7500");
		assertEquals("abcdaefs", kcs.getKunde().getTelefon());
	}
}
