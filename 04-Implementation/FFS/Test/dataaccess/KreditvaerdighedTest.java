package dataaccess;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class KreditvaerdighedTest {

	private Kreditvaerdighed kv;

	@Before
	public void setUp() throws Exception {
		kv = new KreditvaerdighedImpl();
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
