package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This PO class describes found OWNERS page. Page contains a table with search result from owners and links to owner profile.
 * This page appears when the Last Name of owner is not unique, or when the search was made with empty parameters.
 */
public class SearchResultsPage extends BasePage{
// //table[@id='owners']//a[contains(@href, '')] diese Locator was Falsch. Am Ende nach dem Owner, Pet und Visit created wurde, Test hat durchgefallen
    @FindBy(xpath = "//table[@id='owners']")
    WebElement linkFromOwnerSearchResult;



    @Override
    public boolean isPageLoaded() {
    return getHeader().isDisplayed() && linkFromOwnerSearchResult.isDisplayed();
    }

    protected SearchResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


}
