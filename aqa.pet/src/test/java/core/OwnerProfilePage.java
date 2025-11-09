package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OwnerProfilePage extends BasePage{
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



    public OwnerProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    // Method for waiting after add new owner and redirekt untill load Profile Page
    public OwnerProfilePage waitUntilLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(ownerInfoTable));
        return this;
    }

    // Redirection to create new Pet Page
        public AddNewPetPage goToAddNewPetPage(){
                addNewPetButton.click();
                return new AddNewPetPage(driver);
        }

        public AddNewVisitPage goToAddNewVisitPage(){
            addVisitLink.click();
            return new AddNewVisitPage(driver);
        }

    }

