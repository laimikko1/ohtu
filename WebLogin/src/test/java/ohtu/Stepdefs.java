package ohtu;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;


public class Stepdefs {
    private WebDriver driver;

    public Stepdefs() {
//        System.setProperty("webdriver.chrome.driver", "/home/laimikko/Downloads/chromedriver");
//        driver = new ChromeDriver();
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:4567");
//        String baseUrl = "http://localhost:4567";
//        driver.get(baseUrl);
    }


    @Given("^login is selected$")
    public void login_selected() throws Throwable {

        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
    }

    @Given("^command new user is selected")
    public void command_new_user_is_selected() throws Throwable {
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }

    @When("^correct username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_correct_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^correct username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void username_and_incorrect_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^nonexistent username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void nonexistent_username_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^a too short username \"([^\"]*)\" and valid password \"([^\"]*)\" and matching password confirmation are entered$")
    public void a_too_short_username_and_valid_password_and_matching_password_confirmation_are_entered(String username, String password) throws Throwable {
        signUpWith(username, password, null);
    }

    @When("^a valid username \"([^\"]*)\" and password \"([^\"]*)\" and matching password confirmation are entered$")
    public void a_valid_username_and_password_and_matching_password_confirmation_are_entered(String username, String password) throws Throwable {
        signUpWith(username, password, null);
    }

    @When("^a valid username \"([^\"]*)\" and too short password \"([^\"]*)\" and matching password confirmation are entered$")
    public void a_valid_username_and_too_short_password_and_matching_password_confirmation_are_entered(String username, String password) throws Throwable {
        signUpWith(username, password, null);
    }

    @When("^a taken username \"([^\"]*)\" and valid password \"([^\"]*)\" and matching password confirmation are entered$")
    public void a_taken_username_and_valid_password_and_matching_password_confirmation_are_entered(String username, String password) throws Throwable {
        signUpWith(username, password, null);
    }

    @When("^a valid username \"([^\"]*)\" and valid password \"([^\"]*)\" and nonmatching password confirmation \"([^\"]*)\" are entered$")
    public void a_valid_username_and_valid_password_and_nonmatching_password_confirmation_are_entered(String username, String password, String confirmationPassword) throws Throwable {
        signUpWith(username, password, confirmationPassword);
    }


    @Then("^user is logged in$")
    public void user_is_logged_in() throws Throwable {
        pageHasContent("Ohtu Application main page");
    }

    @Then("^user is not logged in and error message is given$")
    public void user_is_not_logged_in_and_error_message_is_given() throws Throwable {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }

    @Then("^a new user is created$")
    public void a_new_user_is_created() throws Throwable {
        pageHasContent("Welcome to Ohtu Application");
        pageHasContent("info for newbie");
        pageHasContent("continue to application mainpage");
    }

    @Then("^user is not created and error \"([^\"]*)\" is reported$")
    public void user_is_not_created_and_error_is_reported(String error) throws Throwable {
        pageHasContent(error);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    /* helper methods */

    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }

    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();
    }

    private void signUpWith(String username, String password, String confirmationPassword) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name(("username")));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        if(confirmationPassword == null) {
            element.sendKeys(password);
        } else {
            element.sendKeys(confirmationPassword);
        }

        element = driver.findElement(By.name("signup"));
        element.submit();
    }

}
