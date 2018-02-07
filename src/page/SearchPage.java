package page;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class SearchPage {
	
	public WebDriver driver;

	private List<WebElement> elemList;
	private List<WebElement> elemList2;
	private String resultLinkCount;
	private String resultLinkCountAfterNarrow;
	
	private By query;
	private By searchButton;
	private By zeroResults;
	private By resultCount;
	private By searchResults;
	private By loadMoreButton;
	private By narrowLink;
	private By closeNarrow;
	private By productName;
	
	public SearchPage() {
		query = By.cssSelector(".search");
		searchButton = By.cssSelector(".btn-search");
		zeroResults = By.cssSelector(".zero-results");
		resultCount = By.cssSelector(".bold-dark.p3-bold");
		searchResults = By.cssSelector(".invisible-link");
		loadMoreButton = By.cssSelector(".search-more");
		narrowLink = By.cssSelector("div.result > a.primary-link");
		closeNarrow = By.cssSelector(".label-close");
		productName = By.cssSelector("div.results-p > h3.lt4");
	    }
	

	@Given("^User open search page$")
	public void openQueryPage() {
		driver = new SafariDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.sony.com/search?query=");
	}
	
	@When("^User write to \"(.*)\" search field$")
	public void writeTextToSearchField(String text){
		driver.findElement(query).sendKeys(text);
	}
	
	@When("^Clear Search Field$")
	public void clearSearchField(){
		driver.findElement(query).clear();
	}
	
	@When ("^User tab to search button$")
	public void tabSearchButton(){
		driver.findElement(searchButton).click();
	}
	
	@When ("^User tab to enter button$")
	public void tabEnterButton(){
		driver.findElement(searchButton).sendKeys(Keys.RETURN);
	}
	
	@When ("^Check zero result message with \"(.*)\"$")
	public void getZeroResults(String expected){
		String actual = driver.findElement(zeroResults).getText();
		Assert.assertEquals("Your search for \"" + expected + "\" returned 0 results", actual);
	}
	
	@When("^Get result count$")
	public void getResultCount(){		
		resultLinkCount = driver.findElement(resultCount).getText();	
	}
	
	@When("^Check Result Count$")
	public void checkResultCount(){		
		String count = driver.findElement(resultCount).getText();
		Assert.assertEquals(resultLinkCount, count);	
	}
	
	@When("^Check result count after narrow$")
	public void checkResultCountAfterNarrow(){		
		String count = driver.findElement(resultCount).getText();
		Assert.assertEquals(resultLinkCountAfterNarrow, count);	
	}
	
	@When("^Get result link after search$")
	public void getResultLinks(){
		elemList = driver.findElements(searchResults);	
	}
	
	@When("^Get result link after click Load More button$")
	public void getResultLinksAfterLoadMore(){
		elemList2 = driver.findElements(searchResults);	
	}
	
	@When("^Click Load More button$")
	public void clickMoreButton(){
		driver.findElement(loadMoreButton).click();
	}
	
	@When("^Check response links are clickable$")
	public void isClickable() {

		if (elemList.isEmpty()) {
			Assert.assertTrue(false);
		}
		try {
			for (WebElement el : elemList) {
				ExpectedConditions.elementToBeClickable(el);
			}
			Assert.assertTrue(true);
		} catch (Exception e) {
			Assert.assertTrue(false);
		}
	}
	
	@When("^Click first narrow link set result count$")
	public void getFirstNarrowLinksAndClick(){
		List<WebElement> elemList = driver.findElements(narrowLink);	
		// can not click with WebElement click method
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", elemList.get(0));

		resultLinkCountAfterNarrow = elemList.get(0).getText().replaceAll("[^\\d.]", "");
	}
	
	@When("^Click x button near narrow link$")
	public void closeNarrow(){
		driver.findElement(closeNarrow).click();
	}
	
	@When("^Check current Url is \"(.*)\"$")
	public void checkCurrentUrl(String searched){	
		String currentUrl = driver.getCurrentUrl();
		String expected = "https://www.sony.com/search?query=" + searched;
		Assert.assertEquals(expected, currentUrl);	
	}
	
	@When("^Check results size is greater after click Load More button$")
	public void checkElemListSize(){	
		Assert.assertTrue(elemList.size() < elemList2.size());
	}
	
	@When("^Check product name is seen at result \"(.*)\"$")
	public void checkProductName(String text){
		String headerText = driver.findElements(productName).get(0).getText();		
		Assert.assertTrue(headerText.contains(text));	
	}
	
	@Then ("^Stop test$")
	public void stopDriver(){
		driver.quit();
	}
	
	@When("^Wait \"(.*)\" milisec$")
	public void waitTime(long milSec){
		try {
			Thread.sleep(milSec);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}