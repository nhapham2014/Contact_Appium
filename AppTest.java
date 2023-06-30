package vn.camautomation;

import static org.junit.Assert.assertTrue;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.AndroidKey;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;


import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    WebDriver driver;

    @Before
    public void initTest() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "10");
        caps.setCapability("deviceName", "R9TR30R8WYJ");
        caps.setCapability("appPackage", "com.samsung.android.app.contacts");
        caps.setCapability("appActivity", "com.samsung.android.contacts.contactslist.PeopleActivity");
        this.driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),caps);
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));


    }
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws IOException, InvalidFormatException, InterruptedException {
        LocalDateTime now = LocalDateTime.now();

        File file = new File("D:\\HỌC AUTOMATION\\Contacts.xlsx");
        FileInputStream inputStream = new FileInputStream(file);
        OPCPackage fs;
        fs=OPCPackage.open(file);
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet wSheet = wb.getSheet("Sheet1");
        /*Input list contacts*/
        for (int i = 1; i <4 ; i++) {
            XSSFRow row = wSheet.getRow(i);
            String Name = row.getCell(0).getStringCellValue();
            String Phone = row.getCell(1).getStringCellValue();
            String sName = row.getCell(2).getStringCellValue();
            String sPhone = row.getCell(3).getStringCellValue();
            if(i==1){
                driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Create contact\"]")).click();
                Thread.sleep(2000);
                driver.findElement(By.xpath("//android.widget.RelativeLayout[@index='0']")).click();
                driver.findElement(By.xpath("//android.widget.Button[@resource-id='com.samsung.android.app.contacts:id/button1']")).click();
                Thread.sleep(2000);
                driver.findElement(By.xpath("//android.widget.EditText[@text='Name']")).sendKeys(Name);
                ((AndroidDriver) this.driver).pressKey(new KeyEvent(AndroidKey.BACK));
                driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Phone\").instance(0))")).click();
                Thread.sleep(2000);
                //driver.findElement(By.xpath("//android.widget.EditText[@text='Name']")).click();
                driver.findElement(By.xpath("//android.widget.EditText[@text='Phone']")).sendKeys(Phone);
                driver.findElement(By.xpath("//android.widget.TextView[@text='Save']")).click();
            }
            else {
                driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Create contact\"]")).click();
                Thread.sleep(2000);

                driver.findElement(By.xpath("//android.widget.EditText[@text='Name']")).sendKeys(Name);
                ((AndroidDriver) this.driver).pressKey(new KeyEvent(AndroidKey.BACK));
                driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Phone\").instance(0))")).click();
                Thread.sleep(2000);
                //driver.findElement(By.xpath("//android.widget.EditText[@text='Name']")).click();
                driver.findElement(By.xpath("//android.widget.EditText[@text='Phone']")).sendKeys(Phone);
                driver.findElement(By.xpath("//android.widget.TextView[@text='Save']")).click();
            }

        }
      /* Update phone number*/
        driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Mai\").instance(0))")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Edit\"]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"Remove phone number\"]")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@text=\"Phone\"]")).click();
        driver.findElement(By.xpath("//android.widget.EditText[@text=\"Phone\"]")).sendKeys("09999999");
        driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Save\"]")).click();

        /*Delete phone number*/
        driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Mai\").instance(0))")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//android.widget.ImageView[@content-desc=\"More options\"])[2]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//android.widget.TextView[@text='Delete']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//android.widget.Button[@text='Move']")).click();
        

    }
}
