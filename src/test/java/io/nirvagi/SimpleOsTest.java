package io.nirvagi;

import org.testng.annotations.*;
import io.nirvagi.OS;


public class SimpleOsTest {

    OS os;


    @BeforeClass
    public void setUp() {
        // code that will be invoked when this test is instantiated

        this.os = new OS();
    }

    @Test
    public void osTypeTest () {
        assert os.isMac() == true : "Expected value is true for my Mac";
    }

    @Test
    public void osTypeTestNegative() {
        assert os.isWindows() == false : "Expected value is false for my Mac";
    }

    @Test
    public void osNameTest() {
        assert os.getOSName() == System.getProperty("os.name") : "Expected OS name is  " + System.getProperty("os.name");
    }

    @Test
    public void userHomeTest() {
        System.out.println(os.getUserHome());
    }

    @Test
    public void userNameTest() {
        System.out.println(os.getUserName());
    }
}
