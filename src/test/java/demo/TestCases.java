package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
    @Test
    public void testCase01() throws InterruptedException{
        System.out.println("start testcase01....");

        // Navigating to flipcart url...
        driver.get("https://www.flipkart.com/");
        Thread.sleep(5000);

        // Searching for product....
        Wrappers.searchForProduct(driver,By.xpath("//div[@class='_3NorZ0 _3jeYYh']/form/div/div/input"),"Washing Machine");
        Thread.sleep(5000);

        // sort by popularity...
        Wrappers.clicElement(driver, By.xpath("//div[text()='Popularity']"));
        Thread.sleep(3000);

        // count the rating of products....
        Boolean status = Wrappers.rating(driver, By.xpath("//span[contains(@id,'productRating')]/div[text()<='4.0']"));
        Assert.assertTrue(status);

        System.out.println("End testcase01....");

    }
    @Test
    public void testCase02() throws InterruptedException{
        System.out.println("Start testcase02");

        // Navigating to flipcart url...
        driver.get("https://www.flipkart.com/");
        Thread.sleep(5000);

        // Searching for product....
        Wrappers.searchForProduct(driver,By.xpath("//div[@class='_3NorZ0 _3jeYYh']/form/div/div/input"),"iPhone");
        Thread.sleep(5000);

        //iphone discount more then 17%......
        Boolean status =Wrappers.ptpdIphone(driver,By.xpath("//div[contains(@class,'yKfJKb')]"),17);
        Assert.assertTrue(status);

        System.out.println("End testcase02....");
    
    } 
   @Test
    public void testCase03() throws InterruptedException{
        System.out.println("Start testcase03....");

        // Navigating to flipcart url...
        driver.get("https://www.flipkart.com/");
        Thread.sleep(5000);

        // Searching for product....
        Wrappers.searchForProduct(driver,By.xpath("//div[@class='_3NorZ0 _3jeYYh']/form/div/div/input"),"Coffee Mug");
        Thread.sleep(5000);

        // Clicking on 4* and above.....
        Wrappers.clicElement(driver,By.xpath("//div[@class='_0BvurA']/section[5]/div[2]/div[1]/div[1]/div[1]/label/div[1]"));
        Thread.sleep(5000);

        // Find the product title image url of top 5 review.....
        Boolean status =Wrappers.printTitleAndImageUrlOfcoffeeMug(driver, By.xpath("//div[@class='slAVV4']//span[@class='Wphh3N']"));
        Assert.assertTrue(status);
        System.out.println("End testcase03.....");

    }
}