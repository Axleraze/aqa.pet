package ui;

import core.*;
import org.junit.jupiter.api.*;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.qameta.allure.*;


/**
 * This is exact Smoke Test that responds to the following steps:
 * User loads MAIN Page --> Clicks to FIND OWNERS Link --> (User is redirected to FIND OWNERS page) Clicks to Add Owner Button  -->
 *  --> (User redirected to ADD OWNER Page) Fills in required fields and clicks ADD OWNER button  -->
 *  --> (User redirected to Owner Information Page. Owner information appeared) Clicks to ADD New Pet Button -->
 *  --> (User redirected to ADD PET Page) Fills in required fields and clicks ADD PET button -->
 *  --> (User redirected to Owner Information Page. Pet information appeared) Clicks to ADD VISIT Link  -->
 *  --> (User redirected to New Visit Page) Fills in required fields and clicks ADD VISIT button -->
 *  --> (User redirected to Owner Information Page. Visit information appeared) --> Clicks to FIND OWNERS Link -->
 *  --> (User redirected to ADD OWNER Page) Types in input field the Last Name added owner and clicks Find Owner button  -->
 *  --> (:: When owers last name ist unique:: User redirected to Owner Information Page) All added information is present.
 *   --> (:: When  owers last name is not unique:: User redirected to Owners Page) Links to owners page are present.
 */
public class SmokeTest extends BaseTest {
    private MainPage mainPage;
    private FindOwnersPage findOwnersPage;
    private AddNewOwnerPage addNewOwnerPage;
    private OwnerProfilePage ownerProfilePage;
    private SearchResultsPage searchResultsPage;

    private static String url;
    static {
        try {
            System.getProperties().load(ClassLoader.getSystemResourceAsStream("application.properties"));
            url = System.getProperty("url");
             } catch (IOException ex) {
                ex.printStackTrace();
        }
    }

    private final String ownerName = System.getProperty("owner.firstName");
    private final String ownerLastName = System.getProperty("owner.lastName");
    private final String ownerAddress = System.getProperty("owner.address");
    private final String ownerTelephone = System.getProperty("owner.telephone");
    private final String ownerCity = System.getProperty("owner.city");
    private final String petName = System.getProperty("pet.name");
    private final String petBirth = System.getProperty("pet.birth");
    private final String visitDescription = System.getProperty("visit.description");
    private final String visitDate = System.getProperty("visit.date");

    @BeforeEach
    public void setupPages() {
        mainPage = new MainPage(driver);
    }

    @Test
    @Description("User goes to main page, creates a new owner, adds a new pet to owner, and add new visit to them")
    public void smokeTest() {
        // Go to FIND OWNERS PAGE
        findOwnersPage = mainPage.goToSearchPage();
        assertTrue(findOwnersPage.isPageLoaded(), () -> "Failed loading " + findOwnersPage.getClass());


        // Go to CREATE new OWNER. If success. add new pet button appears
        ownerProfilePage = findOwnersPage.goToAddOwnerPage()
                .createNewOwner(ownerName, ownerLastName, ownerAddress, ownerCity, ownerTelephone);
        assertTrue(ownerProfilePage.getAddNewPetButton().isDisplayed(), () -> "Fail, new owner was not created");


        // Create new PET for Owner
        ownerProfilePage.goToAddNewPetPage().createNewPet(petName, petBirth);
        assertTrue(ownerProfilePage.getAddVisitLink().isDisplayed(), () ->  "Fail, new pet was not created");


        // Create new VISIT for Owner with Pet
        ownerProfilePage.goToAddNewVisitPage().addNewVisit(visitDescription, visitDate );
        assertTrue(ownerProfilePage.getHiddinVisitInfo().isDisplayed(), () ->  "Fail, new visit was not added");


        // Returm to FIND OWNERS page and check then search is correct
        findOwnersPage = ownerProfilePage.goToSearchPage();
        searchResultsPage = findOwnersPage.searchOwner(ownerLastName);
        assertTrue(searchResultsPage.isPageLoaded(), () ->  "Fail, to load search result page or owner not exist");


    }
}
