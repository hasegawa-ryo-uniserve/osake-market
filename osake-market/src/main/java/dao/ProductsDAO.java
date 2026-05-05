package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.Product;

/**
 * 商品DAOクラス
 */
public class ProductsDAO {
	/** ドライバクラス名 */
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";

	/** 接続するDBのURL */
	private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521/xepdb1";

	/** DB接続するためのユーザ名 */
	private static final String DB_USER = "osake_market";

	/** DB接続するためのパスワード */
	private static final String DB_PASS = "systemsss";

	/** 
	 * カテゴリをもとに商品を探す
	 * 
	 * @param categoryName カテゴリ名
	 * @return ユーザー 
	 * @throws ClassNotFoundException 
	 *         ドライバクラスが見つからなかった場合 
	 * @throws SQLException 
	 *         DB接続に失敗した場合 
	 */
//	public User findByCategoryName(String categoryName) {
//		User user = null;
//		// JDBCドライバを読み込む
//		try {
//			Class.forName(DRIVER);
//		} catch (ClassNotFoundException e) {
//			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
//		}
//		// データベースへ接続
//		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
//
//			// SQLを準備
//			String sql = "SELECT p.product_id, c.category_id, c.category_name, p.product_name, p.price, p.description, p.quantity, "
//					+ "p.create_date, p.mod_date, p.del_date, p.del_flag "
//					+ "FROM products p "
//					+ "INNER JOIN categories c"
//					+ "ON p.category_id = c.category_id"
//					+ "WHERE c.category_name = ?";
//			PreparedStatement pStmt = conn.prepareStatement(sql);
//			pStmt.setString(1, categoryName);
//
//			// SQLを実行し、結果表を取得
//			ResultSet rs = pStmt.executeQuery();
//
//			if (rs.next()) {
//				// ユーザーが存在したらデータを取得
//				// そのユーザーを表すUserインスタンスを生成
//				Integer userId = rs.getInt("user_id");
//				String sei = rs.getString("sei");
//				String mei = rs.getString("mei");
//				LocalDate birthday = rs.getDate("birthday").toLocalDate();
//				int genderInt = rs.getInt("gender");
//				Gender gender = switch (genderInt) {
//				case 1 -> Gender.MALE;
//				case 2 -> Gender.FEMALE;
//				default -> Gender.OTHER;
//				};
//				String postalCode = rs.getString("postal_code");
//				String prefecture = rs.getString("prefecture");
//				String address = rs.getString("address");
//				String building = rs.getString("building");
//				String phoneNumber = rs.getString("phone_number");
//				String mail = rs.getString("mail");
//				String password = rs.getString("password");
//				String token = rs.getString("token");
//				LocalDateTime expire = rs.getTimestamp("expire").toLocalDateTime();
//				LocalDateTime createDate = rs.getTimestamp("create_date").toLocalDateTime();
//				LocalDateTime modDate = rs.getTimestamp("mod_date").toLocalDateTime();
//				LocalDateTime delDate = rs.getTimestamp("del_date").toLocalDateTime();
//				int delFlagInt = rs.getInt("del_flag");
//				boolean delFlag = false;
//				if (delFlagInt == 0) {
//					delFlag = false;
//				} else if (delFlagInt == 1) {
//					delFlag = true;
//				}
//				user = new User(userId, sei, mei, birthday, gender, postalCode, prefecture, address, building,
//						phoneNumber,
//						mail, password, token, expire, createDate, modDate, delDate, delFlag);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return null;
//		}
//		return user;
//	}
	
	/**
	 * 商品一覧を取得する
	 * 
	 * @return 商品リスト
	 * @throws ClassNotFoundException 
	 *         ドライバクラスが見つからなかった場合 
	 * @throws SQLException 
	 *         DB接続に失敗した場合 
	 */
	public List<Product> findAll() {
		List<Product> productList = new ArrayList<>();
		// JDBCドライバを読み込む
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		// データベースへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SQLを準備
			String sql = "SELECT p.product_id, c.category_id, c.category_name, p.product_name, p.price, p.description, p.quantity, p.image_file, "
					+ "p.create_date, p.mod_date, p.del_date, p.del_flag "
					+ "FROM products p "
					+ "INNER JOIN categories c "
					+ "ON p.category_id = c.category_id";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQLを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				// 商品が存在したらデータを取得
				// その商品を表すProductインスタンスを生成
				Integer productId = rs.getInt("product_id");
				Integer categoryId = rs.getInt("category_id");
				String categoryName = rs.getString("category_name");
				String productName = rs.getString("product_name");
				Integer price = rs.getInt("price");
				String description = rs.getString("description");
				Integer quantity = rs.getInt("quantity");
				String imageFile = rs.getString("image_file");
				Timestamp tsCreate = rs.getTimestamp("create_date");
				LocalDateTime createDate = (tsCreate != null) ? tsCreate.toLocalDateTime() : null;
				Timestamp tsMod = rs.getTimestamp("mod_date");
				LocalDateTime modDate = (tsMod != null) ? tsMod.toLocalDateTime() : null;
				Timestamp tsDel = rs.getTimestamp("del_date");
				LocalDateTime delDate = (tsDel != null) ? tsDel.toLocalDateTime() : null;
				int delFlagInt = rs.getInt("del_flag");
				boolean delFlag = false;
				if (delFlagInt == 0) {
					delFlag = false;
				} else if (delFlagInt == 1) {
					delFlag = true;
				}
				Product product = new Product(productId, categoryId, productName, price, description,
						quantity, imageFile, createDate, modDate, delDate, delFlag);
				product.setCategoryName(categoryName);
				productList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return productList;
	}
}
