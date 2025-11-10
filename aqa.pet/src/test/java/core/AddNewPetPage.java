package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This PO class describes ADD NEW PET flow
 */
public class AddNewPetPage extends BasePage {
    @FindBy(xpath = "//input[@id='name']")
    WebElement petNameInputField;

    // Preparation for Calendar input date interaction
    LocalDate today = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String todayDateString = today.format(formatter);

    @FindBy(xpath = "//input[@id='birthDate']")
    WebElement petBirthdayInputField;

    @FindBy(xpath = "//select[@id='type']")
    WebElement petTypeSelect;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement addPetButton;


    public AddNewPetPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public OwnerProfilePage createNewPet(String name, String date){
        petNameInputField.sendKeys(name);
        petBirthdayInputField.sendKeys(date);
        addPetButton.click();
        // TODO add pet type selection from drop down. Now is bird by default.
        return new OwnerProfilePage(driver).waitUntilLoaded();
    }

}
