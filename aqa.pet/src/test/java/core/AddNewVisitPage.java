package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddNewVisitPage extends BasePage{

    // Preparation for Calendar input date interraction
    LocalDate today = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String todayDateString = today.format(formatter);

    @FindBy(xpath = "//input[@id='date']")
    WebElement visitDateInputField;

    @FindBy(xpath = "//input[@id='description']")
    WebElement descriptionInputField;


    public AddNewVisitPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public OwnerProfilePage addNewVisit(String description){
        visitDateInputField.sendKeys(todayDateString);
        descriptionInputField.sendKeys(description);
        return  new OwnerProfilePage(driver).waitUntilLoaded();
    }

}
