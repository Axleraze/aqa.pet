package ui;

import core.BaseTest;
import core.FindOwnersPage;
import core.MainPage;
import core.OwnerProfilePage;
import org.junit.jupiter.api.*;

import java.io.IOException;

/**
 * --- This Smoke-Test follows next scenaio (EN): ---
 * 1. User gets to HOME page the Site and then navigates to FIND OWNERS.
 * 2. User creates a new pet Owner.
 * 3. User create a new Pet and assingns it to created Owner
 * 4. User create new Visit to Clinic and assingns it to created Owner. After then navigates to search page FIND OWNERS
 * 5. User searches the created at satep 2 Owner.
 *
 * --- Dieser Smoke-Test erfüllt das folgende Szenario (DE): ---
 * 1. Benutzer offnet die HOME Page der Site. Dann folgt Benutzer dem Link FIND OWNERS.
 * 2. Benutzer legt eines neuen Tierhalters (Owner) an.
 * 3. Benutzer legt eines neuen Tiers (Pet) zu erstelltenem Tierhalter (Owner) an.
 * 4. Benutzer fügt eines neuen Besuchs (Visit) für ein Tier (Pet) hinzu. Dann folgt dem Link FIND OWNERS.
 * 5. Benutzer sucht nach gegebenem im Schritt 2 Tierhalter (Owner).
 */

// погуглить больше про аннотацию
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SmokeTestOLD extends BaseTest {
    private MainPage mainPage;
    private FindOwnersPage findOwnersPage;
    private OwnerProfilePage ownerProfilePage;

    private static String url;
    static {
        try {
            System.getProperties().load(ClassLoader.getSystemResourceAsStream("application.properties"));
            url= System.getProperty("url");
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

    // Go to Find owner page
    @Test
    @Order(1)
    public void navigateToFindOwners() {
        findOwnersPage = mainPage.goToSearchPage();
    }

    // create new user
    @Test
    @Order(2)
    public void createNewOwner(){
        findOwnersPage = mainPage.goToSearchPage();
        ownerProfilePage = findOwnersPage.goToAddOwnerPage()
                .createNewOwner(ownerName, ownerLastName, ownerAddress,ownerCity, ownerTelephone);
    }

    // create new pet
    @Test
    @Order(3)
    public void createNewPetForOwner() {
        ownerProfilePage = ownerProfilePage.goToAddNewPetPage().createNewPet(petName,petBirth );
    }


//**************
//    @Test
//    public void navigateToSearchPage() {
//        MainPage mainPage = new MainPage(driver);
//        mainPage.navigateToSearchPage();
//    }
//
//    @Test
//    public void searchOwner(){
//        navigateToSearchPage();
//        FindOwnersPage findOwnerPage = new FindOwnersPage();
//        findOwnerPage.searchOwner("Franklin");
//    }

}
