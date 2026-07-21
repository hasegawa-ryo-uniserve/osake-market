package selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

import com.google.common.io.Files;

@TestMethodOrder(OrderAnnotation.class)
class Test001Header {
	
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
	
	// ホーム画面のURLを開く
	private void openHomeURL() {
		webDriver.get("http://localhost:8080/osake-market/");
	}
	
	// 商品カテゴリをクリック
	private void clickCategory(String category) {
		// 「商品カテゴリ」をクリック
		webDriver.findElement(By.className("category-menu")).click();
		
		// 「商品カテゴリ」の「ワイン」をクリック
		webDriver.findElement(By.linkText(category)).click();
	}
	
	// プルダウンの確認
	private void checkPulldown(String category) {
		// カテゴリのプルダウンで「ワイン」が選択されているか検証
		WebElement categoryName = webDriver.findElement(By.name("categoryName"));
		WebElement productName = webDriver.findElement(By.name("productName"));
		WebElement sort = webDriver.findElement(By.name("sort"));
		
		// プルダウン確認
		final Select categorySelect = new Select(categoryName);
		final Select sortSelect = new Select(sort);
		
		assertEquals(category, categorySelect.getFirstSelectedOption().getText());
		assertEquals("", productName.getText());
		assertEquals("追加の新しい順", sortSelect.getFirstSelectedOption().getText());
	}
	
	// 商品一覧確認
	private void assertProductNames(String...productNames) {
		List<WebElement> productList = webDriver.findElements(By.className("product-name"));
		
		assertEquals(productNames.length, productList.size());
		
		for(int i = 0; i < productNames.length; i++) {
			assertEquals(productNames[i], productList.get(i).getText());
		}
	}
	
	// スクリーンショットを取得
	private void takeScreenShot(String fileName) {
		// スクリーンショット保存パス
		String screenshotPath = "screenshots\\test001_header\\";
		
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
		assertEquals(url, webDriver.getCurrentUrl());
	}
	
	// ログイン
	private void login() {
		// ログイン画面に遷移する
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
	}

	@Test
	@DisplayName("No.1 Osake Marketをクリック")
	@Order(1)
	void テスト項目No1() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「Osake Market」を取得しクリック
		webDriver.findElement(By.cssSelector("header h1")).click();
		
		// スクリーンショットを取得
		takeScreenShot("No.01 Osake Marketをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("Osake Market");
	}
	
	@Test
	@DisplayName("No.2 Osake Marketをクリック")
	@Order(2)
	void テスト項目No2() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「Osake Market」を取得しクリック
		webDriver.findElement(By.cssSelector("header h1")).click();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/");
	}
	
	@Test
	@DisplayName("No.3 「商品カテゴリ」の「ワイン」リンクをクリック")
	@Order(3)
	void テスト項目No3() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「商品カテゴリ」の「ワイン」をクリック
		clickCategory("ワイン");
		
		// スクリーンショットを取得
		takeScreenShot("No03_「商品カテゴリ」の「ワイン」リンクをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("商品一覧 - Osake Market");
		
		// カテゴリのプルダウンで「ワイン」が選択されているか検証
		checkPulldown("ワイン");
		
		// カテゴリが「ワイン」の商品が取得されているか検証
		assertProductNames("赤ワイン（フルボディ）", "赤ワイン（ライトボディ）");
	}
	
	@Test
	@DisplayName("No.4 「商品カテゴリ」の「ワイン」リンクをクリック")
	@Order(4)
	void テスト項目No4() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「商品カテゴリ」の「ワイン」をクリック
		clickCategory("ワイン");
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/product/list?categoryName=wine&productName=&sort=new");
	}
	
	@Test
	@DisplayName("No.5 「商品カテゴリ」の「スパークリングワイン」リンクをクリック")
	@Order(5)
	void テスト項目No5() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「商品カテゴリ」の「スパークリングワイン」をクリック
		clickCategory("スパークリングワイン");
		
		// スクリーンショットを取得
		takeScreenShot("No05_「商品カテゴリ」の「スパークリングワイン」リンクをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("商品一覧 - Osake Market");
		
		// カテゴリのプルダウンで「スパークリングワイン」が選択されているか検証
		checkPulldown("スパークリングワイン");
		
		// カテゴリが「スパークリングワイン」の商品が取得されているか検証
		assertProductNames("シュワシュワワイン");
	}
	
	@Test
	@DisplayName("No.6 「商品カテゴリ」の「スパークリングワイン」リンクをクリック")
	@Order(6)
	void テスト項目No6() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「商品カテゴリ」の「スパークリングワイン」をクリック
		clickCategory("スパークリングワイン");
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/product/list?categoryName=sparklingWine&productName=&sort=new");
	}
	
	@Test
	@DisplayName("No.7 「商品カテゴリ」の「ウイスキー」リンクをクリック")
	@Order(7)
	void テスト項目No7() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「商品カテゴリ」の「ウイスキー」をクリック
		clickCategory("ウイスキー");
		
		// スクリーンショットを取得
		takeScreenShot("No07_「商品カテゴリ」の「ウイスキー」リンクをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("商品一覧 - Osake Market");
		
		// カテゴリのプルダウンで「ウイスキー」が選択されているか検証
		checkPulldown("ウイスキー");
		
		// カテゴリが「ワイン」の商品が取得されているか検証
		assertProductNames("コクのあるウイスキー");
	}
	
	@Test
	@DisplayName("No.8 「商品カテゴリ」の「ウイスキー」リンクをクリック")
	@Order(8)
	void テスト項目No8() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「商品カテゴリ」の「ウイスキー」をクリック
		clickCategory("ウイスキー");
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/product/list?categoryName=whisky&productName=&sort=new");
	}
	
	@Test
	@DisplayName("No.9 「商品カテゴリ」の「ブランデー」リンクをクリック")
	@Order(9)
	void テスト項目No9() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「商品カテゴリ」の「ブランデー」をクリック
		clickCategory("ブランデー");
		
		// スクリーンショットを取得
		takeScreenShot("No09_「商品カテゴリ」の「ブランデー」リンクをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("商品一覧 - Osake Market");
		
		// カテゴリのプルダウンで「ブランデー」が選択されているか検証
		checkPulldown("ブランデー");
		
		// カテゴリが「ブランデー」の商品が取得されているか検証
		assertProductNames("高級ブランデー");
	}
	
	@Test
	@DisplayName("No.10 「商品カテゴリ」の「ブランデー」リンクをクリック")
	@Order(10)
	void テスト項目No10() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「商品カテゴリ」の「ブランデー」をクリック
		clickCategory("ブランデー");
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/product/list?categoryName=brandy&productName=&sort=new");
	}
	
	@Test
	@DisplayName("No.11 「商品カテゴリ」の「焼酎」リンクをクリック")
	@Order(11)
	void テスト項目No11() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「商品カテゴリ」の「焼酎」をクリック
		clickCategory("焼酎");
		
		// スクリーンショットを取得
		takeScreenShot("No11_「商品カテゴリ」の「焼酎」リンクをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("商品一覧 - Osake Market");
		
		// カテゴリのプルダウンで「焼酎」が選択されているか検証
		checkPulldown("焼酎");
		
		// カテゴリが「焼酎」の商品が取得されているか検証
		assertProductNames("芋焼酎");
	}
	
	@Test
	@DisplayName("No.12 「商品カテゴリ」の「焼酎」リンクをクリック")
	@Order(12)
	void テスト項目No12() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「商品カテゴリ」の「焼酎」をクリック
		clickCategory("焼酎");
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/product/list?categoryName=shochu&productName=&sort=new");
	}
	
	@Test
	@DisplayName("No.13 「商品カテゴリ」の「日本酒」リンクをクリック")
	@Order(13)
	void テスト項目No13() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「商品カテゴリ」の「日本酒」をクリック
		clickCategory("日本酒");
		
		// スクリーンショットを取得
		takeScreenShot("No13_「商品カテゴリ」の「日本酒」リンクをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("商品一覧 - Osake Market");
		
		// カテゴリのプルダウンで「日本酒」が選択されているか検証
		checkPulldown("日本酒");
		
		// カテゴリが「日本酒」の商品が取得されているか検証
		assertProductNames("神酒");
	}
	
	@Test
	@DisplayName("No.14 「商品カテゴリ」の「日本酒」リンクをクリック")
	@Order(14)
	void テスト項目No14() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「商品カテゴリ」の「日本酒」をクリック
		clickCategory("日本酒");
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/product/list?categoryName=japaneseSake&productName=&sort=new");
	}
	
	@Test
	@DisplayName("No.15 「商品カテゴリ」の「リキュール」リンクをクリック")
	@Order(15)
	void テスト項目No15() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「商品カテゴリ」の「リキュール」をクリック
		clickCategory("リキュール");
		
		// スクリーンショットを取得
		takeScreenShot("No15_「商品カテゴリ」の「リキュール」リンクをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("商品一覧 - Osake Market");
		
		// カテゴリのプルダウンで「リキュール」が選択されているか検証
		checkPulldown("リキュール");
		
		// カテゴリが「リキュール」の商品が取得されているか検証
		assertProductNames("バーボン");
	}
	
	@Test
	@DisplayName("No.16 「商品カテゴリ」の「リキュール」リンクをクリック")
	@Order(16)
	void テスト項目No16() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「商品カテゴリ」の「リキュール」をクリック
		clickCategory("リキュール");
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/product/list?categoryName=liqueur&productName=&sort=new");
	}
	
	@Test
	@DisplayName("No.17 「商品カテゴリ」の「ビール」リンクをクリック")
	@Order(17)
	void テスト項目No17() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「商品カテゴリ」の「ビール」をクリック
		clickCategory("ビール");
		
		// スクリーンショットを取得
		takeScreenShot("No17_「商品カテゴリ」の「ビール」リンクをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("商品一覧 - Osake Market");
		
		// カテゴリのプルダウンで「ビール」が選択されているか検証
		checkPulldown("ビール");
		
		// カテゴリが「ビール」の商品が取得されているか検証
		assertProductNames("黒ラベル");
	}
	
	@Test
	@DisplayName("No.18 「商品カテゴリ」の「ビール」リンクをクリック")
	@Order(18)
	void テスト項目No18() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「商品カテゴリ」の「ビール」をクリック
		clickCategory("ビール");
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/product/list?categoryName=beer&productName=&sort=new");
	}
	
	@Test
	@DisplayName("No.19 「商品カテゴリ」の「おつまみ」リンクをクリック")
	@Order(19)
	void テスト項目No19() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「商品カテゴリ」の「おつまみ」をクリック
		clickCategory("おつまみ");
		
		// スクリーンショットを取得
		takeScreenShot("No19_「商品カテゴリ」の「おつまみ」リンクをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("商品一覧 - Osake Market");
		
		// カテゴリのプルダウンで「おつまみ」が選択されているか検証
		checkPulldown("おつまみ");
		
		// カテゴリが「おつまみ」の商品が取得されているか検証
		assertProductNames("ビーフジャーキー");
	}
	
	@Test
	@DisplayName("No.20 「商品カテゴリ」の「おつまみ」リンクをクリック")
	@Order(20)
	void テスト項目No20() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「商品カテゴリ」の「おつまみ」をクリック
		clickCategory("おつまみ");
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/product/list?categoryName=food&productName=&sort=new");
	}
	
	@Test
	@DisplayName("No.21 「商品一覧」をクリック")
	@Order(21)
	void テスト項目No21() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「商品一覧」を取得しクリック
		webDriver.findElement(By.className("nav-product-list")).click();
		
		// スクリーンショットを取得
		takeScreenShot("No21_「商品一覧」をクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("商品一覧 - Osake Market");
		
		// カテゴリのプルダウンで「すべてのカテゴリ」が選択されているか検証
		checkPulldown("すべてのカテゴリ");
		
		// カテゴリが「すべてのカテゴリ」の商品が取得されているか検証
		assertProductNames("赤ワイン（フルボディ）", "シュワシュワワイン", "コクのあるウイスキー", 
				"高級ブランデー", "芋焼酎", "神酒", "バーボン", "黒ラベル", "ビーフジャーキー",
				"赤ワイン（ライトボディ）");
	}
	
	@Test
	@DisplayName("No.22 「商品一覧」をクリック")
	@Order(22)
	void テスト項目No22() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「商品一覧」を取得しクリック
		webDriver.findElement(By.className("nav-product-list")).click();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/product/list");
	}
	
	@Test
	@DisplayName("No.23 非ログイン状態で「通知」をクリック")
	@Order(23)
	void テスト項目No23() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「通知」を取得しクリック
		webDriver.findElement(By.className("notification")).click();
		
		// スクリーンショットを取得
		takeScreenShot("No23_非ログイン状態で「通知」をクリック");
		
		// ページタイトルを用いてログインページか検証
		assertTitle("ログイン - Osake Market");
	}
	
	@Test
	@DisplayName("No.24 非ログイン状態で「通知」をクリック")
	@Order(24)
	void テスト項目No24() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「通知」を取得しクリック
		webDriver.findElement(By.className("notification")).click();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/login");
	}
	
	@Test
	@DisplayName("No.25 非ログイン状態で「お気に入り」をクリック")
	@Order(25)
	void テスト項目No25() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「お気に入り」を取得しクリック
		webDriver.findElement(By.className("favorite")).click();
		
		// スクリーンショットを取得
		takeScreenShot("No25_非ログイン状態で「お気に入り」をクリック");
		
		// ページタイトルを用いてログインページか検証
		assertTitle("ログイン - Osake Market");
	}
	
	@Test
	@DisplayName("No.26 非ログイン状態で「お気に入り」をクリック")
	@Order(26)
	void テスト項目No26() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「お気に入り」を取得しクリック
		webDriver.findElement(By.className("favorite")).click();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/login");
	}
	
	@Test
	@DisplayName("No.27 非ログイン状態で「カート」をクリック")
	@Order(27)
	void テスト項目No27() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「カート」を取得しクリック
		webDriver.findElement(By.className("cart")).click();
		
		// スクリーンショットを取得
		takeScreenShot("No27_非ログイン状態で「カート」をクリック");
		
		// ページタイトルを用いてログインページか検証
		assertTitle("ログイン - Osake Market");
	}
	
	@Test
	@DisplayName("No.28 非ログイン状態で「カート」をクリック")
	@Order(28)
	void テスト項目No28() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「カート」を取得しクリック
		webDriver.findElement(By.className("cart")).click();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/login");
	}
	
	@Test
	@DisplayName("No.29 非ログイン状態で「ログイン」をクリック")
	@Order(29)
	void テスト項目No29() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「ログイン」を取得しクリック
		webDriver.findElement(By.className("user")).click();
		
		// スクリーンショットを取得
		takeScreenShot("No29_非ログイン状態で「ログイン」をクリック");
		
		// ページタイトルを用いてログインページか検証
		assertTitle("ログイン - Osake Market");
	}
	
	@Test
	@DisplayName("No.30 非ログイン状態で「ログイン」をクリック")
	@Order(30)
	void テスト項目No30() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「ログイン」を取得しクリック
		webDriver.findElement(By.className("user")).click();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/login");
	}
	
	@Test
	@DisplayName("No.31 ログインした状態で「通知」をクリック")
	@Order(31)
	void テスト項目No31() {
		// ログイン
		login();
		
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「通知」を取得しクリック
		webDriver.findElement(By.className("notification")).click();
		
		// スクリーンショットを取得
		takeScreenShot("No31_ログインした状態で「通知」をクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("通知一覧 - Osake Market");
	}
	
	@Test
	@DisplayName("No.32 ログインした状態で「通知」をクリック")
	@Order(32)
	void テスト項目No32() {
		// ログイン
		login();
		
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「通知」を取得しクリック
		webDriver.findElement(By.className("notification")).click();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/notification/list");
	}
	
	@Test
	@DisplayName("No.33 ログインした状態で「お気に入り」をクリック")
	@Order(33)
	void テスト項目No33() {
		// ログイン
		login();
		
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「お気に入り」を取得しクリック
		webDriver.findElement(By.className("favorite")).click();
		
		// スクリーンショットを取得
		takeScreenShot("No33_ログインした状態で「お気に入り」をクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("お気に入り一覧 - Osake Market");
	}
	
	@Test
	@DisplayName("No.34 ログインした状態で「お気に入り」をクリック")
	@Order(34)
	void テスト項目No34() {
		// ログイン
		login();
		
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「お気に入り」を取得しクリック
		webDriver.findElement(By.className("favorite")).click();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/favorite/list");
	}
	
	@Test
	@DisplayName("No.35 ログインした状態で「カート」をクリック")
	@Order(35)
	void テスト項目No35() {
		// ログイン
		login();
		
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「カート」を取得しクリック
		webDriver.findElement(By.className("cart")).click();
		
		// スクリーンショットを取得
		takeScreenShot("No35_ログインした状態で「カート」をクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("カート - Osake Market");
	}
	
	@Test
	@DisplayName("No.36 ログインした状態で「カート」をクリック")
	@Order(36)
	void テスト項目No36() {
		// ログイン
		login();
		
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「カート」を取得しクリック
		webDriver.findElement(By.className("cart")).click();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/cart");
	}
	
	@Test
	@DisplayName("No.37 ログインした状態で「マイページ」をクリック")
	@Order(37)
	void テスト項目No37() {
		// ログイン
		login();
		
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「マイページ」を取得しクリック
		webDriver.findElement(By.className("user")).click();
		
		// スクリーンショットを取得
		takeScreenShot("No37_ログインした状態で「マイページ」をクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("マイページ - Osake Market");
	}
	
	@Test
	@DisplayName("No.38 ログインした状態で「マイページ」をクリック")
	@Order(38)
	void テスト項目No38() {
		// ログイン
		login();
		
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「マイページ」を取得しクリック
		webDriver.findElement(By.className("user")).click();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/mypage");
	}
}
