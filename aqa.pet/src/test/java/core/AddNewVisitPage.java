package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *  This PO Class is responsible for adding new VISIT
 *  and describes ADD VISIT page
 */
public class AddNewVisitPage extends BasePage{

    // Preparation for Calendar input date interraction
    LocalDate today = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String todayDateString = today.format(formatter);

    @FindBy(xpath = "//input[@id='date']")
    WebElement visitDateInputField;

    @FindBy(xpath = "//input[@id='description']")
    WebElement descriptionInputField;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement addVisitSubmitButton;



        public AddNewVisitPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    @Override
    public boolean isPageLoaded() {
        return getHeader().isDisplayed() ;
    }

    /**
     * Method combines action for fill in inputs to add new VISIT
     * @return OwnerProfilePage
     */
    public OwnerProfilePage addNewVisit(String description, String date){
        visitDateInputField.sendKeys(date);
        descriptionInputField.sendKeys(description);
        addVisitSubmitButton.click();
        return  new OwnerProfilePage(driver).waitUntilLoaded();
    }



    public WebElement getAddVisitSubmitButton() {
        return addVisitSubmitButton;
    }

    public WebElement getDescriptionInputField() {
        return descriptionInputField;
    }

    public WebElement getVisitDateInputField() {
        return visitDateInputField;
    }

}
