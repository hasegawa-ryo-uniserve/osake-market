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
class Test005ResetPasswordForm {

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
	
	// パスワード再設定フォームのURLを開く
	private void openURL() {
		webDriver.get("http://localhost:8080/osake-market/reset/password/update?token=test-token");
	}
	
	// スクリーンショットを取得
	private void takeScreenShot(String fileName) {
		// スクリーンショット保存パス
		String screenshotPath = "screenshots\\test005_resetPasswordForm\\";
		
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
	
	// 新しいパスワード、確認用パスワードを入力して送信する
	private void setNewPassword(String inputNewPassword, String inputConfirmPassword) {
		// 新しいパスワード、確認用パスワードを取得する
		final WebElement newPassword = webDriver.findElement(By.name("newPassword"));
		final WebElement confirmPassword = webDriver.findElement(By.name("confirmPassword"));
		newPassword.clear();
		confirmPassword.clear();
		
		// 新しいパスワード、確認用パスワードを取得するを入力する
		newPassword.sendKeys(inputNewPassword);
		confirmPassword.sendKeys(inputConfirmPassword);
		
		// 送信する
		webDriver.findElement(By.cssSelector("input[value = '送信']")).click();
	}
	
	// エラーメッセージを取得
	private void assertErrorMsg(String msg) {
		WebElement errorMsg = webDriver.findElement(By.id("error-msg"));
		assertEquals(msg, errorMsg.getText());
	}
	
	@Test
	@DisplayName("No.3 ブラウザでhttp://localhost:8080/osake-market/reset/password/update?token=test-tokenにアクセス")
	@Order(1)
	void テスト項目No3() {
		// 指定したURLに遷移する
		openURL();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/reset/password/update?token=test-token");
	}
	
	@Test
	@DisplayName("No.4 ブラウザでhttp://localhost:8080/osake-market/reset/password/update?token=test-tokenにアクセス")
	@Order(2)
	void テスト項目No4() {
		// 指定したURLに遷移する
		openURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.04 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("パスワードリセット(新しいパスワードの入力) - Osake Market");
	}
	
	@Test
	@DisplayName("No.5 パスワード再設定 メール送信前画面が表示されている")
	@Order(3)
	void テスト項目No5() {
		// 指定したURLに遷移する
		openURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.05 パスワード再設定 メール送信前画面が表示されている");
		
		// 要素が表示されているか取得
		final WebElement newPassword = webDriver.findElement(By.name("newPassword"));
		final WebElement confirmPassword = webDriver.findElement(By.name("confirmPassword"));
		final WebElement sendMail = webDriver.findElement(By.cssSelector("input[value = '送信']"));
		
		// 要素が表示されているか確認
		assertDisplayed(newPassword, confirmPassword, sendMail);
	}

	@Test
	@DisplayName("No.6 パスワード再設定 メール送信前画面が表示されている")
	@Order(4)
	void テスト項目No6() {
		// 指定したURLに遷移する
		openURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.06 パスワード再設定 メール送信前画面が表示されている");
		
		// 要素が表示されているか取得
		final WebElement newPassword = webDriver.findElement(By.name("newPassword"));
		final WebElement confirmPassword = webDriver.findElement(By.name("confirmPassword"));
		
		// 入力欄が空欄か検証
		assertEquals("", newPassword.getAttribute("value"));
		assertEquals("", confirmPassword.getAttribute("value"));
	}
	
	@Test
	@DisplayName("No.7 新しいパスワード、新しいパスワードの確認を空欄にして、「送信」ボタンをクリック")
	@Order(5)
	void テスト項目No7() {
		// 指定したURLに遷移する
		openURL();
		
		// 新しいパスワード、確認用パスワードを入力し、送信
		setNewPassword("", "");
		
		// スクリーンショットを取得
		takeScreenShot("No.07 新しいパスワード、新しいパスワードの確認を空欄にして、「送信」ボタンをクリック");
		
		// エラーメッセージが表示されるか検証
		assertErrorMsg("新しいパスワードを入力して下さい");
	}
	
	@Test
	@DisplayName("No.8 新しいパスワードを空欄、新しいパスワードを確認を00#Passにして、「送信」ボタンをクリック")
	@Order(6)
	void テスト項目No8() {
		// 指定したURLに遷移する
		openURL();
		
		// 新しいパスワード、確認用パスワードを入力し、送信
		setNewPassword("", "00#Pass");
		
		// スクリーンショットを取得
		takeScreenShot("No.08 新しいパスワードを空欄、新しいパスワードを確認を00#Passにして、「送信」ボタンをクリック");
		
		// エラーメッセージが表示されるか検証
		assertErrorMsg("新しいパスワードを入力して下さい");
	}
	
	@Test
	@DisplayName("No.9 新しいパスワードを00#Pass、新しいパスワードの確認を空欄にして、「送信」ボタンをクリック")
	@Order(7)
	void テスト項目No9() {
		// 指定したURLに遷移する
		openURL();
		
		// 新しいパスワード、確認用パスワードを入力し、送信
		setNewPassword("00#Pass", "");
		
		// スクリーンショットを取得
		takeScreenShot("No.09 新しいパスワードを00#Pass、新しいパスワードの確認を空欄にして、「送信」ボタンをクリック");
		
		// エラーメッセージが表示されるか検証
		assertErrorMsg("パスワードが一致しませんでした");
	}
	
	@Test
	@DisplayName("No.10 新しいパスワードを00#Pass、新しいパスワードの確認をTanaka11#Passにして、「送信」ボタンをクリック")
	@Order(8)
	void テスト項目No10() {
		// 指定したURLに遷移する
		openURL();
		
		// 新しいパスワード、確認用パスワードを入力し、送信
		setNewPassword("00#Pass", "Tanaka11#Pass");
		
		// スクリーンショットを取得
		takeScreenShot("No.10 新しいパスワードを00#Pass、新しいパスワードの確認をTanaka11#Passにして、「送信」ボタンをクリック");
		
		// エラーメッセージが表示されるか検証
		assertErrorMsg("パスワードが一致しませんでした");
	}
	
	@Test
	@DisplayName("No.11 新しいパスワードを00#Pass、新しいパスワードの確認を00#Passにして、「送信」ボタンをクリック")
	@Order(9)
	void テスト項目No11() {
		// 指定したURLに遷移する
		openURL();
		
		// 新しいパスワード、確認用パスワードを入力し、送信
		setNewPassword("00#Pass", "00#Pass");
		
		// スクリーンショットを取得
		takeScreenShot("No.11 新しいパスワードを00#Pass、新しいパスワードの確認を00#Passにして、「送信」ボタンをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("パスワードリセット(パスワードの再設定完了) - Osake Market");
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/reset/password/update/complete");
	}
}
