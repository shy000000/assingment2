package cn.tjuscs.selenium;

import java.util.regex.Pattern;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.csvreader.CsvReader;

public class test2 {
	 private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();
	  
	  @Before
	  public void setUp() throws Exception {
	    driver = new FirefoxDriver();
	    baseUrl = "http://www.ncfxy.com/";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    
	  }

	  @Test
	  public void test2() throws Exception {
		 
			
		  
	    driver.get(baseUrl);
	  /* 
	    driver.findElement(By.id("kw")).click();
	    driver.findElement(By.id("kw")).click();
	    driver.findElement(By.id("kw")).clear();
	    driver.findElement(By.id("kw")).sendKeys("selenium");
	    driver.findElement(By.id("su")).click();
	    assertEquals("selenium_百度搜索", driver.getTitle());
	    */
	    /*
	    WebElement baiduTextBox = driver.findElement(By.id("kw"));
	    Actions actiona = new Actions(driver);
	    actiona.sendKeys(baiduTextBox,"qtp");
	    actiona.build().perform();
	    actiona.click(driver.findElement(By.id("su"))).build().perform();
	    assertEquals("qtp_百度搜索", driver.getTitle());
	    */
//	    ((JavascriptExecutor)driver).executeScript("arguments[0].value=arguments[1]",driver.findElement(By.id("kw")),"selenium");
//	    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.id("su")));
		
	    System.out.println(driver.getTitle());
	    assertEquals("软件测试课程登录", driver.getTitle());
	    CsvReader r = new CsvReader("E://info.csv", ',',Charset.forName("GBK"));
	    r.readHeaders();
        //逐条读取记录，直至读完
        while (r.readRecord()) {
            //读取一条记录
            
            //按列名读取这条记录的值
            String key = r.get("key");
            String email = r.get("email");
            System.out.println(email);
            String password = key.substring(4, 10);
            driver.findElement(By.id("name")).clear();
            driver.findElement(By.id("name")).sendKeys(key);

            driver.findElement(By.id("pwd")).clear();
            driver.findElement(By.id("pwd")).sendKeys(password);
            driver.findElement(By.id("submit")).click();
            
            WebElement tbody =driver.findElement(By.id("table-main"));
            String result =  tbody.getText();
            
            String[] mid = result.split("学号");
            String[] mid2 = mid[0].split("邮箱");
            String[] mid3 = mid2[1].split(" ");
            String[] mid4 = mid3[1].split("\n");
            String emailCharge = mid4[0];
            System.out.println(emailCharge);
            boolean res = emailCharge.equals(email);
            if(res){
            	System.out.println("true");
            }
            assertEquals(true, res);
            driver.get(baseUrl);

        }
        r.close();
	    
	    
	    
	  }

	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

	  private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }

}
