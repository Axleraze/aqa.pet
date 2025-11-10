package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Since all web-pages have the same HEADER, it was moved as separate PO class
 * Here is just navigation to HOME, FIND OWNER, VETERINARIANS pages
 */
public class HeaderComponent {
    protected  final WebDriver driver;
    @FindBy(xpath = "//nav[@role='navigation']")
    private WebElement navigationBar;

    // All 'deprecated' z.B. By.xpath("<my_xPath>") was replaced with @FindBy annotation
    @FindBy(xpath = "//a[@title=\"find owners\"]" )
    private WebElement linkToFindOwners;

    @FindBy(xpath = "//a[@title=\"veterinarians\"]")
    private WebElement linkToVeterenars;

    @FindBy(xpath = "//a[@title=\"home page\"]")
    private WebElement linkToHomePage;


    public HeaderComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
     }

    // Checks is navigation bar / header rendered and displayed
    public boolean isDisplayed() {
        return  navigationBar.isDisplayed();
    }


    // Redirect to HOME Page
     public MainPage goToMainPage(){
        linkToHomePage.click();
        MainPage mainPage = new MainPage(driver);
        return mainPage;
     }

    // Redirect to search FIND OWNER Page
    public FindOwnersPage goToSearchPage(){
        linkToFindOwners.click();
        FindOwnersPage findOwnersPage = new FindOwnersPage(driver);
        return  findOwnersPage;
    }

    // Redirect to VETERINARS page.
    public VeterinarsPage navigateToVeterinarsPage(){
        linkToVeterenars.click();
        VeterinarsPage veterinarsPage = new VeterinarsPage(driver);
        return  veterinarsPage;
    }

}
