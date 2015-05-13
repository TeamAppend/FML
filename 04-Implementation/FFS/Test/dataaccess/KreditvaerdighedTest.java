package dataaccess;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.CallBack;
import domain.Kreditværdighed;
import domain.KreditværdighedImpl;

public class KreditvaerdighedTest {

	private Kreditværdighed kv;

	@Before
	public void setUp() throws Exception {
		kv = new KreditværdighedImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetKreditvaerdighed() {
		CallBack callBack = null;
		kv.setKreditvaerdighed("3006921611", callBack);
		assertEquals("B", kv.getKredigvaerdighed());
	}
	
//	@Test
//	public void testGetKredigvaerdighed() {
//		kv.setKreditvaerdighed("3006921611");
//		assertEquals("B", kv.getKredigvaerdighed());
//	}

}
