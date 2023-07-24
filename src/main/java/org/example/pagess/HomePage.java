package org.example.pagess;

import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

import static java.awt.event.KeyEvent.*;

public class HomePage {

    private WebDriver driver;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    // catlog button
    @FindBy(xpath = "/html/body/div/div/div/div[1]/div[2]/ul/li[1]/a")
    WebElement CatBtn;




    ////////////////////////////////////////////////////////////////////////////////////////
    // add new Category Part

    @FindBy(className = "add-new-categoryin")
    WebElement AddNewCatBtn;

    @FindBy(id = "add_name_en")
    WebElement CatNameInput;

    @FindBy(id = "add_name_ar")
    WebElement CatNameArabic;

    @FindBy(className = "submit-branch")
    WebElement AddCatBtn;

    public void AddCat(String CatName) throws InterruptedException {
        CatBtn.click();
        Thread.sleep(3000);
        AddNewCatBtn.click();
        Thread.sleep(3000);
        CatNameInput.sendKeys(CatName);
        Thread.sleep(3000);
    }
    public void ArabicField(String ArbName){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('value','"+ ArbName +"')",CatNameArabic);
        AddCatBtn.click();
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Edit & delete Part
     int i=0 ;

    // function to get the Row of my Category
    public void testTable() throws InterruptedException {
        i=0;
        Thread.sleep(8000);
        WebElement simpleTable = driver.findElement(By.xpath("//*[@id=\"itemContainerother\"]"));

        //Get all rows
        List<WebElement> rows = simpleTable.findElements(By.tagName("tr"));

        //get data from each row
        for (WebElement row : rows) {
            List<WebElement> cols = row.findElements(By.tagName("td"));
            for (WebElement col : cols) {
           //     System.out.print(col.getText());
            }
            i++;
        }
        i--;


    }


    public void EditCategory() throws InterruptedException {

        CatBtn.click();
        testTable();
        WebElement EditedNameField = driver.findElement
                (By.xpath("/html/body/div/div/div/div[2]/div[5]/div/section/div[2]/div[2]/div[1]/div[3]/table/tbody/tr["+i+"]/td[3]/div[2]/div/form/div/div/ul/li[1]/input"));
        WebElement elementLocator = driver.findElement
                (By.xpath("//*[@id=\"itemContainerother\"]/tbody/tr["+i+"]/td[3]/div[1]/div[1]/div[1]/a"));

        WebElement DoneEditBtn = driver.findElement
                (By.xpath("/html/body/div/div/div/div[2]/div[5]/div/section/div[2]/div[2]/div[1]/div[3]/table/tbody/tr["+ i +"]/td[3]/div[2]/div/form/div/input"));


        elementLocator.click();

        EditedNameField.clear();
        EditedNameField.sendKeys("Backpack2rrr");
        DoneEditBtn.click();

    }

    public void DeleteCategory() throws InterruptedException {
        testTable();
        WebElement DeleteBtn = driver.findElement
                (By.xpath("//*[@id=\"itemContainerother\"]/tbody/tr["+i+"]/td[3]/div[1]/div[1]/div[2]/button"));

        Thread.sleep(3000);
        DeleteBtn.click();
    }


///////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Adding new item to my category
    @FindBy(xpath = "//*[@id=\"tab-2\"]")
    WebElement ItemsBtn;

    @FindBy(xpath = "//*[@id=\"tab2\"]/div[2]/div[2]/div[1]/a")
    WebElement AddNewItem;

    @FindBy(xpath = "//*[@id=\"up_image2\"]/div[1]/ul/li[1]/input")
    WebElement ItemName;

    @FindBy(xpath = "//*[@id=\"mainCategory_select\"]")
    WebElement ChooseCat;

    @FindBy(xpath = "//*[@id=\"mainCategory_select\"]/option[4]")
    WebElement DropDownCAT;

    @FindBy(xpath = "//*[@id=\"discountPriceItemId\"]")
    WebElement DiscountInput;

    @FindBy(id = "options_M_0")
    WebElement SelectOptions;


    @FindBy(xpath = "//*[@id=\"options_M_0\"]/option[4]")
    WebElement Option;

    @FindBy(xpath = "//*[@id=\"up_image2\"]/div[1]/ul/li[7]/div[1]/div[2]/label[1]/span")
    WebElement ItemVartion;

    @FindBy(xpath = "//*[@id=\"up_image2\"]/div[5]/div[2]/div[5]/ul/li[2]/input")
    WebElement SaveAndExitBtn;

    @FindBy(xpath = "//*[@id=\"up_image2\"]/div[4]/input")
    WebElement AddPriceField;

    public void AddItemToCategory() throws InterruptedException, AWTException {
        Thread.sleep(3000);
        CatBtn.click();
        ItemsBtn.click();
        Thread.sleep(2000);
        AddNewItem.click();
        ItemName.sendKeys("MyItem");
        ChooseCat.click();
        DropDownCAT.click();
        uploadphotoWithRobot();
        Thread.sleep(3000);
        DiscountInput.sendKeys("30");
        SelectOptions.click();
        Thread.sleep(3000);
        Option.click();
        ItemVartion.click();
        AddPriceField.clear();
        AddPriceField.sendKeys("500");
        Thread.sleep(8000);
        SaveAndExitBtn.click();

    }

    @FindBy(xpath = "//*[@id=\"up_image2\"]/div[1]/ul/li[4]/div/label")
    WebElement PhotoUpload;

    public void uploadphotoWithRobot() throws AWTException {
        String imagePath = "F:\\atask\\TASK\\uploads\\photo.jpg";
        PhotoUpload.click();

        Robot robot = new Robot();

        StringSelection selection = new StringSelection(imagePath);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection,null);

        robot.keyPress(VK_ENTER);
        robot.keyRelease(VK_ENTER);
        robot.delay(2000);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(VK_V);
        robot.keyRelease(VK_CONTROL);
        robot.delay(2000);
        robot.keyPress(VK_ENTER);
        robot.keyRelease(VK_ENTER);
    }


//////////////////////////////////////////////////////////////////////////////////////////
    //// changing price of an item

    @FindBy(xpath = "//*[@id=\"up_image2\"]/div[4]/input")
     WebElement priceField;

    @FindBy(xpath = "//*[@id=\"up_image2\"]/div[5]/div[5]/ul/li[2]/input")
    WebElement saveBtn;
    int k = 0 ,t=0;
    public void ChangingItemPrice() throws InterruptedException {

        CatBtn.click();
        Thread.sleep(5000);
        ItemsBtn.click();

        k=0;
        Thread.sleep(5000);
        WebElement simpleTable = driver.findElement(By.xpath("//*[@id=\"Grid\"]"));

        //Get all rows
        List<WebElement> rowws = simpleTable.findElements(By.tagName("tr"));

        //get data from each row
        for (WebElement roww : rowws) {
            List<WebElement> colls = roww.findElements(By.tagName("td"));
            for (WebElement coll : colls) {
                if(coll.getText().contains("2 in 1 Duffle Set All Black"))
                {
                    t = k;
                }
                //     System.out.print(col.getText());
            }
            k++;
        }
        k--;
        WebElement EditItem = driver.findElement
                (By.xpath("//*[@id=\"Grid\"]/tbody/tr["+t+"]/td[7]/div/a"));

        EditItem.click();
        priceField.clear();
        priceField.sendKeys("800");
        saveBtn.click();



    }




}
