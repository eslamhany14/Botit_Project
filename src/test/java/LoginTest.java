import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pagess.HomePage;
import org.example.pagess.LoginPage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.*;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest {
    private WebDriver driver;

    private LoginPage loginPageObj;

    private HomePage HomePageObj;

    // link of website we will test
    String Link = "http://transmission-dev.azurewebsites.net/login";
    // opening chorme and navigate to our website
    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(Link);
    }



    // login with vaild email & password
    @Test (priority = 0)
    public void loginTest() throws InterruptedException {
        loginPageObj = new LoginPage(driver);
        HomePageObj = new HomePage(driver);
        loginPageObj.Login("testbotitb2@dist.com" , "123456");
        Thread.sleep(5000);
    }


    // add new category from dataProvider from json file
    @Test(priority = 1,dataProvider = "testdata",enabled = false)
    public void AddCategory(String data) throws InterruptedException {
        String users[]=data.split(",");
        HomePageObj = new HomePage(driver);
        // navigate to add category page and writing english field
        HomePageObj.AddCat(users[0]);
        Thread.sleep(3000);
        // writing arabic field
        HomePageObj.ArabicField(users[1]);
        Thread.sleep(3000);
    }

    // dataProvider to get the data from JSON file
    @DataProvider(name="testdata")
    String[] readJson() throws IOException, ParseException
    {
        JSONParser jsonparser=new JSONParser();
        FileReader reader=new FileReader("F:\\atask\\TASK\\src\\test\\java\\Data\\MyData.json");
        Object obj=jsonparser.parse(reader);
        JSONObject userloginsJsonobj=(JSONObject)obj;
        JSONArray userloginsArray=(JSONArray)userloginsJsonobj.get("userlogins");
        String arr[]=new String[userloginsArray.size()];
        for (int i=0; i<userloginsArray.size();i++)
        {
            JSONObject users=(JSONObject) userloginsArray.get(i);
            String user=(String)users.get("CatName");
            String pwd=(String)users.get("Name");
            arr[i]=user+"," +pwd ;
      //      System.out.println("test" + user +" " + pwd);
        }
        return arr;
    }


    @Test(priority = 3,enabled = false)
    public void EditCategory() throws InterruptedException {
        HomePageObj = new HomePage(driver);
        HomePageObj.EditCategory();
    }

    @Test(priority = 4,enabled = false)
    public void DeleteCategory() throws InterruptedException {
        HomePageObj = new HomePage(driver);
        HomePageObj.DeleteCategory();

    }


    @Test(priority = 2)
    public void AddItemToCategoryTest() throws InterruptedException, AWTException {
        HomePageObj = new HomePage(driver);
        HomePageObj.AddItemToCategory();
    }

    @Test(priority = 5,enabled = false)
    public void ChangingItemPrice() throws InterruptedException {
        HomePageObj = new HomePage(driver);
        HomePageObj.ChangingItemPrice();

    }

    @AfterTest
    public void tearDown(){
      //  driver.quit();
    }
}

