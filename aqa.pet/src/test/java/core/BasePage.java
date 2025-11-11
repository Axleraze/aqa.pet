package core;

import org.openqa.selenium.WebDriver;
/**
 * 'Utilit' class for binding and initialising WebDriver.
 * From this  BasePage will  be inherited  other 'real' Page Object classes
 */

abstract public class BasePage {
    //  in BaseTest for BINDING this two class BaseTest and BasePage
    protected final WebDriver driver; // static  убрала
    protected final HeaderComponent header;   // this is our header


    /**
     * In order to have all WebDriver Settings from  BaseTest class, create method to initialisate driver in Constructor.
     *
     * @param driver
     */

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.header = new HeaderComponent(driver);  // make header vissible in all POM Pages
    }

    // Header is the same for all pages. THats why we put this method here
    public HeaderComponent getHeader() {
        return header;
    }

    // Checks that Heeader / Navigation bar is loaded and displayed
    public boolean isPageLoaded(){
        return getHeader().isDisplayed();
    }

}
