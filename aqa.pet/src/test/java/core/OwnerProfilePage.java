package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OwnerProfilePage extends BasePage{
    @FindBy(xpath = "//a[@title=\"find owners\"]" )
    private WebElement linkToFindOwners;

  @FindBy(xpath="//table[@class='table table-striped']")
  WebElement ownerInfoTable;

  @FindBy(xpath = "//a[contains(text(), 'Edit Owner')]")
  WebElement editOwnerButton;

  @FindBy(xpath = "//a[contains(text(), 'Add New Pet')]")
  WebElement addNewPetButton;

  @FindBy(xpath = "//a[contains(text(), 'Edit Pet')]")
  WebElement editPetLink;

  @FindBy(xpath = "//a[contains(text(), 'Add Visit')]")
  WebElement addVisitLink;


    @FindBy(xpath = "//table[@class='table-condensed']//tbody/tr/td[contains(text(), '20')]")
  WebElement hiddinVisitInfo;



    public OwnerProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

// Check then page is loaded
    @Override
    public boolean isPageLoaded() {
        return getHeader().isDisplayed() && addNewPetButton.isDisplayed();
    }

    // Method for waiting after add new owner and redirekt untill load Profile Page info
    public OwnerProfilePage waitUntilLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(ownerInfoTable));
        return this;
    }

    // Redirection to  ADD  PET Page
        public AddNewPetPage goToAddNewPetPage(){
                addNewPetButton.click();
                return new AddNewPetPage(driver);
        }

    // Redirection to ADD  VISIT  Page
        public AddNewVisitPage goToAddNewVisitPage(){
            addVisitLink.click();
            return new AddNewVisitPage(driver);
        }

// added Navigation in header to search page
        public FindOwnersPage goToSearchPage(){
            linkToFindOwners.click();
            return new FindOwnersPage(driver);
        }



    public WebElement getOwnerInfoTable() {
        return ownerInfoTable;
    }

    public WebElement getEditOwnerButton() {
        return editOwnerButton;
    }

    public WebElement getAddNewPetButton() {
        return addNewPetButton;
    }

    public WebElement getEditPetLink() {
        return editPetLink;
    }

    public WebElement getAddVisitLink() {
        return addVisitLink;
    }

    public WebElement getHiddinVisitInfo() {
        return hiddinVisitInfo;
    }

}

