package selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

@TestMethodOrder(OrderAnnotation.class)
class Test016OrderError {

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
	
	// 注文失敗画面のURLを開く
	private void openOrderFailureURL() {
		webDriver.get("http://localhost:8080/osake-market/order/failure");
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
	}
	
	// ログイン画面のURLを開く
	private void openLoginURL() {
		webDriver.get("http://localhost:8080/osake-market/login");
		
		// ログイン情報を取得し入力する
		final WebElement mail = webDriver.findElement(By.name("mail"));
		final WebElement password = webDriver.findElement(By.name("password"));
		
		mail.clear();
		password.clear();
		
		mail.sendKeys("tomatoakain@gmail.com");
		password.sendKeys("Tanaka11#Pass");
		
		// ログインボタンをクリック
		webDriver.findElement(By.cssSelector("input[value='ログイン']")).click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
	}
	
	// スクリーンショットを取得
	private void takeScreenShot(String fileName) {
		// スクリーンショット保存パス
		String screenshotPath = "screenshots\\test016_orderFailure\\";
		
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
	
	// 要素が表示されているか確認
	private void assertDisplayed(WebElement... elements) {
		for (WebElement element : elements) {
			assertTrue(element.isDisplayed());
		}
	}
	
	@Test
	@DisplayName("No.1 ブラウザでhttp://localhost:8080/osake-market/order/failureにアクセス")
	@Order(1)
	void テスト項目No1() {
		// ログインURLに遷移する
		openLoginURL();
		
		// 注文失敗画面のURLに遷移
		openOrderFailureURL();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/order/failure");
	}
	
	@Test
	@DisplayName("No.2 ブラウザでhttp://localhost:8080/osake-market/order/failureにアクセス")
	@Order(2)
	void テスト項目No2() {
		// ログインURLに遷移する
		openLoginURL();
		
		// 注文失敗画面のURLに遷移
		openOrderFailureURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.02 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("注文失敗 - Osake Market");
	}
	
	@Test
	@DisplayName("No.3 ブラウザでhttp://localhost:8080/osake-market/order/failureにアクセス")
	@Order(3)
	void テスト項目No3() {
		// ログインURLに遷移する
		openLoginURL();
		
		// 注文失敗画面のURLに遷移
		openOrderFailureURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.03 注文確認画面から「注文を確定する」をクリック");
		
		// 注文失敗画面の要素を取得する
		WebElement title = webDriver.findElement(By.tagName("h2"));
		List<WebElement> ps = webDriver.findElements(By.cssSelector("#order-complete p"));
		WebElement cartBtn = webDriver.findElement(By.cssSelector("#order-complete a button"));
		
		// 要素が表示されているか検証
		assertDisplayed(title, ps.get(0), ps.get(1), cartBtn);
	}
	
	@Test
	@DisplayName("No.4 「カートへ戻る」ボタンをクリック")
	@Order(4)
	void テスト項目No4() {
		// ログインURLに遷移する
		openLoginURL();
		
		// 注文失敗画面のURLに遷移
		openOrderFailureURL();
		
		// 「カートへ戻る」ボタンを取得
		WebElement cartBtn = webDriver.findElement(By.cssSelector("#order-complete a button"));
		
		// 「カートへ戻る」ボタンをクリックする
		cartBtn.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/cart");
	}
	
	@Test
	@DisplayName("No.5 「カートへ戻る」ボタンをクリック")
	@Order(5)
	void テスト項目No5() {
		// ログインURLに遷移する
		openLoginURL();
		
		// 注文失敗画面のURLに遷移
		openOrderFailureURL();
		
		// 「カートへ戻る」ボタンを取得
		WebElement cartBtn = webDriver.findElement(By.cssSelector("#order-complete a button"));
		
		// 「カートへ戻る」ボタンをクリックする
		cartBtn.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// スクリーンショットを取得
		takeScreenShot("No.05 「カートへ戻る」ボタンをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("カート - Osake Market");
	}
}
