package selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import com.google.common.io.Files;

@TestMethodOrder(OrderAnnotation.class)
class Test006RegisterUserInput {

	private WebDriver webDriver;
	
	/**
	 * テストメソッドを実行する前に実行されるメソッド
	 */
	@BeforeEach
	public void createDriver() {
	    System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");

	    ChromeOptions ops = new ChromeOptions();
	    ops.addArguments("--remote-allow-origins=*");

	    // パスワード保存ダイアログを無効化
	    Map<String, Object> prefs = new HashMap<>();
	    prefs.put("credentials_enable_service", false);
	    prefs.put("profile.password_manager_enabled", false);
	    ops.setExperimentalOption("prefs", prefs);

	    webDriver = new ChromeDriver(ops);
	}
	
	@AfterEach
	public void quitDriver() {
		webDriver.close();
	}
	
	// 会員登録フォームのURLを開く
	private void openURL() {
		webDriver.get("http://localhost:8080/osake-market/register/user");
	}
	
	// スクリーンショットを取得
	private void takeScreenShot(String fileName) {
		// スクリーンショット保存パス
		String screenshotPath = "screenshots\\test006_registerUserInput\\";
		
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
	
	// 入力欄を空欄にする
	private void clearInput(WebElement... elements) {
		for (WebElement element : elements) {
			element.clear();
		}
	}
	
	@Test
	@DisplayName("No.1 ブラウザでhttp://localhost:8080/osake-market/register/userにアクセス")
	@Order(1)
	void テスト項目No1() {
		// 指定したURLに遷移する
		openURL();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/register/user");
	}
	
	@Test
	@DisplayName("No.2 ブラウザでhttp://localhost:8080/osake-market/register/userにアクセス")
	@Order(2)
	void テスト項目No2() {
		// 指定したURLに遷移する
		openURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.02 ブラウザでhttp://localhost:8080/osake-market/register/userにアクセス");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("新規会員登録(入力) - Osake Market");
	}
	
	@Test
	@DisplayName("No.3 会員情報登録画面が表示されている")
	@Order(3)
	void テスト項目No3() {
		// 指定したURLに遷移する
		openURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.03 会員情報登録画面が表示されている");
		
		// 要素が表示されているか取得
		final WebElement sei = webDriver.findElement(By.name("sei"));
		final WebElement mei = webDriver.findElement(By.name("mei"));
		final WebElement birthday = webDriver.findElement(By.name("birthday"));
		final WebElement gender = webDriver.findElement(By.name("gender"));
		final WebElement postalCode = webDriver.findElement(By.name("postalCode"));
		final WebElement prefecture = webDriver.findElement(By.name("prefecture"));
		final WebElement address = webDriver.findElement(By.name("address"));
		final WebElement building = webDriver.findElement(By.name("building"));
		final WebElement phoneNumber = webDriver.findElement(By.name("phoneNumber"));
		final WebElement mail = webDriver.findElement(By.name("mail"));
		final WebElement password = webDriver.findElement(By.name("password"));
		final WebElement submit = webDriver.findElement(By.cssSelector("input[value = 'アカウントを作成する']"));
		final WebElement back = webDriver.findElement(By.cssSelector("a button"));
		
		// 要素が表示されているか確認
		assertDisplayed(sei, mei, birthday, gender, postalCode, prefecture, address,
				building, phoneNumber, mail, password, submit, back);
	}
	
	@Test
	@DisplayName("No.4 会員情報登録画面が表示されている")
	@Order(4)
	void テスト項目No4() {
		// 指定したURLに遷移する
		openURL();
		
		// スクリーンショットを取得
		takeScreenShot("No.04 会員情報登録画面が表示されている");
		
		// 要素が表示されているか取得
		final WebElement sei = webDriver.findElement(By.name("sei"));
		final WebElement mei = webDriver.findElement(By.name("mei"));
		final WebElement birthday = webDriver.findElement(By.name("birthday"));
		final WebElement postalCode = webDriver.findElement(By.name("postalCode"));
		final WebElement address = webDriver.findElement(By.name("address"));
		final WebElement building = webDriver.findElement(By.name("building"));
		final WebElement phoneNumber = webDriver.findElement(By.name("phoneNumber"));
		final WebElement mail = webDriver.findElement(By.name("mail"));
		final WebElement password = webDriver.findElement(By.name("password"));
		
		List<WebElement> genders = webDriver.findElements(By.name("gender"));
		final WebElement male = genders.get(0);
		
		final WebElement prefecture = webDriver.findElement(By.name("prefecture"));
		final Select select = new Select(prefecture);
		
		// 入力欄が空欄か検証
		assertEquals("", sei.getAttribute("value"));
		assertEquals("", mei.getAttribute("value"));
		assertEquals("", birthday.getAttribute("value"));
		assertEquals("", postalCode.getAttribute("value"));
		assertEquals("", address.getAttribute("value"));
		assertEquals("", building.getAttribute("value"));
		assertEquals("", phoneNumber.getAttribute("value"));
		assertEquals("", mail.getAttribute("value"));
		assertEquals("", password.getAttribute("value"));
		
		// 性別は男性が選択されているか検証
		assertTrue(male.isSelected());
		
		// 都道府県は東京都が選択されているか検証
		assertEquals("東京都", select.getFirstSelectedOption().getText());
	}
	
	@Test
	@DisplayName("No.5 全て空白(性別:男性、都道府県:東京都)で入力")
	@Order(5)
	void テスト項目No5() {
		// 指定したURLに遷移する
		openURL();
		
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
		final WebElement submit = webDriver.findElement(By.cssSelector("input[value = 'アカウントを作成する']"));
		
		// 空白にする
		clearInput(sei, mei, birthday, postalCode, address, building, phoneNumber, mail, password);
		
		// 「アカウントを作成する」ボタンを押す
		submit.click();
		
		// スクリーンショットを取得
		takeScreenShot("No.05 全て空白(性別:男性、都道府県:東京都)で入力");
		
		// 「このフィールドを入力してください」が表示されているか検証
		assertEquals("このフィールドを入力してください。", sei.getAttribute("validationMessage"));
	}
	
	@Test
	@DisplayName("No.6 姓だけ空白で入力")
	@Order(6)
	void テスト項目No6() {
		// 指定したURLに遷移する
		openURL();
		
		// 要素を取得
		WebElement sei = webDriver.findElement(By.name("sei"));
		final WebElement mei = webDriver.findElement(By.name("mei"));
		final WebElement birthday = webDriver.findElement(By.name("birthday"));
		final WebElement female = webDriver.findElement(By.cssSelector("input[value = '2']"));
		final WebElement postalCode = webDriver.findElement(By.name("postalCode"));
		final WebElement prefecture = webDriver.findElement(By.name("prefecture"));
		final Select select = new Select(prefecture);
		final WebElement address = webDriver.findElement(By.name("address"));
		final WebElement building = webDriver.findElement(By.name("building"));
		final WebElement phoneNumber = webDriver.findElement(By.name("phoneNumber"));
		final WebElement mail = webDriver.findElement(By.name("mail"));
		final WebElement password = webDriver.findElement(By.name("password"));
		final WebElement submit = webDriver.findElement(By.cssSelector("input[value = 'アカウントを作成する']"));
		
		// 空白にする
		clearInput(sei, mei, birthday, postalCode, address, building, phoneNumber, mail, password);
		
		// 姓以外を入力する
		mei.sendKeys("花子");
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		js.executeScript("arguments[0].value='2000-01-01';", birthday);
		female.click();
		postalCode.sendKeys("1234567");
		select.selectByVisibleText("東京都");
		address.sendKeys("八王子市1-1-1");
		building.sendKeys("八王子ハイツ102");
		phoneNumber.sendKeys("09012345678");
		mail.sendKeys("hanako@gmail.com");
		password.sendKeys("Watanabe77#Pass");
		
		// 「アカウントを作成する」ボタンを押す
		submit.click();
		
		// スクリーンショットを取得
		takeScreenShot("No.06 姓だけ空白で入力");
		
		// 要素を再取得
		sei = webDriver.findElement(By.name("sei"));
		
		// 「このフィールドを入力してください」が表示されているか検証
		assertEquals("このフィールドを入力してください。", sei.getAttribute("validationMessage"));
	}
	
	@Test
	@DisplayName("No.7 名だけ空白で入力")
	@Order(7)
	void テスト項目No7() {
		// 指定したURLに遷移する
		openURL();
		
		// 要素を取得
		final WebElement sei = webDriver.findElement(By.name("sei"));
		WebElement mei = webDriver.findElement(By.name("mei"));
		final WebElement birthday = webDriver.findElement(By.name("birthday"));
		final WebElement female = webDriver.findElement(By.cssSelector("input[value = '2']"));
		final WebElement postalCode = webDriver.findElement(By.name("postalCode"));
		final WebElement prefecture = webDriver.findElement(By.name("prefecture"));
		final Select select = new Select(prefecture);
		final WebElement address = webDriver.findElement(By.name("address"));
		final WebElement building = webDriver.findElement(By.name("building"));
		final WebElement phoneNumber = webDriver.findElement(By.name("phoneNumber"));
		final WebElement mail = webDriver.findElement(By.name("mail"));
		final WebElement password = webDriver.findElement(By.name("password"));
		final WebElement submit = webDriver.findElement(By.cssSelector("input[value = 'アカウントを作成する']"));
		
		// 空白にする
		clearInput(sei, mei, birthday, postalCode, address, building, phoneNumber, mail, password);
		
		// 名以外を入力する
		sei.sendKeys("渡辺");
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		js.executeScript("arguments[0].value='2000-01-01';", birthday);
		female.click();
		postalCode.sendKeys("1234567");
		select.selectByVisibleText("東京都");
		address.sendKeys("八王子市1-1-1");
		building.sendKeys("八王子ハイツ102");
		phoneNumber.sendKeys("09012345678");
		mail.sendKeys("hanako@gmail.com");
		password.sendKeys("Watanabe77#Pass");
		
		// 「アカウントを作成する」ボタンを押す
		submit.click();
		
		// スクリーンショットを取得
		takeScreenShot("No.07 名だけ空白で入力");
		
		// 要素を再取得
		mei = webDriver.findElement(By.name("mei"));
		
		// 「このフィールドを入力してください」が表示されているか検証
		assertEquals("このフィールドを入力してください。", mei.getAttribute("validationMessage"));
	}
	
	@Test
	@DisplayName("No.8 生年月日だけ空白で入力")
	@Order(8)
	void テスト項目No8() {
		// 指定したURLに遷移する
		openURL();
		
		// 要素を取得
		final WebElement sei = webDriver.findElement(By.name("sei"));
		final WebElement mei = webDriver.findElement(By.name("mei"));
		WebElement birthday = webDriver.findElement(By.name("birthday"));
		final WebElement female = webDriver.findElement(By.cssSelector("input[value = '2']"));
		final WebElement postalCode = webDriver.findElement(By.name("postalCode"));
		final WebElement prefecture = webDriver.findElement(By.name("prefecture"));
		final Select select = new Select(prefecture);
		final WebElement address = webDriver.findElement(By.name("address"));
		final WebElement building = webDriver.findElement(By.name("building"));
		final WebElement phoneNumber = webDriver.findElement(By.name("phoneNumber"));
		final WebElement mail = webDriver.findElement(By.name("mail"));
		final WebElement password = webDriver.findElement(By.name("password"));
		final WebElement submit = webDriver.findElement(By.cssSelector("input[value = 'アカウントを作成する']"));
		
		// 空白にする
		clearInput(sei, mei, birthday, postalCode, address, building, phoneNumber, mail, password);
		
		// 生年月日以外を入力する
		sei.sendKeys("渡辺");
		mei.sendKeys("花子");
		female.click();
		postalCode.sendKeys("1234567");
		select.selectByVisibleText("東京都");
		address.sendKeys("八王子市1-1-1");
		building.sendKeys("八王子ハイツ102");
		phoneNumber.sendKeys("09012345678");
		mail.sendKeys("hanako@gmail.com");
		password.sendKeys("Watanabe77#Pass");
		
		// 「アカウントを作成する」ボタンを押す
		submit.click();
		
		// スクリーンショットを取得
		takeScreenShot("No.08 生年月日だけ空白で入力");
		
		// 要素を再取得
		birthday = webDriver.findElement(By.name("birthday"));
		
		// 「このフィールドを入力してください」が表示されているか検証
		assertEquals("このフィールドを入力してください。", birthday.getAttribute("validationMessage"));
	}
	
	@Test
	@DisplayName("No.9 郵便番号だけ空白で入力")
	@Order(9)
	void テスト項目No9() {
		// 指定したURLに遷移する
		openURL();
		
		// 要素を取得
		final WebElement sei = webDriver.findElement(By.name("sei"));
		final WebElement mei = webDriver.findElement(By.name("mei"));
		final WebElement birthday = webDriver.findElement(By.name("birthday"));
		final WebElement female = webDriver.findElement(By.cssSelector("input[value = '2']"));
		WebElement postalCode = webDriver.findElement(By.name("postalCode"));
		final WebElement prefecture = webDriver.findElement(By.name("prefecture"));
		final Select select = new Select(prefecture);
		final WebElement address = webDriver.findElement(By.name("address"));
		final WebElement building = webDriver.findElement(By.name("building"));
		final WebElement phoneNumber = webDriver.findElement(By.name("phoneNumber"));
		final WebElement mail = webDriver.findElement(By.name("mail"));
		final WebElement password = webDriver.findElement(By.name("password"));
		final WebElement submit = webDriver.findElement(By.cssSelector("input[value = 'アカウントを作成する']"));
		
		// 空白にする
		clearInput(sei, mei, birthday, postalCode, address, building, phoneNumber, mail, password);
		
		// 郵便番号以外を入力する
		sei.sendKeys("渡辺");
		mei.sendKeys("花子");
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		js.executeScript("arguments[0].value='2000-01-01';", birthday);
		female.click();
		select.selectByVisibleText("東京都");
		address.sendKeys("八王子市1-1-1");
		building.sendKeys("八王子ハイツ102");
		phoneNumber.sendKeys("09012345678");
		mail.sendKeys("hanako@gmail.com");
		password.sendKeys("Watanabe77#Pass");
		
		// 「アカウントを作成する」ボタンを押す
		submit.click();
		
		// スクリーンショットを取得
		takeScreenShot("No.09 郵便番号だけ空白で入力");
		
		// 要素を再取得
		postalCode = webDriver.findElement(By.name("postalCode"));
		
		// 「このフィールドを入力してください」が表示されているか検証
		assertEquals("このフィールドを入力してください。", postalCode.getAttribute("validationMessage"));
	}
	
	@Test
	@DisplayName("No.10 住所だけ空白で入力")
	@Order(10)
	void テスト項目No10() {
		// 指定したURLに遷移する
		openURL();
		
		// 要素を取得
		final WebElement sei = webDriver.findElement(By.name("sei"));
		final WebElement mei = webDriver.findElement(By.name("mei"));
		final WebElement birthday = webDriver.findElement(By.name("birthday"));
		final WebElement female = webDriver.findElement(By.cssSelector("input[value = '2']"));
		final WebElement postalCode = webDriver.findElement(By.name("postalCode"));
		final WebElement prefecture = webDriver.findElement(By.name("prefecture"));
		final Select select = new Select(prefecture);
		WebElement address = webDriver.findElement(By.name("address"));
		final WebElement building = webDriver.findElement(By.name("building"));
		final WebElement phoneNumber = webDriver.findElement(By.name("phoneNumber"));
		final WebElement mail = webDriver.findElement(By.name("mail"));
		final WebElement password = webDriver.findElement(By.name("password"));
		final WebElement submit = webDriver.findElement(By.cssSelector("input[value = 'アカウントを作成する']"));
		
		// 空白にする
		clearInput(sei, mei, birthday, postalCode, address, building, phoneNumber, mail, password);
		
		// 住所以外を入力する
		sei.sendKeys("渡辺");
		mei.sendKeys("花子");
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		js.executeScript("arguments[0].value='2000-01-01';", birthday);
		female.click();
		postalCode.sendKeys("1234567");
		select.selectByVisibleText("東京都");
		building.sendKeys("八王子ハイツ102");
		phoneNumber.sendKeys("09012345678");
		mail.sendKeys("hanako@gmail.com");
		password.sendKeys("Watanabe77#Pass");
		
		// 「アカウントを作成する」ボタンを押す
		submit.click();
		
		// スクリーンショットを取得
		takeScreenShot("No.10 住所だけ空白で入力");
		
		// 要素を再取得
		address = webDriver.findElement(By.name("address"));
		
		// 「このフィールドを入力してください」が表示されているか検証
		assertEquals("このフィールドを入力してください。", address.getAttribute("validationMessage"));
	}
	
	@Test
	@DisplayName("No.11 建物名・部屋番号だけ空白で入力")
	@Order(11)
	void テスト項目No11() {
		// 指定したURLに遷移する
		openURL();
		
		// 要素を取得
		final WebElement sei = webDriver.findElement(By.name("sei"));
		final WebElement mei = webDriver.findElement(By.name("mei"));
		final WebElement birthday = webDriver.findElement(By.name("birthday"));
		final WebElement female = webDriver.findElement(By.cssSelector("input[value = '2']"));
		final WebElement postalCode = webDriver.findElement(By.name("postalCode"));
		final WebElement prefecture = webDriver.findElement(By.name("prefecture"));
		final Select select = new Select(prefecture);
		final WebElement address = webDriver.findElement(By.name("address"));
		final WebElement building = webDriver.findElement(By.name("building"));
		final WebElement phoneNumber = webDriver.findElement(By.name("phoneNumber"));
		final WebElement mail = webDriver.findElement(By.name("mail"));
		final WebElement password = webDriver.findElement(By.name("password"));
		final WebElement submit = webDriver.findElement(By.cssSelector("input[value = 'アカウントを作成する']"));
		
		// 空白にする
		clearInput(sei, mei, birthday, postalCode, address, building, phoneNumber, mail, password);
		
		// 建物名・部屋番号以外を入力する
		sei.sendKeys("渡辺");
		mei.sendKeys("花子");
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		js.executeScript("arguments[0].value='2000-01-01';", birthday);
		female.click();
		postalCode.sendKeys("1234567");
		select.selectByVisibleText("東京都");
		address.sendKeys("八王子市1-1-1");
		phoneNumber.sendKeys("09012345678");
		mail.sendKeys("hanako@gmail.com");
		password.sendKeys("Watanabe77#Pass");
		
		// 「アカウントを作成する」ボタンを押す
		submit.click();
		
		// スクリーンショットを取得
		takeScreenShot("No.11 建物名・部屋番号だけ空白で入力");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("新規会員登録(確認) - Osake Market");
	}
	
	@Test
	@DisplayName("No.12 電話番号だけ空白で入力")
	@Order(12)
	void テスト項目No12() {
		// 指定したURLに遷移する
		openURL();
		
		// 要素を取得
		final WebElement sei = webDriver.findElement(By.name("sei"));
		final WebElement mei = webDriver.findElement(By.name("mei"));
		final WebElement birthday = webDriver.findElement(By.name("birthday"));
		final WebElement female = webDriver.findElement(By.cssSelector("input[value = '2']"));
		final WebElement postalCode = webDriver.findElement(By.name("postalCode"));
		final WebElement prefecture = webDriver.findElement(By.name("prefecture"));
		final Select select = new Select(prefecture);
		final WebElement address = webDriver.findElement(By.name("address"));
		final WebElement building = webDriver.findElement(By.name("building"));
		WebElement phoneNumber = webDriver.findElement(By.name("phoneNumber"));
		final WebElement mail = webDriver.findElement(By.name("mail"));
		final WebElement password = webDriver.findElement(By.name("password"));
		final WebElement submit = webDriver.findElement(By.cssSelector("input[value = 'アカウントを作成する']"));
		
		// 空白にする
		clearInput(sei, mei, birthday, postalCode, address, building, phoneNumber, mail, password);
		
		// 電話番号以外を入力する
		sei.sendKeys("渡辺");
		mei.sendKeys("花子");
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		js.executeScript("arguments[0].value='2000-01-01';", birthday);
		female.click();
		postalCode.sendKeys("1234567");
		select.selectByVisibleText("東京都");
		address.sendKeys("八王子市1-1-1");
		building.sendKeys("八王子ハイツ102");
		mail.sendKeys("hanako@gmail.com");
		password.sendKeys("Watanabe77#Pass");
		
		// 「アカウントを作成する」ボタンを押す
		submit.click();
		
		// スクリーンショットを取得
		takeScreenShot("No.12 電話番号だけ空白で入力");
		
		// 要素を再取得
		phoneNumber = webDriver.findElement(By.name("phoneNumber"));
		
		// 「このフィールドを入力してください」が表示されているか検証
		assertEquals("このフィールドを入力してください。", phoneNumber.getAttribute("validationMessage"));
	}
	
	@Test
	@DisplayName("No.13 メールアドレスだけ空白で入力")
	@Order(13)
	void テスト項目No13() {
		// 指定したURLに遷移する
		openURL();
		
		// 要素を取得
		final WebElement sei = webDriver.findElement(By.name("sei"));
		final WebElement mei = webDriver.findElement(By.name("mei"));
		final WebElement birthday = webDriver.findElement(By.name("birthday"));
		final WebElement female = webDriver.findElement(By.cssSelector("input[value = '2']"));
		final WebElement postalCode = webDriver.findElement(By.name("postalCode"));
		final WebElement prefecture = webDriver.findElement(By.name("prefecture"));
		final Select select = new Select(prefecture);
		final WebElement address = webDriver.findElement(By.name("address"));
		final WebElement building = webDriver.findElement(By.name("building"));
		final WebElement phoneNumber = webDriver.findElement(By.name("phoneNumber"));
		WebElement mail = webDriver.findElement(By.name("mail"));
		final WebElement password = webDriver.findElement(By.name("password"));
		final WebElement submit = webDriver.findElement(By.cssSelector("input[value = 'アカウントを作成する']"));
		
		// 空白にする
		clearInput(sei, mei, birthday, postalCode, address, building, phoneNumber, mail, password);
		
		// メールアドレス以外を入力する
		sei.sendKeys("渡辺");
		mei.sendKeys("花子");
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		js.executeScript("arguments[0].value='2000-01-01';", birthday);
		female.click();
		postalCode.sendKeys("1234567");
		select.selectByVisibleText("東京都");
		address.sendKeys("八王子市1-1-1");
		building.sendKeys("八王子ハイツ102");
		phoneNumber.sendKeys("09012345678");
		password.sendKeys("Watanabe77#Pass");
		
		// 「アカウントを作成する」ボタンを押す
		submit.click();
		
		// スクリーンショットを取得
		takeScreenShot("No.13 メールアドレスだけ空白で入力");
		
		// 要素を再取得
		mail = webDriver.findElement(By.name("mail"));
		
		// 「このフィールドを入力してください」が表示されているか検証
		assertEquals("このフィールドを入力してください。", mail.getAttribute("validationMessage"));
	}
	
	@Test
	@DisplayName("No.14 パスワードだけ空白で入力")
	@Order(14)
	void テスト項目No14() {
		// 指定したURLに遷移する
		openURL();
		
		// 要素を取得
		final WebElement sei = webDriver.findElement(By.name("sei"));
		final WebElement mei = webDriver.findElement(By.name("mei"));
		final WebElement birthday = webDriver.findElement(By.name("birthday"));
		final WebElement female = webDriver.findElement(By.cssSelector("input[value = '2']"));
		final WebElement postalCode = webDriver.findElement(By.name("postalCode"));
		final WebElement prefecture = webDriver.findElement(By.name("prefecture"));
		final Select select = new Select(prefecture);
		final WebElement address = webDriver.findElement(By.name("address"));
		final WebElement building = webDriver.findElement(By.name("building"));
		final WebElement phoneNumber = webDriver.findElement(By.name("phoneNumber"));
		final WebElement mail = webDriver.findElement(By.name("mail"));
		WebElement password = webDriver.findElement(By.name("password"));
		final WebElement submit = webDriver.findElement(By.cssSelector("input[value = 'アカウントを作成する']"));
		
		// 空白にする
		clearInput(sei, mei, birthday, postalCode, address, building, phoneNumber, mail, password);
		
		// パスワード以外を入力する
		sei.sendKeys("渡辺");
		mei.sendKeys("花子");
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		js.executeScript("arguments[0].value='2000-01-01';", birthday);
		female.click();
		postalCode.sendKeys("1234567");
		select.selectByVisibleText("東京都");
		address.sendKeys("八王子市1-1-1");
		building.sendKeys("八王子ハイツ102");
		phoneNumber.sendKeys("09012345678");
		mail.sendKeys("hanako@gmail.com");
		
		// 「アカウントを作成する」ボタンを押す
		submit.click();
		
		// スクリーンショットを取得
		takeScreenShot("No.14 パスワードだけ空白で入力");
		
		// 要素を再取得
		password = webDriver.findElement(By.name("password"));
		
		// 「このフィールドを入力してください」が表示されているか検証
		assertEquals("このフィールドを入力してください。", password.getAttribute("validationMessage"));
	}
	
	@Test
	@DisplayName("No.15 全て入力")
	@Order(15)
	void テスト項目No15() {
		// 指定したURLに遷移する
		openURL();
		
		// 要素を取得
		final WebElement sei = webDriver.findElement(By.name("sei"));
		final WebElement mei = webDriver.findElement(By.name("mei"));
		final WebElement birthday = webDriver.findElement(By.name("birthday"));
		final WebElement female = webDriver.findElement(By.cssSelector("input[value = '2']"));
		final WebElement postalCode = webDriver.findElement(By.name("postalCode"));
		final WebElement prefecture = webDriver.findElement(By.name("prefecture"));
		final Select select = new Select(prefecture);
		final WebElement address = webDriver.findElement(By.name("address"));
		final WebElement building = webDriver.findElement(By.name("building"));
		final WebElement phoneNumber = webDriver.findElement(By.name("phoneNumber"));
		final WebElement mail = webDriver.findElement(By.name("mail"));
		final WebElement password = webDriver.findElement(By.name("password"));
		final WebElement submit = webDriver.findElement(By.cssSelector("input[value = 'アカウントを作成する']"));
		
		// 空白にする
		clearInput(sei, mei, birthday, postalCode, address, building, phoneNumber, mail, password);
		
		// 全て入力する
		sei.sendKeys("渡辺");
		mei.sendKeys("花子");
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		js.executeScript("arguments[0].value='2000-01-01';", birthday);
		female.click();
		postalCode.sendKeys("1234567");
		select.selectByVisibleText("東京都");
		address.sendKeys("八王子市1-1-1");
		building.sendKeys("八王子ハイツ102");
		phoneNumber.sendKeys("09012345678");
		mail.sendKeys("hanako@gmail.com");
		password.sendKeys("Watanabe77#Pass");
		
		// 「アカウントを作成する」ボタンを押す
		submit.click();
		
		// スクリーンショットを取得
		takeScreenShot("No.15 全て入力");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("新規会員登録(確認) - Osake Market");
	}
	
	@Test
	@DisplayName("No.16 全て入力")
	@Order(16)
	void テスト項目No16() {
		// 指定したURLに遷移する
		openURL();
		
		// 要素を取得
		final WebElement sei = webDriver.findElement(By.name("sei"));
		final WebElement mei = webDriver.findElement(By.name("mei"));
		final WebElement birthday = webDriver.findElement(By.name("birthday"));
		final WebElement female = webDriver.findElement(By.cssSelector("input[value = '2']"));
		final WebElement postalCode = webDriver.findElement(By.name("postalCode"));
		final WebElement prefecture = webDriver.findElement(By.name("prefecture"));
		final Select select = new Select(prefecture);
		final WebElement address = webDriver.findElement(By.name("address"));
		final WebElement building = webDriver.findElement(By.name("building"));
		final WebElement phoneNumber = webDriver.findElement(By.name("phoneNumber"));
		final WebElement mail = webDriver.findElement(By.name("mail"));
		final WebElement password = webDriver.findElement(By.name("password"));
		final WebElement submit = webDriver.findElement(By.cssSelector("input[value = 'アカウントを作成する']"));
		
		// 空白にする
		clearInput(sei, mei, birthday, postalCode, address, building, phoneNumber, mail, password);
		
		// 全て入力する
		sei.sendKeys("渡辺");
		mei.sendKeys("花子");
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		js.executeScript("arguments[0].value='2000-01-01';", birthday);
		female.click();
		postalCode.sendKeys("1234567");
		select.selectByVisibleText("東京都");
		address.sendKeys("八王子市1-1-1");
		building.sendKeys("八王子ハイツ102");
		phoneNumber.sendKeys("09012345678");
		mail.sendKeys("hanako@gmail.com");
		password.sendKeys("Watanabe77#Pass");
		
		// 「アカウントを作成する」ボタンを押す
		submit.click();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/register/user/confirm");
	}
	
	@Test
	@DisplayName("No.17 「戻る」ボタンをクリック")
	@Order(17)
	void テスト項目No17() {
		// 指定したURLに遷移する
		openURL();
		
		// 要素を取得
		final WebElement back = webDriver.findElement(By.cssSelector("a button"));
		
		// 戻るボタンをクリック
		back.click();
		
		// スクリーンショットを取得
		takeScreenShot("No.17 「戻る」ボタンをクリック");
		
		// ページタイトルを用いて正しいページか検証
		assertTitle("ログイン - Osake Market");
	}
	
	@Test
	@DisplayName("No.18 「戻る」ボタンをクリック")
	@Order(18)
	void テスト項目No18() {
		// 指定したURLに遷移する
		openURL();
		
		// 要素を取得
		final WebElement back = webDriver.findElement(By.cssSelector("a button"));
		
		// 戻るボタンをクリック
		back.click();
		
		// URLを検証
		assertURL("http://localhost:8080/osake-market/login");
	}
}
