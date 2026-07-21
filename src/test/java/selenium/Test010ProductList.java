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
class Test010ProductList {
	
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
	
	// 商品一覧画面のURLを開く
	private void openURL() {
		webDriver.get("http://localhost:8080/osake-market/product/list");
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
	}
	
	// ログインした状態で商品一覧画面のURLを開く
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
		
		webDriver.get("http://localhost:8080/osake-market/product/list");
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
	}
	
	// スクリーンショットを取得
	private void takeScreenShot(String fileName) {
		// スクリーンショット保存パス
		String screenshotPath = "screenshots\\test010_productList\\";
		
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
	
	// 商品検索の確認
	private void checkProductSearch(String inputCategory, String inputProductName, String inputSort) {
		// 商品検索欄を取得
		WebElement category = webDriver.findElement(By.name("categoryName"));
		WebElement product = webDriver.findElement(By.name("productName"));
		WebElement sort = webDriver.findElement(By.name("sort"));
		
		// プルダウン確認
		final Select categorySelect = new Select(category);
		final Select sortSelect = new Select(sort);
		
		assertEquals(inputCategory, categorySelect.getFirstSelectedOption().getText());
		assertEquals(inputProductName, product.getText());
		assertEquals(inputSort, sortSelect.getFirstSelectedOption().getText());
	}
	
	// 要素が表示されているか確認
	private void assertDisplayed(WebElement... elements) {
		for (WebElement element : elements) {
			assertTrue(element.isDisplayed());
		}
	}
	
	// 商品検索を実行
	private void doSearch(String inputCategory, String inputProductName, String inputSort) {
		// 商品検索欄を取得
		WebElement category = webDriver.findElement(By.name("categoryName"));
		WebElement product = webDriver.findElement(By.name("productName"));
		WebElement sort = webDriver.findElement(By.name("sort"));
		WebElement searchBtn = webDriver.findElement(By.cssSelector(".product-list-filter-form-submit.black-btn"));
		
		// 検索欄に入力
		Select select = new Select(category);
		select.selectByVisibleText(inputCategory);
		
		product.clear();
		product.sendKeys(inputProductName); 
		
		select = new Select(sort);
		select.selectByVisibleText(inputSort);
		
		// 検索ボタンをクリック
		searchBtn.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
	}
	
	// 商品一覧確認
	private void assertProductNames(String... productNames) {
		List<WebElement> productList = webDriver.findElements(By.className("product-name"));
		
		assertEquals(productNames.length, productList.size());
		
		for(int i = 0; i < productNames.length; i++) {
			assertEquals(productNames[i], productList.get(i).getText());
		}
	}
	
	// ハートボタンをクリック
	private void clickHeart() {
		// ハートボタンを取得
		WebElement heartBtn = webDriver.findElement(By.cssSelector(".heart-btn"));
		
		// ハートボタンをクリック
		heartBtn.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
	}
	
	// 「カートに追加する」ボタンをクリック
	private void clickAddCart() {
		// 「カートに追加する」ボタンを取得
		WebElement addCartBtn = webDriver.findElement(By.cssSelector(".cart-add-btn.black-btn"));
		
		// 「カートに追加する」ボタンをクリック
		addCartBtn.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
	}
	
	@Test
	@DisplayName("No.1 ブラウザでhttp://localhost:8080/osake-market/product/listにアクセス")
	@Order(1)
	void テスト項目No1() {
		// 指定したURLに遷移する
		openURL();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/product/list");
	}
	
	@Test
	@DisplayName("No.2 ブラウザでhttp://localhost:8080/osake-market/product/listにアクセス")
	@Order(2)
	void テスト項目No2() {
		// 指定したURLに遷移する
		openURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.02 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("商品一覧 - Osake Market");
	}
	
	@Test
	@DisplayName("No.3 ブラウザでhttp://localhost:8080/osake-market/product/listにアクセス")
	@Order(3)
	void テスト項目No3() {
		// 指定したURLに遷移する
		openURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.03 ブラウザでアクセス");
		
		// 検索欄、「検索」ボタンが表示されているか確認
		final WebElement category = webDriver.findElement(By.name("categoryName"));
		final WebElement product = webDriver.findElement(By.name("productName"));
		final WebElement sort = webDriver.findElement(By.name("sort"));
		final WebElement searchBtn = webDriver.findElement(By.cssSelector(".product-list-filter-form-submit.black-btn"));
		assertDisplayed(category, product, sort, searchBtn);
		
		// 「すべてのカテゴリ」、「追加の新しい順」が初期状態となっている。
		checkProductSearch("すべてのカテゴリ", "", "追加の新しい順");
	}
	
	@Test
	@DisplayName("No.4 「すべてのカテゴリ」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック")
	@Order(4)
	void テスト項目No4() {
		// 指定したURLに遷移する
		openURL();
		
		// 「すべてのカテゴリ」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック
		doSearch("すべてのカテゴリ", "", "追加の新しい順");
		
		// スクリーンショットを取得
		takeScreenShot("No.04 「すべてのカテゴリ」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック");
		
		// 表示された商品の順番を検証
		assertProductNames("赤ワイン（フルボディ）", "シュワシュワワイン", "コクのあるウイスキー",
				"高級ブランデー", "芋焼酎", "神酒", "バーボン", "黒ラベル", "ビーフジャーキー",
				"赤ワイン（ライトボディ）");
	}
	
	@Test
	@DisplayName("No.5 「すべてのカテゴリ」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック")
	@Order(5)
	void テスト項目No5() {
		// 指定したURLに遷移する
		openURL();
		
		// 「すべてのカテゴリ」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック
		doSearch("すべてのカテゴリ", "", "追加の新しい順");
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/product/list?categoryName=&productName=&sort=new");
	}
	
	@Test
	@DisplayName("No.6 「すべてのカテゴリ」、「追加の古い順」が選択された状態で、「検索」ボタンをクリック")
	@Order(6)
	void テスト項目No6() {
		// 指定したURLに遷移する
		openURL();
		
		// 「すべてのカテゴリ」、「追加の古い順」が選択された状態で、「検索」ボタンをクリック
		doSearch("すべてのカテゴリ", "", "追加の古い順");
		
		// スクリーンショットを取得
		takeScreenShot("No.06 「すべてのカテゴリ」、「追加の古い順」が選択された状態で、「検索」ボタンをクリック");
		
		// 表示された商品の順番を検証
		assertProductNames("赤ワイン（ライトボディ）", "ビーフジャーキー", "黒ラベル",
				"バーボン", "神酒", "芋焼酎", "高級ブランデー", "コクのあるウイスキー", 
				"シュワシュワワイン", "赤ワイン（フルボディ）");
	}
	
	@Test
	@DisplayName("No.7 「すべてのカテゴリ」、「追加の古い順」が選択された状態で、「検索」ボタンをクリック")
	@Order(7)
	void テスト項目No7() {
		// 指定したURLに遷移する
		openURL();
		
		// 「すべてのカテゴリ」、「追加の古い順」が選択された状態で、「検索」ボタンをクリック
		doSearch("すべてのカテゴリ", "", "追加の古い順");
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/product/list?categoryName=&productName=&sort=old");
	}
	
	@Test
	@DisplayName("No.8 「すべてのカテゴリ」、「価格が高い順」が選択された状態で、「検索」ボタンをクリック")
	@Order(8)
	void テスト項目No8() {
		// 指定したURLに遷移する
		openURL();
		
		// 「すべてのカテゴリ」、「価格が高い順」が選択された状態で、「検索」ボタンをクリック
		doSearch("すべてのカテゴリ", "", "価格が高い順");
		
		// スクリーンショットを取得
		takeScreenShot("No.08 「すべてのカテゴリ」、「価格が高い順」が選択された状態で、「検索」ボタンをクリック");
		
		// 表示された商品の順番を検証
		assertProductNames("赤ワイン（フルボディ）", "高級ブランデー", "コクのあるウイスキー",
				"芋焼酎", "バーボン", "赤ワイン（ライトボディ）", "神酒", "シュワシュワワイン", 
				"黒ラベル", "ビーフジャーキー");
	}
	
	@Test
	@DisplayName("No.9 「すべてのカテゴリ」、「価格が高い順」が選択された状態で、「検索」ボタンをクリック")
	@Order(9)
	void テスト項目No9() {
		// 指定したURLに遷移する
		openURL();
		
		// 「すべてのカテゴリ」、「価格が高い順」が選択された状態で、「検索」ボタンをクリック
		doSearch("すべてのカテゴリ", "", "価格が高い順");
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/product/list?categoryName=&productName=&sort=price-desc");
	}
	
	@Test
	@DisplayName("No.10 「すべてのカテゴリ」、「価格が安い順」が選択された状態で、「検索」ボタンをクリック")
	@Order(10)
	void テスト項目No10() {
		// 指定したURLに遷移する
		openURL();
		
		// 「すべてのカテゴリ」、「価格が安い順」が選択された状態で、「検索」ボタンをクリック
		doSearch("すべてのカテゴリ", "", "価格が安い順");
		
		// スクリーンショットを取得
		takeScreenShot("No.10 「すべてのカテゴリ」、「価格が安い順」が選択された状態で、「検索」ボタンをクリック");
		
		// 表示された商品の順番を検証
		assertProductNames("ビーフジャーキー", "黒ラベル", "シュワシュワワイン", "神酒",
				"赤ワイン（ライトボディ）", "バーボン", "芋焼酎", "コクのあるウイスキー",
				"高級ブランデー", "赤ワイン（フルボディ）");
	}
	
	@Test
	@DisplayName("No.11 「すべてのカテゴリ」、「価格が安い順」が選択された状態で、「検索」ボタンをクリック")
	@Order(11)
	void テスト項目No11() {
		// 指定したURLに遷移する
		openURL();
		
		// 「すべてのカテゴリ」、「価格が安い順」が選択された状態で、「検索」ボタンをクリック
		doSearch("すべてのカテゴリ", "", "価格が安い順");
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/product/list?categoryName=&productName=&sort=price-asc");
	}
	
	@Test
	@DisplayName("No.12 「ワイン」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック")
	@Order(12)
	void テスト項目No12() {
		// 指定したURLに遷移する
		openURL();
		
		// 「ワイン」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック
		doSearch("ワイン", "", "追加の新しい順");
		
		// スクリーンショットを取得
		takeScreenShot("No.12 「ワイン」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック");
		
		// 表示された商品の順番を検証
		assertProductNames("赤ワイン（フルボディ）", "赤ワイン（ライトボディ）");
	}
	
	@Test
	@DisplayName("No.13 「ワイン」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック")
	@Order(13)
	void テスト項目No13() {
		// 指定したURLに遷移する
		openURL();
		
		// 「ワイン」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック
		doSearch("ワイン", "", "追加の新しい順");
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/product/list?categoryName=wine&productName=&sort=new");
	}
	
	@Test
	@DisplayName("No.14 「スパークリングワイン」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック")
	@Order(14)
	void テスト項目No14() {
		// 指定したURLに遷移する
		openURL();
		
		// 「スパークリングワイン」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック
		doSearch("スパークリングワイン", "", "追加の新しい順");
		
		// スクリーンショットを取得
		takeScreenShot("No.14 「スパークリングワイン」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック");
		
		// 表示された商品の順番を検証
		assertProductNames("シュワシュワワイン");
	}
	
	@Test
	@DisplayName("No.15 「スパークリングワイン」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック")
	@Order(15)
	void テスト項目No15() {
		// 指定したURLに遷移する
		openURL();
		
		// 「スパークリングワイン」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック
		doSearch("スパークリングワイン", "", "追加の新しい順");
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/product/list?categoryName=sparklingWine&productName=&sort=new");
	}
	
	@Test
	@DisplayName("No.16 「ウイスキー」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック")
	@Order(16)
	void テスト項目No16() {
		// 指定したURLに遷移する
		openURL();
		
		// 「ウイスキー」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック
		doSearch("ウイスキー", "", "追加の新しい順");
		
		// スクリーンショットを取得
		takeScreenShot("No.16 「ウイスキー」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック");
		
		// 表示された商品の順番を検証
		assertProductNames("コクのあるウイスキー");
	}
	
	@Test
	@DisplayName("No.17 「ウイスキー」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック")
	@Order(17)
	void テスト項目No17() {
		// 指定したURLに遷移する
		openURL();
		
		// 「ウイスキー」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック
		doSearch("ウイスキー", "", "追加の新しい順");
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/product/list?categoryName=whisky&productName=&sort=new");
	}
	
	@Test
	@DisplayName("No.18 「ブランデー」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック")
	@Order(18)
	void テスト項目No18() {
		// 指定したURLに遷移する
		openURL();
		
		// 「ブランデー」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック
		doSearch("ブランデー", "", "追加の新しい順");
		
		// スクリーンショットを取得
		takeScreenShot("No.18 「ブランデー」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック");
		
		// 表示された商品の順番を検証
		assertProductNames("高級ブランデー");
	}
	
	@Test
	@DisplayName("No.19 「ブランデー」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック")
	@Order(19)
	void テスト項目No19() {
		// 指定したURLに遷移する
		openURL();
		
		// 「ブランデー」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック
		doSearch("ブランデー", "", "追加の新しい順");
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/product/list?categoryName=brandy&productName=&sort=new");
	}
	
	@Test
	@DisplayName("No.20 「焼酎」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック")
	@Order(20)
	void テスト項目No20() {
		// 指定したURLに遷移する
		openURL();
		
		// 「焼酎」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック
		doSearch("焼酎", "", "追加の新しい順");
		
		// スクリーンショットを取得
		takeScreenShot("No.20 「焼酎」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック");
		
		// 表示された商品の順番を検証
		assertProductNames("芋焼酎");
	}
	
	@Test
	@DisplayName("No.21 「焼酎」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック")
	@Order(21)
	void テスト項目No21() {
		// 指定したURLに遷移する
		openURL();
		
		// 「焼酎」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック
		doSearch("焼酎", "", "追加の新しい順");
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/product/list?categoryName=shochu&productName=&sort=new");
	}
	
	@Test
	@DisplayName("No.22 「日本酒」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック")
	@Order(22)
	void テスト項目No22() {
		// 指定したURLに遷移する
		openURL();
		
		// 「日本酒」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック
		doSearch("日本酒", "", "追加の新しい順");
		
		// スクリーンショットを取得
		takeScreenShot("No.22 「日本酒」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック");
		
		// 表示された商品の順番を検証
		assertProductNames("神酒");
	}
	
	@Test
	@DisplayName("No.23 「日本酒」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック")
	@Order(23)
	void テスト項目No23() {
		// 指定したURLに遷移する
		openURL();
		
		// 「日本酒」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック
		doSearch("日本酒", "", "追加の新しい順");
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/product/list?categoryName=japaneseSake&productName=&sort=new");
	}
	
	@Test
	@DisplayName("No.24 「リキュール」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック")
	@Order(24)
	void テスト項目No24() {
		// 指定したURLに遷移する
		openURL();
		
		// 「リキュール」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック
		doSearch("リキュール", "", "追加の新しい順");
		
		// スクリーンショットを取得
		takeScreenShot("No.24 「リキュール」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック");
		
		// 表示された商品の順番を検証
		assertProductNames("バーボン");
	}
	
	@Test
	@DisplayName("No.25 「リキュール」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック")
	@Order(25)
	void テスト項目No25() {
		// 指定したURLに遷移する
		openURL();
		
		// 「リキュール」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック
		doSearch("リキュール", "", "追加の新しい順");
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/product/list?categoryName=liqueur&productName=&sort=new");
	}
	
	@Test
	@DisplayName("No.26 「ビール」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック")
	@Order(26)
	void テスト項目No26() {
		// 指定したURLに遷移する
		openURL();
		
		// 「ビール」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック
		doSearch("ビール", "", "追加の新しい順");
		
		// スクリーンショットを取得
		takeScreenShot("No.26 「ビール」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック");
		
		// 表示された商品の順番を検証
		assertProductNames("黒ラベル");
	}
	
	@Test
	@DisplayName("No.27 「ビール」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック")
	@Order(27)
	void テスト項目No27() {
		// 指定したURLに遷移する
		openURL();
		
		// 「ビール」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック
		doSearch("ビール", "", "追加の新しい順");
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/product/list?categoryName=beer&productName=&sort=new");
	}
	
	@Test
	@DisplayName("No.28 「おつまみ」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック")
	@Order(28)
	void テスト項目No28() {
		// 指定したURLに遷移する
		openURL();
		
		// 「おつまみ」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック
		doSearch("おつまみ", "", "追加の新しい順");
		
		// スクリーンショットを取得
		takeScreenShot("No.28 「おつまみ」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック");
		
		// 表示された商品の順番を検証
		assertProductNames("ビーフジャーキー");
	}
	
	@Test
	@DisplayName("No.29 「おつまみ」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック")
	@Order(29)
	void テスト項目No29() {
		// 指定したURLに遷移する
		openURL();
		
		// 「おつまみ」、「追加の新しい順」が選択された状態で、「検索」ボタンをクリック
		doSearch("おつまみ", "", "追加の新しい順");
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/product/list?categoryName=food&productName=&sort=new");
	}
	
	@Test
	@DisplayName("No.30 「すべてのカテゴリ」、「追加の新しい順」が選択された状態で、入力欄に「ワイン」と入力し、「検索」ボタンをクリック")
	@Order(30)
	void テスト項目No30() {
		// 指定したURLに遷移する
		openURL();
		
		// 「すべてのカテゴリ」、「追加の新しい順」が選択された状態で、入力欄に「ワイン」と入力し、「検索」ボタンをクリック
		doSearch("すべてのカテゴリ", "ワイン", "追加の新しい順");
		
		// スクリーンショットを取得
		takeScreenShot("No.30 「すべてのカテゴリ」、「追加の新しい順」が選択された状態で、入力欄に「ワイン」と入力し、「検索」ボタンをクリック");
		
		// 表示された商品の順番を検証
		assertProductNames("赤ワイン（フルボディ）", "シュワシュワワイン", "赤ワイン（ライトボディ）");
	}
	
	@Test
	@DisplayName("No.31 「すべてのカテゴリ」、「追加の新しい順」が選択された状態で、入力欄に「ワイン」と入力し、「検索」ボタンをクリック")
	@Order(31)
	void テスト項目No31() {
		// 指定したURLに遷移する
		openURL();
		
		// 「すべてのカテゴリ」、「追加の新しい順」が選択された状態で、入力欄に「ワイン」と入力し、「検索」ボタンをクリック
		doSearch("すべてのカテゴリ", "ワイン", "追加の新しい順");
		
		// URLを検証
		assertEquals("http://localhost:8080/osake-market/product/list?categoryName=&productName=ワイン&sort=new", 
				java.net.URLDecoder.decode(webDriver.getCurrentUrl(), java.nio.charset.StandardCharsets.UTF_8));
	}
	
	@Test
	@DisplayName("No.32 ログインしていない状態で任意のハートボタンをクリック")
	@Order(32)
	void テスト項目No32() {
		// 指定したURLに遷移する
		openURL();
		
		// ハートボタンをクリック
		clickHeart();
		
		// スクリーンショットを取得
		takeScreenShot("No.32 ログインしていない状態で任意のハートボタンをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("ログイン - Osake Market");
	}
	
	@Test
	@DisplayName("No.33 ログインしていない状態で任意のハートボタンをクリック")
	@Order(33)
	void テスト項目No33() {
		// 指定したURLに遷移する
		openURL();
		
		// ハートボタンをクリック
		clickHeart();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/login");
	}
	
	@Test
	@DisplayName("No.34 ログインしていない状態で任意の「カートに追加する」ボタンをクリック")
	@Order(34)
	void テスト項目No34() {
		// 指定したURLに遷移する
		openURL();
		
		// 「カートに追加する」ボタンをクリック
		clickAddCart();
		
		// スクリーンショットを取得
		takeScreenShot("No.34 ログインしていない状態で任意の「カートに追加する」ボタンをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("ログイン - Osake Market");
	}
	
	@Test
	@DisplayName("No.35 ログインしていない状態で任意の「カートに追加する」ボタンをクリック")
	@Order(35)
	void テスト項目No35() {
		// 指定したURLに遷移する
		openURL();
		
		// 「カートに追加する」ボタンをクリック
		clickAddCart();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/login");
	}
	
	@Test
	@DisplayName("No.36 「赤ワイン（フルボディ）」の画像をクリック")
	@Order(36)
	void テスト項目No36() {
		// 指定したURLに遷移する
		openURL();
		
		// 「赤ワイン（フルボディ）」の画像をクリック
		WebElement productImg = webDriver.findElement(By.cssSelector(".product-image"));
		productImg.click();
		
		// スクリーンショットを取得
		takeScreenShot("No.36 「赤ワイン（フルボディ）」の画像をクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("商品詳細 - Osake Market");
	}
	
	@Test
	@DisplayName("No.37 「赤ワイン（フルボディ）」の画像をクリック")
	@Order(37)
	void テスト項目No37() {
		// 指定したURLに遷移する
		openURL();
		
		// 「赤ワイン（フルボディ）」の画像をクリック
		WebElement productImg = webDriver.findElement(By.cssSelector(".product-image"));
		productImg.click();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/product/detail?productId=1");
	}
	
	@Test
	@DisplayName("No.38 ログインした状態で任意のハートボタンをクリック")
	@Order(38)
	void テスト項目No38() {
		// 指定したURLに遷移する
		loginAndOpenURL();
		
		// ハートボタンをクリック
		clickHeart();
		
		// スクリーンショットを取得
		takeScreenShot("No.38 ログインした状態で任意のハートボタンをクリック");
		
		// 画面上部にメッセージが表示されるか検証
		WebElement favoriteMsg = webDriver.findElement(By.id("favorite-msg"));
		assertEquals("お気に入りに追加しました", favoriteMsg.getText());
	}
	
	@Test
	@DisplayName("No.39 ピンク色のハートボタンをクリック")
	@Order(39)
	void テスト項目No39() {
		// 指定したURLに遷移する
		loginAndOpenURL();
		
		// ピンク色のハートボタンを取得
		WebElement coloredheartBtn = webDriver.findElement(By.cssSelector(".product-list-heart-colored"));
		
		// ピンク色のハートボタンをクリック
		coloredheartBtn.click();
		
		// スクリーンショットを取得
		takeScreenShot("No.39 ピンク色のハートボタンをクリック");
		
		// 画面上部にメッセージが表示されるか検証
		WebElement favoriteMsg = webDriver.findElement(By.id("favorite-msg"));
		assertEquals("お気に入りから削除しました", favoriteMsg.getText());
	}
	
	@Test
	@DisplayName("No.40 ログインした状態で「カートに追加する」ボタンをクリック")
	@Order(40)
	void テスト項目No40() {
		// 指定したURLに遷移する
		loginAndOpenURL();
		
		// 「カートに追加する」ボタンをクリック
		clickAddCart();
		
		// スクリーンショットを取得
		takeScreenShot("No.40 ログインした状態で「カートに追加する」ボタンをクリック");
		
		// 画面上部にメッセージが表示されるか検証
		WebElement favoriteMsg = webDriver.findElement(By.id("cart-msg"));
		assertEquals("カートに追加しました", favoriteMsg.getText());
	}
}
