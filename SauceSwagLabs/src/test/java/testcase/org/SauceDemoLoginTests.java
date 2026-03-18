package testcase.org;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SauceDemoLoginTests {

    WebDriver driver;

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("BEFORE SUITE: Initializing Login Test Suite");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("BEFORE TEST: Launching Firefox via Selenium Manager");
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    	
    @Test
    public void validLoginTest() throws InterruptedException {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        Thread.sleep(3000);
        
        String heading = driver.findElement(By.className("title")).getText();
        Assert.assertEquals(heading, "Products");
        System.out.println("TESTCASE: validLoginTest → Home page validated successfully." + heading);
    
        
        Thread.sleep(3000);

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        System.out.println("TWO PRODUCTS ADDED TO CART");
        
        Thread.sleep(3000);

        driver.findElement(By.className("shopping_cart_link")).click();
        System.out.println("CART ICON CLICKED → Navigated to Cart page");
        
        Thread.sleep(3000);

        List<WebElement> cartItem1 =
                driver.findElements(By.className("cart_item"));
        for (WebElement item : cartItem1) {
            System.out.println("----- PRODUCT DETAILS -----");
            
            System.out.println("Name        : " +
                    item.findElement(By.className("inventory_item_name")).getText());

            System.out.println("Description : " +
                    item.findElement(By.className("inventory_item_desc")).getText());

            System.out.println("Price       : " +
            item.findElement(By.className("inventory_item_price")).getText());
        }   
        
        driver.findElement(By.id("checkout")).click();
        System.out.println("CHECKOUT BUTTON CLICKED");
        
        driver.findElement(By.id("first-name")).sendKeys("Suwathi");
        driver.findElement(By.id("last-name")).sendKeys("karthikeyan");
        driver.findElement(By.id("postal-code")).sendKeys("600001");
        
        Thread.sleep(3000);
        System.out.println("CHECKOUT INFO ENTERED");
        
        driver.findElement(By.id("continue")).click(); 
        System.out.println("----- PAYMENT & TOTAL DETAILS -----");

     String paymentInfo = driver.findElements(By.className("summary_value_label")).get(0).getText();
     System.out.println("Payment Information : " + paymentInfo);

     String shippingInfo = driver.findElements(By.className("summary_value_label")).get(1).getText();
     System.out.println("Shipping Information : " + shippingInfo);

     System.out.println(driver.findElement(By.className("summary_subtotal_label")).getText());
     System.out.println(driver.findElement(By.className("summary_tax_label")).getText());
     System.out.println(driver.findElement(By.className("summary_total_label")).getText());
   
     driver.findElement(By.id("finish")).click();

        Assert.assertTrue(
                driver.findElement(By.className("complete-header"))
                        .getText().contains("Thank you")
        );

        System.out.println("ORDER COMPLETED SUCCESSFULLY");
    }
       
    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

    @AfterTest
    public void afterTest() {
        System.out.println("AFTER TEST: All login test combinations executed");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("AFTER SUITE: Login Test Suite Completed");
    }
}