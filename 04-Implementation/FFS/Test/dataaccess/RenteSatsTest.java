package dataaccess;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ferrari.finances.dk.bank.InterestRate;
import com.ferrari.finances.dk.bank.developertools.InterestRateTestTool;

import dataaccess.RenteSats;
import dataaccess.RenteSatsImpl;


public class RenteSatsTest implements RenteSats {
	private RenteSats rs;
	private InterestRateTestTool testTool;
	private InterestRate IR;
	

	@Before
	public void setUp() throws Exception {
		rs = new RenteSatsImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetRenteSats() {
		
		//assertEquals(5, InterestRateTestTool.newInterestRateMock(5).todaysRate(), 0.00);
	}

	@Override
	public double getRenteSats() {
		return 0;
	}

}
