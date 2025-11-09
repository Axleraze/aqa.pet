package core;

import net.bytebuddy.asm.Advice;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Here we're describing the FIND OWNERS page http://<yourAddress:port>/owners/find
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

    // Method to SEARCH an existing pet owner
   public void searchOwner(String lastName){
       searchInputField.sendKeys(lastName);
       findOwnerButton.click();
   }

   // Method to NAVIGATE to other page - AddNewOwner (to add new owner).
   public AddNewOwnerPage goToAddOwnerPage(){
        addOwnerButton.click();
        return new AddNewOwnerPage(driver);
   }

}
