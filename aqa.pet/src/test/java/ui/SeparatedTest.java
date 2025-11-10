package ui;

import core.BaseTest;
import core.FindOwnersPage;
import core.MainPage;
import core.OwnerProfilePage;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class SeparatedTest extends BaseTest {
    private static String url;
    static {
        try {
            System.getProperties().load(ClassLoader.getSystemResourceAsStream("application.properties"));
            url = System.getProperty("url");
        } catch (IOException ex) {
            ex.printStackTrace();
     }
      }
    private MainPage mainPage;
    private FindOwnersPage findOwnersPage;
    private OwnerProfilePage ownerProfilePage;

    // TODO Split the SmokeTest class mathods to 8 test (stateful)

}
