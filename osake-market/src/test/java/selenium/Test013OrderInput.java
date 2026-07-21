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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

@TestMethodOrder(OrderAnnotation.class)
class Test013OrderInput {

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
		String screenshotPath = "screenshots\\test013_orderInput\\";
		
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
	@DisplayName("No.1 カートに商品を追加していない状態でブラウザでhttp://localhost:8080/osake-market/order/inputにアクセス")
	@Order(1)
	void テスト項目No1() {
		// 指定したURLに遷移する
		openLoginURL();
		openOrderInputURL();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/cart");
	}
	
	@Test
	@DisplayName("No.2 カートに商品を追加していない状態でブラウザでhttp://localhost:8080/osake-market/order/inputにアクセス")
	@Order(2)
	void テスト項目No2() {
		// 指定したURLに遷移する
		openLoginURL();
		openOrderInputURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.02 カートに商品を追加していない状態でブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("カート - Osake Market");
	}
	
	@Test
	@DisplayName("No.3 カートに商品を追加した状態でブラウザでhttp://localhost:8080/osake-market/order/inputにアクセス")
	@Order(3)
	void テスト項目No3() {
		// ログインする
		openLoginURL();
		
		// 商品を追加する
		addProductOnList();
		
		// 注文情報入力画面に行く
		openOrderInputURL();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/order/input");
	}
	
	@Test
	@DisplayName("No.4 カートに商品を追加した状態でブラウザでhttp://localhost:8080/osake-market/order/inputにアクセス")
	@Order(4)
	void テスト項目No4() {
		// ログインする
		openLoginURL();
		
		// 商品を追加する
		addProductOnList();
		
		// 注文情報入力画面に行く
		openOrderInputURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.04 カートに商品を追加した状態でブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("注文情報入力 - Osake Market");
		
		// 「支払方法」のプルダウンを取得
		WebElement paymentMethod = webDriver.findElement(By.className("paymentMethod"));
		Select select = new Select(paymentMethod);
		
		// 「支払方法」のプルダウンが「選択してください」と表示されているか検証
		assertEquals("選択してください", select.getFirstSelectedOption().getText());
		
		// 注文内容を取得
		List<WebElement> orderItems = webDriver.findElements(By.id("order-item"));
		WebElement orderItem = orderItems.get(0);
		
		// 注文内容を検証
		assertEquals("赤ワイン（フルボディ） ×  1  ＝  ￥10,000", orderItem.getText());
	}
	
	@Test
	@DisplayName("No.5 支払方法を選択せずに「確認画面に進む」ボタンをクリック")
	@Order(5)
	void テスト項目No5() {
		// ログインする
		openLoginURL();
		
		// 商品を追加する
		addProductOnList();
		
		// 注文情報入力画面に行く
		openOrderInputURL();
		
		// 「確認画面に進む」ボタンを取得
		WebElement orderInputSubmitBtn = webDriver.findElement(By.className("order-input-submit"));
		
		// 「確認画面に進む」ボタンをクリック
		orderInputSubmitBtn.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// スクリーンショットを取得
		takeScreenShot("No.05 支払方法を選択せずに「確認画面に進む」ボタンをクリック");
		
		// 「このフィールドを入力して下さい」と表示されるか検証
		WebElement paymentMethod = webDriver.findElement(By.className("paymentMethod"));
		assertEquals("リスト内の項目を選択してください。", paymentMethod.getAttribute("validationMessage"));
	}
	
	@Test
	@DisplayName("No.6 支払方法のプルダウンで「クレジットカード」を選択する")
	@Order(6)
	void テスト項目No6() {
		// ログインする
		openLoginURL();
		
		// 商品を追加する
		addProductOnList();
		
		// 注文情報入力画面に行く
		openOrderInputURL();
		
		// 「支払方法」のプルダウンを取得
		WebElement paymentMethod = webDriver.findElement(By.className("paymentMethod"));
		Select select = new Select(paymentMethod);
		select.selectByValue("CREDIT_CARD");
		
		// スクリーンショットを取得
		takeScreenShot("No.06 支払方法のプルダウンで「クレジットカード」を選択する");
		
		// 「カード情報を入力する」ボタンが表示されるか検証
		WebElement cardInfoBtn = webDriver.findElement(By.id("creditCardArea"));
		assertTrue(cardInfoBtn.isDisplayed());		
	}
	
	@Test
	@DisplayName("No.7 支払方法のプルダウンで「クレジットカード」を選択し、カード情報を入力せずに\n"
			+ "「注文を確定する」ボタンをクリック")
	@Order(7)
	void テスト項目No7() {
		// ログインする
		openLoginURL();
		
		// 商品を追加する
		addProductOnList();
		
		// 注文情報入力画面に行く
		openOrderInputURL();
		
		// 「支払方法」のプルダウンを取得
		WebElement paymentMethod = webDriver.findElement(By.className("paymentMethod"));
		Select select = new Select(paymentMethod);
		select.selectByValue("CREDIT_CARD");
		
		// スクリーンショットを取得
		takeScreenShot("No.07 支払方法のプルダウンで「クレジットカード」を選択し、カード情報を入力せずに\n"
				+ "「注文を確定する」ボタンをクリック");
		
		// 「確認画面に進む」ボタンを取得
		WebElement orderInputSubmitBtn = webDriver.findElement(By.className("order-input-submit"));
		
		// 「確認画面に進む」ボタンをクリック
		orderInputSubmitBtn.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// 「クレジットカード情報を入力してください」と画面上部にメッセージが表示されるか検証
		WebElement errorMsg = webDriver.findElement(By.id("error-msg"));
		assertEquals("クレジットカード情報を入力してください", errorMsg.getText());
	}
	
	@Test
	@DisplayName("No.12 支払方法のプルダウンで「銀行振り込み」を選択する")
	@Order(12)
	void テスト項目No12() {
		// ログインする
		openLoginURL();
		
		// 商品を追加する
		addProductOnList();
		
		// 注文情報入力画面に行く
		openOrderInputURL();
		
		// 「支払方法」のプルダウンを取得
		WebElement paymentMethod = webDriver.findElement(By.className("paymentMethod"));
		Select select = new Select(paymentMethod);
		select.selectByValue("BANK");
		
		// スクリーンショットを取得
		takeScreenShot("No.12 支払方法のプルダウンで「銀行振り込み」を選択する");
		
		// 「振込先口座 三菱UFJ銀行 渋谷支店 普通 1234567」が表示されるか検証
		WebElement bankArea = webDriver.findElement(By.id("bankArea"));
		assertTrue(bankArea.isDisplayed());
	}
	
	@Test
	@DisplayName("No.13 支払方法が選択された状態で、「確認画面に進む」ボタンをクリック")
	@Order(13)
	void テスト項目No13() {
		// ログインする
		openLoginURL();
		
		// 商品を追加する
		addProductOnList();
		
		// 注文情報入力画面に行く
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
		
		// スクリーンショットを取得
		takeScreenShot("No.13 支払方法が選択された状態で、「確認画面に進む」ボタンをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("注文内容確認 - Osake Market");
	}
	
	@Test
	@DisplayName("No.14 支払方法が選択された状態で、「確認画面に進む」ボタンをクリック")
	@Order(14)
	void テスト項目No14() {
		// ログインする
		openLoginURL();
		
		// 商品を追加する
		addProductOnList();
		
		// 注文情報入力画面に行く
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
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/order/confirm");
	}
	
	@Test
	@DisplayName("No.15 「カートに戻る」ボタンをクリック")
	@Order(15)
	void テスト項目No15() {
		// ログインする
		openLoginURL();
		
		// 商品を追加する
		addProductOnList();
		
		// 注文情報入力画面に行く
		openOrderInputURL();
		
		// 「カートに戻る」ボタンを取得
		WebElement returnCartBtn = webDriver.findElement(By.className("order-input-return"));
		
		// 「カートに戻る」ボタンをクリック
		returnCartBtn.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// スクリーンショットを取得
		takeScreenShot("No.15 「カートに戻る」ボタンをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("カート - Osake Market");
	}
	
	@Test
	@DisplayName("No.16 「カートに戻る」ボタンをクリック")
	@Order(16)
	void テスト項目No16() {
		// ログインする
		openLoginURL();
		
		// 商品を追加する
		addProductOnList();
		
		// 注文情報入力画面に行く
		openOrderInputURL();
		
		// 「カートに戻る」ボタンを取得
		WebElement returnCartBtn = webDriver.findElement(By.className("order-input-return"));
		
		// 「カートに戻る」ボタンをクリック
		returnCartBtn.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/cart");
	}
}
