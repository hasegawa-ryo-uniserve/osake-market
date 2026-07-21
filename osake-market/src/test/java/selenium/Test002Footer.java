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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.google.common.io.Files;

@TestMethodOrder(OrderAnnotation.class)
class Test002Footer {
	
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
	
	// スクリーンショットを取得
	private void takeScreenShot(String fileName) {
		// スクリーンショット保存パス
		String screenshotPath = "screenshots\\test002_footer\\";
		
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

	@Test
	@DisplayName("No.1 Osake Marketをクリック")
	@Order(1)
	void テスト項目No1() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「Osake Market」を取得しクリック
		webDriver.findElement(By.cssSelector("footer h1")).click();
		
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
		webDriver.findElement(By.cssSelector("footer h1")).click();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/");
	}
	
	@Test
	@DisplayName("No.3 「商品一覧」をクリック")
	@Order(3)
	void テスト項目No3() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「商品一覧」を取得しクリック
		webDriver.findElement(By.cssSelector(".footer-nav li:first-child a")).click();
		
		// スクリーンショットを取得
		takeScreenShot("No03_「商品一覧」をクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("商品一覧 - Osake Market");
	}
	
	@Test
	@DisplayName("No.4 「商品一覧」をクリック")
	@Order(4)
	void テスト項目No4() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「商品一覧」を取得しクリック
		webDriver.findElement(By.cssSelector(".footer-nav li:first-child a")).click();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/product/list");
	}
	
	@Test
	@DisplayName("No.5 「特定商取引法」をクリック")
	@Order(5)
	void テスト項目No5() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「特定商取引法」を取得しクリック
		webDriver.findElement(By.linkText("特定商取引法")).click();
		
		// スクリーンショットを取得
		takeScreenShot("No05_「特定商取引法」をクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("特定商取引法 - Osake Market");
	}
	
	@Test
	@DisplayName("No.6 「特定商取引法」をクリック")
	@Order(6)
	void テスト項目No6() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「特定商取引法」を取得しクリック
		webDriver.findElement(By.linkText("特定商取引法")).click();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/legal");
	}
	
	@Test
	@DisplayName("No.7 「会社概要」をクリック")
	@Order(7)
	void テスト項目No7() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「会社概要」を取得しクリック
		webDriver.findElement(By.linkText("会社概要")).click();
		
		// スクリーンショットを取得
		takeScreenShot("No07_「会社概要」をクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("会社概要 - Osake Market");
	}
	
	@Test
	@DisplayName("No.8 「会社概要」をクリック")
	@Order(8)
	void テスト項目No8() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「会社概要」を取得しクリック
		webDriver.findElement(By.linkText("会社概要")).click();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/company");
	}
	
	@Test
	@DisplayName("No.9 「お問い合わせ」をクリック")
	@Order(9)
	void テスト項目No9() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「お問い合わせ」を取得しクリック
		webDriver.findElement(By.linkText("お問い合わせ")).click();
		
		// スクリーンショットを取得
		takeScreenShot("No09_「お問い合わせ」をクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("お問い合わせ - Osake Market");
	}
	
	@Test
	@DisplayName("No.10 「お問い合わせ」をクリック")
	@Order(10)
	void テスト項目No10() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「お問い合わせ」を取得しクリック
		webDriver.findElement(By.linkText("お問い合わせ")).click();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/contact");
	}
	
	@Test
	@DisplayName("No.11 「プライバシーポリシー」をクリック")
	@Order(11)
	void テスト項目No11() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「プライバシーポリシー」を取得しクリック
		webDriver.findElement(By.linkText("プライバシーポリシー")).click();
		
		// スクリーンショットを取得
		takeScreenShot("No11_「プライバシーポリシー」をクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("プライバシーポリシー - Osake Market");
	}
	
	@Test
	@DisplayName("No.12 「プライバシーポリシー」をクリック")
	@Order(12)
	void テスト項目No12() {
		// 指定したURLに遷移する
		openHomeURL();
		
		// 「プライバシーポリシー」を取得しクリック
		webDriver.findElement(By.linkText("プライバシーポリシー")).click();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/privacyPolicy");
	}
}
