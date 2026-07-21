package selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
class Test018NotificationList {

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
	
	// 通知一覧画面のURLを開く
	private void openNotificationListURL() {
		webDriver.get("http://localhost:8080/osake-market/notification/list");
		
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
		String screenshotPath = "screenshots\\test018_notificationList\\";
		
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
	
	@Test
	@DisplayName("No.1 ブラウザでhttp://localhost:8080/osake-market/notification/listにアクセス")
	@Order(1)
	void テスト項目No1() {
		// ログインURLに遷移する
		openLoginURL();
		
		// 通知一覧画面のURLに遷移
		openNotificationListURL();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/notification/list");
	}
	
	@Test
	@DisplayName("No.2 ブラウザでhttp://localhost:8080/osake-market/notification/listにアクセス")
	@Order(2)
	void テスト項目No2() {
		// ログインURLに遷移する
		openLoginURL();
		
		// 通知一覧画面のURLに遷移
		openNotificationListURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.02 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("通知一覧 - Osake Market");
	}
	
	@Test
	@DisplayName("No.3 ブラウザでhttp://localhost:8080/osake-market/notification/listにアクセス")
	@Order(3)
	void テスト項目No3() {
		// ログインURLに遷移する
		openLoginURL();
		
		// 通知一覧画面のURLに遷移
		openNotificationListURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.03 ブラウザでアクセス");
		
		// 通知一覧を取得
		List<WebElement> notifications = webDriver.findElements(By.className("notification-wrapper"));
		
		// 合計10個あるか検証
		assertEquals(10, notifications.size());
		
		// 日付が新しい順か検証
		List<WebElement> dates = webDriver.findElements(By.className("notification-list-date"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		
		for (int i = 0; i < dates.size() - 1; i++) {
			LocalDateTime current = LocalDateTime.parse(dates.get(i).getText(), formatter);
			LocalDateTime next = LocalDateTime.parse(dates.get(i + 1).getText(), formatter);
			
			assertTrue(current.isAfter(next) || current.isEqual(next));
		}
		
		// 画面下部の「1」が太字になっており、「2」がリンクになっているか検証
		WebElement pagination = webDriver.findElement(By.className("pagination"));
		
		WebElement currentPage = pagination.findElement(By.tagName("strong"));
		assertEquals("1", currentPage.getText());
		
		WebElement nextPage = pagination.findElement(By.tagName("a"));
		assertEquals("2", nextPage.getText());
	}
	
	@Test
	@DisplayName("No.4 画面下部の「2」のリンクをクリック")
	@Order(4)
	void テスト項目No4() {
		// ログインURLに遷移する
		openLoginURL();
		
		// 通知一覧画面のURLに遷移
		openNotificationListURL();
		
		// 画面下部の「2」をクリック
		WebElement pagination = webDriver.findElement(By.className("pagination"));
		WebElement nextPage = pagination.findElement(By.tagName("a"));
		nextPage.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// スクリーンショットを取得
		takeScreenShot("No.04 画面下部の「2」のリンクをクリック");
		
		// 通知一覧を取得
		List<WebElement> notifications = webDriver.findElements(By.className("notification-wrapper"));
		
		// 合計1個あるか検証
		assertEquals(1, notifications.size());
		
		// 画面下部の「1」がリンクになっており、「2」が太字になっているか検証
		pagination = webDriver.findElement(By.className("pagination"));
		
		WebElement beforePage = pagination.findElement(By.tagName("a"));
		assertEquals("1", beforePage.getText());
		
		WebElement currentPage = pagination.findElement(By.tagName("strong"));
		assertEquals("2", currentPage.getText());
	}
	
	@Test
	@DisplayName("No.5 画面下部の「2」のリンクをクリック")
	@Order(5)
	void テスト項目No5() {
		// ログインURLに遷移する
		openLoginURL();
		
		// 通知一覧画面のURLに遷移
		openNotificationListURL();
		
		// 画面下部の「2」をクリック
		WebElement pagination = webDriver.findElement(By.className("pagination"));
		WebElement nextPage = pagination.findElement(By.tagName("a"));
		nextPage.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/notification/list?page=2");
	}
	
	@Test
	@DisplayName("No.6 画面下部の「1」のリンクをクリック")
	@Order(6)
	void テスト項目No6() {
		// ログインURLに遷移する
		openLoginURL();
		
		// 通知一覧画面のURLに遷移
		openNotificationListURL();
		
		// 画面下部の「2」をクリック
		WebElement pagination = webDriver.findElement(By.className("pagination"));
		WebElement nextPage = pagination.findElement(By.tagName("a"));
		nextPage.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// 画面下部の「1」をクリック
		pagination = webDriver.findElement(By.className("pagination"));
		WebElement beforePage = pagination.findElement(By.tagName("a"));
		beforePage.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// スクリーンショットを取得
		takeScreenShot("No.06 画面下部の「1」のリンクをクリック");
		
		// 通知一覧を取得
		List<WebElement> notifications = webDriver.findElements(By.className("notification-wrapper"));
		
		// 合計10個あるか検証
		assertEquals(10, notifications.size());
		
		// 画面下部の「1」が太字になっており、「2」がリンクになっているか検証
		pagination = webDriver.findElement(By.className("pagination"));
		
		WebElement currentPage = pagination.findElement(By.tagName("strong"));
		assertEquals("1", currentPage.getText());
		
		nextPage = pagination.findElement(By.tagName("a"));
		assertEquals("2", nextPage.getText());
	}
	
	@Test
	@DisplayName("No.7 画面下部の「1」のリンクをクリック")
	@Order(7)
	void テスト項目No7() {
		// ログインURLに遷移する
		openLoginURL();
		
		// 通知一覧画面のURLに遷移
		openNotificationListURL();
		
		// 画面下部の「2」をクリック
		WebElement pagination = webDriver.findElement(By.className("pagination"));
		WebElement nextPage = pagination.findElement(By.tagName("a"));
		nextPage.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// 画面下部の「1」をクリック
		pagination = webDriver.findElement(By.className("pagination"));
		WebElement beforePage = pagination.findElement(By.tagName("a"));
		beforePage.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/notification/list?page=1");
	}
	
	@Test
	@DisplayName("No.8 任意の通知のタイトルのリンクをクリック")
	@Order(8)
	void テスト項目No8() {
		// ログインURLに遷移する
		openLoginURL();
		
		// 通知一覧画面のURLに遷移
		openNotificationListURL();
		
		// 通知一覧を取得
		List<WebElement> notifications = webDriver.findElements(By.className("notification-wrapper"));
		
		// 最初の通知のリンクをクリックする
		WebElement first = notifications.get(0).findElement(By.tagName("a"));
		first.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// スクリーンショットを取得
		takeScreenShot("No.08 任意の通知のタイトルのリンクをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("通知詳細 - Osake Market");
	}
	
	@Test
	@DisplayName("No.9 任意の通知のタイトルのリンクをクリック")
	@Order(9)
	void テスト項目No9() {
		// ログインURLに遷移する
		openLoginURL();
		
		// 通知一覧画面のURLに遷移
		openNotificationListURL();
		
		// 通知一覧を取得
		List<WebElement> notifications = webDriver.findElements(By.className("notification-wrapper"));
		
		// 最初の通知のリンクをクリックする
		WebElement first = notifications.get(0).findElement(By.tagName("a"));
		first.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/notification/detail?notificationId=11");
	}
}
