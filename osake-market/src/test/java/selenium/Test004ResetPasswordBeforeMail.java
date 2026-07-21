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
class Test004ResetPasswordBeforeMail {

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
	
	// パスワード再設定 メール送信前画面のURLを開く
	private void openURL() {
		webDriver.get("http://localhost:8080/osake-market/reset/password/mail");
	}
	
	// スクリーンショットを取得
	private void takeScreenShot(String fileName) {
		// スクリーンショット保存パス
		String screenshotPath = "screenshots\\test004_resetPasswordBeforeMail\\";
		
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
	
	// 要素が表示されているか確認
	private void assertDisplayed(WebElement... elements) {
		for (WebElement element : elements) {
			assertTrue(element.isDisplayed());
		}
	}
	
	// メールアドレスを入力して送信する
	private void sendMail(String inputMail) {
		// メールアドレス入力欄を取得する
		final WebElement mail = webDriver.findElement(By.name("mail"));
		mail.clear();
		
		// メールアドレスを入力する
		mail.sendKeys(inputMail);
		
		// 送信する
		webDriver.findElement(By.cssSelector("button.black-btn")).click();
	}
	
	// エラーメッセージを取得
	private void assertErrorMsg(String msg) {
		WebElement errorMsg = webDriver.findElement(By.id("error-msg"));
		assertEquals(msg, errorMsg.getText());
	}
	
	@Test
	@DisplayName("No.1 ブラウザでhttp://localhost:8080/osake-market/reset/password/mailにアクセス")
	@Order(1)
	void テスト項目No1() {
		// 指定したURLに遷移する
		openURL();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/reset/password/mail");
	}
	
	@Test
	@DisplayName("No.2 ブラウザでhttp://localhost:8080/osake-market/reset/password/mailにアクセス")
	@Order(2)
	void テスト項目No2() {
		// 指定したURLに遷移する
		openURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.02 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("パスワードリセット(メールアドレス入力) - Osake Market");
	}
	
	@Test
	@DisplayName("No.3 パスワード再設定 メール送信前画面が表示されている")
	@Order(3)
	void テスト項目No3() {
		// 指定したURLに遷移する
		openURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.03 パスワード再設定 メール送信前画面が表示されている");
		
		// 要素が表示されているか取得
		final WebElement mail = webDriver.findElement(By.name("mail"));
		final WebElement submit = webDriver.findElement(By.cssSelector("button.black-btn"));
		final WebElement back = webDriver.findElement(By.cssSelector("a button"));
		
		// 要素が表示されているか確認
		assertDisplayed(mail, submit, back);
	}
	
	@Test
	@DisplayName("No.4 パスワード再設定 メール送信前画面が表示されている")
	@Order(4)
	void テスト項目No4() {
		// 指定したURLに遷移する
		openURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.04 パスワード再設定 メール送信前画面が表示されている");
		
		// メールアドレス入力欄を取得
		final WebElement mail = webDriver.findElement(By.name("mail"));
		
		// 入力欄が空欄か検証
		assertEquals("", mail.getAttribute("value"));
	}
	
	@Test
	@DisplayName("No.5 メールアドレスを空欄にして、「送信」ボタンをクリック")
	@Order(5)
	void テスト項目No5() {
		// 指定したURLに遷移する
		openURL();
		
		// メールアドレスを空欄にして、「送信」ボタンをクリック
		sendMail("");
		
		// スクリーンショットを取得
		takeScreenShot("No.05 メールアドレスを空欄にして、「送信」ボタンをクリック");
		
		// エラーメッセージが表示されるか検証
		assertErrorMsg("メール送信に失敗しました");
	}
	
	@Test
	@DisplayName("No.6 DBに登録されていないメールアドレスを入力して、「送信」ボタンをクリック")
	@Order(6)
	void テスト項目No6() {
		// 指定したURLに遷移する
		openURL();
		
		// DBに登録されていないメールアドレスを入力して、「送信」ボタンをクリック
		sendMail("dummy@example.com");
		
		// スクリーンショットを取得
		takeScreenShot("No.06 DBに登録されていないメールアドレスを入力して、「送信」ボタンをクリック");
		
		// エラーメッセージが表示されるか検証
		assertErrorMsg("メール送信に失敗しました");
	}
	
	@Test
	@DisplayName("No.7 削除フラグが立っている会員のメールアドレス：suzuki@gmail.comを入力して、"
			+ "「送信」ボタンをクリック")
	@Order(7)
	void テスト項目No7() {
		// 指定したURLに遷移する
		openURL();
		
		// 削除フラグが立っている会員のメールアドレス：suzuki@gmail.comを入力して、「送信」ボタンをクリック
		sendMail("suzuki@gmail.com");
		
		// スクリーンショットを取得
		takeScreenShot("No.07 削除フラグが立っている会員のメールアドレス：suzuki@gmail.comを入力して、"
				+ "「送信」ボタンをクリック");
		
		// エラーメッセージが表示されるか検証
		assertErrorMsg("メール送信に失敗しました");
	}
	
	@Test
	@DisplayName("No.8 削除フラグが立っていない会員のメールアドレス：tomatoakain@gmail.comを入力して、"
			+ "「送信」ボタンをクリック")
	@Order(8)
	void テスト項目No8() {
		// 指定したURLに遷移する
		openURL();
		
		// 削除フラグが立っていない会員のメールアドレス：tomatoakain@gmail.comを入力して、「送信」ボタンをクリック
		sendMail("tomatoakain@gmail.com");
		
		// スクリーンショットを取得
		takeScreenShot("No.08 削除フラグが立っていない会員のメールアドレス：tomatoakain@gmail.comを入力して、"
				+ "「送信」ボタンをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("パスワードリセット(メール送信済み) - Osake Market");
	}
	
	@Test
	@DisplayName("No.9 削除フラグが立っていない会員のメールアドレス：tomatoakain@gmail.comを入力して、"
			+ "「送信」ボタンをクリック")
	@Order(9)
	void テスト項目No9() {
		// 指定したURLに遷移する
		openURL();
		
		// 削除フラグが立っていない会員のメールアドレス：tomatoakain@gmail.comを入力して、「送信」ボタンをクリック
		sendMail("tomatoakain@gmail.com");
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/reset/password/mail");
	}
	
	@Test
	@DisplayName("No.10 「戻る」ボタンをクリック")
	@Order(10)
	void テスト項目No10() {
		// 指定したURLに遷移する
		openURL();
		
		// 「戻る」ボタンをクリック
		webDriver.findElement(By.cssSelector("a button")).click();
		
		// スクリーンショットを取得
		takeScreenShot("No.10 「戻る」ボタンをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("ログイン - Osake Market");
	}
	
	@Test
	@DisplayName("No.11 「戻る」ボタンをクリック")
	@Order(11)
	void テスト項目No11() {
		// 指定したURLに遷移する
		openURL();
		
		// 「戻る」ボタンをクリック
		webDriver.findElement(By.cssSelector("a button")).click();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/login");
	}

}
