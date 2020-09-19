package maven.cucumber;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Steps {
	
	public static WebDriver driver;		
	
	Properties prop = new Properties();
	Properties env_vars = new Properties();
	
	JSONParser parser;
	JSONObject All_Objects;
	JSONObject Env_Vars;
	JSONObject UsersInfo;
	
	String FName;
	String LName;
	
	@Before
    public void beforeScenario(){
		
			//This will run before all scenarios
			try {
				parser = new JSONParser();
				All_Objects = (JSONObject) parser.parse(new FileReader("./Locators/All_Objects.json"));
				Env_Vars = (JSONObject) parser.parse(new FileReader("./Env_Vars.json"));
				UsersInfo = (JSONObject) parser.parse(new FileReader("./Data/UsersInfo.json"));
			} catch (Exception e) {
				e.printStackTrace();
			}
				
		}	
	
	
	@Given("^I Navigate to \"(.*)\"$")
	public void i_Navigate_to_website(String appURL) throws Throwable {
		
		appURL = (String)Env_Vars.get(appURL);
		String browserType = (String)Env_Vars.get("browserType");
		
		try {
				String os= System.getProperty("os.name").toLowerCase();
				//I am using Chrome version 85 and so have included driver for that only.
				if(browserType.equals("chrome"))
				{
					//Launching google chrome with new profile..
					
					if(os.contains("mac"))
					{
						//Launching google chrome in mac.., I have included driver for Chrome version 85 in the project
						System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver");
						//You can replace chromedriver and specify path here if you are not using chrome version 85
						//System.setProperty("webdriver.chrome.driver", System.getProperty("<path_to_chromedriver>/chromedriver⁩"));

					}
					else
					{
						//Launching google chrome in windows..";
						System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");	
					}
					
					driver = new ChromeDriver();
					
				}
				//We can add support of Firefox and other browsers if required like below
//				else if(browserType.equals("firefox"))
//				{
//						//System.out.println("Launching Firefox browser..");
//						driver = new FirefoxDriver();
//				}
			driver.manage().window().maximize();
			driver.navigate().to(appURL);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				  
		}catch (Exception e) {
			System.out.println("Error....." + e.getStackTrace());
		}
	}


	@When("^I register new user$")
	public void new_user_registration() throws Throwable {
		try {
			
			String FirstName= (String)All_Objects.get("FirstName_TextBox");
			String LastName= (String)All_Objects.get("LastName_TextBox");
			String Password= (String)All_Objects.get("Password_TextBox");
			String Address = (String)All_Objects.get("Address_TextBox");
			String City = (String)All_Objects.get("City_TextBox");
			String State = (String)All_Objects.get("State_Dropdown");
			String Zip = (String)All_Objects.get("Zip_TextBox");
			String Country = (String)All_Objects.get("Country_Dropdown");
			String MobilePhone = (String)All_Objects.get("MobilePhone_TextBox");
			
			FName= (String)UsersInfo.get("FirstName");
			LName= (String)UsersInfo.get("LastName");
						
			driver.findElement(By.cssSelector(FirstName)).sendKeys((String)UsersInfo.get("FirstName"));
			driver.findElement(By.cssSelector(LastName)).sendKeys((String)UsersInfo.get("LastName"));
			driver.findElement(By.cssSelector(Password)).sendKeys((String)UsersInfo.get("Password"));
			driver.findElement(By.cssSelector(Address)).sendKeys((String)UsersInfo.get("Address"));
			driver.findElement(By.cssSelector(City)).sendKeys((String)UsersInfo.get("City"));
			Select s = new Select(driver.findElement(By.cssSelector(State)));
			s.selectByVisibleText((String)UsersInfo.get("State"));
			driver.findElement(By.cssSelector(Zip)).sendKeys(UsersInfo.get("Zip").toString());
			Select c = new Select(driver.findElement(By.cssSelector(Country)));
			c.selectByVisibleText((String)UsersInfo.get("Country"));
			driver.findElement(By.cssSelector(MobilePhone)).sendKeys(UsersInfo.get("MobilePhone").toString());			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@When("^I click on \"(.*)\"$")
	public void i_click_on_objToClick(String objToClick) throws Throwable {
		
		objToClick = (String)All_Objects.get(objToClick);
		driver.findElement(By.cssSelector(objToClick)).click();
	  
	}
	
	@When("^I enter \"(.*)\" text on \"(.*)\"$")
	public void i_enter_userTxt_text_on_editBoxObj(String userTxt,String editBoxObj) throws Throwable {
		    
		editBoxObj = (String)All_Objects.get(editBoxObj);
			  
		if(userTxt.compareTo("newEmailAddress")==0)
		{
			  String newTxt = "";
			  Random rand = new Random();
		      int upperbound = 100000;
		      //generate random values from 0-100000
		      Integer int_random;
		      int_random = rand.nextInt(upperbound); 
		      newTxt = "abcdedfg"+int_random.toString()+"@abcdedfg"+int_random.toString()+".com";
		      driver.findElement(By.cssSelector(editBoxObj)).sendKeys(newTxt);
		}
		else
		{
			driver.findElement(By.cssSelector(editBoxObj)).sendKeys(userTxt);
		}

	}
	
	
	@Then("^I should see \"(.*)\" text in \"(.*)\"$")
	public void i_should_see_the_text(String expTxt, String objForGetTxt) throws Throwable {
		
		objForGetTxt = (String)All_Objects.get(objForGetTxt);
		
		//if expTxt is name then we should replace expTxt with Name of User Created (Priyanka Gupta in this case)
		if(expTxt.compareTo("name")==0)
		{
			expTxt = FName + " " + LName;
		}
		Assert.assertEquals(driver.findElement(By.cssSelector(objForGetTxt)).getText(),expTxt);
	}
	
	
	@Then("^I should navigate to Page having title \"(.*)\"$")
	public void i_should_navigate_to(String expTitle) throws Throwable {
		Assert.assertEquals(driver.getTitle(),expTitle);
	}


	@When("^I select (.*) from \"(.*)\"$")
	public void i_select_London_from(String value, String objDropDown) throws Throwable {
		objDropDown= (String)All_Objects.get(objDropDown);
		Select sel = new Select(driver.findElement(By.cssSelector(objDropDown)));
		sel.selectByVisibleText(value);
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}

}
