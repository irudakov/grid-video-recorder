package io.nirvagi;

import org.testng.annotations.*;
import io.nirvagi.ExecuteCommand;

public class RunOSCommandTest {


    @BeforeClass
    public void setUp() {
        // code that will be invoked when this test is instantiated

    }

    @Test
    public void executeSimpleCommand () {

        String result = ExecuteCommand.execRuntime("pwd").toString();
        System.out.println(result);
    }

    @Test
    public void executeWrongCommand () {

        String result = ExecuteCommand.execRuntime("pwdsss").toString();
        System.out.println(result);
    }
}
