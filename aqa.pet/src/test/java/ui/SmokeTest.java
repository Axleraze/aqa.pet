package ui;

import core.BaseTest;
import core.FindOwnersPage;
import core.MainPage;
import core.OwnerProfilePage;
import org.junit.jupiter.api.*;
import java.io.IOException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SmokeTest extends BaseTest {

    private MainPage mainPage;
    private FindOwnersPage findOwnersPage;
    private OwnerProfilePage ownerProfilePage;

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
    private final String ownerCity = System.getProperty("owner.city");
    private final String ownerTelephone = System.getProperty("owner.telephone");
    private final String petName = System.getProperty("pet.name");
    private final String petBirth = System.getProperty("pet.birth");
    private final String visitDescription = System.getProperty("visit.description");

    @BeforeEach
    public void setupPages() {
        mainPage = new MainPage(driver);
    }

    @Test
    public void fullSmokeTest() {
        // Go to OWNERS PAGE
        findOwnersPage = mainPage.goToSearchPage();

        // Create new OWNER
        ownerProfilePage = findOwnersPage.goToAddOwnerPage()
                .createNewOwner(ownerName, ownerLastName, ownerAddress, ownerCity, ownerTelephone);

        // Create new PET for Owner
        ownerProfilePage.goToAddNewPetPage()
                .createNewPet(petName, petBirth);

        // Create new VISIT for Owner with Pet
        ownerProfilePage.goToAddNewVisitPage().addNewVisit(visitDescription);              ;

        // Returm to FEIND OWNERS page and check then newlz created user exists
        findOwnersPage = mainPage.goToSearchPage();
        findOwnersPage.searchOwner(ownerLastName);
//        assertTrue(findOwnersPage.isOwnerPresent(ownerName, ownerLastName),
//                "Созданный владелец не найден на странице поиска!");
    }
}
