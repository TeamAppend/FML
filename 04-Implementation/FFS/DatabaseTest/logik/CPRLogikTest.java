package logik;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import domain.CPRnummer;
import domain.CPRnummerImpl;

public class CPRLogikTest {


	@Test
	public void testListCPR() {
		CPRLogik cl = new CPRLogikImpl();
		try {
			java.util.List<CPRnummerImpl> list = cl.listCPR(0);
			CPRnummer cpr = list.get(0);
			assertEquals("0807911449", cpr.getCPRnummer());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void testListCPR2() {
		CPRLogik cl = new CPRLogikImpl();
		CPRnummer cp = new CPRnummerImpl();
		cp.setCPRnummer("0807911449");
		try {
			java.util.List<CPRnummerImpl> list = cl.listCPR(0);
			CPRnummer cpr = list.get(0);
			assertEquals(cp.getCPRnummer(), cpr.getCPRnummer());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
