
package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DemoblazePage {

	WebDriver driver;

    public DemoblazePage(WebDriver driver) {
        this.driver = driver;
    }
    By signUp = By.id("signin2");
    By signUpUsername = By.id("sign-username");
    By signUpPassword = By.id("sign-password");
    By signUpButton = By.xpath("//button[text()='Sign up']");

    By login = By.id("login2");
    By loginUsername = By.id("loginusername");
    By loginPassword = By.id("loginpassword");
    By loginButton = By.xpath("//button[text()='Log in']");

    By product = By.linkText("Samsung galaxy s6");
    By addToCart = By.linkText("Add to cart");
    By cart = By.id("cartur");
    By placeOrder = By.xpath("//button[text()='Place Order']");
    By name = By.id("name");
    By card = By.id("card");
    By purchaseBtn = By.xpath("//button[text()='Purchase']");
    By okBtn = By.xpath("//button[text()='OK']");
    By logout = By.id("logout2");

    
    public void registerUser(String username, String password) throws InterruptedException {
        driver.findElement(signUp).click();
        Thread.sleep(2000);
        driver.findElement(signUpUsername).sendKeys(username);
        driver.findElement(signUpPassword).sendKeys(password);
        driver.findElement(signUpButton).click();
        Thread.sleep(2000);
    }

    public void loginUser(String username, String password) throws InterruptedException {
        driver.findElement(login).click();
        Thread.sleep(2000);
        driver.findElement(loginUsername).sendKeys(username);
        driver.findElement(loginPassword).sendKeys(password);
        driver.findElement(loginButton).click();
        Thread.sleep(2000);
    }

    public void selectProductAndAddToCart() throws InterruptedException {
        driver.findElement(product).click();
        Thread.sleep(2000);
        driver.findElement(addToCart).click();
        Thread.sleep(2000);
        driver.switchTo().alert().accept();
        Thread.sleep(2000);
    }

    public void checkoutAndPlaceOrder(String userName, String cardDetails) throws InterruptedException {
        driver.findElement(cart).click();
        Thread.sleep(2000);
        driver.findElement(placeOrder).click();
        Thread.sleep(2000);
        driver.findElement(name).sendKeys(userName);
        driver.findElement(card).sendKeys(cardDetails);
        driver.findElement(purchaseBtn).click();
        Thread.sleep(2000);
        driver.findElement(okBtn).click();
        Thread.sleep(2000);
    }

    public void logout() {
        driver.findElement(logout).click();
    }
}
