package domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ferrari.finances.dk.rki.Rating;

import domain.CallBack;
import domain.Kreditværdighed;
import domain.KreditværdighedImpl;

public class KreditvaerdighedTest {

	private Kreditværdighed kv;
	private boolean requestCompleted = false;
	
	@Before
	public void setUp() throws Exception {
		kv = new KreditværdighedImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void testSetKreditvaerdighed() throws Exception {
		int timeOut = 5000;
		kv.setKreditværdighed("3006921611", new CallBack(){
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
			assertEquals( Rating.valueOf("B"), kv.getKredigværdighed());
			assertEquals(true, kv.getkvAcceptabel());
			assertEquals(2, kv.getTillægspoint());
		if(requestCompleted == false)
			fail("Call to RKI exceeded timeout");
	}
}
