package selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
class Test007RegisterUserConfirm {

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
	
	// 会員登録フォーム→会員登録確認の画面に遷移
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
		final WebElement female = webDriver.findElement(By.cssSelector("input[value = '2']"));
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
		sei.sendKeys("渡辺");
		mei.sendKeys("花子");
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		js.executeScript("arguments[0].value='2000-01-01';", birthday);
		female.click();
		postalCode.sendKeys("1234567");
		select.selectByVisibleText("東京都");
		address.sendKeys("八王子市1-1-1");
		building.sendKeys("八王子ハイツ102");
		phoneNumber.sendKeys("09012345678");
		mail.sendKeys("hanako@gmail.com");
		password.sendKeys("Watanabe77#Pass");
		
		// 「アカウントを作成する」ボタンを押す
		submit.click();
		
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
		String screenshotPath = "screenshots\\test007_registerUserConfirm\\";
		
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
	@DisplayName("No.1 ブラウザでhttp://localhost:8080/osake-market/register/user/confirmにアクセス")
	@Order(1)
	void テスト項目No1() {
		// 指定したURLに遷移する
		webDriver.get("http://localhost:8080/osake-market/register/user/confirm");
		
		// スクリーンショットを取得
		takeScreenShot("No.01 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("新規会員登録(入力) - Osake Market");
	}
	
	@Test
	@DisplayName("No.2 会員情報登録画面が表示されている")
	@Order(2)
	void テスト項目No2() {
		// 指定したURLに遷移する
		openURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.02 会員情報登録画面が表示されている");
		
		// 要素が表示されているか取得
		List<WebElement> thList = webDriver.findElements(By.tagName("th"));
		final WebElement submit = webDriver.findElement(By.cssSelector("input[value = '会員登録する']"));
		final WebElement back = webDriver.findElement(By.cssSelector("input[value = '戻る']"));
		
		// 要素が表示されているか確認
		for (WebElement th : thList) {
			assertTrue(th.isDisplayed());
		}
		assertDisplayed(submit, back);
	}
	
	@Test
	@DisplayName("No.3 会員情報登録画面が表示されている")
	@Order(3)
	void テスト項目No3() {
		// 指定したURLに遷移する
		openURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.03 会員情報登録画面が表示されている");
		
		// 要素が表示されているか取得
		List<WebElement> tdList = webDriver.findElements(By.tagName("td"));
		
		// 入力した内容と等しいか確認
		assertEquals("渡辺", tdList.get(0).getText());
		assertEquals("花子", tdList.get(1).getText());
		assertEquals("2000/01/01", tdList.get(2).getText());
		assertEquals("女性", tdList.get(3).getText());
		assertEquals("1234567", tdList.get(4).getText());
		assertEquals("東京都", tdList.get(5).getText());
		assertEquals("八王子市1-1-1", tdList.get(6).getText());
		assertEquals("八王子ハイツ102", tdList.get(7).getText());
		assertEquals("09012345678", tdList.get(8).getText());
		assertEquals("hanako@gmail.com", tdList.get(9).getText());
		assertEquals("***************", tdList.get(10).getText());
	}
	
	@Test
	@DisplayName("No.4 「戻る」ボタンをクリック")
	@Order(4)
	void テスト項目No4() {
		// 指定したURLに遷移する
		openURL();
		
		// 要素が表示されているか取得
		final WebElement back = webDriver.findElement(By.cssSelector("input[value = '戻る']"));
		
		// 「戻る」ボタンをクリック
		back.click();
		
		// スクリーンショットを取得
		takeScreenShot("No.04 「戻る」ボタンをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("新規会員登録(入力) - Osake Market");
	}
	
	@Test
	@DisplayName("No.5 「戻る」ボタンをクリック")
	@Order(5)
	void テスト項目No5() {
		// 指定したURLに遷移する
		openURL();
		
		// 要素が表示されているか取得
		final WebElement back = webDriver.findElement(By.cssSelector("input[value = '戻る']"));
		
		// 「戻る」ボタンをクリック
		back.click();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/register/user");
	}
	
	@Test
	@DisplayName("No.6 「戻る」ボタンをクリック")
	@Order(6)
	void テスト項目No6() {
		// 指定したURLに遷移する
		openURL();
		
		// 要素が表示されているか取得
		final WebElement back = webDriver.findElement(By.cssSelector("input[value = '戻る']"));
		
		// 「戻る」ボタンをクリック
		back.click();
		
		// 要素が表示されているか取得
		final WebElement sei = webDriver.findElement(By.name("sei"));
		final WebElement mei = webDriver.findElement(By.name("mei"));
		final WebElement birthday = webDriver.findElement(By.name("birthday"));
		final WebElement postalCode = webDriver.findElement(By.name("postalCode"));
		final WebElement address = webDriver.findElement(By.name("address"));
		final WebElement building = webDriver.findElement(By.name("building"));
		final WebElement phoneNumber = webDriver.findElement(By.name("phoneNumber"));
		final WebElement mail = webDriver.findElement(By.name("mail"));
		final WebElement password = webDriver.findElement(By.name("password"));
		
		List<WebElement> genders = webDriver.findElements(By.name("gender"));
		final WebElement female = genders.get(1);
		
		final WebElement prefecture = webDriver.findElement(By.name("prefecture"));
		final Select select = new Select(prefecture);
		
		// 入力欄が入力した内容か検証
		assertEquals("渡辺", sei.getAttribute("value"));
		assertEquals("花子", mei.getAttribute("value"));
		assertEquals("2000-01-01", birthday.getAttribute("value"));
		assertEquals("1234567", postalCode.getAttribute("value"));
		assertEquals("八王子市1-1-1", address.getAttribute("value"));
		assertEquals("八王子ハイツ102", building.getAttribute("value"));
		assertEquals("09012345678", phoneNumber.getAttribute("value"));
		assertEquals("hanako@gmail.com", mail.getAttribute("value"));
		assertEquals("", password.getAttribute("value"));
		
		// 性別は女性が選択されているか検証
		assertTrue(female.isSelected());
		
		// 都道府県は東京都が選択されているか検証
		assertEquals("東京都", select.getFirstSelectedOption().getText());
		
		// スクリーンショットを取得
		takeScreenShot("No.06 「戻る」ボタンをクリック");
	}
	
	@Test
	@DisplayName("No.7 「会員登録をする」ボタンをクリック")
	@Order(7)
	void テスト項目No7() {
		// 指定したURLに遷移する
		openURL();
		
		// 要素が表示されているか取得
		final WebElement submit = webDriver.findElement(By.cssSelector("input[value = '会員登録する']"));
		
		// 「会員登録をする」ボタンをクリック
		submit.click();
		
		// スクリーンショットを取得
		takeScreenShot("No.07 「会員登録をする」ボタンをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("新規会員登録(結果) - Osake Market");
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/register/user/complete");
	}
	
	@Test
	@DisplayName("No.8 DBに登録した状態で同じ会員情報を登録")
	@Order(8)
	void テスト項目No8() {
		// 指定したURLに遷移する
		openURL();
		
		// 要素が表示されているか取得
		final WebElement submit = webDriver.findElement(By.cssSelector("input[value = '会員登録する']"));
		
		// 「会員登録をする」ボタンをクリック
		submit.click();
		
		// スクリーンショットを取得
		takeScreenShot("No.08 DBに登録した状態で同じ会員情報を登録");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("新規会員登録(結果) - Osake Market");
		
		// 会員登録が失敗した内容が表示されているか検証
		final WebElement result = webDriver.findElement(By.tagName("h2"));
		assertEquals("会員登録に失敗しました", result.getText());
	}
	
	@Test
	@DisplayName("No.9 DBに登録した状態で同じ会員情報を登録")
	@Order(9)
	void テスト項目No9() {
		// 指定したURLに遷移する
		openURL();
		
		// 要素が表示されているか取得
		final WebElement submit = webDriver.findElement(By.cssSelector("input[value = '会員登録する']"));
		
		// 「会員登録をする」ボタンをクリック
		submit.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/register/user/complete");
	}
}
