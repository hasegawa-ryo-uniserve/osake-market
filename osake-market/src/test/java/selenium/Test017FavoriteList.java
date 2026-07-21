package selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
class Test017FavoriteList {

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
	
	// お気に入り一覧画面のURLを開く
	private void openFavoriteListURL() {
		webDriver.get("http://localhost:8080/osake-market/favorite/list");
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
	}
	
	// ログイン画面のURLを開く
	private void openLoginURL(String inputMail, String inputPassword) {
		webDriver.get("http://localhost:8080/osake-market/login");
		
		// ログイン情報を取得し入力する
		final WebElement mail = webDriver.findElement(By.name("mail"));
		final WebElement password = webDriver.findElement(By.name("password"));
		
		mail.clear();
		password.clear();
		
		mail.sendKeys(inputMail);
		password.sendKeys(inputPassword);
		
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
		String screenshotPath = "screenshots\\test017_favoriteList\\";
		
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
	
	// 商品一覧画面のURLを開く
	private void openProductListURL() {
		webDriver.get("http://localhost:8080/osake-market/product/list");
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
	}
	
	// 商品一覧でハートボタンをクリック
	private void clickHeart() {
		// ハートボタンを取得
		WebElement heartBtn = webDriver.findElement(By.cssSelector(".heart-and-button-wrapper .heart-btn"));
		
		// ハートボタンをクリック
		heartBtn.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
	}
	
	// 商品詳細画面のURLを開く
	private void openProductDetailURL() {
		webDriver.get("http://localhost:8080/osake-market/product/detail?productId=1");
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
	}
	
	// 商品詳細で「お気に入りに追加する」ボタンをクリック
	private void clickFavorite() {
		// 「お気に入りに追加する」ボタンを取得
		final WebElement favoriteAddBtn = webDriver.findElement(By.cssSelector(".product-detail-favorite-add .white-btn"));
		
		// 「お気に入りに追加する」ボタンをクリック
		favoriteAddBtn.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
	}
	
	
	@Test
	@DisplayName("No.1 ブラウザでhttp://localhost:8080/osake-market/favorite/listにアクセス")
	@Order(1)
	void テスト項目No1() {
		// ログインURLに遷移する
		openLoginURL("tomatoakain@gmail.com", "Tanaka11#Pass");
		
		// お気に入り一覧画面のURLに遷移
		openFavoriteListURL();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/favorite/list");
	}
	
	@Test
	@DisplayName("No.2 ブラウザでhttp://localhost:8080/osake-market/favorite/listにアクセス")
	@Order(2)
	void テスト項目No2() {
		// ログインURLに遷移する
		openLoginURL("tomatoakain@gmail.com", "Tanaka11#Pass");
		
		// お気に入り一覧画面のURLに遷移
		openFavoriteListURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.02 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("お気に入り一覧 - Osake Market");
	}
	
	@Test
	@DisplayName("No.3 ブラウザでhttp://localhost:8080/osake-market/favorite/listにアクセス")
	@Order(3)
	void テスト項目No3() {
		// ログインURLに遷移する
		openLoginURL("tomatoakain@gmail.com", "Tanaka11#Pass");
		
		// お気に入り一覧画面のURLに遷移
		openFavoriteListURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.03 ブラウザでアクセス");
		
		// 「お気に入り一覧」と、「該当する商品が見つかりませんでした」を取得
		WebElement title = webDriver.findElement(By.tagName("h2"));
		WebElement notFound = webDriver.findElement(By.className("not-found"));
		
		// 要素が表示されているか確認
		assertDisplayed(title, notFound);
	}
	
	@Test
	@DisplayName("No.4 商品一覧から商品をお気に入り追加した後、ブラウザでhttp://localhost:8080/osake-market/favorite/listにアクセス")
	@Order(4)
	void テスト項目No4() {
		// ログインURLに遷移する
		openLoginURL("tomatoakain@gmail.com", "Tanaka11#Pass");
		
		// 商品一覧画面からお気に入りを追加する
		openProductListURL();
		clickHeart();
		
		// お気に入り一覧画面のURLに遷移
		openFavoriteListURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.04 商品一覧から商品をお気に入り追加した後、ブラウザでアクセス");
		
		// 追加した商品が表示されているか検証
		List<WebElement> products = webDriver.findElements(By.className("favorite-list-wrapper"));
		assertTrue(products.get(0).isDisplayed());
		
		WebElement info = products.get(0).findElement(By.className("favorite-list-info"));
		String text = info.getText();

		// 1行目が商品名
		String productName = text.split("\n")[0];
		assertEquals("赤ワイン（フルボディ）", productName);
	}
	
	@Test
	@DisplayName("No.5 商品詳細から商品をお気に入り追加した後、ブラウザでhttp://localhost:8080/osake-market/favorite/listにアクセス")
	@Order(5)
	void テスト項目No5() {
		// ログインURLに遷移する
		openLoginURL("takahashi@gmail.com", "Takahashi33#Pass");
		
		// 商品詳細画面からお気に入りを追加する
		openProductDetailURL();
		clickFavorite();
		
		// お気に入り一覧画面のURLに遷移
		openFavoriteListURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.05 商品詳細から商品をお気に入り追加した後、ブラウザでアクセス");
		
		// 追加した商品が表示されているか検証
		List<WebElement> products = webDriver.findElements(By.className("favorite-list-wrapper"));
		assertFalse(products.isEmpty());
		
		WebElement info = products.get(0).findElement(By.className("favorite-list-info"));
		String text = info.getText();

		// 1行目が商品名
		String productName = text.split("\n")[0];
		assertEquals("赤ワイン（フルボディ）", productName);
	}
	
	@Test
	@DisplayName("No.6 お気に入り一覧の任意の商品の画像をクリック")
	@Order(6)
	void テスト項目No6() {
		// ログインURLに遷移する
		openLoginURL("kobayashi@gmail.com", "Kobayashi44#Pass");
		
		// 商品一覧画面からお気に入りを追加する
		openProductListURL();
		clickHeart();
		
		// お気に入り一覧画面のURLに遷移
		openFavoriteListURL();
		
		// 任意の商品の画像をクリック
		List<WebElement> products = webDriver.findElements(By.className("favorite-list-wrapper"));
		WebElement image = products.get(0).findElement(By.className("product-image"));
		image.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// スクリーンショットを取得
		takeScreenShot("No.06 お気に入り一覧の任意の商品の画像をクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("商品詳細 - Osake Market");
	}
	
	@Test
	@DisplayName("No.7 お気に入り一覧の任意の商品の画像をクリック")
	@Order(7)
	void テスト項目No7() {
		// ログインURLに遷移する
		openLoginURL("sasaki@gmail.com", "Sasaki55#Pass");
		
		// 商品一覧画面からお気に入りを追加する
		openProductListURL();
		clickHeart();
		
		// お気に入り一覧画面のURLに遷移
		openFavoriteListURL();
		
		// 任意の商品の画像をクリック
		List<WebElement> products = webDriver.findElements(By.className("favorite-list-wrapper"));
		WebElement image = products.get(0).findElement(By.className("product-image"));
		image.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/product/detail?productId=1");
	}
	
	@Test
	@DisplayName("No.8 商品詳細画面の「お気に入りから削除する」ボタンをクリックし、お気に入り一覧画面を開く")
	@Order(8)
	void テスト項目No8() {
		// ログインURLに遷移する
		openLoginURL("okazaki@gmail.com", "Okazaki66#Pass");
		
		// 商品一覧画面からお気に入りを追加する
		openProductListURL();
		clickHeart();
		
		// お気に入り一覧画面のURLに遷移
		openFavoriteListURL();
		
		// 任意の商品の画像をクリック
		List<WebElement> products = webDriver.findElements(By.className("favorite-list-wrapper"));
		WebElement image = products.get(0).findElement(By.className("product-image"));
		image.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// 「お気に入りから削除する」ボタンをクリック
		WebElement favoriteDeleteBtn = webDriver.findElement(By.className("colored-btn"));
		favoriteDeleteBtn.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// お気に入り一覧画面のURLに遷移
		openFavoriteListURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.08 商品詳細画面の「お気に入りから削除する」ボタンをクリックし、お気に入り一覧画面を開く");
		
		// 商品が削除されているか検証
		products = webDriver.findElements(By.className("favorite-list-wrapper"));
		assertTrue(products.isEmpty());
	}
	
	@Test
	@DisplayName("No.9 商品一覧画面のピンク色のハートボタンをクリックし、お気に入り一覧画面を開く")
	@Order(9)
	void テスト項目No9() {
		// ログインURLに遷移する
		openLoginURL("hanako@gmail.com", "Watanabe77#Pass");
		
		// 商品一覧画面からお気に入りを追加する
		openProductListURL();
		clickHeart();
	
		// ピンク色のハートボタンをクリック
		openProductListURL();
		clickHeart();
		
		// お気に入り一覧画面のURLに遷移
		openFavoriteListURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.09 商品一覧画面のピンク色のハートボタンをクリックし、お気に入り一覧画面を開く");
		
		// 商品が削除されているか検証
		List<WebElement> products = webDriver.findElements(By.className("favorite-list-wrapper"));
		assertTrue(products.isEmpty());
	}
	
	@Test
	@DisplayName("No.10 お気に入り一覧画面の「お気に入り解除」ボタンをクリック")
	@Order(10)
	void テスト項目No10() {
		// ログインURLに遷移する
		openLoginURL("saburo@gmail.com", "Kobayashi88#Pass");
		
		// 商品一覧画面からお気に入りを追加する
		openProductListURL();
		
		clickHeart();
		
		// もう一つの商品をお気に入りに追加する
		webDriver.get("http://localhost:8080/osake-market/product/detail?productId=2");
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		clickFavorite();
		
		// お気に入り一覧画面のURLに遷移
		openFavoriteListURL();
		
		
		// 「お気に入り解除」ボタンをクリック
		List<WebElement> products = webDriver.findElements(By.className("favorite-list-wrapper"));
		WebElement deleteBtn = products.get(0).findElement(By.cssSelector(".favorite-delete-form button"));
		deleteBtn.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// スクリーンショットを取得
		takeScreenShot("No.10 お気に入り一覧画面の「お気に入り解除」ボタンをクリック");
		
		// 商品が削除されているか検証
		products = webDriver.findElements(By.cssSelector(".favorite-list-wrapper"));
		WebElement info = products.get(0).findElement(By.className("favorite-list-info"));
		String text = info.getText();

		// 1行目が商品名
		String productName = text.split("\n")[0];
		
		assertEquals("シュワシュワワイン", productName);
	}
	
	@Test
	@DisplayName("No.11 商品が1つだけ表示された状態で「お気に入り解除」ボタンをクリック")
	@Order(11)
	void テスト項目No11() {
		// ログインURLに遷移する
		openLoginURL("kyosuke@gmail.com", "Usami99#Path");
		
		// 商品一覧画面からお気に入りを追加する
		openProductListURL();
		clickHeart();
		
		// お気に入り一覧画面のURLに遷移
		openFavoriteListURL();
		
		// 「お気に入り解除」ボタンをクリック
		List<WebElement> products = webDriver.findElements(By.className("favorite-list-wrapper"));
		WebElement deleteBtn = products.get(0).findElement(By.cssSelector(".favorite-delete-form button"));
		deleteBtn.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// スクリーンショットを取得
		takeScreenShot("No.11 商品が1つだけ表示された状態で「お気に入り解除」ボタンをクリック");
		
		// 「お気に入り一覧」と、「該当する商品が見つかりませんでした」を取得
		WebElement title = webDriver.findElement(By.tagName("h2"));
		WebElement notFound = webDriver.findElement(By.className("not-found"));
		
		// 要素が表示されているか確認
		assertDisplayed(title, notFound);
	}
}
