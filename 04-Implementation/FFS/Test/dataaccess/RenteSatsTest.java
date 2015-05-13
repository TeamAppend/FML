package dataaccess;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//import com.ferrari.finances.dk.bank.InterestRate;
import com.ferrari.finances.dk.bank.developertools.InterestRateTestTool;

import domain.CallBack;
import domain.RenteSats;
import domain.RenteSatsImpl;


public class RenteSatsTest {
	private RenteSats rs;
	//private InterestRateTestTool testTool;
	//private InterestRate IR;
	

	@Before
	public void setUp() throws Exception {
		rs = new RenteSatsImpl();
	
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testGetRenteSats() {
		rs.setRenteSats(new CallBack(){
			@Override
			public void onRequestComplete() {
				assertEquals(5,rs.getRenteSats(), 0.00);
			};
		});
		assertEquals(5,rs.getRenteSats(), 0.00);
		//assertEquals(5, InterestRateTestTool.newInterestRateMock(5).todaysRate(), 0.00);
	}


}
