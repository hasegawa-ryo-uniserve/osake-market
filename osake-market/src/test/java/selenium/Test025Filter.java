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

import com.google.common.io.Files;

@TestMethodOrder(OrderAnnotation.class)
class Test025Filter {
	
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
	
	// URLを開く
	private void openURL(String url) {
		webDriver.get(url);
		
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
		String screenshotPath = "screenshots\\test025_filter\\";
		
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
	
	@Test
	@DisplayName("No.1 ログインしていない状態でブラウザでhttp://localhost:8080/osake-market/にアクセス")
	@Order(1)
	void テスト項目No1() {
		// URLを開く
		openURL("http://localhost:8080/osake-market/");
		
		// スクリーンショットを取得
		takeScreenShot("No.01 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("Osake Market");
	}
	
	@Test
	@DisplayName("No.2 ログインしていない状態でブラウザでhttp://localhost:8080/osake-market/register/userにアクセス")
	@Order(2)
	void テスト項目No2() {
		// URLを開く
		openURL("http://localhost:8080/osake-market/register/user");
		
		// スクリーンショットを取得
		takeScreenShot("No.02 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("新規会員登録(入力) - Osake Market");
	}
	
	@Test
	@DisplayName("No.3 ログインしていない状態でブラウザでhttp://localhost:8080/osake-market/register/user/confirmにアクセス")
	@Order(3)
	void テスト項目No3() {
		// URLを開く
		openURL("http://localhost:8080/osake-market/register/user/confirm");
		
		// スクリーンショットを取得
		takeScreenShot("No.03 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("新規会員登録(入力) - Osake Market");
	}
	
	@Test
	@DisplayName("No.4 ログインしていない状態でブラウザでhttp://localhost:8080/osake-market/register/user/doneにアクセス")
	@Order(4)
	void テスト項目No4() {
		// URLを開く
		openURL("http://localhost:8080/osake-market/register/user/done");
		
		// スクリーンショットを取得
		takeScreenShot("No.04 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("新規会員登録(入力) - Osake Market");
	}
	
	@Test
	@DisplayName("No.5 ログインしていない状態でブラウザでhttp://localhost:8080/osake-market/loginにアクセス")
	@Order(5)
	void テスト項目No5() {
		// URLを開く
		openURL("http://localhost:8080/osake-market/login");
		
		// スクリーンショットを取得
		takeScreenShot("No.05 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("ログイン - Osake Market");
	}
	
	@Test
	@DisplayName("No.6 ログインしていない状態でブラウザでhttp://localhost:8080/osake-market/reset/password/mailにアクセス")
	@Order(6)
	void テスト項目No6() {
		// URLを開く
		openURL("http://localhost:8080/osake-market/reset/password/mail");
		
		// スクリーンショットを取得
		takeScreenShot("No.06 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("パスワードリセット(メールアドレス入力) - Osake Market");
	}
	
	@Test
	@DisplayName("No.7 ログインしていない状態でブラウザでhttp://localhost:8080/osake-market/reset/password/update/completeにアクセス")
	@Order(7)
	void テスト項目No7() {
		// URLを開く
		openURL("http://localhost:8080/osake-market/reset/password/update/complete");
		
		// スクリーンショットを取得
		takeScreenShot("No.07 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("パスワードリセット(パスワードの再設定完了) - Osake Market");
	}
	
	@Test
	@DisplayName("No.8 ログインしていない状態でブラウザでhttp://localhost:8080/osake-market/product/listにアクセス")
	@Order(8)
	void テスト項目No8() {
		// URLを開く
		openURL("http://localhost:8080/osake-market/product/list");
		
		// スクリーンショットを取得
		takeScreenShot("No.08 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("商品一覧 - Osake Market");
	}
	
	@Test
	@DisplayName("No.9 ログインしていない状態でブラウザでhttp://localhost:8080/osake-market/product/detail?productId=1にアクセス")
	@Order(9)
	void テスト項目No9() {
		// URLを開く
		openURL("http://localhost:8080/osake-market/product/detail?productId=1");
		
		// スクリーンショットを取得
		takeScreenShot("No.09 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("商品詳細 - Osake Market");
	}
	
	@Test
	@DisplayName("No.10 ログインしていない状態でブラウザでhttp://localhost:8080/osake-market/cartにアクセス")
	@Order(10)
	void テスト項目No10() {
		// URLを開く
		openURL("http://localhost:8080/osake-market/cart");
		
		// スクリーンショットを取得
		takeScreenShot("No.10 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("ログイン - Osake Market");
	}
	
	@Test
	@DisplayName("No.11 ログインしていない状態でブラウザでhttp://localhost:8080/osake-market/order/inputにアクセス")
	@Order(11)
	void テスト項目No11() {
		// URLを開く
		openURL("http://localhost:8080/osake-market/order/input");
		
		// スクリーンショットを取得
		takeScreenShot("No.11 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("ログイン - Osake Market");
	}
	
	@Test
	@DisplayName("No.12 ログインしていない状態でブラウザでhttp://localhost:8080/osake-market/order/confirmにアクセス")
	@Order(12)
	void テスト項目No12() {
		// URLを開く
		openURL("http://localhost:8080/osake-market/order/confirm");
		
		// スクリーンショットを取得
		takeScreenShot("No.12 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("ログイン - Osake Market");
	}
	
	@Test
	@DisplayName("No.13 ログインしていない状態でブラウザでhttp://localhost:8080/osake-market/order/completeにアクセス")
	@Order(13)
	void テスト項目No13() {
		// URLを開く
		openURL("http://localhost:8080/osake-market/order/complete");
		
		// スクリーンショットを取得
		takeScreenShot("No.13 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("ログイン - Osake Market");
	}
	
	@Test
	@DisplayName("No.14 ログインしていない状態でブラウザでhttp://localhost:8080/osake-market/order/failureにアクセス")
	@Order(14)
	void テスト項目No14() {
		// URLを開く
		openURL("http://localhost:8080/osake-market/order/failure");
		
		// スクリーンショットを取得
		takeScreenShot("No.14 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("ログイン - Osake Market");
	}
	
	@Test
	@DisplayName("No.15 ログインしていない状態でブラウザでhttp://localhost:8080/osake-market/favorite/listにアクセス")
	@Order(15)
	void テスト項目No15() {
		// URLを開く
		openURL("http://localhost:8080/osake-market/favorite/list");
		
		// スクリーンショットを取得
		takeScreenShot("No.15 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("ログイン - Osake Market");
	}
	
	@Test
	@DisplayName("No.16 ログインしていない状態でブラウザでhttp://localhost:8080/osake-market/notification/listにアクセス")
	@Order(16)
	void テスト項目No16() {
		// URLを開く
		openURL("http://localhost:8080/osake-market/notification/list");
		
		// スクリーンショットを取得
		takeScreenShot("No.16 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("ログイン - Osake Market");
	}
	
	@Test
	@DisplayName("No.17 ログインしていない状態でブラウザでhttp://localhost:8080/osake-market/notification/detail?notificationId=1にアクセス")
	@Order(17)
	void テスト項目No17() {
		// URLを開く
		openURL("http://localhost:8080/osake-market/notification/detail?notificationId=1");
		
		// スクリーンショットを取得
		takeScreenShot("No.17 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("ログイン - Osake Market");
	}
	
	@Test
	@DisplayName("No.18 ログインしていない状態でブラウザでhttp://localhost:8080/osake-market/contactにアクセス")
	@Order(18)
	void テスト項目No18() {
		// URLを開く
		openURL("http://localhost:8080/osake-market/contact");
		
		// スクリーンショットを取得
		takeScreenShot("No.18 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("お問い合わせ - Osake Market");
	}
	
	@Test
	@DisplayName("No.19 ログインしていない状態でブラウザでhttp://localhost:8080/osake-market/companyにアクセス")
	@Order(19)
	void テスト項目No19() {
		// URLを開く
		openURL("http://localhost:8080/osake-market/company");
		
		// スクリーンショットを取得
		takeScreenShot("No.19 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("会社概要 - Osake Market");
	}
	
	@Test
	@DisplayName("No.20 ログインしていない状態でブラウザでhttp://localhost:8080/osake-market/privacyPolicyにアクセス")
	@Order(20)
	void テスト項目No20() {
		// URLを開く
		openURL("http://localhost:8080/osake-market/privacyPolicy");
		
		// スクリーンショットを取得
		takeScreenShot("No.20 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("プライバシーポリシー - Osake Market");
	}
	
	@Test
	@DisplayName("No.21 ログインしていない状態でブラウザでhttp://localhost:8080/osake-market/legalにアクセス")
	@Order(21)
	void テスト項目No21() {
		// URLを開く
		openURL("http://localhost:8080/osake-market/legal");
		
		// スクリーンショットを取得
		takeScreenShot("No.21 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("特定商取引法 - Osake Market");
	}
	
	@Test
	@DisplayName("No.22 ログインしていない状態でブラウザでhttp://localhost:8080/osake-market/mypageにアクセス")
	@Order(22)
	void テスト項目No22() {
		// URLを開く
		openURL("http://localhost:8080/osake-market/mypage");
		
		// スクリーンショットを取得
		takeScreenShot("No.22 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("ログイン - Osake Market");
	}
	
	@Test
	@DisplayName("No.23 ログインしていない状態でブラウザでhttp://localhost:8080/osake-market/update/user/formにアクセス")
	@Order(23)
	void テスト項目No23() {
		// URLを開く
		openURL("http://localhost:8080/osake-market/update/user/form");
		
		// スクリーンショットを取得
		takeScreenShot("No.23 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("ログイン - Osake Market");
	}
	
	@Test
	@DisplayName("No.24 ログインしていない状態でブラウザでhttp://localhost:8080/osake-market/order/listにアクセス")
	@Order(24)
	void テスト項目No24() {
		// URLを開く
		openURL("http://localhost:8080/osake-market/order/list");
		
		// スクリーンショットを取得
		takeScreenShot("No.24 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("ログイン - Osake Market");
	}
	
	@Test
	@DisplayName("No.25 ログインしていない状態でブラウザでhttp://localhost:8080/osake-market/order/detail?orderId=1にアクセス")
	@Order(25)
	void テスト項目No25() {
		// URLを開く
		openURL("http://localhost:8080/osake-market/order/detail?orderId=1");
		
		// スクリーンショットを取得
		takeScreenShot("No.25 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("ログイン - Osake Market");
	}
	
	@Test
	@DisplayName("No.26 ログインした状態でブラウザでhttp://localhost:8080/osake-market/にアクセス")
	@Order(26)
	void テスト項目No26() {
		// ログインする		
		openLoginURL();
		
		// URLを開く
		openURL("http://localhost:8080/osake-market/");
		
		// スクリーンショットを取得
		takeScreenShot("No.26 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("Osake Market");
	}
	
	@Test
	@DisplayName("No.27 ログインした状態でブラウザでhttp://localhost:8080/osake-market/register/userにアクセス")
	@Order(27)
	void テスト項目No27() {
		// ログインする		
		openLoginURL();
		
		// URLを開く
		openURL("http://localhost:8080/osake-market/register/user");
		
		// スクリーンショットを取得
		takeScreenShot("No.27 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("マイページ - Osake Market");
	}
	
	@Test
	@DisplayName("No.28 ログインした状態でブラウザでhttp://localhost:8080/osake-market/register/user/confirmにアクセス")
	@Order(28)
	void テスト項目No28() {
		// ログインする		
		openLoginURL();
		
		// URLを開く
		openURL("http://localhost:8080/osake-market/register/user/confirm");
		
		// スクリーンショットを取得
		takeScreenShot("No.28 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("マイページ - Osake Market");
	}
	
	@Test
	@DisplayName("No.29 ログインした状態でブラウザでhttp://localhost:8080/osake-market/register/user/doneにアクセス")
	@Order(29)
	void テスト項目No29() {
		// ログインする		
		openLoginURL();
		
		// URLを開く
		openURL("http://localhost:8080/osake-market/register/user/done");
		
		// スクリーンショットを取得
		takeScreenShot("No.29 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("マイページ - Osake Market");
	}
	
	@Test
	@DisplayName("No.30 ログインした状態でブラウザでhttp://localhost:8080/osake-market/loginにアクセス")
	@Order(30)
	void テスト項目No30() {
		// ログインする		
		openLoginURL();
		
		// URLを開く
		openURL("http://localhost:8080/osake-market/login");
		
		// スクリーンショットを取得
		takeScreenShot("No.30 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("Osake Market");
	}
	
	@Test
	@DisplayName("No.31 ログインした状態でブラウザでhttp://localhost:8080/osake-market/reset/password/mailにアクセス")
	@Order(31)
	void テスト項目No31() {
		// ログインする		
		openLoginURL();
		
		// URLを開く
		openURL("http://localhost:8080/osake-market/reset/password/mail");
		
		// スクリーンショットを取得
		takeScreenShot("No.31 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("マイページ - Osake Market");
	}
	
	@Test
	@DisplayName("No.32 ログインした状態でブラウザでhttp://localhost:8080/osake-market/reset/password/updateにアクセス")
	@Order(32)
	void テスト項目No32() {
		// ログインする		
		openLoginURL();
		
		// URLを開く
		openURL("http://localhost:8080/osake-market/reset/password/update");
		
		// スクリーンショットを取得
		takeScreenShot("No.32 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("マイページ - Osake Market");
	}
	
	@Test
	@DisplayName("No.33 ログインした状態でブラウザでhttp://localhost:8080/osake-market/reset/password/update/completeにアクセス")
	@Order(33)
	void テスト項目No33() {
		// ログインする		
		openLoginURL();
		
		// URLを開く
		openURL("http://localhost:8080/osake-market/reset/password/update/complete");
		
		// スクリーンショットを取得
		takeScreenShot("No.33 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("マイページ - Osake Market");
	}
	
	@Test
	@DisplayName("No.34 ログインした状態でブラウザでhttp://localhost:8080/osake-market/product/listにアクセス")
	@Order(34)
	void テスト項目No34() {
		// ログインする		
		openLoginURL();
		
		// URLを開く
		openURL("http://localhost:8080/osake-market/product/list");
		
		// スクリーンショットを取得
		takeScreenShot("No.34 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("商品一覧 - Osake Market");
	}
	
	@Test
	@DisplayName("No.35 ログインした状態でブラウザでhttp://localhost:8080/osake-market/product/detail?productId=1にアクセス")
	@Order(35)
	void テスト項目No35() {
		// ログインする		
		openLoginURL();
		
		// URLを開く
		openURL("http://localhost:8080/osake-market/product/detail?productId=1");
		
		// スクリーンショットを取得
		takeScreenShot("No.35 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("商品詳細 - Osake Market");
	}
	
	@Test
	@DisplayName("No.36 ログインした状態でブラウザでhttp://localhost:8080/osake-market/cartにアクセス")
	@Order(36)
	void テスト項目No36() {
		// ログインする		
		openLoginURL();
		
		// URLを開く
		openURL("http://localhost:8080/osake-market/cart");
		
		// スクリーンショットを取得
		takeScreenShot("No.36 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("カート - Osake Market");
	}
	
	@Test
	@DisplayName("No.37 ログインした状態でブラウザでhttp://localhost:8080/osake-market/order/inputにアクセス")
	@Order(37)
	void テスト項目No37() {
		// ログインする		
		openLoginURL();
		
		// URLを開く
		openURL("http://localhost:8080/osake-market/order/input");
		
		// スクリーンショットを取得
		takeScreenShot("No.37 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("カート - Osake Market");
	}
	
	@Test
	@DisplayName("No.38 ログインした状態でブラウザでhttp://localhost:8080/osake-market/order/confirmにアクセス")
	@Order(38)
	void テスト項目No38() {
		// ログインする		
		openLoginURL();
		
		// URLを開く
		openURL("http://localhost:8080/osake-market/order/confirm");
		
		// スクリーンショットを取得
		takeScreenShot("No.38 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("カート - Osake Market");
	}
	
	@Test
	@DisplayName("No.39 ログインした状態でブラウザでhttp://localhost:8080/osake-market/order/completeにアクセス")
	@Order(39)
	void テスト項目No39() {
		// ログインする		
		openLoginURL();
		
		// URLを開く
		openURL("http://localhost:8080/osake-market/order/complete");
		
		// スクリーンショットを取得
		takeScreenShot("No.39 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("注文完了 - Osake Market");
	}
	
	@Test
	@DisplayName("No.40 ログインした状態でブラウザでhttp://localhost:8080/osake-market/order/failureにアクセス")
	@Order(40)
	void テスト項目No40() {
		// ログインする		
		openLoginURL();
		
		// URLを開く
		openURL("http://localhost:8080/osake-market/order/failure");
		
		// スクリーンショットを取得
		takeScreenShot("No.40 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("注文失敗 - Osake Market");
	}
	
	@Test
	@DisplayName("No.41 ログインした状態でブラウザでhttp://localhost:8080/osake-market/favorite/listにアクセス")
	@Order(41)
	void テスト項目No41() {
		// ログインする		
		openLoginURL();
		
		// URLを開く
		openURL("http://localhost:8080/osake-market/favorite/list");
		
		// スクリーンショットを取得
		takeScreenShot("No.41 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("お気に入り一覧 - Osake Market");
	}
	
	@Test
	@DisplayName("No.42 ログインした状態でブラウザでhttp://localhost:8080/osake-market/notification/listにアクセス")
	@Order(42)
	void テスト項目No42() {
		// ログインする		
		openLoginURL();
		
		// URLを開く
		openURL("http://localhost:8080/osake-market/notification/list");
		
		// スクリーンショットを取得
		takeScreenShot("No.42 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("通知一覧 - Osake Market");
	}
	
	@Test
	@DisplayName("No.43 ログインした状態でブラウザでhttp://localhost:8080/osake-market/notification/detail?notificationId=1にアクセス")
	@Order(43)
	void テスト項目No43() {
		// ログインする		
		openLoginURL();
		
		// URLを開く
		openURL("http://localhost:8080/osake-market/notification/detail?notificationId=1");
		
		// スクリーンショットを取得
		takeScreenShot("No.43 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("通知詳細 - Osake Market");
	}
	
	@Test
	@DisplayName("No.44 ログインした状態でブラウザでhttp://localhost:8080/osake-market/contactにアクセス")
	@Order(44)
	void テスト項目No44() {
		// ログインする		
		openLoginURL();
		
		// URLを開く
		openURL("http://localhost:8080/osake-market/contact");
		
		// スクリーンショットを取得
		takeScreenShot("No.44 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("お問い合わせ - Osake Market");
	}
	
	@Test
	@DisplayName("No.45 ログインした状態でブラウザでhttp://localhost:8080/osake-market/companyにアクセス")
	@Order(45)
	void テスト項目No45() {
		// ログインする		
		openLoginURL();
		
		// URLを開く
		openURL("http://localhost:8080/osake-market/company");
		
		// スクリーンショットを取得
		takeScreenShot("No.45 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("会社概要 - Osake Market");
	}
	
	@Test
	@DisplayName("No.46 ログインした状態でブラウザでhttp://localhost:8080/osake-market/privacyPolicyにアクセス")
	@Order(46)
	void テスト項目No46() {
		// ログインする		
		openLoginURL();
		
		// URLを開く
		openURL("http://localhost:8080/osake-market/privacyPolicy");
		
		// スクリーンショットを取得
		takeScreenShot("No.46 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("プライバシーポリシー - Osake Market");
	}
	
	@Test
	@DisplayName("No.47 ログインした状態でブラウザでhttp://localhost:8080/osake-market/legalにアクセス")
	@Order(47)
	void テスト項目No47() {
		// ログインする		
		openLoginURL();
		
		// URLを開く
		openURL("http://localhost:8080/osake-market/legal");
		
		// スクリーンショットを取得
		takeScreenShot("No.47 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("特定商取引法 - Osake Market");
	}
	
	@Test
	@DisplayName("No.48 ログインした状態でブラウザでhttp://localhost:8080/osake-market/mypageにアクセス")
	@Order(48)
	void テスト項目No48() {
		// ログインする		
		openLoginURL();
		
		// URLを開く
		openURL("http://localhost:8080/osake-market/mypage");
		
		// スクリーンショットを取得
		takeScreenShot("No.48 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("マイページ - Osake Market");
	}
	
	@Test
	@DisplayName("No.49 ログインした状態でブラウザでhttp://localhost:8080/osake-market/update/user/formにアクセス")
	@Order(49)
	void テスト項目No49() {
		// ログインする		
		openLoginURL();
		
		// URLを開く
		openURL("http://localhost:8080/osake-market/update/user/form");
		
		// スクリーンショットを取得
		takeScreenShot("No.49 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("会員情報更新 - Osake Market");
	}
	
	@Test
	@DisplayName("No.50 ログインした状態でブラウザでhttp://localhost:8080/osake-market/order/listにアクセス")
	@Order(50)
	void テスト項目No50() {
		// ログインする		
		openLoginURL();
		
		// URLを開く
		openURL("http://localhost:8080/osake-market/order/list");
		
		// スクリーンショットを取得
		takeScreenShot("No.50 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("注文履歴一覧 - Osake Market");
	}
	
	@Test
	@DisplayName("No.51 ログインした状態でブラウザでhttp://localhost:8080/osake-market/order/detail?orderId=1にアクセス")
	@Order(51)
	void テスト項目No51() {
		// ログインする		
		openLoginURL();
		
		// URLを開く
		openURL("http://localhost:8080/osake-market/order/detail?orderId=1");
		
		// スクリーンショットを取得
		takeScreenShot("No.51 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("注文履歴詳細 - Osake Market");
	}
}
