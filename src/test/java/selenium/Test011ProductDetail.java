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
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

@TestMethodOrder(OrderAnnotation.class)
class Test011ProductDetail {

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
	
	// 商品詳細画面のURLを開く
	private void openURL() {
		webDriver.get("http://localhost:8080/osake-market/product/detail?productId=1");
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
	}
	
	// ログインした状態で商品詳細画面のURLを開く
	private void loginAndOpenURL() {
		webDriver.get("http://localhost:8080/osake-market/login");
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// ログイン情報を取得し入力する
		final WebElement mail = webDriver.findElement(By.name("mail"));
		final WebElement password = webDriver.findElement(By.name("password"));
		
		mail.clear();
		password.clear();
		
		mail.sendKeys("tomatoakain@gmail.com");
		password.sendKeys("Tanaka11#Pass");
		
		// ログインボタンをクリック
		webDriver.findElement(By.cssSelector("input[value='ログイン']")).click();
		
		webDriver.get("http://localhost:8080/osake-market/product/detail?productId=1");
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
	}
	
	// スクリーンショットを取得
	private void takeScreenShot(String fileName) {
		// スクリーンショット保存パス
		String screenshotPath = "screenshots\\test011_productDetail\\";
		
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
	
	// レビューを投稿する
	private void postReview(int inputStar, String inputContent) {
		// 「レビューを投稿する」ボタンを取得
		final WebElement reviewPostBtn = webDriver.findElement(By.cssSelector(".review-post-btn"));
		
		// 「レビューを投稿する」ボタンをクリック
		reviewPostBtn.click();
		
		// 星を選択
		switch (inputStar) {
			case 1:
				webDriver.findElement(By.cssSelector("label[for='review01']")).click();
				break;
			case 2:
				webDriver.findElement(By.cssSelector("label[for='review02']")).click();
				break;
			case 3:
				webDriver.findElement(By.cssSelector("label[for='review03']")).click();
				break;
			case 4:
				webDriver.findElement(By.cssSelector("label[for='review04']")).click();
				break;
			case 5:
				webDriver.findElement(By.cssSelector("label[for='review05']")).click();
				break;
			default:
				break; // 0なら何もしない
		}
		
		// テキストを入力
		WebElement reviewTextArea = webDriver.findElement(By.className("review-textarea"));
		reviewTextArea.clear();
		reviewTextArea.sendKeys(inputContent);
		
		// 「投稿する」ボタンをクリック
		WebElement reviewPost = webDriver.findElement(By.cssSelector(".review-post.black-btn"));
		reviewPost.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
	}
	
	@Test
	@DisplayName("No.1 ブラウザでhttp://localhost:8080/osake-market/product/detail?productId=1にアクセス")
	@Order(1)
	void テスト項目No1() {
		// 指定したURLに遷移する
		openURL();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/product/detail?productId=1");
	}
	
	@Test
	@DisplayName("No.2 ブラウザでhttp://localhost:8080/osake-market/product/detail?productId=1にアクセス")
	@Order(2)
	void テスト項目No2() {
		// 指定したURLに遷移する
		openURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.02 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("商品詳細 - Osake Market");
	}
	
	@Test
	@DisplayName("No.3 ブラウザでhttp://localhost:8080/osake-market/product/detail?productId=1にアクセス")
	@Order(3)
	void テスト項目No3() {
		// 指定したURLに遷移する
		openURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.03 ブラウザでアクセス");
		
		/*
		 * 商品画像、商品タイトル、説明文、値段、「カートに追加する」ボタン、
		 * 「お気に入りに追加する」ボタン、「レビューを投稿する」ボタン、
		 * 「この商品のレビュー」、レビュー一覧を取得
		 */
		final WebElement productImg = webDriver.findElement(By.cssSelector(".product-detail-image"));
		final WebElement productName = webDriver.findElement(By.cssSelector(".product-detail-wrapper h1"));
		final WebElement productDescription = webDriver.findElement(By.cssSelector(".product-description"));
		final WebElement productPrice = webDriver.findElement(By.cssSelector(".product-price"));
		final WebElement cartAddBtn = webDriver.findElement(By.cssSelector(".cart-add-btn"));
		final WebElement favoriteAddBtn = webDriver.findElement(By.cssSelector(".product-detail-favorite-add .white-btn"));
		final WebElement reviewPostBtn = webDriver.findElement(By.cssSelector(".review-post-btn"));
		final WebElement reviewAllTitle = webDriver.findElement(By.cssSelector(".review-for-product"));
		final WebElement reviewAll = webDriver.findElement(By.cssSelector(".review-item-and-reviewer-container"));
		
		// 要素が表示されているか確認
		assertDisplayed(productImg, productName, productDescription, productPrice, cartAddBtn,
				favoriteAddBtn, reviewPostBtn, reviewAllTitle, reviewAll);
	}
	
	@Test
	@DisplayName("No.4 ログインしていない状態で「カートに追加する」ボタンをクリック")
	@Order(4)
	void テスト項目No4() {
		// 指定したURLに遷移する
		openURL();
		
		// 「カートに追加する」ボタンを取得
		final WebElement cartAddBtn = webDriver.findElement(By.cssSelector(".cart-add-btn"));
		
		// 「カートに追加する」ボタンをクリック
		cartAddBtn.click();
		
		// スクリーンショットを取得
		takeScreenShot("No.04 ログインしていない状態で「カートに追加する」ボタンをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("ログイン - Osake Market");
	}
	
	@Test
	@DisplayName("No.5 ログインしていない状態で「カートに追加する」ボタンをクリック")
	@Order(5)
	void テスト項目No5() {
		// 指定したURLに遷移する
		openURL();
		
		// 「カートに追加する」ボタンを取得
		final WebElement cartAddBtn = webDriver.findElement(By.cssSelector(".cart-add-btn"));
		
		// 「カートに追加する」ボタンをクリック
		cartAddBtn.click();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/login");
	}
	
	@Test
	@DisplayName("No.6 ログインしていない状態で「お気に入りに追加する」ボタンをクリック")
	@Order(6)
	void テスト項目No6() {
		// 指定したURLに遷移する
		openURL();
		
		// 「お気に入りに追加する」ボタンを取得
		final WebElement favoriteAddBtn = webDriver.findElement(By.cssSelector(".product-detail-favorite-add .white-btn"));
		
		// 「お気に入りに追加する」ボタンをクリック
		favoriteAddBtn.click();
		
		// スクリーンショットを取得
		takeScreenShot("No.06 ログインしていない状態で「お気に入りに追加する」ボタンをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("ログイン - Osake Market");
	}
	
	@Test
	@DisplayName("No.7 ログインしていない状態で「お気に入りに追加する」ボタンをクリック")
	@Order(7)
	void テスト項目No7() {
		// 指定したURLに遷移する
		openURL();
		
		// 「お気に入りに追加する」ボタンを取得
		final WebElement favoriteAddBtn = webDriver.findElement(By.cssSelector(".product-detail-favorite-add .white-btn"));
		
		// 「お気に入りに追加する」ボタンをクリック
		favoriteAddBtn.click();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/login");
	}
	
	@Test
	@DisplayName("No.8 「レビューを投稿する」ボタンをクリック")
	@Order(8)
	void テスト項目No8() {
		// 指定したURLに遷移する
		openURL();
		
		// 「レビューを投稿する」ボタンを取得
		final WebElement reviewPostBtn = webDriver.findElement(By.cssSelector(".review-post-btn"));
		
		// 「レビューを投稿する」ボタンをクリック
		reviewPostBtn.click();
		
		// スクリーンショットを取得
		takeScreenShot("No.08 「レビューを投稿する」ボタンをクリック");
		
		// 星の入力欄と、テキストの入力欄、「投稿する」ボタンを取得
		final WebElement inputStars = webDriver.findElement(By.className("input-stars"));
		final WebElement reviewTextArea = webDriver.findElement(By.className("review-textarea"));
		final WebElement reviewPost = webDriver.findElement(By.cssSelector(".review-post.black-btn"));
		
		// 星の入力欄と、テキストの入力欄、「投稿する」ボタンが表示されているか確認
		assertDisplayed(inputStars, reviewTextArea, reviewPost);
	}
	
	@Test
	@DisplayName("No.9 レビュー投稿欄に、星：未選択、テキスト：「おいしかった」と入力し、「投稿する」ボタンをクリック")
	@Order(9)
	void テスト項目No9() {
		// 指定したURLに遷移する
		openURL();
		
		// 投稿前のレビュー数を確認
		int before = webDriver.findElements(By.className("review-item")).size();
		
		// レビュー投稿欄に、星：未選択、テキスト：「おいしかった」と入力し、「投稿する」ボタンをクリック
		postReview(0, "おいしかった");
		
		// スクリーンショットを取得
		takeScreenShot("No.09 レビュー投稿欄に、星：未選択、テキスト：「おいしかった」と入力し、「投稿する」ボタンをクリック");
		
		// ポストが投稿されないか検証
		int after = webDriver.findElements(By.className("review-item")).size();
		assertEquals(before, after);
	}
	
	@Test
	@DisplayName("No.10 レビュー投稿欄に、星：5、テキスト：空白　で入力し、「投稿する」ボタンをクリック")
	@Order(10)
	void テスト項目No10() {
		// 指定したURLに遷移する
		openURL();
		
		// レビュー投稿欄に、星：5、テキスト：空白　で入力し、「投稿する」ボタンをクリック
		postReview(5, "");
		
		// スクリーンショットを取得
		takeScreenShot("No.10 レビュー投稿欄に、星：5、テキスト：空白　で入力し、「投稿する」ボタンをクリック");
		
		// 「このフィールドを入力して下さい」と表示されるか検証
		WebElement reviewTextArea = webDriver.findElement(By.className("review-textarea"));
		assertEquals("このフィールドを入力してください。", reviewTextArea.getAttribute("validationMessage"));
	}
	
	@Test
	@DisplayName("No.11 ログインしていない状態で、レビュー投稿欄に、星：5、テキスト：「おいしかった」と入力し、「投稿する」ボタンをクリック")
	@Order(11)
	void テスト項目No11() {
		// 指定したURLに遷移する
		openURL();
		
		// レビュー投稿欄に、星：5、テキスト：「おいしかった」　で入力し、「投稿する」ボタンをクリック
		postReview(5, "おいしかった");
		
		// スクリーンショットを取得
		takeScreenShot("No.11 ログインしていない状態で、レビュー投稿欄に、星：5、テキスト：「おいしかった」と入力し、「投稿する」ボタンをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("ログイン - Osake Market");
	}
	
	@Test
	@DisplayName("No.12 ログインしていない状態で、レビュー投稿欄に、星：5、テキスト：「おいしかった」と入力し、「投稿する」ボタンをクリック")
	@Order(12)
	void テスト項目No12() {
		// 指定したURLに遷移する
		openURL();
		
		// レビュー投稿欄に、星：5、テキスト：「おいしかった」　で入力し、「投稿する」ボタンをクリック
		postReview(5, "おいしかった");
		
		// スクリーンショットを取得
		takeScreenShot("No.12 ログインしていない状態で、レビュー投稿欄に、星：5、テキスト：「おいしかった」と入力し、「投稿する」ボタンをクリック");
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/login");
	}
	
	@Test
	@DisplayName("No.13 ログインした状態で「カートに追加する」ボタンをクリック")
	@Order(13)
	void テスト項目No13() {
		// 指定したURLに遷移する
		loginAndOpenURL();
		
		// 「カートに追加する」ボタンを取得
		final WebElement cartAddBtn = webDriver.findElement(By.cssSelector(".cart-add-btn"));
		
		// 「カートに追加する」ボタンをクリック
		cartAddBtn.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// スクリーンショットを取得
		takeScreenShot("No.13 ログインした状態で「カートに追加する」ボタンをクリック");
		
		// 画面上部に「カートに追加しました」と表示されたか検証
		final WebElement cartMsg = webDriver.findElement(By.id("cart-msg"));
		assertEquals("カートに追加しました", cartMsg.getText());
	}
	
	@Test
	@DisplayName("No.14 ログインした状態で「お気に入りに追加する」ボタンをクリック")
	@Order(14)
	void テスト項目No14() {
		// 指定したURLに遷移する
		loginAndOpenURL();
		
		// 「お気に入りに追加する」ボタンを取得
		final WebElement favoriteAddBtn = webDriver.findElement(By.cssSelector(".product-detail-favorite-add .white-btn"));
		
		// 「お気に入りに追加する」ボタンをクリック
		favoriteAddBtn.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// スクリーンショットを取得
		takeScreenShot("No.14 ログインした状態で「お気に入りに追加する」ボタンをクリック");
		
		// 画面上部に「お気に入りに追加しました」と表示されたか検証
		final WebElement favMsg = webDriver.findElement(By.id("fav-msg"));
		assertEquals("お気に入りに追加しました", favMsg.getText());
		
		// 「お気に入りに追加する」ボタンが非表示になったか検証
		assertTrue(webDriver.findElements(By.cssSelector(".product-detail-favorite-add .white-btn")).isEmpty());
		
		// ピンク色の「お気に入りから削除する」ボタンが表示されたか検証
		final WebElement favoriteDeleteBtn = webDriver.findElement(By.className("colored-btn"));
		assertTrue(favoriteDeleteBtn.isDisplayed());
	}
	
	@Test
	@DisplayName("No.15 ログインした状態で「お気に入りから削除する」ボタンをクリック")
	@Order(15)
	void テスト項目No15() {
		// 指定したURLに遷移する
		loginAndOpenURL();
		
		// 「お気に入りから削除する」ボタンを取得
		final WebElement favoriteDeleteBtn = webDriver.findElement(By.className("colored-btn"));
		
		WebElement favoriteAddBtn;
		
		// 「お気に入りに追加する」ボタンが表示されていたら、お気に入り追加する
		if (!favoriteDeleteBtn.isDisplayed()) {
			// 「お気に入りに追加する」ボタンを取得
			favoriteAddBtn = webDriver.findElement(By.cssSelector(".product-detail-favorite-add .white-btn"));
			
			// 「お気に入りに追加する」ボタンをクリック
			favoriteAddBtn.click();
		}
		
		// 「お気に入りから削除する」ボタンをクリック
		favoriteDeleteBtn.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// スクリーンショットを取得
		takeScreenShot("No.15 ログインした状態で「お気に入りから削除する」ボタンをクリック");
		
		// 画面上部に「お気に入りから削除しました」と表示されたか検証
		final WebElement favMsg = webDriver.findElement(By.id("fav-msg"));
		assertEquals("お気に入りから削除しました", favMsg.getText());
		
		// 「お気に入りから削除する」ボタンが非表示になったか検証
		assertTrue(webDriver.findElements(By.className("colored-btn")).isEmpty());
		
		// 「お気に入りに追加する」ボタンが表示されたか検証
		favoriteAddBtn = webDriver.findElement(By.cssSelector(".product-detail-favorite-add .white-btn"));
		assertTrue(favoriteAddBtn.isDisplayed());
	}
	
	@Test
	@DisplayName("No.16 ログインした状態で、レビュー投稿欄に、星：5、テキスト：「おいしかった」と入力し、「投稿する」ボタンをクリック")
	@Order(16)
	void テスト項目No16() {
		// 指定したURLに遷移する
		loginAndOpenURL();
		
		// 投稿前のレビュー数を確認
		int before = webDriver.findElements(By.className("review-item")).size();
		
		// レビュー投稿欄に、星：5、テキスト：「おいしかった」　で入力し、「投稿する」ボタンをクリック
		postReview(5, "おいしかった");
		
		// スクリーンショットを取得
		takeScreenShot("No.16 ログインした状態で、レビュー投稿欄に、星：5、テキスト：「おいしかった」と入力し、「投稿する」ボタンをクリック");
		
		// ポストが投稿されたか検証
		int after = webDriver.findElements(By.className("review-item")).size();
		assertEquals(before + 1, after);
	}
}
