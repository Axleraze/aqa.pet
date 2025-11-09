package core;
/**
 * 'Utilit' class for binding and initialising WebDriver.
 * From this  BasePage will  be inherited  other 'real' Page Object classes
 */

import org.openqa.selenium.WebDriver;

abstract public class BasePage {
    //  6. STATIC modifier is necessary for init this driver in BaseTest for BINDING this two class BaseTest and BasePage
    protected final WebDriver driver; // static тут убрала
    protected final HeaderComponent header;


//    // 7. In order to have all WebDriver Settings from  BaseTest class, create method to initialisate driver.
//    public static void setDriver(WebDriver webdriver){
//        driver = webdriver;
//    }

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.header = new HeaderComponent(driver);  // make header vissible in all POM Pages
    }

    // Header is the same for all pages. THats why we put this method here
    public HeaderComponent getHeader() {
        return header;
    }


}
