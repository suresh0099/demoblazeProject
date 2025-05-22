package utilities;
import org.testng.annotations.Test;
import Pages.DemoblazePage;
import utilities.TestBase;
public class DemoblazeTest extends TestBase {
	@Test
    public void testDemoblazeFlow() throws InterruptedException {
        test = extent.createTest("Demoblaze Product Purchase Flow");

        DemoblazePage demoblaze = new DemoblazePage(driver);

        demoblaze.registerUser("testUser123", "testPass123");
        demoblaze.loginUser("testUser123", "testPass123");
        demoblaze.selectProductAndAddToCart();
        demoblaze.checkoutAndPlaceOrder("Test User", "1234567890123456");
        demoblaze.logout();
    }

}
