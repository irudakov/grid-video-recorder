package io.nirvagi;

import org.testng.annotations.*;
import org.testng.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class SimpleServletTest {

    @Test
    public void whenSendingGet_thenMessageIsReturned() throws IOException {
        String url = "http://localhost:8080/ScreenRecorderServlet?action=SHOWFILES";
        URLConnection connection = new URL(url).openConnection();
        try (InputStream response = connection.getInputStream();
             Scanner scanner = new Scanner(response)) {
            String responseBody = scanner.nextLine();
            System.out.println(responseBody);
            Assert.assertEquals("Welcome to Baeldung!", responseBody);
        }
    }
}
