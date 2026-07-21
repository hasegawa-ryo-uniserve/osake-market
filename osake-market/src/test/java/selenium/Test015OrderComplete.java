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
class Test015OrderComplete {

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
	
	// 注文完了画面のURLを開く
	private void openOrderCompleteURL() {
		webDriver.get("http://localhost:8080/osake-market/order/complete");
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
	}
	
	// 注文情報入力画面のURLを開く
	private void openOrderInputURL() {
		webDriver.get("http://localhost:8080/osake-market/order/input");
		
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
		String screenshotPath = "screenshots\\test015_orderComplete\\";
		
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
	
	// 商品一覧画面上で商品を1つ追加する
	private void addProductOnList() {		
		// 商品一覧画面に遷移
		webDriver.get("http://localhost:8080/osake-market/product/list");
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// 「カートに追加する」ボタンを取得
		WebElement addCartBtn = webDriver.findElement(By.cssSelector(".cart-add-btn.black-btn"));
		
		// 「カートに追加する」ボタンをクリック
		addCartBtn.click();
	}
	
	// 要素が表示されているか確認
	private void assertDisplayed(WebElement... elements) {
		for (WebElement element : elements) {
			assertTrue(element.isDisplayed());
		}
	}
	
	@Test
	@DisplayName("No.1 ブラウザでhttp://localhost:8080/osake-market/order/completeにアクセス")
	@Order(1)
	void テスト項目No1() {
		// ログインURLに遷移する
		openLoginURL();
		
		// 注文完了画面のURLに遷移
		openOrderCompleteURL();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/order/complete");
	}
	
	@Test
	@DisplayName("No.2 ブラウザでhttp://localhost:8080/osake-market/order/completeにアクセス")
	@Order(2)
	void テスト項目No2() {
		// ログインURLに遷移する
		openLoginURL();
		
		// 注文完了画面のURLに遷移
		openOrderCompleteURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.02 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("注文完了 - Osake Market");
	}
	
	@Test
	@DisplayName("No.3 注文確認画面から「注文を確定する」をクリック")
	@Order(3)
	void テスト項目No3() {
		// ログインURLに遷移する
		openLoginURL();
		
		// カートに商品を追加する
		addProductOnList();
		
		// 注文情報入力画面のURLに遷移
		openOrderInputURL();
		
		// 「支払方法」のプルダウンを取得
		WebElement paymentMethod = webDriver.findElement(By.className("paymentMethod"));
		Select select = new Select(paymentMethod);
		select.selectByValue("BANK");
		
		// 「確認画面に進む」ボタンを取得
		WebElement orderInputSubmitBtn = webDriver.findElement(By.className("order-input-submit"));
		
		// 「確認画面に進む」ボタンをクリック
		orderInputSubmitBtn.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// 「注文を確定するボタン」を取得
		WebElement confirmBtn = webDriver.findElement(By.className("confirm-btn"));
		
		// 「注文を確定するボタン」をクリック
		confirmBtn.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// スクリーンショットを取得
		takeScreenShot("No.03 注文確認画面から「注文を確定する」をクリック");
		
		// 注文完了画面の要素を取得する
		WebElement title = webDriver.findElement(By.tagName("h2"));
		WebElement description = webDriver.findElement(By.cssSelector("#order-complete p"));
		WebElement mail = webDriver.findElement(By.className("mail"));
		WebElement homeBtn = webDriver.findElement(By.cssSelector("#order-complete a button"));
		
		// 要素が表示されているか検証
		assertDisplayed(title, description, mail, homeBtn);
		
		// メールアドレスが正しいか検証
		assertEquals("tomatoakain@gmail.com", mail.getText());
	}
	
	@Test
	@DisplayName("No.5 「ホームへ戻る」ボタンをクリック")
	@Order(5)
	void テスト項目No5() {
		// ログインURLに遷移する
		openLoginURL();
		
		// 注文完了画面のURLに遷移
		openOrderCompleteURL();
		
		// 「ホームへ戻る」ボタンを取得
		WebElement homeBtn = webDriver.findElement(By.cssSelector("#order-complete a button"));
		
		// 「ホームへ戻る」ボタンをクリック
		homeBtn.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/");
	}
	
	@Test
	@DisplayName("No.6 「ホームへ戻る」ボタンをクリック")
	@Order(6)
	void テスト項目No6() {
		// ログインURLに遷移する
		openLoginURL();
		
		// 注文完了画面のURLに遷移
		openOrderCompleteURL();
		
		// 「ホームへ戻る」ボタンを取得
		WebElement homeBtn = webDriver.findElement(By.cssSelector("#order-complete a button"));
		
		// 「ホームへ戻る」ボタンをクリック
		homeBtn.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// スクリーンショットを取得
		takeScreenShot("No.06 「ホームへ戻る」ボタンをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("Osake Market");
	}
}
