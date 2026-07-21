package selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
class Test014OrderConfirm {

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
	
	// 注文内容画面のURLを開く
	private void openOrderConfirmURL() {
		webDriver.get("http://localhost:8080/osake-market/order/confirm");
		
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
		String screenshotPath = "screenshots\\test014_orderConfirm\\";
		
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
	
	@Test
	@DisplayName("No.1 カートに商品を追加していない状態でブラウザでhttp://localhost:8080/osake-market/order/confirmにアクセス")
	@Order(1)
	void テスト項目No1() {
		// ログインURLに遷移する
		openLoginURL();
		
		// 注文内容確認画面のURLに遷移
		openOrderConfirmURL();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/cart");
	}
	
	@Test
	@DisplayName("No.2 カートに商品を追加していない状態でブラウザでhttp://localhost:8080/osake-market/order/confirmにアクセス")
	@Order(2)
	void テスト項目No2() {
		// ログインURLに遷移する
		openLoginURL();
		
		// 注文内容確認画面のURLに遷移
		openOrderConfirmURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.02 カートに商品を追加していない状態でブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("カート - Osake Market");
	}
	
	@Test
	@DisplayName("No.3 カートに商品を追加した状態でブラウザでhttp://localhost:8080/osake-market/order/confirmにアクセス")
	@Order(3)
	void テスト項目No3() {
		// ログインURLに遷移する
		openLoginURL();
		
		// カートに商品を追加する
		addProductOnList();
		
		// 注文内容確認画面のURLに遷移
		openOrderConfirmURL();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/order/input");
	}
	
	@Test
	@DisplayName("No.4 カートに商品を追加した状態でブラウザでhttp://localhost:8080/osake-market/order/confirmにアクセス")
	@Order(4)
	void テスト項目No4() {
		// ログインURLに遷移する
		openLoginURL();
		
		// カートに商品を追加する
		addProductOnList();
		
		// 注文内容確認画面のURLに遷移
		openOrderConfirmURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.04 カートに商品を追加した状態でブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("注文情報入力 - Osake Market");
	}
	
	@Test
	@DisplayName("No.9 「注文を確定する」ボタンをクリック")
	@Order(9)
	void テスト項目No9() {
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
		takeScreenShot("No.09 「注文を確定する」ボタンをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("注文完了 - Osake Market");
	}
	
	@Test
	@DisplayName("No.10 「注文を確定する」ボタンをクリック")
	@Order(10)
	void テスト項目No10() {
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
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/order/complete");
	}
}
