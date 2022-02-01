import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class explicitWait {
    WebDriver driver = null;

//    to execute before test
    @BeforeTest
    public void open(){
        //set the driver location
        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver");
        driver = new ChromeDriver();
        //maximize the screen
        driver.manage().window().maximize();
        //set time out 10 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //open url(our HTML file)
        driver.get("file:///home/abdelrhman/Downloads/ExplicitWait.html");

    }

    @Test
    public void verifyAlert(){

//        click the "Click me, to Open an alert after 5 seconds" button to show the alert
        WebElement alertBTN = driver.findElement(By.id("alert"));
        alertBTN.click();

//        wait until the alert appear then accept it
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
        wait.until(ExpectedConditions.alertIsPresent());

        driver.switchTo().alert().accept();
    }


    @Test
    public void textChanged(){

//        check the text is in initial form
        WebElement text = driver.findElement(By.xpath("//h2[@id=\"h2\"]"));
        Assert.assertEquals(text.getText(),"I Will be Change");

//        click the "Change Text to Selenium Webdriver" button to change the text
        WebElement changeBTN = driver.findElement(By.id("populate-text"));
        changeBTN.click();

//        wait until the text changed the check if it's in the new form
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(By.xpath("//h2[@id=\"h2\"]"),"innerHTML","I Will be Change")));

        Assert.assertEquals(text.getText(),"Selenium Webdriver");


    }

    @Test
    public void verifyElementIsVisable(){

//        check that the second button is hidden
        WebElement hiddenBTN = driver.findElement(By.xpath("//button[@id=\"hidden\"]"));
        Assert.assertFalse(hiddenBTN.isDisplayed());

//        click "Display button after 10 seconds" button to appear the second button
        WebElement displayBTN = driver.findElement(By.className("btn-display"));
        displayBTN.click();

//        wait until the second button to be appeared and check its visibility
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(hiddenBTN));

        Assert.assertTrue(hiddenBTN.isDisplayed());

    }

    @Test
    public void verifyElementIsClickable(){
//        click "Enable button after 10 seconds" button
        WebElement clickableBTN = driver.findElement(By.xpath("//button[@id=\"enable-button\"]"));
        clickableBTN.click();

//        wait until the disable-button to be enabled and clickable
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id=\"disable\"]")));
    }

    @Test
    public void verifyElementIschecked(){
//        check that our checkbox is unchecked
        WebElement checkBox = driver.findElement(By.xpath("//input[@id='ch']"));
        Assert.assertFalse(checkBox.isSelected());

//        click "Check Checkbox after 10 seconds" button to check the checkbox automatically
        WebElement checkBTN = driver.findElement(By.xpath("//button[@id=\"checkbox\"]"));
        checkBTN.click();

//        ensure that the checkbox is checked
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeSelected(checkBox));
    }

    @AfterTest
    public void close(){
        driver.close();
    }
}

