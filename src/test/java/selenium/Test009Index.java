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
class Test009Index {

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
	private void openURL() {
		webDriver.get("http://localhost:8080/osake-market/");
	}
	
	// スクリーンショットを取得
	private void takeScreenShot(String fileName) {
		// スクリーンショット保存パス
		String screenshotPath = "screenshots\\test009_index\\";
		
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
	
	// 「カテゴリから選ぶ」ボタンをクリック
	private void selectCategory(int category) {
		List<WebElement> categoryButtons = webDriver.findElements(By.cssSelector(".home-category-link"));
		WebElement categoryButton = categoryButtons.get(category);
		categoryButton.click();
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
	
	@Test
	@DisplayName("No.1 ブラウザでhttp://localhost:8080/osake-market/にアクセス")
	@Order(1)
	void テスト項目No1() {
		// 指定したURLに遷移する
		openURL();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/");
	}
	
	@Test
	@DisplayName("No.2 ブラウザでhttp://localhost:8080/osake-market/にアクセス")
	@Order(2)
	void テスト項目No2() {
		// 指定したURLに遷移する
		openURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.02 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("Osake Market");
	}
	
	@Test
	@DisplayName("No.3 「商品を探す」ボタンをクリック")
	@Order(3)
	void テスト項目No3() {
		// 指定したURLに遷移する
		openURL();
		
		// 「商品を探す」ボタンをクリック
		final WebElement findProductButton = webDriver.findElement(By.cssSelector(".top-img .wrapper .red-btn"));
		findProductButton.click();
		
		// スクリーンショットを取得
		takeScreenShot("No.03 「商品を探す」ボタンをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("商品一覧 - Osake Market");
	}
	
	@Test
	@DisplayName("No.4 「商品を探す」ボタンをクリック")
	@Order(4)
	void テスト項目No4() {
		// 指定したURLに遷移する
		openURL();
		
		// 「商品を探す」ボタンをクリック
		final WebElement findProductButton = webDriver.findElement(By.cssSelector(".top-img .wrapper .red-btn"));
		findProductButton.click();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/product/list");
	}
	
	@Test
	@DisplayName("No.5 「ワイン」ボタンをクリック")
	@Order(5)
	void テスト項目No5() {
		// 指定したURLに遷移する
		openURL();
		
		// 「ワイン」ボタンをクリック
		selectCategory(0);
		
		// スクリーンショットを取得
		takeScreenShot("No.05 「ワイン」ボタンをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("商品一覧 - Osake Market");
		
		// カテゴリのプルダウンで「ワイン」が選択されているか検証
		checkPulldown("ワイン");
		
		// カテゴリが「ワイン」の商品が取得されているか検証
		assertProductNames("赤ワイン（フルボディ）", "赤ワイン（ライトボディ）");
	}
	
	@Test
	@DisplayName("No.6 「ワイン」ボタンをクリック")
	@Order(6)
	void テスト項目No6() {
		// 指定したURLに遷移する
		openURL();
		
		// 「ワイン」ボタンをクリック
		selectCategory(0);
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/product/list?categoryName=wine&productName=&sort=new");
	}
	
	@Test
	@DisplayName("No.7 「スパークリングワイン」ボタンをクリック")
	@Order(7)
	void テスト項目No7() {
		// 指定したURLに遷移する
		openURL();
		
		// 「スパークリングワイン」ボタンをクリック
		selectCategory(1);
		
		// スクリーンショットを取得
		takeScreenShot("No.07 「スパークリングワイン」ボタンをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("商品一覧 - Osake Market");
		
		// カテゴリのプルダウンで「スパークリングワイン」が選択されているか検証
		checkPulldown("スパークリングワイン");
		
		// カテゴリが「スパークリングワイン」の商品が取得されているか検証
		assertProductNames("シュワシュワワイン");
	}
	
	@Test
	@DisplayName("No.8 「スパークリングワイン」ボタンをクリック")
	@Order(8)
	void テスト項目No8() {
		// 指定したURLに遷移する
		openURL();
		
		// 「スパークリングワイン」ボタンをクリック
		selectCategory(1);
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/product/list?categoryName=sparklingWine&productName=&sort=new");
	}
	
	@Test
	@DisplayName("No.9 「ウイスキー」ボタンをクリック")
	@Order(9)
	void テスト項目No9() {
		// 指定したURLに遷移する
		openURL();
		
		// 「ウイスキー」ボタンをクリック
		selectCategory(2);
		
		// スクリーンショットを取得
		takeScreenShot("No.09 「ウイスキー」ボタンをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("商品一覧 - Osake Market");
		
		// カテゴリのプルダウンで「ウイスキー」が選択されているか検証
		checkPulldown("ウイスキー");
		
		// カテゴリが「ウイスキー」の商品が取得されているか検証
		assertProductNames("コクのあるウイスキー");
	}
	
	@Test
	@DisplayName("No.10 「ウイスキー」ボタンをクリック")
	@Order(10)
	void テスト項目No10() {
		// 指定したURLに遷移する
		openURL();
		
		// 「ウイスキー」ボタンをクリック
		selectCategory(2);
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/product/list?categoryName=whisky&productName=&sort=new");
	}
	
	@Test
	@DisplayName("No.11 「ブランデー」ボタンをクリック")
	@Order(11)
	void テスト項目No11() {
		// 指定したURLに遷移する
		openURL();
		
		// 「ブランデー」ボタンをクリック
		selectCategory(3);
		
		// スクリーンショットを取得
		takeScreenShot("No.11 「ブランデー」ボタンをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("商品一覧 - Osake Market");
		
		// カテゴリのプルダウンで「ブランデー」が選択されているか検証
		checkPulldown("ブランデー");
		
		// カテゴリが「ブランデー」の商品が取得されているか検証
		assertProductNames("高級ブランデー");
	}
	
	@Test
	@DisplayName("No.12 「ブランデー」ボタンをクリック")
	@Order(12)
	void テスト項目No12() {
		// 指定したURLに遷移する
		openURL();
		
		// 「ブランデー」ボタンをクリック
		selectCategory(3);
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/product/list?categoryName=brandy&productName=&sort=new");
	}
	
	@Test
	@DisplayName("No.13 「焼酎」ボタンをクリック")
	@Order(13)
	void テスト項目No13() {
		// 指定したURLに遷移する
		openURL();
		
		// 「焼酎」ボタンをクリック
		selectCategory(4);
		
		// スクリーンショットを取得
		takeScreenShot("No.13 「焼酎」ボタンをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("商品一覧 - Osake Market");
		
		// カテゴリのプルダウンで「焼酎」が選択されているか検証
		checkPulldown("焼酎");
		
		// カテゴリが「焼酎」の商品が取得されているか検証
		assertProductNames("芋焼酎");
	}
	
	@Test
	@DisplayName("No.14 「焼酎」ボタンをクリック")
	@Order(14)
	void テスト項目No14() {
		// 指定したURLに遷移する
		openURL();
		
		// 「焼酎」ボタンをクリック
		selectCategory(4);
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/product/list?categoryName=shochu&productName=&sort=new");
	}
	
	@Test
	@DisplayName("No.15 「日本酒」ボタンをクリック")
	@Order(15)
	void テスト項目No15() {
		// 指定したURLに遷移する
		openURL();
		
		// 「日本酒」ボタンをクリック
		selectCategory(5);
		
		// スクリーンショットを取得
		takeScreenShot("No.15 「日本酒」ボタンをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("商品一覧 - Osake Market");
		
		// カテゴリのプルダウンで「日本酒」が選択されているか検証
		checkPulldown("日本酒");
		
		// カテゴリが「日本酒」の商品が取得されているか検証
		assertProductNames("神酒");
	}
	
	@Test
	@DisplayName("No.16 「日本酒」ボタンをクリック")
	@Order(16)
	void テスト項目No16() {
		// 指定したURLに遷移する
		openURL();
		
		// 「日本酒」ボタンをクリック
		selectCategory(5);
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/product/list?categoryName=japaneseSake&productName=&sort=new");
	}
	
	@Test
	@DisplayName("No.17 「リキュール」ボタンをクリック")
	@Order(17)
	void テスト項目No17() {
		// 指定したURLに遷移する
		openURL();
		
		// 「リキュール」ボタンをクリック
		selectCategory(6);
		
		// スクリーンショットを取得
		takeScreenShot("No.17 「リキュール」ボタンをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("商品一覧 - Osake Market");
		
		// カテゴリのプルダウンで「リキュール」が選択されているか検証
		checkPulldown("リキュール");
		
		// カテゴリが「リキュール」の商品が取得されているか検証
		assertProductNames("バーボン");
	}
	
	@Test
	@DisplayName("No.18 「リキュール」ボタンをクリック")
	@Order(18)
	void テスト項目No18() {
		// 指定したURLに遷移する
		openURL();
		
		// 「リキュール」ボタンをクリック
		selectCategory(6);
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/product/list?categoryName=liqueur&productName=&sort=new");
	}
	
	@Test
	@DisplayName("No.19 「ビール」ボタンをクリック")
	@Order(19)
	void テスト項目No19() {
		// 指定したURLに遷移する
		openURL();
		
		// 「ビール」ボタンをクリック
		selectCategory(7);
		
		// スクリーンショットを取得
		takeScreenShot("No.19 「ビール」ボタンをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("商品一覧 - Osake Market");
		
		// カテゴリのプルダウンで「ビール」が選択されているか検証
		checkPulldown("ビール");
		
		// カテゴリが「ビール」の商品が取得されているか検証
		assertProductNames("黒ラベル");
	}
	
	@Test
	@DisplayName("No.20 「黒ラベル」ボタンをクリック")
	@Order(20)
	void テスト項目No20() {
		// 指定したURLに遷移する
		openURL();
		
		// 「黒ラベル」ボタンをクリック
		selectCategory(7);
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/product/list?categoryName=beer&productName=&sort=new");
	}
	
	@Test
	@DisplayName("No.21 「おつまみ」ボタンをクリック")
	@Order(21)
	void テスト項目No21() {
		// 指定したURLに遷移する
		openURL();
		
		// 「おつまみ」ボタンをクリック
		selectCategory(8);
		
		// スクリーンショットを取得
		takeScreenShot("No.21 「おつまみ」ボタンをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("商品一覧 - Osake Market");
		
		// カテゴリのプルダウンで「おつまみ」が選択されているか検証
		checkPulldown("おつまみ");
		
		// カテゴリが「おつまみ」の商品が取得されているか検証
		assertProductNames("ビーフジャーキー");
	}
	
	@Test
	@DisplayName("No.22 「おつまみ」ボタンをクリック")
	@Order(22)
	void テスト項目No22() {
		// 指定したURLに遷移する
		openURL();
		
		// 「おつまみ」ボタンをクリック
		selectCategory(8);
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/product/list?categoryName=food&productName=&sort=new");
	}
}
