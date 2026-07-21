package selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
class Test012Cart {

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
			Thread.sleep(1000);			
		} catch(Exception e) {
			return;
		}
	}
	
	// カート画面のURLを開く
	private void openCartURL() {
		webDriver.get("http://localhost:8080/osake-market/cart");
		
		try {
			Thread.sleep(1000);			
		} catch(Exception e) {
			return;
		}
	}
	
	// スクリーンショットを取得
	private void takeScreenShot(String fileName) {
		// スクリーンショット保存パス
		String screenshotPath = "screenshots\\test012_cart\\";
		
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
			Thread.sleep(1000);			
		} catch(Exception e) {
			return;
		}
		
		// 「カートに追加する」ボタンを取得
		WebElement addCartBtn = webDriver.findElement(By.cssSelector(".cart-add-btn.black-btn"));
		
		// 「カートに追加する」ボタンをクリック
		addCartBtn.click();
	}
	
	// 商品詳細画面上で商品を1つ追加する
	private void addProductOnDetail() {		
		// 商品詳細画面に遷移
		webDriver.get("http://localhost:8080/osake-market/product/detail?productId=1");
		
		try {
			Thread.sleep(1000);			
		} catch(Exception e) {
			return;
		}
		
		// 「カートに追加する」ボタンを取得
		final WebElement cartAddBtn = webDriver.findElement(By.cssSelector(".cart-add-btn"));
		
		// 「カートに追加する」ボタンをクリック
		cartAddBtn.click();
	}
	
	// カートから削除する
	private void deleteCart() {
		webDriver.get("http://localhost:8080/osake-market/cart");
		
		try {
			Thread.sleep(1000);			
		} catch(Exception e) {
			return;
		}
		
		// 削除ボタンを取得
		List<WebElement> deleteButtons = webDriver.findElements(By.cssSelector(".cart-item-delete button"));
		
		// 削除ボタンが表示されていたら、クリックする
		if (!deleteButtons.isEmpty()) {
	        deleteButtons.get(0).click();
	    } else {
	    	return;
	    }
		
		try {
			Thread.sleep(1000);			
		} catch(Exception e) {
			return;
		}
	}
	
	// 小計を取得する
	private int getSubTotal() {
		WebElement prices = webDriver.findElement(By.className("cart-price"));
		String[] lines = prices.getText().split("\n");
		
		String subTotalString = lines[0].replace("小計：￥", "");
		
		int subTotal = Integer.parseInt(subTotalString.replace(",", ""));
		
		return subTotal;
	}
	
	// 合計を取得する
	private int getTotal() {
		WebElement prices = webDriver.findElement(By.className("cart-price"));
		String[] lines = prices.getText().split("\n");
		
		String totalString = lines[2].replace("合計：￥", "");
		
		int total = Integer.parseInt(totalString.replace(",", ""));	
		
		return total;
	}
	
	@Test
	@DisplayName("No.1 ブラウザでhttp://localhost:8080/osake-market/cartにアクセス")
	@Order(1)
	void テスト項目No1() {
		// 指定したURLに遷移する
		openLoginURL();
		openCartURL();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/cart");
	}
	
	@Test
	@DisplayName("No.2 ブラウザでhttp://localhost:8080/osake-market/cartにアクセス")
	@Order(2)
	void テスト項目No2() {
		// 指定したURLに遷移する
		openLoginURL();
		openCartURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.02 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("カート - Osake Market");
	}
	
	@Test
	@DisplayName("No.3 カートに商品を追加していない状態でブラウザでアクセス")
	@Order(3)
	void テスト項目No3() {
		// 指定したURLに遷移する
		openLoginURL();
		openCartURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.03 カートに商品を追加していない状態でブラウザでアクセス");
		
		// 「カート」と「カートに商品はありません」と表示されるか検証
		final WebElement title = webDriver.findElement(By.tagName("h2"));
		final WebElement message = webDriver.findElement(By.tagName("p"));
		
		assertEquals("カート", title.getText());
		assertEquals("カートに商品はありません", message.getText());
	}
	
	@Test
	@DisplayName("No.4 商品一覧画面または商品詳細画面で商品を追加した状態で、"
			+ "ブラウザでhttp://localhost:8080/osake-market/cartにアクセス")
	@Order(4)
	void テスト項目No4() {
		// 商品一覧画面から商品を一つカートに追加する
		openLoginURL();
		addProductOnList();
		
		// 指定したURLに遷移する
		openCartURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.04 商品一覧画面で商品を追加した状態で、ブラウザでアクセス");
		
		// カートに追加した商品を取得
		List<WebElement> product = webDriver.findElements(By.cssSelector("tbody tr td"));
		
		// 赤ワイン（フルボディ）の商品が選択されるか検証
		assertEquals("赤ワイン（フルボディ）", product.get(1).getText());
	}
	
	@Test
	@DisplayName("No.5 商品詳細画面で商品を追加した状態で、"
			+ "ブラウザでhttp://localhost:8080/osake-market/cartにアクセス")
	@Order(5)
	void テスト項目No5() {
		// 商品詳細画面から商品を一つカートに追加する
		openLoginURL();
		addProductOnDetail();
		
		// 指定したURLに遷移する
		openCartURL();
		
		
		// スクリーンショットを取得
		takeScreenShot("No.05 商品詳細画面で商品を追加した状態で、ブラウザでアクセス");
		
		// カートに追加した商品を取得
		List<WebElement> product = webDriver.findElements(By.cssSelector("tbody tr td"));
		
		// 赤ワイン（フルボディ）の商品が選択されるか検証
		assertEquals("赤ワイン（フルボディ）", product.get(1).getText());
	}
	
	@Test
	@DisplayName("No.6 追加した任意の商品の数量の「+」ボタンをクリック")
	@Order(6)
	void テスト項目No6() {
		// 商品一覧画面から商品を一つカートに追加する
		openLoginURL();
		addProductOnList();
		
		// 指定したURLに遷移する
		openCartURL();
		
		// 数量を取得
		WebElement quantity = webDriver.findElement(By.className("qty"));
		int beforeQuantity = Integer.parseInt(quantity.getText());
		
		// 金額を取得
		int beforeSubTotal = getSubTotal();
		int beforeTotal = getTotal();	
		
		// カートに追加した商品の「+」ボタンを取得		
		WebElement plusBtn = webDriver.findElement(By.xpath("//button[text()='+']"));
		
		// 「+」ボタンをクリック
		plusBtn.click();
		
		// スクリーンショットを取得
		takeScreenShot("No.06 追加した任意の商品の数量の「+」ボタンをクリック");
		
		// 商品数が1増加しているか検証
		quantity = webDriver.findElement(By.className("qty"));
		int afterQuantity = Integer.parseInt(quantity.getText());
		
		assertEquals(beforeQuantity + 1, afterQuantity);
		
		// 	テーブルの小計とテーブル外の小計、合計の金額が変化しているか検証
		int afterSubTotal = getSubTotal();
		int afterTotal = getTotal();	
		
		List<WebElement> product = webDriver.findElements(By.cssSelector("tbody tr td"));
		String priceString = product.get(2).getText().replace(",", "");
		int price = Integer.parseInt(priceString.replace(",", ""));
		
		assertEquals(beforeSubTotal + price, afterSubTotal);
		assertEquals(beforeTotal + price, afterTotal);
	}
	
	@Test
	@DisplayName("No.7 追加した任意の商品の数量の「-」ボタンをクリック")
	@Order(7)
	void テスト項目No7() {
		// 商品一覧画面から商品を二つカートに追加する
		openLoginURL();
		addProductOnList();
		addProductOnList();
		
		// 指定したURLに遷移する
		openCartURL();
		
		// 数量を取得
		WebElement quantity = webDriver.findElement(By.className("qty"));
		int beforeQuantity = Integer.parseInt(quantity.getText());
		
		// 金額を取得
		int beforeSubTotal = getSubTotal();
		int beforeTotal = getTotal();	
		
		// カートに追加した商品の「-」ボタンを取得		
		WebElement minusBtn = webDriver.findElement(By.xpath("//button[text()='-']"));
		
		// 「-」ボタンをクリック
		minusBtn.click();
		
		// スクリーンショットを取得
		takeScreenShot("No.07 追加した任意の商品の数量の「-」ボタンをクリック");
		
		// 商品数が1減少しているか検証
		quantity = webDriver.findElement(By.className("qty"));
		int afterQuantity = Integer.parseInt(quantity.getText());
		
		assertEquals(beforeQuantity - 1, afterQuantity);
		
		// 	テーブルの小計とテーブル外の小計、合計の金額が変化しているか検証
		int afterSubTotal = getSubTotal();
		int afterTotal = getTotal();	
		
		List<WebElement> product = webDriver.findElements(By.cssSelector("tbody tr td"));
		String priceString = product.get(2).getText().replace(",", "");
		int price = Integer.parseInt(priceString.replace(",", ""));
		
		assertEquals(beforeSubTotal - price, afterSubTotal);
		assertEquals(beforeTotal - price, afterTotal);
	}
	
	@Test
	@DisplayName("No.8 数量が1の商品の数量の「-」ボタンをクリック")
	@Order(8)
	void テスト項目No8() {
		// 商品一覧画面から商品を一つカートに追加する
		openLoginURL();
		addProductOnList();
		
		// 商品詳細画面に遷移
		webDriver.get("http://localhost:8080/osake-market/product/detail?productId=2");
		
		try {
			Thread.sleep(1);			
		} catch(Exception e) {
			return;
		}
		
		// 「カートに追加する」ボタンを取得
		final WebElement cartAddBtn = webDriver.findElement(By.cssSelector(".cart-add-btn"));
		
		// 「カートに追加する」ボタンをクリック
		cartAddBtn.click();
		
		// 指定したURLに遷移する
		openCartURL();
		
		// 金額を取得
		int beforeSubTotal = getSubTotal();
		int beforeTotal = getTotal();
		
		// 最初の行を取得
		List<WebElement> lines = webDriver.findElements(By.cssSelector("tbody tr"));
		WebElement beforeFirstLine = lines.get(0);
		
		// 最初の行の金額を取得
		List<WebElement> product = webDriver.findElements(By.cssSelector("tbody tr td"));
		String priceString = product.get(2).getText().replace(",", "");
		int price = Integer.parseInt(priceString.replace(",", ""));
		
		// カートに追加した商品の「-」ボタンを取得		
		WebElement minusBtn = webDriver.findElement(By.xpath("//button[text()='-']"));
		
		// 「-」ボタンをクリック
		minusBtn.click();
		
		// スクリーンショットを取得
		takeScreenShot("No.08 数量が1の商品の数量の「-」ボタンをクリック");
		
		// 1行削除されているか検証
		lines = webDriver.findElements(By.cssSelector("tbody tr"));
		WebElement afterFirstLine = lines.get(0);
		
		assertNotEquals(beforeFirstLine, afterFirstLine);
		
		// 	テーブルの小計とテーブル外の小計、合計の金額が変化しているか検証
		int afterSubTotal = getSubTotal();
		int afterTotal = getTotal();
		
		assertEquals(beforeSubTotal - price, afterSubTotal);
		assertEquals(beforeTotal - price, afterTotal);
	}
	
	@Test
	@DisplayName("No.9 任意の商品の「削除」ボタンをクリック")
	@Order(9)
	void テスト項目No9() {
		// 商品一覧画面から商品を一つカートに追加する
		openLoginURL();
		addProductOnList();
		
		// 商品詳細画面に遷移
		webDriver.get("http://localhost:8080/osake-market/product/detail?productId=2");
		
		try {
			Thread.sleep(1000);			
		} catch(Exception e) {
			return;
		}
		
		// 「カートに追加する」ボタンを取得
		final WebElement cartAddBtn = webDriver.findElement(By.cssSelector(".cart-add-btn"));
		
		// 「カートに追加する」ボタンをクリック
		cartAddBtn.click();
		
		// 指定したURLに遷移する
		openCartURL();
		
		// 金額を取得
		int beforeSubTotal = getSubTotal();
		int beforeTotal = getTotal();
		
		// 最初の行を取得
		List<WebElement> lines = webDriver.findElements(By.cssSelector("tbody tr"));
		WebElement beforeFirstLine = lines.get(0);
		
		// 最初の行の金額を取得
		List<WebElement> product = webDriver.findElements(By.cssSelector("tbody tr td"));
		String priceString = product.get(2).getText().replace(",", "");
		int price = Integer.parseInt(priceString.replace(",", ""));
		
		// カートに追加した商品の「削除」ボタンをクリック		
		deleteCart();
		
		// スクリーンショットを取得
		takeScreenShot("No.09 任意の商品の「削除」ボタンをクリック");
		
		// 1行削除されているか検証
		lines = webDriver.findElements(By.cssSelector("tbody tr"));
		WebElement afterFirstLine = lines.get(0);
		
		assertNotEquals(beforeFirstLine, afterFirstLine);
		
		// 	テーブルの小計とテーブル外の小計、合計の金額が変化しているか検証
		int afterSubTotal = getSubTotal();
		int afterTotal = getTotal();
		
		assertEquals(beforeSubTotal - price, afterSubTotal);
		assertEquals(beforeTotal - price, afterTotal);
	}
	
	@Test
	@DisplayName("No.10 テーブルに行が1つしかない状態で、商品の「削除」ボタンをクリック")
	@Order(10)
	void テスト項目No10() {
		// 商品一覧画面から商品を一つカートに追加する
		openLoginURL();
		addProductOnList();
		
		// 指定したURLに遷移する
		openCartURL();
		
		// カートに追加した商品の「削除」ボタンをクリック		
		deleteCart();
		
		// スクリーンショットを取得
		takeScreenShot("No.10 テーブルに行が1つしかない状態で、商品の「削除」ボタンをクリック");
		
		// 表が削除されているか検証
		List<WebElement> cartTable = webDriver.findElements(By.className("cart-table"));
		assertTrue(cartTable.isEmpty());
	}
	
	@Test
	@DisplayName("No.11 「購入手続きに進む」ボタンをクリック")
	@Order(11)
	void テスト項目No11() {
		// 商品一覧画面から商品を一つカートに追加する
		openLoginURL();
		addProductOnList();
		
		// 指定したURLに遷移する
		openCartURL();
		
		// 「購入手続きに進む」ボタンをクリック
		WebElement orderBtn = webDriver.findElement(By.className("cart-button"));
		orderBtn.click();
		
		try {
			Thread.sleep(1000);			
		} catch(Exception e) {
			return;
		}
		
		// スクリーンショットを取得
		takeScreenShot("No.11 「購入手続きに進む」ボタンをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("注文情報入力 - Osake Market");
	}
	
	@Test
	@DisplayName("No.12 「購入手続きに進む」ボタンをクリック")
	@Order(12)
	void テスト項目No12() {
		// 商品一覧画面から商品を一つカートに追加する
		openLoginURL();
		addProductOnList();
		
		// 指定したURLに遷移する
		openCartURL();
		
		// 「購入手続きに進む」ボタンをクリック
		WebElement orderBtn = webDriver.findElement(By.className("cart-button"));
		orderBtn.click();
		
		try {
			Thread.sleep(1000);			
		} catch(Exception e) {
			return;
		}
		
		/// URLを検証
		assertURL("http://localhost:8080/osake-market/order/input");
	}
}
