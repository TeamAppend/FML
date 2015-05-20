package allTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import view.OpretKundeTest;
import logik.AllTestsLogik;
import domain.AllTestsDomain;

@RunWith(Suite.class)
@SuiteClasses({OpretKundeTest.class, AllTestsLogik.class, AllTestsDomain.class })
public class AllTestsTest {

}
