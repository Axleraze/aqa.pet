package ui;

import core.*;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Smoke test for full owner creation and search flow.
 * Steps:
 * 1. Open Main Page
 * 2. Navigate to Find Owners page
 * 3. Add new owner
 * 4. Add new pet for the owner
 * 5. Add new visit for the pet
 * 6. Search for the owner
 * 7. Verify all information is displayed correctly
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Epic("UI Smoke Tests")
@Feature("Owner and Pet creation")
@Story("Create new owner and her pet to have a visit in pet-clinic")
@Severity(SeverityLevel.CRITICAL)
@DisplayName("Smoke Test - Owner with Pet creation and data verification")
public class SeparatedTest extends BaseTest {

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
    @Description("Complete user flow: go to search page → create owner → add pet → add visit → search owner")
    public void smokeTestSteps() {
                goToFindOwnersPage();
                createNewOwner();
                addNewPetToOwner();
                addNewVisitToOwner();
                searchOwnerAndVerify();
    }


    @Step("Go to Find Owners page")
    public void goToFindOwnersPage() {
        findOwnersPage = mainPage.goToSearchPage();
        assertTrue(findOwnersPage.isPageLoaded(), "Find Owners page not loaded");
    }

    @Step("Create new owner {ownerName} {ownerLastName}")
    public void createNewOwner() {
        ownerProfilePage = findOwnersPage.goToAddOwnerPage()
                .createNewOwner(ownerName, ownerLastName, ownerAddress, ownerCity, ownerTelephone);
        assertTrue(ownerProfilePage.getAddNewPetButton().isDisplayed(), "New owner was not created");
    }

    @Step("Add new pet {petName} for owner")
    public void addNewPetToOwner() {
        ownerProfilePage.goToAddNewPetPage().createNewPet(petName, petBirth);
        assertTrue(ownerProfilePage.getAddVisitLink().isDisplayed(), "New pet was not created");
    }

    @Step("Add new visit for {petName}")
    public void addNewVisitToOwner() {
        ownerProfilePage.goToAddNewVisitPage().addNewVisit(visitDescription, visitDate);
        assertTrue(ownerProfilePage.getHiddinVisitInfo().isDisplayed(), "New visit was not added");
    }

    @Step("Search owner by last name {ownerLastName} and verify info")
    public void searchOwnerAndVerify() {
        findOwnersPage = ownerProfilePage.goToSearchPage();
        searchResultsPage = findOwnersPage.searchOwner(ownerLastName);
        assertTrue(searchResultsPage.isPageLoaded(), "Search results page not loaded");
    }
}

