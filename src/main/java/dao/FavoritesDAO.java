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

import model.entity.Favorite;
import model.entity.Product;

/**
 * お気に入りDAOクラス
 */
public class FavoritesDAO {
	/** ドライバクラス名 */
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";

	/** 接続するDBのURL */
	private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521/xepdb1";

	/** DB接続するためのユーザ名 */
	private static final String DB_USER = "osake_market";

	/** DB接続するためのパスワード */
	private static final String DB_PASS = "systemsss";

	/** 
	 * お気に入り一覧を取得する
	 * 
	 * @param userId 会員ID
	 * @return お気に入り商品一覧
	 * @throws ClassNotFoundException 
	 *         ドライバクラスが見つからなかった場合 
	 * @throws SQLException 
	 *         DB接続に失敗した場合 
	 */
	public List<Product> findByUserId(int userId) {
		List<Product> productList = new ArrayList<Product>();
		
		// JDBCドライバを読み込む
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		// データベースに接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SQLを準備
			String sql = "SELECT p.product_id, p.category_id, p.product_name, p.price, p.description, "
							+ "p.image_file, p.stock, p.create_date, p.mod_date, p.del_date, p.del_flag "
							+ "FROM favorites f "
							+ "INNER JOIN products p "
							+ "ON f.product_id = p.product_id "
							+ "WHERE f.user_id = ? AND f.del_flag = 0 AND p.del_flag = 0 "
							+ "ORDER BY f.create_date ASC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, userId);

			// SQLを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				// 商品が存在したらデータを取得
				// その商品を表すProductインスタンスを生成
				Integer productId = rs.getInt("product_id");
				Integer categoryId = rs.getInt("category_id");
				String productName = rs.getString("product_name");
				Integer price = rs.getInt("price");
				String description = rs.getString("description");
				Integer stock = rs.getInt("stock");
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
						stock, imageFile, createDate, modDate, delDate, delFlag);
				productList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return productList;
	}
	
	/** 
	 * お気に入りを追加する
	 * 
	 * @param favorite お気に入り
	 * @return 実行結果
	 * @throws ClassNotFoundException 
	 *         ドライバクラスが見つからなかった場合 
	 * @throws SQLException 
	 *         DB接続に失敗した場合 
	 */
	public boolean registerFavorite(Favorite favorite) {
		// JDBCドライバを読み込む
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		// データベースへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SQLを準備
			String sql = "INSERT INTO favorites(favorite_id, user_id, product_id, del_flag) "
						+ "VALUES(seq_favorite.NEXTVAL, ?, ?, 0)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, favorite.getUserId());
			pStmt.setInt(2, favorite.getProductId());

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
	 * お気に入りを削除する
	 * 
	 * @param favorite お気に入り
	 * @return 実行結果
	 * @throws ClassNotFoundException 
	 *         ドライバクラスが見つからなかった場合 
	 * @throws SQLException 
	 *         DB接続に失敗した場合 
	 */
	public boolean deleteFavorite(Favorite favorite) {
		// JDBCドライバを読み込む
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		// データベースへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SQLを準備
			String sql = "DELETE FROM favorites "
						+ "WHERE user_id = ? AND product_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, favorite.getUserId());
			pStmt.setInt(2, favorite.getProductId());

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
