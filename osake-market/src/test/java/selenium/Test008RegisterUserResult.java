package selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import com.google.common.io.Files;

@TestMethodOrder(OrderAnnotation.class)
class Test008RegisterUserResult {

	private WebDriver webDriver;
	
	/**
	 * テストメソッドを実行する前に実行されるメソッド
	 */
	@BeforeEach
	public void createDriver() {
	    System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");

	    ChromeOptions ops = new ChromeOptions();
	    ops.addArguments("--remote-allow-origins=*");

	    // パスワード保存ダイアログを無効化
	    Map<String, Object> prefs = new HashMap<>();
	    prefs.put("credentials_enable_service", false);
	    prefs.put("profile.password_manager_enabled", false);
	    ops.setExperimentalOption("prefs", prefs);

	    webDriver = new ChromeDriver(ops);
	}
	
	@AfterEach
	public void quitDriver() {
		webDriver.close();
	}
	
	// 会員登録フォーム→会員登録確認→会員登録結果の画面に遷移
	private void openURL() {
		webDriver.get("http://localhost:8080/osake-market/register/user");
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// 要素を取得
		final WebElement sei = webDriver.findElement(By.name("sei"));
		final WebElement mei = webDriver.findElement(By.name("mei"));
		final WebElement birthday = webDriver.findElement(By.name("birthday"));
		final WebElement male = webDriver.findElement(By.cssSelector("input[value = '1']"));
		final WebElement postalCode = webDriver.findElement(By.name("postalCode"));
		final WebElement prefecture = webDriver.findElement(By.name("prefecture"));
		final Select select = new Select(prefecture);
		final WebElement address = webDriver.findElement(By.name("address"));
		final WebElement building = webDriver.findElement(By.name("building"));
		final WebElement phoneNumber = webDriver.findElement(By.name("phoneNumber"));
		final WebElement mail = webDriver.findElement(By.name("mail"));
		final WebElement password = webDriver.findElement(By.name("password"));
		final WebElement submit = webDriver.findElement(By.cssSelector("input[value = 'アカウントを作成する']"));
		
		// 空白にする
		clearInput(sei, mei, birthday, postalCode, address, building, phoneNumber, mail, password);
		
		// 全て入力する
		sei.sendKeys("小林");
		mei.sendKeys("三郎");
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		js.executeScript("arguments[0].value='2000-01-01';", birthday);
		male.click();
		postalCode.sendKeys("1234567");
		select.selectByVisibleText("東京都");
		address.sendKeys("町田市1-1-1");
		building.sendKeys("町田ハイツ101");
		phoneNumber.sendKeys("09023456789");
		mail.sendKeys("saburo@gmail.com");
		password.sendKeys("Kobayashi88#Pass");
		
		// 「アカウントを作成する」ボタンを押す
		submit.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// 要素が表示されているか取得
		final WebElement submit2 = webDriver.findElement(By.cssSelector("input[value = '会員登録する']"));
		
		// 「会員登録をする」ボタンをクリック
		submit2.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
	}
	
	// 入力欄を空欄にする
	private void clearInput(WebElement... elements) {
		for (WebElement element : elements) {
			element.clear();
		}
	}
	
	// スクリーンショットを取得
	private void takeScreenShot(String fileName) {
		// スクリーンショット保存パス
		String screenshotPath = "screenshots\\test008_registerUserResult\\";
		
		// スクリーンショット取得
		File file = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		
		// スクリーンショット保存
		try {
			Files.move(file, new File(screenshotPath + fileName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// タイトルの検証
	private void assertTitle(String title) {
		assertEquals(title, webDriver.getTitle());
	}
	
	// URLの検証
	private void assertURL(String url) {
		assertEquals(url, webDriver.getCurrentUrl());
	}
	
	// 要素が表示されているか確認
	private void assertDisplayed(WebElement... elements) {
		for (WebElement element : elements) {
			assertTrue(element.isDisplayed());
		}
	}
	
	@Test
	@DisplayName("No.1 ブラウザでhttp://localhost:8080/osake-market/register/user/doneにアクセス")
	@Order(1)
	void テスト項目No1() {
		// 指定したURLに遷移する
		webDriver.get("http://localhost:8080/osake-market/register/user/done");
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// スクリーンショットを取得
		takeScreenShot("No.01 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("新規会員登録(入力) - Osake Market");
	}
	
	@Test
	@DisplayName("No.2 会員情報を入力して会員登録ボタンを押す")
	@Order(2)
	void テスト項目No2() {
		// 指定したURLに遷移する
		openURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.02 会員情報を入力して会員登録ボタンを押す");
		
		// 会員登録が成功した内容が表示されているか検証
		final WebElement result = webDriver.findElement(By.tagName("h2"));
		assertEquals("会員登録ありがとうございます", result.getText());
		
		final WebElement button = webDriver.findElement(By.cssSelector(".register-user-result a button"));
		assertEquals("ホームに戻る", button.getText());
		
		assertDisplayed(result, button);
	}
	
	@Test
	@DisplayName("No.3 会員登録をして「ホームに戻る」ボタンを押す")
	@Order(3)
	void テスト項目No3() {
		// 指定したURLに遷移する
		webDriver.get("http://localhost:8080/osake-market/register/user");
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// 要素を取得
		final WebElement sei = webDriver.findElement(By.name("sei"));
		final WebElement mei = webDriver.findElement(By.name("mei"));
		final WebElement birthday = webDriver.findElement(By.name("birthday"));
		final WebElement male = webDriver.findElement(By.cssSelector("input[value = '1']"));
		final WebElement postalCode = webDriver.findElement(By.name("postalCode"));
		final WebElement prefecture = webDriver.findElement(By.name("prefecture"));
		final Select select = new Select(prefecture);
		final WebElement address = webDriver.findElement(By.name("address"));
		final WebElement building = webDriver.findElement(By.name("building"));
		final WebElement phoneNumber = webDriver.findElement(By.name("phoneNumber"));
		final WebElement mail = webDriver.findElement(By.name("mail"));
		final WebElement password = webDriver.findElement(By.name("password"));
		final WebElement submit = webDriver.findElement(By.cssSelector("input[value = 'アカウントを作成する']"));
		
		// 空白にする
		clearInput(sei, mei, birthday, postalCode, address, building, phoneNumber, mail, password);
		
		// 全て入力する
		sei.sendKeys("宇佐美");
		mei.sendKeys("京介");
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		js.executeScript("arguments[0].value='2005-01-01';", birthday);
		male.click();
		postalCode.sendKeys("9998888");
		select.selectByVisibleText("沖縄県");
		address.sendKeys("那覇市1-1-1");
		phoneNumber.sendKeys("08045678912");
		mail.sendKeys("kyosuke@gmail.com");
		password.sendKeys("Usami99#Path");
		
		// 「アカウントを作成する」ボタンを押す
		submit.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// 要素が表示されているか取得
		final WebElement submit2 = webDriver.findElement(By.cssSelector("input[value = '会員登録する']"));
		
		// 「会員登録をする」ボタンをクリック
		submit2.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// 「ホームに戻る」ボタンをクリック
		final WebElement button = webDriver.findElement(By.cssSelector(".register-user-result a button"));
		button.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// スクリーンショットを取得
		takeScreenShot("No.03 会員登録をして「ホームに戻る」ボタンを押す");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("Osake Market");
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/");
	}
	
	@Test
	@DisplayName("No.4 DBに登録した会員情報を入力して会員登録ボタンを押す")
	@Order(4)
	void テスト項目No4() {
		// 指定したURLに遷移する
		openURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.04 DBに登録した会員情報を入力して会員登録ボタンを押す");
		
		// 会員登録が成功した内容が表示されているか検証
		final WebElement result = webDriver.findElement(By.tagName("h2"));
		assertEquals("会員登録に失敗しました", result.getText());
		
		final WebElement button = webDriver.findElement(By.cssSelector(".register-user-result a button"));
		assertEquals("新規会員登録", button.getText());
		
		assertDisplayed(result, button);
	}
	
	@Test
	@DisplayName("No.5 「新規会員登録」ボタンをクリック")
	@Order(5)
	void テスト項目No5() {
		// 指定したURLに遷移する
		openURL();
		
		// 「新規会員登録」ボタンをクリック
		final WebElement button = webDriver.findElement(By.cssSelector(".register-user-result a button"));
		button.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// スクリーンショットを取得
		takeScreenShot("No.05 「新規会員登録」ボタンをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("新規会員登録(入力) - Osake Market");
	}
	
	@Test
	@DisplayName("No.6 「新規会員登録」ボタンをクリック")
	@Order(6)
	void テスト項目No6() {
		// 指定したURLに遷移する
		openURL();
		
		// 「新規会員登録」ボタンをクリック
		final WebElement button = webDriver.findElement(By.cssSelector(".register-user-result a button"));
		button.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/register/user");
	}
}
