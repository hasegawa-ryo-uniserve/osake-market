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
import org.openqa.selenium.Alert;
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
class Test021Mypage {

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
	
	// マイページ画面のURLを開く
	private void openMypageURL() {
		webDriver.get("http://localhost:8080/osake-market/mypage");
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
	}
	
	// ログイン画面のURLを開く
	private void openLoginURL(String inputMail, String inputPassword) {
		webDriver.get("http://localhost:8080/osake-market/login");
		
		// ログイン情報を取得し入力する
		final WebElement mail = webDriver.findElement(By.name("mail"));
		final WebElement password = webDriver.findElement(By.name("password"));
		
		mail.clear();
		password.clear();
		
		mail.sendKeys(inputMail);
		password.sendKeys(inputPassword);
		
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
		String screenshotPath = "screenshots\\test021_mypage\\";
		
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
	@DisplayName("No.1 ブラウザでhttp://localhost:8080/osake-market/contactにアクセス")
	@Order(1)
	void テスト項目No1() {
		// ログイン画面のURLを開く
		openLoginURL("hanako@gmail.com", "Watanabe77#Pass");
		
		// マイページ画面のURLを開く
		openMypageURL();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/mypage");
	}
	
	@Test
	@DisplayName("No.2 ブラウザでhttp://localhost:8080/osake-market/contactにアクセス")
	@Order(2)
	void テスト項目No2() {
		// ログイン画面のURLを開く
		openLoginURL("hanako@gmail.com", "Watanabe77#Pass");
		
		// マイページ画面のURLを開く
		openMypageURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.02 ブラウザでアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("マイページ - Osake Market");
	}
	
	@Test
	@DisplayName("No.3 ブラウザでhttp://localhost:8080/osake-market/contactにアクセス")
	@Order(3)
	void テスト項目No3() {
		// ログイン画面のURLを開く
		openLoginURL("hanako@gmail.com", "Watanabe77#Pass");
		
		// マイページ画面のURLを開く
		openMypageURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.03 ブラウザでアクセス");
		
		// マイページの要素を取得する
		List<WebElement> elements = webDriver.findElements(By.cssSelector(".mypage-menu a"));
		WebElement userUpdate = elements.get(0);
		WebElement orderList = elements.get(1);
		WebElement favorite = elements.get(2);
		WebElement notification = elements.get(3);
		
		List<WebElement> buttons = webDriver.findElements(By.cssSelector(".withdrow-and-logout-wrapper form button"));
		WebElement withdraw = buttons.get(0);
		WebElement logout = buttons.get(1);
		
		// 要素が存在するか検証
		assertDisplayed(userUpdate, orderList, favorite, notification, withdraw, logout);
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("マイページ - Osake Market");
	}
	
	@Test
	@DisplayName("No.4 「会員情報を管理する」をクリック")
	@Order(4)
	void テスト項目No4() {
		// ログイン画面のURLを開く
		openLoginURL("hanako@gmail.com", "Watanabe77#Pass");
		
		// マイページ画面のURLを開く
		openMypageURL();
		
		// 「会員情報を管理する」をクリック
		List<WebElement> elements = webDriver.findElements(By.cssSelector(".mypage-menu a"));
		WebElement userUpdate = elements.get(0);
		userUpdate.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// スクリーンショットを取得
		takeScreenShot("No.04 「会員情報を管理する」をクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("会員情報更新 - Osake Market");
	}
	
	@Test
	@DisplayName("No.5 「会員情報を管理する」をクリック")
	@Order(5)
	void テスト項目No5() {
		// ログイン画面のURLを開く
		openLoginURL("hanako@gmail.com", "Watanabe77#Pass");
		
		// マイページ画面のURLを開く
		openMypageURL();
		
		// 「会員情報を管理する」をクリック
		List<WebElement> elements = webDriver.findElements(By.cssSelector(".mypage-menu a"));
		WebElement userUpdate = elements.get(0);
		userUpdate.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/update/user/form");
	}
	
	@Test
	@DisplayName("No.6 「注文履歴」をクリック")
	@Order(6)
	void テスト項目No6() {
		// ログイン画面のURLを開く
		openLoginURL("tomatoakain@gmail.com", "Tanaka11#Pass");
		
		// マイページ画面のURLを開く
		openMypageURL();
		
		// 「注文履歴」をクリック
		List<WebElement> elements = webDriver.findElements(By.cssSelector(".mypage-menu a"));
		WebElement orderList = elements.get(1);
		orderList.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// スクリーンショットを取得
		takeScreenShot("No.06 「注文履歴」をクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("注文履歴一覧 - Osake Market");
	}
	
	@Test
	@DisplayName("No.7 「注文履歴」をクリック")
	@Order(7)
	void テスト項目No7() {
		// ログイン画面のURLを開く
		openLoginURL("tomatoakain@gmail.com", "Tanaka11#Pass");
		
		// マイページ画面のURLを開く
		openMypageURL();
		
		// 「注文履歴」をクリック
		List<WebElement> elements = webDriver.findElements(By.cssSelector(".mypage-menu a"));
		WebElement orderList = elements.get(1);
		orderList.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/order/list");
	}
	
	@Test
	@DisplayName("No.8 「お気に入り」をクリック")
	@Order(8)
	void テスト項目No8() {
		// ログイン画面のURLを開く
		openLoginURL("hanako@gmail.com", "Watanabe77#Pass");
		
		// マイページ画面のURLを開く
		openMypageURL();
		
		// 「お気に入り」をクリック
		List<WebElement> elements = webDriver.findElements(By.cssSelector(".mypage-menu a"));
		WebElement favorite = elements.get(2);
		favorite.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// スクリーンショットを取得
		takeScreenShot("No.08 「お気に入り」をクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("お気に入り一覧 - Osake Market");
	}
	
	@Test
	@DisplayName("No.9 「お気に入り」をクリック")
	@Order(9)
	void テスト項目No9() {
		// ログイン画面のURLを開く
		openLoginURL("hanako@gmail.com", "Watanabe77#Pass");
		
		// マイページ画面のURLを開く
		openMypageURL();
		
		// 「お気に入り」をクリック
		List<WebElement> elements = webDriver.findElements(By.cssSelector(".mypage-menu a"));
		WebElement favorite = elements.get(2);
		favorite.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/favorite/list");
	}
	
	@Test
	@DisplayName("No.10 「通知」をクリック")
	@Order(10)
	void テスト項目No10() {
		// ログイン画面のURLを開く
		openLoginURL("hanako@gmail.com", "Watanabe77#Pass");
		
		// マイページ画面のURLを開く
		openMypageURL();
		
		// 「通知」をクリック
		List<WebElement> elements = webDriver.findElements(By.cssSelector(".mypage-menu a"));
		WebElement notification = elements.get(3);
		notification.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// スクリーンショットを取得
		takeScreenShot("No.10 「通知」をクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("通知一覧 - Osake Market");
	}
	
	@Test
	@DisplayName("No.11 「通知」をクリック")
	@Order(11)
	void テスト項目No11() {
		// ログイン画面のURLを開く
		openLoginURL("hanako@gmail.com", "Watanabe77#Pass");
		
		// マイページ画面のURLを開く
		openMypageURL();
		
		// 「通知」をクリック
		List<WebElement> elements = webDriver.findElements(By.cssSelector(".mypage-menu a"));
		WebElement notification = elements.get(3);
		notification.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/notification/list");
	}
	
	@Test
	@DisplayName("No.12 「退会する」をクリック")
	@Order(12)
	void テスト項目No12() {
		// ログイン画面のURLを開く
		openLoginURL("hanako@gmail.com", "Watanabe77#Pass");
		
		// マイページ画面のURLを開く
		openMypageURL();
		
		// 「退会する」をクリック
		List<WebElement> buttons = webDriver.findElements(By.cssSelector(".withdrow-and-logout-wrapper form button"));
		WebElement withdraw = buttons.get(0);
		withdraw.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// ダイアログを取得
		Alert alert = webDriver.switchTo().alert();
		
		// メッセージを検証
		assertEquals("本当に退会しますか", alert.getText());
		
		// 「いいえ」をクリックして終了
		alert.dismiss();
	}
	
	@Test
	@DisplayName("No.13 「退会する」をクリックし「本当に退会しますか」とダイアログが表示され、「いいえ」をクリック")
	@Order(13)
	void テスト項目No13() {
		// ログイン画面のURLを開く
		openLoginURL("hanako@gmail.com", "Watanabe77#Pass");
		
		// マイページ画面のURLを開く
		openMypageURL();
		
		// 「退会」するを押す前のURLを取得
		String beforeUrl = webDriver.getCurrentUrl();
		
		// 「退会する」をクリック
		List<WebElement> buttons = webDriver.findElements(By.cssSelector(".withdrow-and-logout-wrapper form button"));
		WebElement withdraw = buttons.get(0);
		withdraw.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// ダイアログを取得
		Alert alert = webDriver.switchTo().alert();
		
		// 「いいえ」をクリック
		alert.dismiss();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// スクリーンショットを取得
		takeScreenShot("No.13 「退会する」をクリックし「本当に退会しますか」とダイアログが表示され、「いいえ」をクリック");
		
		// 「いいえ」を押した後のURLを取得
		String afterUrl = webDriver.getCurrentUrl();
		
		// URLが同じか検証
		assertEquals(beforeUrl, afterUrl);
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("マイページ - Osake Market");
	}
	
	@Test
	@DisplayName("No.14 「退会する」をクリックし「本当に退会しますか」とダイアログが表示され、「はい」をクリック")
	@Order(14)
	void テスト項目No14() {
		// ログイン画面のURLを開く
		openLoginURL("hanako@gmail.com", "Watanabe77#Pass");
		
		// マイページ画面のURLを開く
		openMypageURL();
		
		// 「退会する」をクリック
		List<WebElement> buttons = webDriver.findElements(By.cssSelector(".withdrow-and-logout-wrapper form button"));
		WebElement withdraw = buttons.get(0);
		withdraw.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// ダイアログを取得
		Alert alert = webDriver.switchTo().alert();
		
		// 「はい」をクリック
		alert.accept();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// スクリーンショットを取得
		takeScreenShot("No.14 「退会する」をクリックし「本当に退会しますか」とダイアログが表示され、「はい」をクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("Osake Market");
		
		// 右上が「ログイン」となっているか検証
		WebElement loginText = webDriver.findElement(By.cssSelector("a.user span"));
		assertEquals("ログイン", loginText.getText());
	}
	
	@Test
	@DisplayName("No.16 退会したアカウントでログインをする")
	@Order(16)
	void テスト項目No16() {
		// ログイン画面のURLを開く
		openLoginURL("hanako@gmail.com", "Watanabe77#Pass");
		
		// スクリーンショットを取得
		takeScreenShot("No.16 退会したアカウントでログインをする");
		
		// エラーメッセージが表示されるか検証
		WebElement errorMsg = webDriver.findElement(By.id("error-msg"));
		assertEquals("ログインに失敗しました", errorMsg.getText());
	}
	
	@Test
	@DisplayName("No.17 「ログアウトする」をクリックする")
	@Order(17)
	void テスト項目No17() {
		// ログイン画面のURLを開く
		openLoginURL("tomatoakain@gmail.com", "Tanaka11#Pass");
		
		// マイページ画面のURLを開く
		openMypageURL();
		
		// 「ログアウトする」をクリックする
		List<WebElement> buttons = webDriver.findElements(By.cssSelector(".withdrow-and-logout-wrapper form button"));
		WebElement logout = buttons.get(1);
		logout.click();
		
		try {
			Thread.sleep(500);			
		} catch(Exception e) {
			return;
		}
		
		// スクリーンショットを取得
		takeScreenShot("No.17 「ログアウトする」をクリックする");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("Osake Market");
		
		// 右上が「ログイン」となっているか検証
		WebElement loginText = webDriver.findElement(By.cssSelector("a.user span"));
		assertEquals("ログイン", loginText.getText());
	}
}
