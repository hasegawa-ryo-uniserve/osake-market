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

import model.Review;

/**
 * レビューDAOクラス
 */
public class ReviewsDAO {
	/** ドライバクラス名 */
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";

	/** 接続するDBのURL */
	private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521/xepdb1";

	/** DB接続するためのユーザ名 */
	private static final String DB_USER = "osake_market";

	/** DB接続するためのパスワード */
	private static final String DB_PASS = "systemsss";
	
	/**
	 * 商品IDをもとにレビューを取得する
	 * 
	 * @param productId 商品ID
	 * @return レビューリスト
	 * @throws ClassNotFoundException 
	 *         ドライバクラスが見つからなかった場合 
	 * @throws SQLException 
	 *         DB接続に失敗した場合 
	 */
	public List<Review> findByProductId(int inputProductId) {
		List<Review> reviewList = new ArrayList<>();
		// JDBCドライバを読み込む
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		// データベースへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SQLを準備
			String sql = "SELECT review_id, user_id, product_id, star, review_text, "
					+ "create_date, mod_date, del_date, del_flag "
					+ "FROM reviews "
					+ "WHERE product_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, inputProductId);

			// SQLを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				// 商品が存在したらデータを取得
				// その商品を表すProductインスタンスを生成
				Integer reviewId = rs.getInt("review_id");
				Integer userId = rs.getInt("user_id");
				Integer productId = rs.getInt("product_id");
				Integer star = rs.getInt("star");
				String reviewText = rs.getString("review_text");
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
				Review review = new Review(reviewId, userId, productId, star, reviewText,
						createDate, modDate, delDate, delFlag);
				reviewList.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return reviewList;
	}
}
