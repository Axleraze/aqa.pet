package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderComponent {
    protected  final WebDriver driver;

    @FindBy(xpath = "//a[@title=\"find owners\"]" )  // analog - 'deprecated' By.xpath("//a[@title=\"find owners\"]")
    private WebElement linkToFindOwners;

    @FindBy(xpath = "//a[@title=\"veterinarians\"]")
    private WebElement linkToVeterenars;

    @FindBy(xpath = "//a[@title=\"home page\"]")
    private WebElement linkToHomePage;


    public HeaderComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
     }


// Method for navigation to seach Page.
    public FindOwnersPage goToSearchPage(){
        linkToFindOwners.click();
        FindOwnersPage findOwnersPage = new FindOwnersPage(driver);
        return  findOwnersPage;
    }

    // Method for navigation to veterinars page.
    public VeterinarsPage navigateToVeterinarsPage(){
        linkToVeterenars.click();
        VeterinarsPage veterinarsPage = new VeterinarsPage(driver);
        return  veterinarsPage;
    }

}
