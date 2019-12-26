package propack;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class CreateproNg {

  public WebDriver driver;
  public ExtentHtmlReporter reporter;
  public ExtentReports extent;
  public ExtentTest test;
  public long login_start;
  public long finish_start;
  public long home_start;
  public Select selects_country,st_selects,selects_cmpny;
  @BeforeTest
     public void Reports() {
                                                
                                                //Report Generation 
       reporter= new ExtentHtmlReporter("./reports/myreports.html");
         System.out.println("file created");
                reporter.config().setDocumentTitle("Automation Report");
                reporter.config().setReportName("Functional report");
                    reporter.config().setTheme(Theme.DARK);
          extent=new ExtentReports();
           extent.attachReporter(reporter);
           extent.setSystemInfo("Hostname","LocalHost");
          extent.setSystemInfo("OS","Windows 10");
        extent.setSystemInfo("Browser","Google Chrome");
 }
                                @BeforeTest
                                public void Credentials() {
                                System.setProperty("webdriver.chrome.driver", "D:\\Drivers-Selenium\\chromedriver.exe");
                                driver = new ChromeDriver(); 
                                login_start =System.currentTimeMillis();
                                driver.navigate().to("https://dev-procontact.prometric.com/");
                                finish_start =System.currentTimeMillis();
                                                                driver.manage().window().maximize();
                } 
                                
                //Successfull Login
                                @Test(priority=2)
                                public void SuccessfulLogin() throws InterruptedException {
                                                driver.navigate().refresh();
                                                driver.findElement(By.xpath("//*[@id=\"userNameInput\"]")).clear();
                                                test=extent.createTest("Test-1.1-Successful Login");
                                                driver.findElement(By.xpath("//*[@id=\"userNameInput\"]")).sendKeys("bilproint\\sarah.kannaiyan");
                                                driver.findElement(By.xpath("//*[@id=\"passwordInput\"]")).sendKeys("Prometric09876");
                                                driver.findElement(By.xpath("//*[@id=\"submitButton\"]")).click();
                                                Thread.sleep(5000);
                                                System.out.println("Test-1.1 Passed Successfully-successful Login");
                                                extent.flush();
                                }
                                
                                // Unsuccessful login
                                @Test(priority=1)
                                public void UnsuccessfulLogin() throws InterruptedException {
                                Thread.sleep(3000);
                                test=extent.createTest("Test-1.2 -Unsuccessful Login");
                                driver.findElement(By.xpath("//*[@id=\"userNameInput\"]")).sendKeys("bilproint\\Senthilk.Sethu");
                                driver.findElement(By.xpath("//*[@id=\"passwordInput\"]")).sendKeys("Prometric09876");
                                driver.findElement(By.xpath("//*[@id=\"submitButton\"]")).click();
                                home_start=System.currentTimeMillis();
                                //test.log(Status.PASS,"Test case is"+result.getName() );
                                System.out.println("Test-1.2 Passed Successfully-Unsuccessful Login");
                                extent.flush();
                                
                                }
                                
                                //LoginPage Loading time
                                @Test(priority=3)
                                public void Login_loading_Time() throws InterruptedException {
                                                test=extent.createTest("Test-1.3-Login_loading_Time");
                                                long login_total_time = finish_start - login_start;
                                                String login_time =Long.toString(login_total_time);
                                                test.log(Status.PASS, "Login page loading time"+login_time);
                                               System.out.println("Login page loading time"+login_total_time);
                                               System.out.println("Test-1.3 Passed Successfully-Login Page loading time");
                                                Thread.sleep(3000);
                                               extent.flush();
                                    }
                                
                                //2.Home Screen
                                
                                //Test-2.1-Home screen Loading time
                                @Test(priority=4)
                                public void Home_loading_Time() throws InterruptedException {
                                                test=extent.createTest("Test-2.1-Home screen Loading time");
                                                                long home_finish=System.currentTimeMillis();
                                                                long home_total =home_finish - home_start;
                                                test.log(Status.PASS, "Home page loading time"+home_total);
                                                               System.out.println("Home Screen loading time is : "+home_total);
                                                                System.out.println("Test-2.1-Home screen Loading time - Pass");
                                                Thread.sleep(3000);
                                                extent.flush();
                                    }
                                
                                //Test-2.2 Prometric Image Check
                                @Test(priority=5)
                                public void Prometric_Image_Check() {
                                                test=extent.createTest("Test-2.2 Prometric Image Check");
                                                boolean logo_check = driver.findElement(By.xpath("/html/body/app-root/app-header/nav/a/img")).isDisplayed();
                                                Assert.assertTrue(logo_check);
                                                String logo_chc = Boolean.toString(logo_check);
                                                if (logo_check=true) {
                                                                test.log(Status.PASS,"Logo Image is present - "+logo_chc);
                                                                System.out.println("Logo Image is present");
                                                                System.out.println("Test-2.2 Prometric Image Check-Pass");
                                                }
                                                else {
                                                                test.log(Status.FAIL,"Logo Image is present - "+logo_chc);
                                                                System.out.println("Logo Image is not present");
                                                }
                                                
                                                
                                                extent.flush();
                                }
                                
                                //Test-2.3-Prometric Image Click
                                @Test(priority=6)
                                public void Prometric_Image_Click() {
                                                test=extent.createTest("Test-2.3-Prometric Image Click");
                                                                driver.findElement(By.xpath("/html/body/app-root/app-subheader/nav/div/div[1]/label/a/span")).click();
                                                                WebElement label1Img =driver.findElement(By.xpath("/html/body/app-root/app-header/nav/a/img"));
                                                                Actions act = new Actions(driver);
                                                                act.moveToElement(label1Img).build().perform();
                                                                label1Img.click();
                                                                System.out.println("Test-2.3-Prometric Image Click-Pass");
                                                                extent.flush();
                                }
                                //Test-2.4 Home screen text Check
                                @Test(priority=7)
                                public void Home_text_Check() throws InterruptedException {
                                                test =extent.createTest("Test-2.4 Home screen text Check");
                                                Thread.sleep(3000);
                                                boolean Hme_txt_Chck = driver.findElement(By.xpath("/html/body/app-root/app-content/section/div/div/div/div/h2")).isDisplayed();
                                                if (Hme_txt_Chck=true) {
                                                                System.out.println("Test-2.4 -Home screen text Present");
                                                }
                                                extent.flush();
                                } 

                //Test-2.5-Navigation Drawer Click
                                @Test(priority=8)
                                public void Navigation_Drawer() throws InterruptedException {
                                                test=extent.createTest("Test-2.5-Navigation Drawer Click");
                                                WebElement nav_ele =driver.findElement(By.xpath("//*[@id=\"openNav\"]"));
                                                Actions nav_more = new Actions(driver);
                                                nav_more.moveToElement(nav_ele).build().perform();
                                                nav_ele.click();
                                                Thread.sleep(3000);
                                    boolean nav_chck =driver.findElement(By.xpath("//*[@id=\"myDIV\"]/a[1]")).isDisplayed();
                                                if (nav_chck=true) {
                                                                System.out.println("Test-2.5-Navigation Drawer Click-Pass");
                                                }
                                                else {
                                                                System.out.println("Test-2.5-Navigation Drawer Click-Fail");
                                                }
                                                
                                                extent.flush();
                                }
                                
                                
                                //Test-2.6-Provider Profile in Navigation drawer
                                @Test(priority=9)
                                public void Pro_prfl_nav() throws InterruptedException {
                                                test=extent.createTest("Test-2.6-Provider Profile in Navigation drawer");
                                                Thread.sleep(3000);
                                                                driver.findElement(By.xpath("//*[@id=\"myDIV\"]/a[1]")).click();

                                                                boolean Pro_navChck = driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[2]/div[1]")).isDisplayed();
                                                                if (Pro_navChck=true) {
                                                                                System.out.println("Test-2.6 -Provider Profile in Navigation drawer - Pass");
                                                                }
                                                                extent.flush();
                                                extent.flush();
                                }
                                
                                //Test-2.7-Reservation in Navigation drawer
                                                @Test(priority=10)
                                                public void res_nav() throws InterruptedException {
                                                                test=extent.createTest("Test-2.7-Reservation in Navigation drawer");
                                                                
                                                                                WebElement nav2_ele =driver.findElement(By.xpath("//*[@id=\"openNav\"]"));
                                                                                Actions nav2_more = new Actions(driver);
                                                                                nav2_more.moveToElement(nav2_ele).build().perform();
                                                                                nav2_ele.click();
                                                                
                                                                                Thread.sleep(3000);
                                                                                driver.findElement(By.xpath("//*[@id=\"myDIV\"]/a[2]")).click();
                                                                                boolean res_navChck = driver.findElement(By.xpath("/html/body/app-root/app-reservation/div/div/div/div[1]/div[2]/button[1]/span")).isDisplayed();
                                                                                if (res_navChck=true) {
                                                                                                System.out.println("Test-2. -Reservation in Navigation drawer - Pass");
                                                                                }
                                                                                extent.flush();
                                                                extent.flush();
                                                }
                                

                                //Test-2.8 User-Logo Check
                                @Test(priority=11)
                                public void User_logo() throws InterruptedException {
                                                test =extent.createTest("Test-2.8 User_Logo Check");
                                                Thread.sleep(3000);
                                                boolean user_logo_check = driver.findElement(By.xpath("/html/body/app-root/app-header/nav/span/ul/li[2]/a/img")).isDisplayed();
                                                if (user_logo_check=true) {
                                                                System.out.println("Test-2.8 -User Logo Present");
                                                }
                                                extent.flush();
                                }
                                

                                
                                //Test-2.9 Reservation  Button
                                @Test(priority=12)
                                public void Res_But() throws InterruptedException {
                                                test =extent.createTest("2.7-Reservation  Button");
                                                Thread.sleep(3000);
                                                driver.findElement(By.xpath("/html/body/app-root/app-subheader/nav/div/div[2]/label/a/span")).click();
                                                boolean res_check = driver.findElement(By.xpath("/html/body/app-root/app-reservation/div/div/div/div[1]/div[2]/button[1]/span")).isDisplayed();
                                                if (res_check=true) {
                                                                System.out.println("Test-2.10-Reservation Button Check");
                                                }
                                                extent.flush();
                                }
                                
                                
                                // Test-2.10 Provider profile  Button
                                @Test(priority=13)
                                public void Pro_prfl_But() throws InterruptedException {
                                                test =extent.createTest("Provider profile  Button");
                                                Thread.sleep(3000);
                                                driver.findElement(By.xpath("/html/body/app-root/app-subheader/nav/div/div[1]/label/a/span")).click();
                                                
                                boolean pro_prfl_check = driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[2]/div[1]")).isDisplayed();
                                                if (pro_prfl_check=true) {
                                                                System.out.println("Test-2.9-Provider profile  Button Check");
                                                }
                                                Thread.sleep(3000);
                                                extent.flush();
                                }
                                

                                                
                                                
                                                
                                                
                                                
                                                //Provider Profile
                                                
                                                //Create Profile
                                                
                                                //3.1-Company/Organization name field validation with valid data
                                                
                                                @Test(priority=14)
                                                public void Comp_name_valid() throws InterruptedException {
                                                                test =extent.createTest("3.1-Company/Organization name field validation with valid data");
                                                                Thread.sleep(3000);
                                                                WebElement comp_ele = driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[2]/div[1]/div/input"));
                                                                comp_ele.sendKeys("SampleTest");
                                                                comp_ele.sendKeys(Keys.TAB);
                                                                try {
                                                                if(driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[2]/div[2]/p"))!= null){
                                                                                System.out.println("3.1-Company/Organization name field validation with valid data-Failed");
                                                                }
                                                }
                                                                catch(NoSuchElementException e) {
                                                                                
                                                                                System.out.println("3.1-Company/Organization name field validation with valid data-Pass");
                                                                }

                                                                extent.flush();
                                                                
                                                }
                                                
                                                
                                                //3.2-Company/Organization name field validation with no data
                                                
                                                @Test(priority=14)
                                                public void Comp_name_no_Data_validation() throws InterruptedException {
                                                                test =extent.createTest("3.2-Company/Organization name field validation with invalid data");
                                                                driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[2]/div[1]/div/input")).clear();
                                                                WebElement comp_ele = driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[2]/div[1]/div/input"));
                                                                comp_ele.sendKeys(Keys.TAB);
                                                                
                                                                try {
                                                                                if(driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[2]/div[2]/p"))!= null){
                                                                                                System.out.println("3.2-Company/Organization name field validation with invalid data with validation-Pass");
                                                                                }
                                                                }
                                                                                catch(NoSuchElementException e) {
                                                                                                
                                                                                                System.out.println("3.2-Company/Organization name field validation with invalid data with validation-Fail");
                                                                                }
                                                                
                                                                extent.flush();
                                                                                
                                                                }
                                                
                                                
                                                
                                                
                                                
                                                
                                                
                                                
                                                
                                                
                                                
//3.3-Full name field validation with valid data
                                                
                                                @Test(priority=15)
                                                public void Full_name_valid() throws InterruptedException {
                                                                test =extent.createTest("3.3-Full name field validation with valid data");
                                                                Thread.sleep(3000);
                                                                WebElement comp_ele = driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[4]/div[1]/div/input"));
                                                                comp_ele.sendKeys("SampleName");
                                                                comp_ele.sendKeys(Keys.TAB);
                                                                try {
                                                                if(driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[4]/div[2]/p"))!= null){
                                                                                System.out.println("3.3-Full name field validation with valid data-Fail");
                                                                }
                                                }
                                                                catch(NoSuchElementException e) {
                                                                                
                                                                                System.out.println("3.3-Full name field validation with valid data-Pass");
                                                                }

                                                                extent.flush();
                                                                
                                                }
                                                
                                                
                                                //3.4-Full name field validation with no data
                                                
                                                @Test(priority=16)
                                                public void Full_name_no_Data_validation() throws InterruptedException {
                                                                test =extent.createTest("3.4-Full name field validation with invalid data");
                                                                driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[4]/div[1]/div/input")).clear();
                                                                WebElement comp_ele = driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[4]/div[1]/div/input"));
                                                                comp_ele.sendKeys(Keys.TAB);
                                                                
                                                                try {
                                                                                if(driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[4]/div[2]/p"))!= null){
                                                                                                System.out.println("3.4-Full name field validation with invalid data-Pass");
                                                                                }
                                                                }
                                                                                catch(NoSuchElementException e) {
                                                                                                
                                                                                                System.out.println("3.4-Full name field validation with invalid data-Fail");
                                                                                }
                                                                
                                                                extent.flush();
                                                                                
                                                                }
                                                
                                                
                                                
                                                
                                                
//3.5-Email Address field validation with valid data
                                                
                                                @Test(priority=17)
                                                public void Email_valid() throws InterruptedException {
                                                                test =extent.createTest("3.5-Full name field validation with valid data");
                                                                Thread.sleep(3000);
                                                                WebElement comp_ele = driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[6]/div[1]/div/input"));
                                                                
                                                                comp_ele.sendKeys("SampleName");
                                                                comp_ele.sendKeys(Keys.TAB);
                                                                try {
                                                                if(driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[6]/div[2]/p"))!= null){
                                                                                System.out.println("3.5-Email Address field validation with invalid data-Fail");
                                                                }
                                                }
                                                                catch(NoSuchElementException e) {
                                                                                
                                                                                System.out.println("3.5-Email Address field validation with invalid data-Pass");
                                                                }

                                                                extent.flush();
                                                                
                                                }
                                                
                                                
                                                //3.6-Email Address field validation with no data
                                                
                                                @Test(priority=18)
                                                public void Email_Address_no_Data_validation() throws InterruptedException {
                                                                test =extent.createTest("3.6-Full name field validation with invalid data");
                                                                driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[6]/div[1]/div/input")).clear();
                                                                WebElement comp_ele = driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[6]/div[1]/div/input"));
                                                                comp_ele.sendKeys(Keys.TAB);
                                                                
                                                                try {
                                                                                if(driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[6]/div[2]/p"))!= null){
                                                                                                System.out.println("3.6-Email Address field validation with invalid data-Pass");
                                                                                }
                                                                }
                                                                                catch(NoSuchElementException e) {
                                                                                                
                                                                                                System.out.println("3.6-Email Address field validation with invalid data-Fail");
                                                                                }
                                                                
                                                                extent.flush();
                                                                                
                                                                }
                                                
                                                
                                                //3.7-Email Address field validation with invalid data without '.com'
                                                
                                                @Test(priority=19)
                                                public void Email_Address_invalid_Data_after() throws InterruptedException {
                                                                test =extent.createTest("3.7-Full name field validation with invalid data without '.com'");
                                                                driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[6]/div[1]/div/input")).clear();
                                                                WebElement comp_ele = driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[6]/div[1]/div/input"));
                                                                comp_ele.sendKeys("Sampletest@123");
                                                                comp_ele.sendKeys(Keys.TAB);
                                                                
                                                                try {
                                                                                if(driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[6]/div[2]/p"))!= null){
                                                                                                System.out.println("3.7-Email Address field validation without '.com' -Pass");
                                                                                }
                                                                }
                                                                                catch(NoSuchElementException e) {
                                                                                                
                                                                                                System.out.println("3.7-Email Address field validation without '.com' -Fail");
                                                                                }
                                                                
                                                                extent.flush();
                                                                                
                                                                }
                                                
                                                
//3.8-Email Address field validation with invalid data with special characters
                                                
                                                @Test(priority=20)
                                                public void Email_Address_invalid_Data_spcl_chars() throws InterruptedException {
                                                                test =extent.createTest("3.8-Email Address field validation with invalid data with special characters");
                                                                driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[6]/div[1]/div/input")).clear();
                                                                WebElement comp_ele = driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[6]/div[1]/div/input"));
                                                                comp_ele.sendKeys("Sampletest###@123.com");
                                                                comp_ele.sendKeys(Keys.TAB);
                                                                
                                                                try {
                                                                                if(driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[6]/div[2]/p"))!= null){
                                                                                                System.out.println("3.8-Email Address field validation with invalid data with special characters -Pass");
                                                                                }
                                                                }
                                                                                catch(NoSuchElementException e) {
                                                                                                
                                                                                                System.out.println("3.8-Email Address field validation with invalid data with special characters -Fail");
                                                                                }
                                                                
                                                                extent.flush();
                                                                                
                                                                }
                                                
//3.9-Phone number field validation with only numbers.
                                                
                                                @Test(priority=21)
                                                public void Phone_num_valid() throws InterruptedException {
                                                                test =extent.createTest("3.9-Phone number field validation with only numbers.");
                                                                Thread.sleep(3000);
                                
                                                                
                                                                try {
                                                                                WebElement comp_ele = driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[5]/div/div/input"));
                                                                                comp_ele.sendKeys("7845745158");
                                                                                
                                                                                System.out.println("3.9-Phone number field validation with only numbers.-Pass");
                                                                
                                                }
                                                                catch(NoSuchElementException e) {
                                                                                
                                                                                System.out.println("3.9-Phone number field validation with only numbers.-Fail");
                                                                }

                                                                extent.flush();
                                                                
                                                }
                                                
                                                
//3.10-Phone number field validation with numbers and texts
                                                
                                                @Test(priority=22)
                                                public void Phone_num_withText() throws InterruptedException {
                                                                test =extent.createTest("3.10-Phone number field validation with numbers and texts");
                                                                Thread.sleep(3000);
                                                                driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[5]/div/div/input")).clear();
                                                
                                                                
                                                                try {
                                                                                
                                                                                WebElement comp_ele = driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[5]/div/div/input"));
                                                                                comp_ele.sendKeys("Balanumber");
                                                                
                                                                                if(driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[5]/div/div/input"))!= null){
                                                                                                System.out.println("3.10-Phone number field validation with numbers and texts(accepts only number)-Fail");
                                                                                }
                                                                                else {
                                                                                                System.out.println("3.10-Phone number field validation with numbers and texts(accepts only number) -Pass");
                                                                                }
                                                                                
                                                                }
                                                                                catch(NoSuchElementException e) {
                                                                                                
                                                                                                
                                                                                }
                                                                
                                                                extent.flush();
                                                                                
                                                                }
                                                
                                                //3.11-Country select from drop down.
                                                
                                                @Test(priority=23)
                                                public void Country_select() throws InterruptedException {
                                                                test =extent.createTest("3.11-Country select from drop down");
                                                                Thread.sleep(3000);
                                                                WebElement ele_country =driver.findElement(By.id("selectCountryList"));
                                                                selects_country = new Select(ele_country);
                                                                selects_country.selectByValue("AUS");
                                                                WebElement option_country = selects_country.getFirstSelectedOption();
                                                                String selectedValueInDropDown = option_country.getText();
                                                                
                                                                if(selectedValueInDropDown!=null) {
                                                                                System.out.println("3.11-Country select from drop down-Pass");
                                                                                
                                                                }
                                                                else {
                                                                                System.out.println("3.11-Country select from drop down-Fail");
                                                                }
                                                                
                                                                
                                                                extent.flush();
                                                }
                                                
//3.12-State/province select from drop down.
                                                
                                                @Test(priority=24)
                                                public void State_province_select() throws InterruptedException {
                                                                test =extent.createTest("3.12-State/province select from drop down.");
                                                                Thread.sleep(3000);
                                                                
                                                                WebElement states =driver.findElement(By.id("selectRegionList"));
                                                                st_selects = new Select(states);
                                                                st_selects.selectByValue("NSW");
                                                                
                                                                
                                                                WebElement option_st = st_selects.getFirstSelectedOption();
                                                                String selectedValueInDropDown = option_st.getText();
                                                
                                                                if(selectedValueInDropDown!=null) {
                                                                                System.out.println("3.12-State/province select from drop down");
                                                                                
                                                                }
                                                                else {
                                                                                System.out.println("3.12-State/province select from drop down");
                                                                }
                                                                extent.flush();
                                                }
                                                
//3.13-Zipcode field validation with only numbers.
                                                
                                                @Test(priority=25)
                                                public void Zipcode_valid() throws InterruptedException {
                                                                test =extent.createTest("3.12-Zipcode field validation with only numbers");
                                                                Thread.sleep(3000);
                                
                                                                
                                                                try {
                                                                                WebElement zip1_ele = driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[11]/div/div/input"));
                                                                                zip1_ele.sendKeys("784574");
                                                                                String textval =zip1_ele.getText();
                                                                                
                                                                                System.out.println(textval);
                                                                                
                                                                                System.out.println("3.12-Zipcode field validation with only numbers-Pass");
                                                                
                                                }
                                                                catch(NoSuchElementException e) {
                                                                                
                                                                                System.out.println("3.12-Zipcode field validation with only numbers-Fail");
                                                                }

                                                                extent.flush();
                                                                
                                                }
                                                
                                                
                                                
//3.14-Zipcode field validation with numbers and texts
                                                
                                                @Test(priority=26)
                                                public void Zipcode_Text() throws InterruptedException {
                                                                test =extent.createTest("3.14-Zipcode field validation with numbers and texts");
                                                                Thread.sleep(3000);
                                                                driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[11]/div/div/input")).clear();
                                                
                                                                
                                                                
                                                                                
                                                                                WebElement zip_ele = driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[11]/div/div/input"));
                                                                                zip_ele.sendKeys("Balanumber");
                                                                                
                                                                                try {
                                                                                                if(driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[11]/div/div/input"))!= null){
                                                                                                                System.out.println("3.14-Zipcode field validation with numbers and texts(accepts only number)-Fail");
                                                                                                }
                                                                                                else {
                                                                                                                System.out.println("3.14-Zipcode field validation with numbers and texts(accepts only number) -Pass");
                                                                                                }
                                                                                }
                                                                                                catch(NoSuchElementException e) {
                                                                                                                
                                                                                                }
                                                                                
                                                                
                                                                extent.flush();
                                                                                
                                                                }
                                                
//3.15-Cancel Button enabled when value in any field.
                                                
                                                @Test(priority=27)
                                                public void Cancel_bt_enable_when_any_value() throws InterruptedException {
                                                                test =extent.createTest("3.14-Cancel Button enabled when value in any field.");
                                                                Thread.sleep(3000);
                                                                Boolean cancel_present =driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[12]/button[1]")).isEnabled();
                                                                if(cancel_present=true) {
                                                                                System.out.println("3.14-Cancel Button enabled when value in any field-Pass");
                                                                                
                                                                }
                                                                else {
                                                                                System.out.println("3.14-Cancel Button enabled when value in any field-Fail");
                                                                }
                                                                
                                                                
                                                                extent.flush();
                                                }
                                                
//3.16-Cancel Button enabled after clearing values in all fields.
                                                
                                                @Test(priority=28)
                                                public void Cancel_bt_enable_after_del_all_values() throws InterruptedException {
                                                                test =extent.createTest("3.17-Cancel Button enabled after clearing values in all fields");
                                                                Thread.sleep(3000);
                                                                
                                                                //Company name clear
                                                                driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[2]/div[1]/div/input")).clear();
                                                                //email address
                                                                driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[6]/div[1]/div/input")).clear();
                                                                selects_country.selectByVisibleText("Select Country");
                                                                st_selects.selectByVisibleText("Select State/Province/Region");
                                                

                                                Boolean cacncel_enable = driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[12]/button[1]")).isEnabled();
                                                                if(cacncel_enable=true) {
                                                                                System.out.println("3.17-Cancel Button enabled after clearing values in all fields-Fail");
                                                                                
                                                                }
                                                                else {
                                                                                System.out.println("3.17-Cancel Button enabled after clearing values in all fields-Pass");
                                                                }
                                                                
                                                                
                                                                extent.flush();
                                                }
                                                
//3.17-Cancel Button click.
                                                
                                                @Test(priority=29)
                                                public void Cancel_bt_clk() throws InterruptedException {
                                                                test =extent.createTest("3.16-Cancel Button click");
                                                                Thread.sleep(3000);
                                                driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[12]/button[1]")).click();
                                
                                                //Company
                                                Boolean compn_name_check =(driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[2]/div[1]/div/input"))!= null);
                                                //Full name
                                                Boolean full_name_check =(driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[4]/div[1]/div/input"))!= null);
                                                //Phone number
                                                Boolean phn_num_check =(driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[5]/div/div/input"))!= null);
                                                //Email Address
                                                Boolean mail_check =(driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[6]/div[1]/div/input"))!= null);
                                                //Address
                                                Boolean address_one_check =(driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[7]/div/div[1]/input"))!= null);
                                                Boolean address_two_check =(driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[7]/div/div[2]/input"))!= null);
                                                
                                                //City
                                                Boolean city_check =(driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[8]/div/div/input"))!= null);
                                                //Zipcode
                                                Boolean zip_check =(driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[11]/div/div/input"))!= null);
                                                
                                                
                                                if (compn_name_check == true && full_name_check==true && phn_num_check == true && mail_check == true && address_one_check== true && address_two_check == true&& city_check== true && zip_check == true) {
                                                                System.out.println("Test-3.17-Cancel Button click.-Pass");
                                                }
                                                else{
                                                                System.out.println("Test-3.17-Cancel Button click.-Fail");
                                                }
                                                                
                                                                extent.flush();
                                                }
//3.18-Lookup profile Button click.
                                                
                                                @Test(priority=30)
                                                public void lukup_profile_clk() throws InterruptedException {
                                                                test =extent.createTest("3.18-Lookup profile Button click.");
                                                                Thread.sleep(3000);
                                                                driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[2]/div[2]/button[2]/span")).sendKeys(Keys.ENTER);
                                                                
                                                                
                                                                if (driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[2]/div[2]/button[2]/span")).isSelected()) {
                                                                                System.out.println("3.18-Lookup profile Button click-Pass");
                                                                }
                                                                else {
                                                                                System.out.println("3.18-Lookup profile Button click-Fail");
                                                                }
                                                                }
                                                                
                                                
                                                
//LookUp Provider profile
                                                
//4.1-Company/organanization-select from drop down.
                                                
                                                @Test(priority=4)
                                                public void Cmpny_slct() throws InterruptedException {
                                                                test =extent.createTest("4.1-Company/organanization-select from drop down");
                                                                driver.findElement(By.xpath("/html/body/app-root/app-subheader/nav/div/div[1]/label/a/span")).click();
                                                                driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[2]/div[2]/button[2]/span")).click();
                                                                
                                                                Thread.sleep(3000);
                                                                
                                                                WebElement ele_cmpny =driver.findElement(By.id("providerSelector"));
                                                                selects_cmpny = new Select(ele_cmpny);
                                                                selects_cmpny.selectByValue("ABCD Provider");
                                                                WebElement option_cmpny = selects_country.getFirstSelectedOption();
                                                                String selectedValueInDropDown = option_cmpny.getText();
                                                                
                                                                if(selectedValueInDropDown.contains("ABCD") ) {
                                                                                System.out.println("Test-4.1-Company/organanization-select from drop down-Pass");
                                                                                
                                                                }
                                                                else {
                                                                                System.out.println("Test-4.1-Company/organanization-select from drop down-Fail");
                                                                }
                                                                
                                                                
                                                                extent.flush();
                                                                }
                                                
                                                
//4.2-Update Full name with valid data.
                                                
                                                @Test(priority=5)
                                                public void full_name_updt() throws InterruptedException {
                                                                test =extent.createTest("4.2-Update Full name with valid data.");
                                                                WebElement name_Chck = driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[4]/div[1]/div/input"));
                                                                name_Chck.sendKeys("ACDB");
                                                                Thread.sleep(3000);
                                                                String name_samp = name_Chck.getText();
                                                                if(name_samp.contains("ACDB")) {
                                                                                System.out.println("Test-4.2-Update Full name with valid data-Pass");
                                                                }
                                                                else {
                                                                                System.out.println("Test-4.2-Update Full name with valid data-Fail");
                                                                }
                                                
                                                                extent.flush();
                                                }
/*//4.3-Update Full name with invalid data(Special Characters and numbers).
                                                
                                                @Test(priority=6)
                                                public void full_name_updt_invald() throws InterruptedException {
                                                                test =extent.createTest("4.3-Update Full name with invalid data(Special Characters and numbers)");
                                                                
                                                                WebElement name_invld_Chck = driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[4]/div[1]/div/input"));
                                                                                                                            
                                                                name_invld_Chck.clear();
                                                                name_invld_Chck.sendKeys("ABCCCCDDD@#$$#123");
                                                                Thread.sleep(3000);
                                                                
                                                                //String name_samp = name_invld_Chck.getText();
                                                                WebElement invalid_chck =driver.findElement(By.xpath("/html/body/app-root/app-provider-profile/div/div/div/div[3]/form/div[4]/div[2]/p"));
                                                                String namesampe = invalid_chck.getText();
                                                                if(namesampe!=null) {
                                                                                
                                                                                System.out.println("Test-4.3-Update Full name with invalid data(Special Characters and numbers)-Pass");
                                                                }
                                                                else {
                                                                                System.out.println("Test-4.3-Update Full name with invalid data(Special Characters and numbers)-Fail");
                                                                }
                                                
                                                                extent.flush();
                                                }              
                                                */
                                                
/*//4.4-Update Phone number with only numbers.
                                                
                                                @Test(priority=5)
                                                public void phne_num_updt_valid() throws InterruptedException {
                                                                test =extent.createTest("4.4-Update Phone number with only numbers.");
                                                                WebElement name_Chck = driver.findElement(By.xpath(""));
                                                                name_Chck.sendKeys("7845745158");
                                                                String name_samp = name_Chck.getText();
                                                                if(name_samp.contains("7845")) {
                                                                                System.out.println("Test-4.4-Update Phone number with only numbers.-Pass");
                                                                }
                                                                else {
                                                                                System.out.println("Test-4.4-Update Phone number with only numbers.-Fail");
                                                                }
                                                
                                                                extent.flush();
                                                }*/
                                                
                                                
                /*                           //More Option Button
                                                @Test(priority=15)
                                                public void More_Option_Button() {
                                                                test=extent.createTest("Test-2.5-More Option Button");
                                                                
                                                                                WebElement more_ele =driver.findElement(By.xpath("//*[@id=\"navbardrop\"]/img"));
                                                                                Actions act_more = new Actions(driver);
                                                                                act_more.moveToElement(more_ele).build().perform();
                                                                                more_ele.click();
                                                                                System.out.println("Test-2.5-More Option Button-Pass");
                                                                extent.flush();
                                                }
                                                //Logout Button and functionality
                                @Test(priority=16)
                                public void Logout_Button_Check() throws InterruptedException {
                                                test=extent.createTest("Test-2.6-Logout Button and functionality");
                                                Thread.sleep(3000);
                                                                WebElement logout_ele =driver.findElement(By.xpath("/html/body/app-root/app-header/nav/span/ul/li[4]/div/a"));
                                                                Actions logout_more = new Actions(driver);
                                                                logout_more.moveToElement(logout_ele).build().perform();
                                                                logout_ele.click();
                                                                
                                                                boolean logout_chck =driver.findElement(By.xpath("//*[@id=\"openingMessage\"]")).isDisplayed();
                                                                if (logout_chck=true) {
                                                                                System.out.println("Test-2.6-Logout Button and functionality-Pass");
                                                                }
                                                                else {
                                                                                System.out.println("Test-2.6-Logout Button and functionality-Fail");
                                                                }
                                                                
                                                 extent.flush();
                                }*/

/*               @AfterMethod
                   public void flushing() {
                                extent.flush();
                    }*/
                /*           @AfterMethod
                                public void ResultCase(ITestResult result) {
                                                if(result.getStatus() ==ITestResult.FAILURE) {
                                                                test.log(Status.FAIL, "Test case is"+result.getName());
                                                                test.log(Status.FAIL, "Test case is"+result.getThrowable());
                                                }
                                                else if(result.getStatus() ==ITestResult.SKIP){
                                                                test.log(Status.SKIP, "Test case is"+result.getName());
                                                }
                                                else if((result.getStatus() ==ITestResult.SUCCESS)) {
                                                                test.log(Status.PASS, "Test case is"+result.getName());
                                                }
                                                
                                                
                                }*/


                @AfterTest
                public void CloseTest() {
                                //driver.close();
                }
}
