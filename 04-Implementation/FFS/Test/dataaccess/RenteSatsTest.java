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
	private CallBack callBack;
	

	@Before
	public void setUp() throws Exception {
		rs = new RenteSatsImpl();
		callBack = new CallBack(){
			@Override
			public void onRequestComplete() {
				System.out.println("test");
			}};
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetRenteSats() {
		rs.setRenteSats(callBack);
		//double d = rs.getRenteSats();
		assertEquals(5, InterestRateTestTool.newInterestRateMock(5).todaysRate(), 0.00);
	}


}
