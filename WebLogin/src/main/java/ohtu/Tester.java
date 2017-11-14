package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.Random;

public class Tester {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/home/laimikko/Downloads/chromedriver");
        WebDriver driver = new ChromeDriver();

//        WebDriver driver = new HtmlUnitDriver();

        driver.get("http://localhost:4567");
        System.out.println(driver.getPageSource());

        sleep(2);
        tryLoginWithWrongPassword(driver);

        sleep(2);

        tryloginWithWrongUsername(driver);
//


        sleep(2);

        createNewUser(driver);
        logoutAfterCreatingNew(driver);

        loginWorksWithCorrectCredentials(driver);

        sleep(2);

        sleep(3);

        driver.quit();
    }


    private static void logoutAfterCreatingNew(WebDriver driver) {
        WebElement element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();

        sleep(2);


        element = driver.findElement(By.linkText("logout"));
        element.click();
    }


    private static void createNewUser(WebDriver driver) {
        WebElement element = driver.findElement(By.linkText("back to home"));
        element.click();

        sleep(2);

        element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(2);


        element = driver.findElement(By.name("username"));
        Random r = new Random();

        element = driver.findElement(By.name("username"));
        element.sendKeys("mikko" + r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("okkim");

        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("okkim");

        sleep(2);

        element = driver.findElement(By.name("signup"));
        element.submit();
    }

    private static void loginWorksWithCorrectCredentials(WebDriver driver) {
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);
        
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));

        sleep(2);

        element.submit();
    }

    private static void tryloginWithWrongUsername(WebDriver driver) {
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("moi");
        element = driver.findElement(By.name("login"));
        sleep(2);

        element.submit();

    }

    private static void sleep(int n) {
        try {
            Thread.sleep(n * 1000);
        } catch (Exception e) {
        }
    }

    private static void tryLoginWithWrongPassword(WebDriver driver) {
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("moi");
        element = driver.findElement(By.name("login"));
        sleep(2);

        element.submit();

    }


}
