package view;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class OpretKundeTest {

	public class OpretKundeStub extends KundePanel {
	}
	
	private KundePanel oks;
	
	@Before
	public void setUp() throws Exception {
		oks = new OpretKundeStub();
	}

	@Test
	public void testValiderCPR() {
		assertEquals(false, oks.validerCPR("asdsbdfsbas"));
		assertEquals(true, oks.validerCPR("3006921811"));
	}

	@Test
	public void testValiderNavn() {
		assertEquals(false, oks.validerNavn("25326"));
		assertEquals(true, oks.validerNavn("Thomas Borg Nielsen"));
	}

	@Test
	public void testValiderAdresse() {
		assertEquals(false, oks.validerAdresse("+-Holstebrovej 17"));
		assertEquals(true, oks.validerAdresse("Holstebrovej 17"));
	}

	@Test
	public void testValiderPostnummer() {
		assertEquals(false, oks.validerPostnummer("Hej"));
		assertEquals(true, oks.validerPostnummer("7400"));
	}

	@Test
	public void testValiderTelefon() {
		assertEquals(false, oks.validerTelefon("abcdefghi"));
		assertEquals(true, oks.validerTelefon("24815501"));
	}

	@Test
	public void testValiderBy() {
		assertEquals(true, oks.validerBy("Holstebro"));
	}

	@Test
	public void testValiderTekstfelter() {
		assertEquals(true, oks.validerTekstfelter("24815501", "3006921811", "Thomas Borg Nielsen", "Birk Centerpark 107D", "7400", "Herning"));
	}
}
