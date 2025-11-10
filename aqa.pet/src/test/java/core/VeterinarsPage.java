package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class VeterinarsPage extends BasePage{

    public VeterinarsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    // This page was not in Task.
    // TODO add navigation throw pagination 1-2-... //may be later
}
