package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This PO Class is responsible for adding new pet OWNER
 * and describes ADD OWNER Page
 */
public class AddNewOwnerPage extends BasePage {
    @FindBy(xpath = "//input[@id='firstName']")
    private WebElement firstNameInputField;

    @FindBy(xpath = "//input[@id='lastName']")
    private WebElement lastNameInputField;

    @FindBy(xpath = "//input[@id='address']")
    private WebElement addressInputField;

    @FindBy(xpath = "//input[@id='city']")
    private WebElement cityInputField;

    @FindBy(xpath = "//input[@id='telephone']")
    private WebElement telephoneInputField;

    @FindBy(xpath = "//button[@type='submit'][contains(text(), 'Add Owner')]")
    private WebElement addOwnerButton;


    public AddNewOwnerPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    // Check that Page and header loaded
    @Override
    public boolean isPageLoaded() {
        return getHeader().isDisplayed() && firstNameInputField.isDisplayed() ;
    }

    /**
     *   Method combines all navigation actions to fill in required input fields to add new OWNER.
     * @return OwnerProfilePage
     */
    public OwnerProfilePage createNewOwner(String firstName, String lastName, String address, String city, String phone){
        firstNameInputField.sendKeys(firstName);
        lastNameInputField.sendKeys(lastName);
        addressInputField.sendKeys(address);
        cityInputField.sendKeys(city);
        telephoneInputField.sendKeys(phone);
        addOwnerButton.click();
        return new OwnerProfilePage(driver).waitUntilLoaded();
    }

}
