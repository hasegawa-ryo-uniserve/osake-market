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

import com.google.common.io.Files;

@TestMethodOrder(OrderAnnotation.class)
class Test003Login {

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
	}
	
	// スクリーンショットを取得
	private void takeScreenShot(String fileName) {
		// スクリーンショット保存パス
		String screenshotPass = "screenshots\\test003_login\\";
		
		// スクリーンショット取得
		File file = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		
		// スクリーンショット保存
		try {
			Files.move(file, new File(screenshotPass + fileName + ".png"));
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
	
	// 要素が表示されているか確認
	private void assertDisplayed(WebElement... elements) {
		for (WebElement element : elements) {
			assertTrue(element.isDisplayed());
		}
	}
	
	// ログイン
	private void login(String inputMail, String inputPassword) {		
		// ログイン情報を取得し入力する
		final WebElement mail = webDriver.findElement(By.name("mail"));
		final WebElement password = webDriver.findElement(By.name("password"));
		
		mail.clear();
		password.clear();
		
		mail.sendKeys(inputMail);
		password.sendKeys(inputPassword);
		
		// ログインボタンをクリック
		webDriver.findElement(By.cssSelector("input[value='ログイン']")).click();
	}
	
	// エラーメッセージを取得
	private void assertErrorMsg(String msg) {
		WebElement errorMsg = webDriver.findElement(By.id("error-msg"));
		assertEquals(msg, errorMsg.getText());
	}
	
	@Test
	@DisplayName("No.1 ブラウザでhttp://localhost:8080/osake-market/loginにアクセス")
	@Order(1)
	void テスト項目No1() {
		// 指定したURLに遷移する
		openLoginURL();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/login");
	}
	
	@Test
	@DisplayName("No.2 ブラウザでhttp://localhost:8080/osake-market/loginにアクセス")
	@Order(2)
	void テスト項目No2() {
		// 指定したURLに遷移する
		openLoginURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.02 ブラウザでloginにアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("ログイン - Osake Market");
	}
	
	@Test
	@DisplayName("No.3 ログイン画面が表示されている")
	@Order(3)
	void テスト項目No3() {
		// 指定したURLに遷移する
		openLoginURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.03 ログイン画面が表示されている");
		
		// 要素が表示されているか取得
		final WebElement mail = webDriver.findElement(By.name("mail"));
		final WebElement password = webDriver.findElement(By.name("password"));
		final WebElement loginButton = webDriver.findElement(By.cssSelector("input[value = 'ログイン']"));
		final WebElement register = webDriver.findElement(By.linkText("アカウントを新規作成する"));
		final WebElement forgetPassword = webDriver.findElement(By.linkText("パスワードを忘れた方はこちら"));
		
		// 要素が表示されているか確認
		assertDisplayed(mail, password, loginButton, register, forgetPassword);
	}
	
	@Test
	@DisplayName("No.4 ログイン画面が表示されている")
	@Order(4)
	void テスト項目No4() {
		// 指定したURLに遷移する
		openLoginURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.04 ログイン画面が表示されている");
		
		// ログイン情報を取得する
		final WebElement mail = webDriver.findElement(By.name("mail"));
		final WebElement password = webDriver.findElement(By.name("password"));
		
		// 入力欄が空欄かどうか検証
		assertEquals("", mail.getAttribute("value"));
		assertEquals("", password.getAttribute("value"));
	}
	
	@Test
	@DisplayName("No.5 メールアドレスとパスワードを空欄にして、「ログイン」ボタンをクリック")
	@Order(5)
	void テスト項目No5() {
		// 指定したURLに遷移する
		openLoginURL();
		
		// メールアドレスとパスワードを空欄にして、「ログイン」ボタンをクリック
		login("", "");
		
		// スクリーンショットを取得
		takeScreenShot("No.05 メールアドレスとパスワードを空欄にして、「ログイン」ボタンをクリック");
		
		// エラーメッセージが表示されるか検証
		assertErrorMsg("ログインに失敗しました");
	}
	
	@Test
	@DisplayName("No.6 メールアドレスを空欄にして、「ログイン」ボタンをクリック")
	@Order(6)
	void テスト項目No6() {
		// 指定したURLに遷移する
		openLoginURL();
		
		// メールアドレスを空欄にして、「ログイン」ボタンをクリック
		login("", "Tanaka11#Pass");
		
		// スクリーンショットを取得
		takeScreenShot("No.06 メールアドレスを空欄にして、「ログイン」ボタンをクリック");
		
		// エラーメッセージが表示されるか検証
		assertErrorMsg("ログインに失敗しました");
	}
	
	@Test
	@DisplayName("No.7 パスワードを空欄にして、「ログイン」ボタンをクリック")
	@Order(7)
	void テスト項目No7() {
		// 指定したURLに遷移する
		openLoginURL();
		
		// パスワードを空欄にして、「ログイン」ボタンをクリック
		login("tomatoakain@gmal.com", "");
		
		// スクリーンショットを取得
		takeScreenShot("No.07 パスワードを空欄にして、「ログイン」ボタンをクリック");
		
		// エラーメッセージが表示されるか検証
		assertErrorMsg("ログインに失敗しました");
	}
	
	@Test
	@DisplayName("No.8 DBに登録されていないメールアドレス、パスワードを入力して、"
			+ "「ログイン」ボタンをクリック")
	@Order(8)
	void テスト項目No8() {
		// 指定したURLに遷移する
		openLoginURL();
		
		// パスワードを空欄にして、「ログイン」ボタンをクリック
		login("dummy@example.com", "dummy");
		
		// スクリーンショットを取得
		takeScreenShot("No.08 DBに登録されていないメールアドレス、パスワードを入力して、"
				+ "「ログイン」ボタンをクリック");
		
		// エラーメッセージが表示されるか検証
		assertErrorMsg("ログインに失敗しました");
	}
	
	@Test
	@DisplayName("No.9 メールアドレス：tomatoakain@gmail.com、パスワード：Tanaka11#Passを入力して、"
			+ "「ログイン」ボタンをクリック")
	@Order(9)
	void テスト項目No9() {
		// 指定したURLに遷移する
		openLoginURL();
		
		// メールアドレス：tomatoakain@gmail.com、パスワード：Tanaka11#Passを入力して、「ログイン」ボタンをクリック
		login("tomatoakain@gmail.com", "Tanaka11#Pass");
		
		// スクリーンショットを取得
		takeScreenShot("No.09 メールアドレス：tomatoakain@gmail.com、パスワード：Tanaka11#Passを入力して、「ログイン」ボタンをクリック");
		
		// ホーム画面に遷移し、右上にマイページの文字が表示されるか検証
		assertTitle("Osake Market");
		WebElement mypage = webDriver.findElement(By.cssSelector("a.user span"));
		assertEquals("マイページ", mypage.getText());
	}
	
	@Test
	@DisplayName("No.10 メールアドレス：suzuki@gmail.com、パスワード：Suzuki22#Passを入力して、"
			+ "「ログイン」ボタンをクリック")
	@Order(10)
	void テスト項目No10() {
		// 指定したURLに遷移する
		openLoginURL();
		
		// メールアドレス：suzuki@gmail.com、パスワード：Suzuki22#Passを入力して、「ログイン」ボタンをクリック
		login("suzuki@gmail.com", "Suzuki22#Pass");
		
		// スクリーンショットを取得
		takeScreenShot("No.10 メールアドレス：suzuki@gmail.com、パスワード：Suzuki22#Passを入力して、"
				+ "「ログイン」ボタンをクリック");
		
		// エラーメッセージが表示されるか検証
		assertErrorMsg("ログインに失敗しました");
	}
	
	@Test
	@DisplayName("No.11 「アカウントを新規作成する」リンクをクリック")
	@Order(11)
	void テスト項目No11() {
		// 指定したURLに遷移する
		openLoginURL();
		
		// 「アカウントを新規作成する」リンクをクリック
		webDriver.findElement(By.linkText("アカウントを新規作成する")).click();
		
		// スクリーンショットを取得
		takeScreenShot("No.11 「アカウントを新規作成する」リンクをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("新規会員登録(入力) - Osake Market");
	}
	
	@Test
	@DisplayName("No.12 「アカウントを新規作成する」リンクをクリック")
	@Order(12)
	void テスト項目No12() {
		// 指定したURLに遷移する
		openLoginURL();
		
		// 「アカウントを新規作成する」リンクをクリック
		webDriver.findElement(By.linkText("アカウントを新規作成する")).click();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/register/user");
	}
	
	@Test
	@DisplayName("No.13 「パスワードを忘れた方はこちら」リンクをクリック")
	@Order(13)
	void テスト項目No13() {
		// 指定したURLに遷移する
		openLoginURL();
		
		// 「パスワードを忘れた方はこちら」リンクをクリック
		webDriver.findElement(By.linkText("パスワードを忘れた方はこちら")).click();
		
		// スクリーンショットを取得
		takeScreenShot("No.13 「パスワードを忘れた方はこちら」リンクをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("パスワードリセット(メールアドレス入力) - Osake Market");
	}
	
	@Test
	@DisplayName("No.14 「パスワードを忘れた方はこちら」リンクをクリック")
	@Order(14)
	void テスト項目No14() {
		// 指定したURLに遷移する
		openLoginURL();
		
		// 「パスワードを忘れた方はこちら」リンクをクリック
		webDriver.findElement(By.linkText("パスワードを忘れた方はこちら")).click();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/reset/password/mail");
	}
	
	@Test
	@DisplayName("No.15 ログインした状態でhttp://localhost:8080/osake-market/loginにアクセス")
	@Order(15)
	void テスト項目No15() {
		// 指定したURLに遷移する
		openLoginURL();
		
		// ログインする
		login("tomatoakain@gmail.com", "Tanaka11#Pass");
		
		// ログインした状態でhttp://localhost:8080/osake-market/loginにアクセス
		openLoginURL();
		
		// ホーム画面に遷移し、右上にマイページの文字が表示されるか検証
		assertTitle("Osake Market");
		WebElement mypage = webDriver.findElement(By.cssSelector("a.user span"));
		assertEquals("マイページ", mypage.getText());
	}
}
