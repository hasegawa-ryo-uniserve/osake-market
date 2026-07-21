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
import org.openqa.selenium.Alert;
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
class Test024OrderDetail {
	
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
	
	// 注文履歴詳細画面のURLを開く
	private void openOrderDetailURL() {
		webDriver.get("http://localhost:8080/osake-market/order/detail?orderId=1");
		
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
		String screenshotPath = "screenshots\\test024_orderDetail\\";
		
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
	@DisplayName("No.1 ブラウザでhttp://localhost:8080/osake-market/order/detail?orderId=1にアクセス")
	@Order(1)
	void テスト項目No1() {
		// ログイン画面のURLを開く
		openLoginURL();
		
		// 注文履歴詳細画面のURLを開く
		openOrderDetailURL();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/order/detail?orderId=1");
	}
	
	@Test
	@DisplayName("No.2 ブラウザでhttp://localhost:8080/osake-market/order/detail?orderId=1にアクセス")
	@Order(2)
	void テスト項目No2() {
		// ログイン画面のURLを開く
		openLoginURL();
		
		// 注文履歴詳細画面のURLを開く
		openOrderDetailURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.02 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("注文履歴詳細 - Osake Market");
	}

	@Test
	@DisplayName("No.3 一番日付が古い注文履歴にアクセス")
	@Order(3)
	void テスト項目No3() {
		// ログインURLに遷移する
		openLoginURL();
		
		// 注文履歴詳細画面のURLを開く
		openOrderDetailURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.03 一番日付が古い注文履歴にアクセス");
		
		/*
		 * 商品画像、ご注文日時、商品名、商品番号、商品代金、
		 * 個数、支払方法、注文ステータス、
		 * 「戻る」ボタン、「注文を取り消す」ボタンを取得
		 */
		List<WebElement> orderDetails = webDriver.findElements(By.className("order-detail-wrapper"));
		WebElement first = orderDetails.get(0);
		WebElement second = orderDetails.get(1);
		
		WebElement sparklingImg = first.findElement(By.className("product-detail-image"));
		List<WebElement> sparklingElements = first.findElements(By.cssSelector(".order-detail-wrapper p span"));
		WebElement sparklingProductName = sparklingElements.get(1);
		WebElement sparklingProductId = sparklingElements.get(2);
		WebElement sparklingPrice = sparklingElements.get(3);
		WebElement sparklingQuantity = sparklingElements.get(4);
		WebElement sparklingPaymentMethod = sparklingElements.get(5);
		WebElement sparklingStatus = sparklingElements.get(6);
		
		WebElement jerkyProductImg = second.findElement(By.className("product-detail-image"));
		List<WebElement> jerkyElements = second.findElements(By.cssSelector(".order-detail-wrapper p span"));
		WebElement jerkyProductName = jerkyElements.get(1);
		WebElement jerkyProductId = jerkyElements.get(2);
		WebElement jerkyPrice = jerkyElements.get(3);
		WebElement jerkyQuantity = jerkyElements.get(4);
		WebElement jerkyPaymentMethod = jerkyElements.get(5);
		WebElement jerkyStatus = jerkyElements.get(6);
		
		final WebElement backBtn = webDriver.findElement(By.className("return"));
		final WebElement cancelBtn = webDriver.findElement(By.className("order-cancel"));
		
		// 商品詳細を検証
		assertTrue(sparklingImg.isDisplayed());
		assertEquals("商品名：シュワシュワワイン", sparklingProductName.getText());
		assertEquals("商品番号：2", sparklingProductId.getText());
		assertEquals("商品代金：￥2,000", sparklingPrice.getText());
		assertEquals("個数：2", sparklingQuantity.getText());
		assertEquals("支払方法： クレジットカード", sparklingPaymentMethod.getText());
		assertEquals("注文ステータス： 決済完了済み", sparklingStatus.getText());
		
		assertTrue(jerkyProductImg.isDisplayed());
		assertEquals("商品名：ビーフジャーキー", jerkyProductName.getText());
		assertEquals("商品番号：9", jerkyProductId.getText());
		assertEquals("商品代金：￥500", jerkyPrice.getText());
		assertEquals("個数：1", jerkyQuantity.getText());
		assertEquals("支払方法： クレジットカード", jerkyPaymentMethod.getText());
		assertEquals("注文ステータス： 決済完了済み", jerkyStatus.getText());
		
		// 「戻る」ボタン、「注文を取り消す」ボタンが表示されているか検証
		assertDisplayed(backBtn, cancelBtn);
	}
	
	@Test
	@DisplayName("No.4 「戻る」ボタンをクリック")
	@Order(4)
	void テスト項目No4() {
		// ログインURLに遷移する
		openLoginURL();
		
		// 注文履歴詳細画面のURLを開く
		openOrderDetailURL();
		
		//「戻る」ボタンをクリック
		final WebElement backBtn = webDriver.findElement(By.className("return"));
		backBtn.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// スクリーンショットを取得
		takeScreenShot("No.04 「戻る」ボタンをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("注文履歴一覧 - Osake Market");
	}
	
	@Test
	@DisplayName("No.5 「戻る」ボタンをクリック")
	@Order(5)
	void テスト項目No5() {
		// ログインURLに遷移する
		openLoginURL();
		
		// 注文履歴詳細画面のURLを開く
		openOrderDetailURL();
		
		//「戻る」ボタンをクリック
		final WebElement backBtn = webDriver.findElement(By.className("return"));
		backBtn.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// スクリーンショットを取得
		takeScreenShot("No.04 「戻る」ボタンをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("注文履歴一覧 - Osake Market");
	}
	
	@Test
	@DisplayName("No.6 「戻る」ボタンをクリック")
	@Order(6)
	void テスト項目No6() {
		// ログインURLに遷移する
		openLoginURL();
		
		// 注文履歴詳細画面のURLを開く
		openOrderDetailURL();
		
		//「戻る」ボタンをクリック
		final WebElement backBtn = webDriver.findElement(By.className("return"));
		backBtn.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// スクリーンショットを取得
		takeScreenShot("No.04 「戻る」ボタンをクリック");
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/order/list");
	}
	
	@Test
	@DisplayName("No.7 「注文を取り消す」ボタンをクリックし、"
			+ "「注文の取り消しを実行してよろしいですか？」とポップアップが表示されたら、"
			+ "「キャンセル」をクリック")
	@Order(7)
	void テスト項目No7() {
		// ログインURLに遷移する
		openLoginURL();
		
		// 注文履歴詳細画面のURLを開く
		openOrderDetailURL();
		
		// 「注文を取り消す」ボタンをクリック
		WebElement cancelBtn = webDriver.findElement(By.className("order-cancel"));
		cancelBtn.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// ダイアログを取得
		Alert confirm = webDriver.switchTo().alert();
		
		// メッセージを検証
		assertEquals("注文の取り消しを実行してよろしいですか？", confirm.getText());
		
		// 「キャンセル」をクリックして終了
		confirm.dismiss();
		
		// スクリーンショットを取得
		takeScreenShot("No.07 「注文を取り消す」ボタンをクリックし、"
				+ "「注文の取り消しを実行してよろしいですか？」とポップアップが表示されたら、"
				+ "「キャンセル」をクリック");
		
		// 「注文を取り消す」ボタンが表示されているか検証
		cancelBtn = webDriver.findElement(By.className("order-cancel"));
		assertDisplayed(cancelBtn);
	}
	
	@Test
	@DisplayName("No.8 「注文を取り消す」ボタンをクリックし、"
			+ "「注文の取り消しを実行してよろしいですか？」とポップアップが表示されたら、"
			+ "「OK」をクリック")
	@Order(8)
	void テスト項目No8() {
		// ログインURLに遷移する
		openLoginURL();
		
		// 注文履歴詳細画面のURLを開く
		openOrderDetailURL();
		
		// 「注文を取り消す」ボタンをクリック
		WebElement cancelBtn = webDriver.findElement(By.className("order-cancel"));
		cancelBtn.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// ダイアログを取得
		Alert confirm = webDriver.switchTo().alert();
		
		// メッセージを検証
		assertEquals("注文の取り消しを実行してよろしいですか？", confirm.getText());
		
		// 「OK」をクリック
		confirm.accept();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// 2つ目のダイアログを取得
		Alert alert = webDriver.switchTo().alert();
		
		// メッセージを検証
		assertEquals("注文の取り消しを実行しました", alert.getText());
		
		// 「OK」をクリック
		alert.accept();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// スクリーンショットを取得
		takeScreenShot("No.08 「注文を取り消す」ボタンをクリックし、"
				+ "「注文の取り消しを実行してよろしいですか？」とポップアップが表示されたら、"
				+ "「OK」をクリック");
		
		// 「キャンセル済み」と表示されているか検証
		WebElement canceled = webDriver.findElement(By.id("canceled"));
		assertTrue(canceled.isDisplayed());
		assertEquals("キャンセル済み", canceled.getText());
		
		// 「注文を取り消す」ボタンが非表示になっているか検証
		List<WebElement> cancelBtns =webDriver.findElements(By.className("order-cancel"));
		assertTrue(cancelBtns.isEmpty());
	}
	
	@Test
	@DisplayName("No.9 注文の取り消しを実行し、注文履歴一覧画面に戻る")
	@Order(9)
	void テスト項目No9() {
		// ログインURLに遷移する
		openLoginURL();
		
		// 注文履歴詳細一覧のURLを開く
		webDriver.get("http://localhost:8080/osake-market/order/list");
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// 画面下部の「2」をクリック
		WebElement pagination = webDriver.findElement(By.className("pagination"));
		WebElement nextPage = pagination.findElement(By.tagName("a"));
		nextPage.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// 「キャンセル済み」を取得する
		WebElement canceled = webDriver.findElement(By.id("canceled"));
		assertTrue(canceled.isDisplayed());
		assertEquals("キャンセル済み", canceled.getText());
	}
}
