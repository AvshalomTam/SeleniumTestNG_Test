import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

/*****************************************************************************
 * Author:      Avshalom Tam
 * Description: This is the first Selenium TestNG test.
 *              It opens amazon homepage and make test there
 ****************************************************************************/

public class FirstTest {
    //-----------------------------------Global Variables-----------------------------------
    //Declare a Webdriver variable
    public WebDriver driver;
    //Declare a test URL variable
    public String testURL = "https://www.amazon.in/";
    //-----------------------------------Test Setup-----------------------------------
    @BeforeMethod
    public void setupTest (){
        //Create a new ChromeDriver
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.navigate().to(testURL);
        //maximize the window size
        driver.manage().window().maximize();
    }

    //-----------------------------------Tests-----------------------------------
    @Test
    public void firstTest () throws InterruptedException {
        //Get page title
        String title = driver.getTitle();
        //Print page's title
        System.out.println("Page Title: " + title);
        //Assertion for validation purpuses
        Assert.assertEquals(title, "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in", "Title assertion is failed!");

        //locate the web element
        String tagname = " ";
        tagname = driver.findElement(By.cssSelector("[tabindex='15'] [class='nav-line-2']")).getText();
        System.out.println(tagname);

        //dropdown
        WebElement category = driver.findElement(By.cssSelector("[tabindex='15'] [class='nav-line-2']"));
        Actions action = new Actions(driver);
        action.moveToElement(category).perform();
        Thread.sleep(2000);

        //go to books on menu
        WebElement books = driver.findElement(By.cssSelector("[data-nav-panelkey='BooksPanel'] .nav-text"));
        action.moveToElement(books);
        action.click();
        action.perform();
        Thread.sleep(3000);

        //click on fiction books
        driver.findElement(By.linkText("Fiction Books")).click();
        Thread.sleep(2000);

        //Get page title
        String booksTitle = driver.getTitle();
        Assert.assertEquals(title, "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in", "Title assertion is failed!");

        //type the text
        WebElement lable = driver.findElement(By.xpath("/html//input[@id='twotabsearchtextbox']"));
        lable.sendKeys("John Grisham");

        //click on search
        driver.findElement(By.xpath("//div[@id='nav-search']/form[@role='search']//input[@value='Go']")).click();
        Thread.sleep(2000);
    }

    //-----------------------------------Test TearDown-----------------------------------
    @AfterMethod
    public void teardownTest (){
        //Close browser and end the session
        driver.quit();
    }
}