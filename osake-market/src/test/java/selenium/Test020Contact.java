package selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

@TestMethodOrder(OrderAnnotation.class)
class Test020Contact {

	private WebDriver webDriver;
	
	/**
	 * テストメソッドを実行する前に実行されるメソッド
	 */
	@BeforeEach
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--remote-allow-origins=*");
		webDriver = new ChromeDriver(ops);
	}
	
	@AfterEach
	public void quitDriver() {
		webDriver.close();
	}
	
	// 問い合わせ画面のURLを開く
	private void openContactURL() {
		webDriver.get("http://localhost:8080/osake-market/contact");
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
	}
	
	// スクリーンショットを取得
	private void takeScreenShot(String fileName) {
		// スクリーンショット保存パス
		String screenshotPath = "screenshots\\test020_contact\\";
		
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
		WebDriverWait wait = new WebDriverWait(webDriver, 5);
		wait.until(driver -> driver.getCurrentUrl().equals(url));

		assertEquals(url, webDriver.getCurrentUrl());
	}
	
	// フォームに入力して「送信」ボタンをクリック
	private void sendForm(String inputName, String inputMail, String inputCategory, 
			String inputContent) {
		// 要素を取得
		WebElement form = webDriver.findElement(By.tagName("form"));
		WebElement name = form.findElement(By.name("name"));
		WebElement mail = form.findElement(By.name("mail"));
		WebElement category = form.findElement(By.name("category"));
		Select select = new Select(category);
		WebElement content = form.findElement(By.name("content"));
		WebElement btn = form.findElement(By.cssSelector("form button"));
		
		// 入力
		name.sendKeys(inputName);
		mail.sendKeys(inputMail);
		select.selectByVisibleText(inputCategory); 
		content.sendKeys(inputContent);
		
		// 「送信」ボタンをクリック
		btn.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
	}
	
	@Test
	@DisplayName("No.1 ブラウザでhttp://localhost:8080/osake-market/contactにアクセス")
	@Order(1)
	void テスト項目No1() {
		// 問い合わせ画面のURLに遷移
		openContactURL();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/contact");
	}
	
	@Test
	@DisplayName("No.2 ブラウザでhttp://localhost:8080/osake-market/contactにアクセス")
	@Order(2)
	void テスト項目No2() {
		// 問い合わせ画面のURLに遷移
		openContactURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.02 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("お問い合わせ - Osake Market");
	}
	
	@Test
	@DisplayName("No.3 ブラウザでhttp://localhost:8080/osake-market/contactにアクセス")
	@Order(3)
	void テスト項目No3() {
		// 問い合わせ画面のURLに遷移
		openContactURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.03 ブラウザでアクセス");
		
		// 要素を取得
		WebElement title = webDriver.findElement(By.className("page-title"));
		WebElement form = webDriver.findElement(By.tagName("form"));
		WebElement name = form.findElement(By.name("name"));
		WebElement mail = form.findElement(By.name("mail"));
		WebElement category = form.findElement(By.name("category"));
		Select select = new Select(category);
		WebElement content = form.findElement(By.name("content"));
		WebElement btn = form.findElement(By.cssSelector("form button"));
		
		// 要素が正しいか検証
		assertEquals("お問い合わせ", title.getText());
		assertEquals("", name.getAttribute("value"));
		assertEquals("", mail.getAttribute("value"));
		assertEquals("選択してください", select.getFirstSelectedOption().getText());
		assertEquals("", content.getAttribute("value"));
		assertTrue(btn.isDisplayed());
	}
	
	@Test
	@DisplayName("No.4 全て空白で「送信」ボタンをクリック")
	@Order(4)
	void テスト項目No4() {
		// 問い合わせ画面のURLに遷移
		openContactURL();
		
		// 全て空白で「送信」ボタンをクリック
		sendForm("", "", "選択してください", "");
		
		// スクリーンショットを取得
		takeScreenShot("No.04 全て空白で「送信」ボタンをクリック");
		
		// 「このフィールドを入力してください」が表示されているか検証
		WebElement name = webDriver.findElement(By.name("name"));
		assertEquals("このフィールドを入力してください。", name.getAttribute("validationMessage"));
	}
	
	@Test
	@DisplayName("No.5 名前のみ空白で「送信」ボタンをクリック")
	@Order(5)
	void テスト項目No5() {
		// 問い合わせ画面のURLに遷移
		openContactURL();
		
		// 名前のみ空白で「送信」ボタンをクリック
		sendForm("", "tomatoakain@gmail.com", "商品について", "ワインの産地はどこが多いですか。");
		
		// スクリーンショットを取得
		takeScreenShot("No.05 名前のみ空白で「送信」ボタンをクリック");
		
		// 「このフィールドを入力してください」が表示されているか検証
		WebElement name = webDriver.findElement(By.name("name"));
		assertEquals("このフィールドを入力してください。", name.getAttribute("validationMessage"));
	}
	
	@Test
	@DisplayName("No.6 メールアドレスのみ空白で「送信」ボタンをクリック")
	@Order(6)
	void テスト項目No6() {
		// 問い合わせ画面のURLに遷移
		openContactURL();
		
		// メールアドレスのみ空白で「送信」ボタンをクリック
		sendForm("山田 太郎", "", "商品について", "ワインの産地はどこが多いですか。");
		
		// スクリーンショットを取得
		takeScreenShot("No.06 メールアドレスのみ空白で「送信」ボタンをクリック");
		
		// 「このフィールドを入力してください」が表示されているか検証
		WebElement mail = webDriver.findElement(By.name("mail"));
		assertEquals("このフィールドを入力してください。", mail.getAttribute("validationMessage"));
	}
	
	@Test
	@DisplayName("No.7 問い合わせ種別を「選択してください」で「送信」ボタンをクリック")
	@Order(7)
	void テスト項目No7() {
		// 問い合わせ画面のURLに遷移
		openContactURL();
		
		// 問い合わせ種別を「選択してください」で「送信」ボタンをクリック
		sendForm("山田 太郎", "tomatoakain@gmail.com", "選択してください", "ワインの産地はどこが多いですか。");
		
		// スクリーンショットを取得
		takeScreenShot("No.07 問い合わせ種別を「選択してください」で「送信」ボタンをクリック");
		
		// 「リスト内の項目を選択してください。」が表示されているか検証
		WebElement category = webDriver.findElement(By.name("category"));
		assertEquals("リスト内の項目を選択してください。", category.getAttribute("validationMessage"));
	}
	
	@Test
	@DisplayName("No.8 お問い合わせ内容のみ空白で「送信」ボタンをクリック")
	@Order(8)
	void テスト項目No8() {
		// 問い合わせ画面のURLに遷移
		openContactURL();
		
		// お問い合わせ内容のみ空白で「送信」ボタンをクリック
		sendForm("山田 太郎", "tomatoakain@gmail.com", "商品について", "");
		
		// スクリーンショットを取得
		takeScreenShot("No.08 お問い合わせ内容のみ空白で「送信」ボタンをクリック");
		
		// 「このフィールドを入力してください。」が表示されているか検証
		WebElement content = webDriver.findElement(By.name("content"));
		assertEquals("このフィールドを入力してください。", content.getAttribute("validationMessage"));
	}
	
	@Test
	@DisplayName("No.9 全て入力して「送信」ボタンをクリック")
	@Order(9)
	void テスト項目No9() {
		// 問い合わせ画面のURLに遷移
		openContactURL();
		
		// 全て入力して「送信」ボタンをクリック
		sendForm("山田 太郎", "tomatoakain@gmail.com", "商品について", "ワインの産地はどこが多いですか。");
		
		// スクリーンショットを取得
		takeScreenShot("No.09 全て入力して「送信」ボタンをクリック");
		
		// 画面上部に「お問い合わせ内容が送信されました」と表示されるか検証
		WebElement msg = webDriver.findElement(By.id("send-success-msg"));
		assertEquals("お問い合わせ内容が送信されました", msg.getText());
		
		// 入力した情報がフォームから消えているか検証
		WebElement form = webDriver.findElement(By.tagName("form"));
		WebElement name = form.findElement(By.name("name"));
		WebElement mail = form.findElement(By.name("mail"));
		WebElement category = form.findElement(By.name("category"));
		Select select = new Select(category);
		WebElement content = form.findElement(By.name("content"));
		
		assertEquals("", name.getAttribute("value"));
		assertEquals("", mail.getAttribute("value"));
		assertEquals("選択してください", select.getFirstSelectedOption().getText());
		assertEquals("", content.getAttribute("value"));
	}
}
