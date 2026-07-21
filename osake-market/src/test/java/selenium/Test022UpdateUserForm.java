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
class Test022UpdateUserForm {

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
	
	// 会員情報更新画面のURLを開く
	private void openUpdateUserFormURL() {
		webDriver.get("http://localhost:8080/osake-market/update/user/form");
		
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
		String screenshotPath = "screenshots\\test022_updateUserForm\\";
		
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
	
	@Test
	@DisplayName("No.1 ブラウザでhttp://localhost:8080/osake-market/update/user/formにアクセス")
	@Order(1)
	void テスト項目No1() {
		// ログイン画面のURLを開く
		openLoginURL();
		
		// 会員情報更新画面のURLを開く
		openUpdateUserFormURL();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/update/user/form");
	}
	
	@Test
	@DisplayName("No.2 ブラウザでhttp://localhost:8080/osake-market/update/user/formにアクセス")
	@Order(2)
	void テスト項目No2() {
		// ログイン画面のURLを開く
		openLoginURL();
		
		// 会員情報更新画面のURLを開く
		openUpdateUserFormURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.02 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("会員情報更新 - Osake Market");
	}
	
	@Test
	@DisplayName("No.3 ブラウザでhttp://localhost:8080/osake-market/update/user/formにアクセス")
	@Order(3)
	void テスト項目No3() {
		// ログイン画面のURLを開く
		openLoginURL();
		
		// 会員情報更新画面のURLを開く
		openUpdateUserFormURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.03 ブラウザでアクセス");
		
		// 要素を取得
		final WebElement sei = webDriver.findElement(By.name("sei"));
		final WebElement mei = webDriver.findElement(By.name("mei"));
		final WebElement birthday = webDriver.findElement(By.name("birthday"));
		final WebElement postalCode = webDriver.findElement(By.name("postalCode"));
		final WebElement address = webDriver.findElement(By.name("address"));
		final WebElement building = webDriver.findElement(By.name("building"));
		final WebElement phoneNumber = webDriver.findElement(By.name("phoneNumber"));
		final WebElement mail = webDriver.findElement(By.name("mail"));
		final WebElement password = webDriver.findElement(By.name("password"));
		final WebElement submit = webDriver.findElement(By.className("update-user-submit"));
		final WebElement back = webDriver.findElement(By.className("update-user-return"));
		
		List<WebElement> genders = webDriver.findElements(By.name("gender"));
		final WebElement male = genders.get(0);
		
		final WebElement prefecture = webDriver.findElement(By.name("prefecture"));
		final Select select = new Select(prefecture);
		
		// 入力欄が空欄か検証
		assertEquals("田中", sei.getAttribute("value"));
		assertEquals("一郎", mei.getAttribute("value"));
		assertEquals("1996-07-03", birthday.getAttribute("value"));
		assertEquals("9876543", postalCode.getAttribute("value"));
		assertEquals("調布市西つつじケ丘", address.getAttribute("value"));
		assertEquals("朝日ハイツ101", building.getAttribute("value"));
		assertEquals("08011112222", phoneNumber.getAttribute("value"));
		assertEquals("tomatoakain@gmail.com", mail.getAttribute("value"));
		assertEquals("", password.getAttribute("value"));
		
		// 性別は男性が選択されているか検証
		assertTrue(male.isSelected());
		
		// 都道府県は東京都が選択されているか検証
		assertEquals("東京都", select.getFirstSelectedOption().getText());
		
		// 「保存する」ボタン、「戻る」ボタンが表示されているか検証
		assertDisplayed(submit, back);
	}
	
	@Test
	@DisplayName("No.4 「戻る」ボタンをクリック")
	@Order(4)
	void テスト項目No4() {
		// ログイン画面のURLを開く
		openLoginURL();
		
		// 会員情報更新画面のURLを開く
		openUpdateUserFormURL();

		// 「戻る」ボタンをクリック
		final WebElement back = webDriver.findElement(By.className("update-user-return"));
		back.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// スクリーンショットを取得
		takeScreenShot("No.04 「戻る」ボタンをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("マイページ - Osake Market");
	}
	
	@Test
	@DisplayName("No.5 「戻る」ボタンをクリック")
	@Order(5)
	void テスト項目No5() {
		// ログイン画面のURLを開く
		openLoginURL();
		
		// 会員情報更新画面のURLを開く
		openUpdateUserFormURL();

		// 「戻る」ボタンをクリック
		final WebElement back = webDriver.findElement(By.className("update-user-return"));
		back.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/mypage");
	}
	
	@Test
	@DisplayName("No.6 姓のみ空白にして「保存」ボタンをクリック")
	@Order(6)
	void テスト項目No6() {
		// ログイン画面のURLを開く
		openLoginURL();
		
		// 会員情報更新画面のURLを開く
		openUpdateUserFormURL();
		
		// 要素を取得
		final WebElement sei = webDriver.findElement(By.name("sei"));
		final WebElement password = webDriver.findElement(By.name("password"));
		final WebElement submit = webDriver.findElement(By.className("update-user-submit"));
		
		// 姓を空白にする
		sei.clear();
		
		// パスワードを入力
		password.sendKeys("Tanaka00#Pass");
		
		// 「保存」ボタンをクリック
		submit.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// スクリーンショットを取得
		takeScreenShot("No.06 姓のみ空白にして「保存」ボタンをクリック");
		
		// 「このフィールドを入力してください」が表示されているか検証
		assertEquals("このフィールドを入力してください。", sei.getAttribute("validationMessage"));
	}
	
	@Test
	@DisplayName("No.7 名のみ空白にして「保存」ボタンをクリック")
	@Order(7)
	void テスト項目No7() {
		// ログイン画面のURLを開く
		openLoginURL();
		
		// 会員情報更新画面のURLを開く
		openUpdateUserFormURL();
		
		// 要素を取得
		final WebElement mei = webDriver.findElement(By.name("mei"));
		final WebElement password = webDriver.findElement(By.name("password"));
		final WebElement submit = webDriver.findElement(By.className("update-user-submit"));
		
		// 名を空白にする
		mei.clear();
		
		// パスワードを入力
		password.sendKeys("Tanaka00#Pass");
		
		// 「保存」ボタンをクリック
		submit.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// スクリーンショットを取得
		takeScreenShot("No.07 名のみ空白にして「保存」ボタンをクリック");
		
		// 「このフィールドを入力してください」が表示されているか検証
		assertEquals("このフィールドを入力してください。", mei.getAttribute("validationMessage"));
	}
	
	@Test
	@DisplayName("No.8 生年月日のみ空白にして「保存」ボタンをクリック")
	@Order(8)
	void テスト項目No8() {
		// ログイン画面のURLを開く
		openLoginURL();
		
		// 会員情報更新画面のURLを開く
		openUpdateUserFormURL();
		
		// 要素を取得
		final WebElement birthday = webDriver.findElement(By.name("birthday"));
		final WebElement password = webDriver.findElement(By.name("password"));
		final WebElement submit = webDriver.findElement(By.className("update-user-submit"));
		
		// 生年月日を空白にする
		birthday.clear();
		
		// パスワードを入力
		password.sendKeys("Tanaka00#Pass");
		
		// 「保存」ボタンをクリック
		submit.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// スクリーンショットを取得
		takeScreenShot("No.08 生年月日のみ空白にして「保存」ボタンをクリック");
		
		// 「このフィールドを入力してください」が表示されているか検証
		assertEquals("このフィールドを入力してください。", birthday.getAttribute("validationMessage"));
	}
	
	@Test
	@DisplayName("No.9 郵便番号のみ空白にして「保存」ボタンをクリック")
	@Order(9)
	void テスト項目No9() {
		// ログイン画面のURLを開く
		openLoginURL();
		
		// 会員情報更新画面のURLを開く
		openUpdateUserFormURL();
		
		// 要素を取得
		final WebElement postalCode = webDriver.findElement(By.name("postalCode"));
		final WebElement password = webDriver.findElement(By.name("password"));
		final WebElement submit = webDriver.findElement(By.className("update-user-submit"));
		
		// 郵便番号を空白にする
		postalCode.clear();
		
		// パスワードを入力
		password.sendKeys("Tanaka00#Pass");
		
		// 「保存」ボタンをクリック
		submit.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// スクリーンショットを取得
		takeScreenShot("No.09 郵便番号のみ空白にして「保存」ボタンをクリック");
		
		// 「このフィールドを入力してください」が表示されているか検証
		assertEquals("このフィールドを入力してください。", postalCode.getAttribute("validationMessage"));
	}
	
	@Test
	@DisplayName("No.10 住所のみ空白にして「保存」ボタンをクリック")
	@Order(10)
	void テスト項目No10() {
		// ログイン画面のURLを開く
		openLoginURL();
		
		// 会員情報更新画面のURLを開く
		openUpdateUserFormURL();
		
		// 要素を取得
		final WebElement address = webDriver.findElement(By.name("address"));
		final WebElement password = webDriver.findElement(By.name("password"));
		final WebElement submit = webDriver.findElement(By.className("update-user-submit"));
		
		// 住所を空白にする
		address.clear();
		
		// パスワードを入力
		password.sendKeys("Tanaka00#Pass");
		
		// 「保存」ボタンをクリック
		submit.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// スクリーンショットを取得
		takeScreenShot("No.10 住所のみ空白にして「保存」ボタンをクリック");
		
		// 「このフィールドを入力してください」が表示されているか検証
		assertEquals("このフィールドを入力してください。", address.getAttribute("validationMessage"));
	}
	
	@Test
	@DisplayName("No.11 建物名・部屋番号のみ空白にして「保存」ボタンをクリック")
	@Order(11)
	void テスト項目No11() {
		// ログイン画面のURLを開く
		openLoginURL();
		
		// 会員情報更新画面のURLを開く
		openUpdateUserFormURL();
		
		// 要素を取得
		WebElement building = webDriver.findElement(By.name("building"));
		final WebElement password = webDriver.findElement(By.name("password"));
		final WebElement submit = webDriver.findElement(By.className("update-user-submit"));
		
		// 建物名・部屋番号を空白にする
		building.clear();
		
		// パスワードを入力
		password.sendKeys("Tanaka11#Pass");
		
		// 「保存」ボタンをクリック
		submit.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// スクリーンショットを取得
		takeScreenShot("No.11 建物名・部屋番号のみ空白にして「保存」ボタンをクリック");
		
		// 「会員情報を管理しました」と画面上部にメッセージが表示されるか検証
		WebElement msg = webDriver.findElement(By.id("update-done-msg"));
		assertEquals("会員情報を更新しました", msg.getText());
		
		// 建物名・部屋番号が空白か検証
		building = webDriver.findElement(By.name("building"));
		assertEquals("", building.getText());
	}
	
	@Test
	@DisplayName("No.12 携帯電話番号のみ空白にして「保存」ボタンをクリック")
	@Order(12)
	void テスト項目No12() {
		// ログイン画面のURLを開く
		openLoginURL();
		
		// 会員情報更新画面のURLを開く
		openUpdateUserFormURL();
		
		// 要素を取得
		final WebElement phoneNumber = webDriver.findElement(By.name("phoneNumber"));
		final WebElement password = webDriver.findElement(By.name("password"));
		final WebElement submit = webDriver.findElement(By.className("update-user-submit"));
		
		// 携帯電話番号を空白にする
		phoneNumber.clear();
		
		// パスワードを入力
		password.sendKeys("Tanaka00#Pass");
		
		// 「保存」ボタンをクリック
		submit.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// スクリーンショットを取得
		takeScreenShot("No.12 携帯電話番号のみ空白にして「保存」ボタンをクリック");
		
		// 「このフィールドを入力してください」が表示されているか検証
		assertEquals("このフィールドを入力してください。", phoneNumber.getAttribute("validationMessage"));
	}
	
	@Test
	@DisplayName("No.13 メールアドレスのみ空白にして「保存」ボタンをクリック")
	@Order(13)
	void テスト項目No13() {
		// ログイン画面のURLを開く
		openLoginURL();
		
		// 会員情報更新画面のURLを開く
		openUpdateUserFormURL();
		
		// 要素を取得
		final WebElement mail = webDriver.findElement(By.name("mail"));
		final WebElement password = webDriver.findElement(By.name("password"));
		final WebElement submit = webDriver.findElement(By.className("update-user-submit"));
		
		// メールアドレスを空白にする
		mail.clear();
		
		// パスワードを入力
		password.sendKeys("Tanaka00#Pass");
		
		// 「保存」ボタンをクリック
		submit.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// スクリーンショットを取得
		takeScreenShot("No.13 メールアドレスのみ空白にして「保存」ボタンをクリック");
		
		// 「このフィールドを入力してください」が表示されているか検証
		assertEquals("このフィールドを入力してください。", mail.getAttribute("validationMessage"));
	}
	
	@Test
	@DisplayName("No.14 パスワードのみ空白にして「保存」ボタンをクリック")
	@Order(14)
	void テスト項目No14() {
		// ログイン画面のURLを開く
		openLoginURL();
		
		// 会員情報更新画面のURLを開く
		openUpdateUserFormURL();
		
		// 要素を取得
		final WebElement password = webDriver.findElement(By.name("password"));
		final WebElement submit = webDriver.findElement(By.className("update-user-submit"));
		
		// パスワードを空白にする
		password.clear();
		
		// 「保存」ボタンをクリック
		submit.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// スクリーンショットを取得
		takeScreenShot("No.14 パスワードのみ空白にして「保存」ボタンをクリック");
		
		// 「このフィールドを入力してください」が表示されているか検証
		assertEquals("このフィールドを入力してください。", password.getAttribute("validationMessage"));
	}
	
	@Test
	@DisplayName("No.15 パスワードのみ変更して「保存」ボタンをクリック")
	@Order(15)
	void テスト項目No15() {
		// ログイン画面のURLを開く
		openLoginURL();
		
		// 会員情報更新画面のURLを開く
		openUpdateUserFormURL();
		
		// 要素を取得
		final WebElement password = webDriver.findElement(By.name("password"));
		final WebElement submit = webDriver.findElement(By.className("update-user-submit"));
		
		// パスワードを入力
		password.clear();
		password.sendKeys("Tanaka00#Pass");
		
		// 「保存」ボタンをクリック
		submit.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// スクリーンショットを取得
		takeScreenShot("No.15 パスワードのみ変更して「保存」ボタンをクリック");
		
		// 「会員情報を管理しました」と画面上部にメッセージが表示されるか検証
		WebElement msg = webDriver.findElement(By.id("update-done-msg"));
		assertEquals("会員情報を更新しました", msg.getText());
	}
}
