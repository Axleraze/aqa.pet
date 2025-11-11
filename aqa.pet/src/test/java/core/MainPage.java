package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

/**
 * Start Page our PetClinic App (and int this case header can be used for other pages).
 * Webdriver is already initialised (so Chrome browser starts without additional setups)
 * Due to Page Objec Model concept here we describe needed elements and locators (logic).
 */
public class MainPage extends BasePage {
    // 9.  Define ELEMENTS as class fields to navigate page in tests later with annotation and locators
  // перенесла три ссылочных элемента в кновый класс HeaderComponent
    @FindBy(xpath = "//div[@id='main-navbar']")
            WebElement navbar;

    @FindBy(xpath = "//a[@title='find owners']" )  // analog - 'deprecated' By.xpath("//a[@title=\"find owners\"]")
    private WebElement linkToFindOwners;

    // 10 Create url variable and read in static block config file. This will be used in class CONSTRUCTOR with Factory
    String url;
    {
        try {
            System.getProperties().load(ClassLoader.getSystemResourceAsStream("application.properties"));
            url = System.getProperty("url");
        } catch (IOException ex){
            System.out.println("Property files not found");
        }
    }

    // 11. Then INITIALIZE all founded elements via Page FACTORY Pattern. Only after then we can interact with elements
    public MainPage(WebDriver driver) {    //  TODO проверить надо ли добавлять в агрументы WebDriver drver
        super(driver);
        driver.get(url);
        PageFactory.initElements(driver, this);
    }

    // Method for waiting after add new owner and redirekt untill load Profile Page
    public MainPage waitUntilLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(navbar));
        return this;
    }

    public FindOwnersPage goToSearchPage(){
        linkToFindOwners.click();
        FindOwnersPage findOwnersPage = new FindOwnersPage(driver);
        return  findOwnersPage;

   }
}


