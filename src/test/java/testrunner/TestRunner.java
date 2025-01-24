package testrunner;
import java.util.ArrayList;
import java.util.List;
import org.testng.TestNG;

public class TestRunner {
    public static void main(String[] args) {
        TestNG testng = new TestNG();
      //  testng.setTestClasses(new Class[] { tests.AuthTest.class }); // Directly set test classes
        List<String> suites = new ArrayList<>();
        //suites.add("src/test/resources/suites/testng.xml");
        String suitePath = "src/test/resources/suites/testng.xml";
        System.out.println("Using test suite from: " + new java.io.File(suitePath).getAbsolutePath());
        suites.add(suitePath);
        testng.setTestSuites(suites);
        testng.run();    
    }
}
//<suite name="Parallel Test Suite" parallel="methods" thread-count="4">
