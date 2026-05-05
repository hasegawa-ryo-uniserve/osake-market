package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import model.Gender;
import model.Login;
import model.User;

/**
 * ユーザーDAOクラス
 */
public class UsersDAO {
	/** ドライバクラス名 */
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";

	/** 接続するDBのURL */
	private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521/xepdb1";

	/** DB接続するためのユーザ名 */
	private static final String DB_USER = "osake_market";

	/** DB接続するためのパスワード */
	private static final String DB_PASS = "systemsss";

	/** 
	 * ログイン情報と同じユーザーがあるか探す 
	 * 
	 * @param login ログイン情報
	 * @return ユーザー 
	 * @throws ClassNotFoundException 
	 *         ドライバクラスが見つからなかった場合 
	 * @throws SQLException 
	 *         DB接続に失敗した場合 
	 */
	public User findByLogin(Login login) {
		User user = null;
		// JDBCドライバを読み込む
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		// データベースへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SQLを準備
			String sql = "SELECT user_id, sei, mei, birthday, gender, postal_code, prefecture,"
					+ "address, building, phone_number, mail, password, token, expire, create_date, mod_date,"
					+ "del_date, del_flag "
					+ "FROM users "
					+ "WHERE mail = ? AND password = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, login.getMail());
			pStmt.setString(2, login.getPassword());

			// SQLを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				// ユーザーが存在したらデータを取得
				// そのユーザーを表すUserインスタンスを生成
				Integer userId = rs.getInt("user_id");
				String sei = rs.getString("sei");
				String mei = rs.getString("mei");
				LocalDate birthday = rs.getDate("birthday").toLocalDate();
				int genderInt = rs.getInt("gender");
				Gender gender = switch (genderInt) {
				case 1 -> Gender.MALE;
				case 2 -> Gender.FEMALE;
				default -> Gender.OTHER;
				};
				String postalCode = rs.getString("postal_code");
				String prefecture = rs.getString("prefecture");
				String address = rs.getString("address");
				String building = rs.getString("building");
				String phoneNumber = rs.getString("phone_number");
				String mail = rs.getString("mail");
				String password = rs.getString("password");
				String token = rs.getString("token");
//				LocalDateTime expire = rs.getTimestamp("expire").toLocalDateTime();
//				LocalDateTime createDate = rs.getTimestamp("create_date").toLocalDateTime();
//				LocalDateTime modDate = rs.getTimestamp("mod_date").toLocalDateTime();
//				LocalDateTime delDate = rs.getTimestamp("del_date").toLocalDateTime();
				Timestamp ts = rs.getTimestamp("expire");
				LocalDateTime expire = (ts != null) ? ts.toLocalDateTime() : null;
				ts = rs.getTimestamp("create_date");
				LocalDateTime createDate = (ts != null) ? ts.toLocalDateTime() : null;
				ts = rs.getTimestamp("mod_date");
				LocalDateTime modDate = (ts != null) ? ts.toLocalDateTime() : null;
				ts = rs.getTimestamp("del_date");
				LocalDateTime delDate = (ts != null) ? ts.toLocalDateTime() : null;
				int delFlagInt = rs.getInt("del_flag");
				boolean delFlag = false;
				if (delFlagInt == 0) {
					delFlag = false;
				} else if (delFlagInt == 1) {
					delFlag = true;
				}
				user = new User(userId, sei, mei, birthday, gender, postalCode, prefecture, address, building,
						phoneNumber,
						mail, password, token, expire, createDate, modDate, delDate, delFlag);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}

	/**
	 * パスワードリセットを送るためのメールアドレスを探す
	 * 
	 * @param resetMail パスワードリセット用メールアドレス
	 * @return ユーザー
	 * @throws ClassNotFoundException 
	 *         ドライバクラスが見つからなかった場合 
	 * @throws SQLException 
	 *         DB接続に失敗した場合 
	 */
	public User findByMail(String resetMail) {
		User user = null;
		// JDBCドライバを読み込む
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		// データベースへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SQLを準備
			String sql = "SELECT user_id, sei, mei, birthday, gender, postal_code, prefecture,"
					+ "address, building, phone_number, mail, password, token, expire, create_date, mod_date,"
					+ "del_date, del_flag "
					+ "FROM users "
					+ "WHERE mail = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, resetMail);

			// SQLを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				// ユーザーが存在したらデータを取得
				// そのユーザーを表すUserインスタンスを生成
				Integer userId = rs.getInt("user_id");
				String sei = rs.getString("sei");
				String mei = rs.getString("mei");
				LocalDate birthday = rs.getDate("birthday").toLocalDate();
				int genderInt = rs.getInt("gender");
				Gender gender = switch (genderInt) {
				case 1 -> Gender.MALE;
				case 2 -> Gender.FEMALE;
				default -> Gender.OTHER;
				};
				String postalCode = rs.getString("postal_code");
				String prefecture = rs.getString("prefecture");
				String address = rs.getString("address");
				String building = rs.getString("building");
				String phoneNumber = rs.getString("phone_number");
				String mail = rs.getString("mail");
				String password = rs.getString("password");
				String token = rs.getString("token");
				//				LocalDateTime expire = rs.getTimestamp("expire").toLocalDateTime();
				Timestamp ts = rs.getTimestamp("expire");
				LocalDateTime expire = (ts != null) ? ts.toLocalDateTime() : null;
				LocalDateTime createDate = rs.getTimestamp("create_date").toLocalDateTime();
				LocalDateTime modDate = rs.getTimestamp("mod_date").toLocalDateTime();
				LocalDateTime delDate = rs.getTimestamp("del_date").toLocalDateTime();
				int delFlagInt = rs.getInt("del_flag");
				boolean delFlag = false;
				if (delFlagInt == 0) {
					delFlag = false;
				} else if (delFlagInt == 1) {
					delFlag = true;
				}
				user = new User(userId, sei, mei, birthday, gender, postalCode, prefecture, address, building,
						phoneNumber,
						mail, password, token, expire, createDate, modDate, delDate, delFlag);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}

	/**
	 * パスワードリセットトークンと有効期限をDBに保存
	 * 
	 * @param userID ユーザーID
	 * @param token パスワードリセットトークン
	 * @param expire トークン有効期限
	 * @return 実行結果
	 * @throws ClassNotFoundException 
	 *         ドライバクラスが見つからなかった場合 
	 * @throws SQLException 
	 *         DB接続に失敗した場合 
	 */
	public boolean saveToken(int userId, String token, LocalDateTime expire) {
		// JDBCドライバを読み込む
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		// データベースへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SQLを準備
			String sql = "UPDATE users SET token = ?, expire = ? WHERE user_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, token);
			pStmt.setTimestamp(2, java.sql.Timestamp.valueOf(expire));
			pStmt.setInt(3, userId);

			// SQLを実行
			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * トークンがあっているか確認
	 * 
	 * @param token パスワードリセットトークン
	 * @return ユーザー
	 * @throws ClassNotFoundException 
	 *         ドライバクラスが見つからなかった場合 
	 * @throws SQLException 
	 *         DB接続に失敗した場合 
	 */
	public User findByToken(String mailToken) {
		User user = null;
		// JDBCドライバを読み込む
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		// データベースへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SQLを準備
			String sql = "SELECT user_id, sei, mei, birthday, gender, postal_code, prefecture,"
					+ "address, building, phone_number, mail, password, token, expire, create_date, mod_date,"
					+ "del_date, del_flag "
					+ "FROM users "
					+ "WHERE token = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, mailToken);

			// SQLを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				// ユーザーが存在したらデータを取得
				// そのユーザーを表すUserインスタンスを生成
				Integer userId = rs.getInt("user_id");
				String sei = rs.getString("sei");
				String mei = rs.getString("mei");
				LocalDate birthday = rs.getDate("birthday").toLocalDate();
				int genderInt = rs.getInt("gender");
				Gender gender = switch (genderInt) {
				case 1 -> Gender.MALE;
				case 2 -> Gender.FEMALE;
				default -> Gender.OTHER;
				};
				String postalCode = rs.getString("postal_code");
				String prefecture = rs.getString("prefecture");
				String address = rs.getString("address");
				String building = rs.getString("building");
				String phoneNumber = rs.getString("phone_number");
				String mail = rs.getString("mail");
				String password = rs.getString("password");
				String token = rs.getString("token");
				LocalDateTime expire = rs.getTimestamp("expire").toLocalDateTime();
				LocalDateTime createDate = rs.getTimestamp("create_date").toLocalDateTime();
				LocalDateTime modDate = rs.getTimestamp("mod_date").toLocalDateTime();
				LocalDateTime delDate = rs.getTimestamp("del_date").toLocalDateTime();
				int delFlagInt = rs.getInt("del_flag");
				boolean delFlag = false;
				if (delFlagInt == 0) {
					delFlag = false;
				} else if (delFlagInt == 1) {
					delFlag = true;
				}
				user = new User(userId, sei, mei, birthday, gender, postalCode, prefecture, address, building,
						phoneNumber,
						mail, password, token, expire, createDate, modDate, delDate, delFlag);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}

	/**
	 * パスワードを更新する
	 * 
	 * @param userId ユーザーID
	 * @param newPassword 新しいパスワード
	 * @return 実行結果
	 * @throws ClassNotFoundException 
	 *         ドライバクラスが見つからなかった場合 
	 * @throws SQLException 
	 *         DB接続に失敗した場合 
	 */
	public boolean updatePassword(int userId, String newPassword) {
		// JDBCドライバを読み込む
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		// データベースへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SQLを準備
			String sql = "UPDATE users SET password = ? WHERE user_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, newPassword);
			pStmt.setInt(2, userId);

			// SQLを実行
			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * パスワードリセットトークンを削除する
	 * 
	 * @param userId ユーザーID
	 * @return 実行結果
	 * @throws ClassNotFoundException 
	 *         ドライバクラスが見つからなかった場合 
	 * @throws SQLException 
	 *         DB接続に失敗した場合 
	 */
	public boolean clearToken(int userId) {
		// JDBCドライバを読み込む
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		// データベースへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SQLを準備
			String sql = "UPDATE users SET token = NULL, expire = NULL WHERE user_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, userId);

			// SQLを実行
			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean registerUser(User user) {
		// JDBCドライバを読み込む
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		// データベースへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SQLを準備
			String sql = "INSERT INTO users(user_id, sei, mei, birthday, gender, postal_code, prefecture,"
					+ "address, building, phone_number, mail, password, token, expire, del_flag)"
					+ "VALUES(seq_user.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, null, null, 0)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user.getSei());
			pStmt.setString(2, user.getMei());
			pStmt.setDate(3, Date.valueOf(user.getBirthday()));
			pStmt.setInt(4, user.getGender().getValue());
			pStmt.setString(5, user.getPostalCode());
			pStmt.setString(6, user.getPrefecture());
			pStmt.setString(7, user.getAddress());
			pStmt.setString(8, user.getBuilding());
			pStmt.setString(9, user.getPhoneNumber());
			pStmt.setString(10, user.getMail());
			pStmt.setString(11, user.getPassword());

			// SQLを実行
			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
