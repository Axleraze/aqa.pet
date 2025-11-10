package core;

import net.bytebuddy.asm.Advice;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Here we're describing the FIND OWNERS page http://yourAddress:port/owners/find
 * due to POM concept: elements and methods
 */
public class FindOwnersPage extends BasePage {
    @FindBy(xpath = "//input[@class=\"form-control\"]")
    WebElement searchInputField;

    @FindBy(xpath = "//button[@type=\"submit\"][contains(text(), \"Find Owner\")]")
    WebElement findOwnerButton;

    @FindBy(xpath = "//a[@class=\"btn btn-primary\"][contains(text(), \"Add Owner\")]")
    private WebElement addOwnerButton;


    public FindOwnersPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


// Check is Page loaded. Nested from Base Page PO class and added searchInputField appearance
    @Override
    public boolean isPageLoaded() {
        return getHeader().isDisplayed() && searchInputField.isDisplayed();
    }


    // Method to SEARCH an existing pet owner
   public SearchResultsPage searchOwner(String lastName){
       searchInputField.sendKeys(lastName);
       findOwnerButton.click();
       return new SearchResultsPage(driver);
   }

   // Redirect to ADD OWNER Page
   public AddNewOwnerPage goToAddOwnerPage(){
        addOwnerButton.click();
        return new AddNewOwnerPage(driver);
   }

}
