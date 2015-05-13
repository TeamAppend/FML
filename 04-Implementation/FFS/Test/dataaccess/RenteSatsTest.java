package dataaccess;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;





import com.ferrari.finances.dk.bank.InterestRate;
//import com.ferrari.finances.dk.bank.InterestRate;
import com.ferrari.finances.dk.bank.developertools.InterestRateTestTool;

import domain.CallBack;
import domain.RenteSats;
import domain.RenteSatsImpl;


public class RenteSatsTest {
	public class RenteSatsImplStub extends RenteSatsImpl {
		@Override
		protected InterestRate getInterestRateRef(){
			return InterestRateTestTool.newInterestRateMock(7.49);
		}
	}

	private RenteSats rs;
	boolean requestCompleted = false;
	

	@Before
	public void setUp() throws Exception {
		rs = new RenteSatsImplStub();
	
	}

	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testGetRenteSats() throws Exception{
		int timeOut = 4000;
		rs.setRenteSats(new CallBack(){
			@Override
			public void onRequestComplete() {
				requestCompleted = true;
			};
		});
		while(!requestCompleted && timeOut > 0){
			Thread.sleep(500);
			timeOut -= 500;
		}
		if(requestCompleted)
			assertEquals(7.49,rs.getRenteSats(), 0.00);
		else
			fail("Call to bank exceeded timeout");
	}
}
