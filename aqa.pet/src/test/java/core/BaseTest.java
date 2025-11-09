package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Here we're making initialisation our WebDriver and BINDING  BaseTest class and BasePage class
 * Also we put it in Before-Condition (and also we need After-)for each future test that will be accordingly applied.
 */
abstract public class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    public void setUp(){
        // 1. This library von B.Garcia that automatically download necessary chrome /ff webdriver and set it up
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // 2. Add wait setting to all elements can be loaded, 10 sec
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        // 3. Add one more wait to elements become 'visible'
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


// 4 Close WebDriver and Chrome Tab
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
