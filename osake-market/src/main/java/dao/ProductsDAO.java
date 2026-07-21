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

import model.entity.Product;

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
	 * カテゴリ名をもとに商品を取得する
	 * 
	 * @param inputCategoryName カテゴリ名
	 * @return 商品リスト一覧
	 * @throws ClassNotFoundException 
	 *         ドライバクラスが見つからなかった場合 
	 * @throws SQLException 
	 *         DB接続に失敗した場合 
	 */
	public List<Product> findByCategoryName(String inputCategoryName) {
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
			String sql = "SELECT p.product_id, c.category_id, c.category_name, p.product_name, p.price, p.description, p.stock, p.image_file, "
						+ "p.create_date, p.mod_date, p.del_date, p.del_flag "
						+ "FROM products p "
						+ "INNER JOIN categories c "
						+ "ON p.category_id = c.category_id "
						+ "WHERE category_name = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, inputCategoryName);

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
				product.setCategoryName(categoryName);
				productList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return productList;
	}

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
			String sql = "SELECT p.product_id, c.category_id, c.category_name, p.product_name, p.price, p.description, p.stock, p.image_file, "
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
				product.setCategoryName(categoryName);
				productList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return productList;
	}

	/**
	 * 商品IDをもとに商品を取得する
	 * 
	 * @param productId 商品ID
	 * @param userId 会員ID
	 * @return 商品
	 * @throws ClassNotFoundException 
	 *         ドライバクラスが見つからなかった場合 
	 * @throws SQLException 
	 *         DB接続に失敗した場合 
	 */
	public Product findByProductId(int inputProductId, Integer userId) {
		Product product = null;
		// JDBCドライバを読み込む
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		// データベースへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = null;
			PreparedStatement pStmt = null;
			if (userId == null) {
				// SQLを準備
				sql = "SELECT p.product_id, p.category_id, c.category_name, p.product_name, p.price, "
					+ "p.description, p.stock, p.image_file, "
					+ "p.create_date, p.mod_date, p.del_date, p.del_flag "
					+ "FROM products p "
					+ "INNER JOIN categories c "
					+ "ON p.category_id = c.category_id "
					+ "WHERE product_id = ?";
				
				pStmt = conn.prepareStatement(sql);
				pStmt.setInt(1, inputProductId);
			} else {
				// SQLを準備
				sql = "SELECT p.product_id, p.category_id, c.category_name, p.product_name, p.price, "
					+ "p.description, p.stock, p.image_file, "
					+ "p.create_date, p.mod_date, p.del_date, p.del_flag, f.favorite_id "
					+ "FROM products p "
					+ "INNER JOIN categories c "
					+ "ON p.category_id = c.category_id "
					+ "LEFT JOIN favorites f "
					+ "ON p.product_id = f.product_id AND f.user_id = ? "
					+ "WHERE p.product_id = ?";
				
				pStmt = conn.prepareStatement(sql);
				pStmt.setInt(1, userId);
				pStmt.setInt(2, inputProductId);
			}

			// SQLを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				// 商品が存在したらデータを取得
				// その商品を表すProductインスタンスを生成
				Integer productId = rs.getInt("product_id");
				Integer categoryId = rs.getInt("category_id");
				String categoryName = rs.getString("category_name");
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
				product = new Product(productId, categoryId, productName, price, description,
						stock, imageFile, createDate, modDate, delDate, delFlag);
				product.setCategoryName(categoryName);
				
				// ログインしているならお気に入りフラグをtrueにする
				boolean favoriteFlag = false;
				if (userId != null) {
					int favoriteId = rs.getInt("favorite_id");
					favoriteFlag = (favoriteId != 0);
				}
				product.setFavoriteFlag(favoriteFlag);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return product;
	}

	/**
	 * ソートで商品一覧を絞込検索
	 * 
	 * @param sort ソート
	 * @param userId 会員ID
	 * @return 商品リスト
	 * @throws ClassNotFoundException 
	 *         ドライバクラスが見つからなかった場合 
	 * @throws SQLException 
	 *         DB接続に失敗した場合 
	 */
	public List<Product> findAllOrderBySort(String sort, Integer userId) {
		List<Product> productList = new ArrayList<>();
		// JDBCドライバを読み込む
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		// データベースへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = null;
			PreparedStatement pStmt = null;
			// 非ログイン
			if (userId == null) {
				sql = "SELECT p.product_id, c.category_id, c.category_name, p.product_name, p.price, p.description, p.stock, p.image_file, "
					+ "p.create_date, p.mod_date, p.del_date, p.del_flag "
					+ "FROM products p "
					+ "INNER JOIN categories c "
					+ "ON p.category_id = c.category_id "
					+ "ORDER BY " + sort + ", category_id, product_id";

				pStmt = conn.prepareStatement(sql);
			} else {
				// ログイン
				sql = "SELECT p.product_id, c.category_id, c.category_name, p.product_name, p.price, p.description, p.stock, p.image_file, "
					+ "p.create_date, p.mod_date, p.del_date, p.del_flag, f.favorite_id "
					+ "FROM products p "
					+ "INNER JOIN categories c "
					+ "ON p.category_id = c.category_id "
					+ "LEFT JOIN favorites f "
					+ "ON p.product_id = f.product_id AND f.user_id = ? "
					+ "ORDER BY " + sort + ", category_id, product_id";

				pStmt = conn.prepareStatement(sql);
				pStmt.setInt(1, userId);
			}

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
				product.setCategoryName(categoryName);

				// ログインしているならお気に入りフラグをtrueにする
				boolean favoriteFlag = false;
				if (userId != null) {
					int favoriteId = rs.getInt("favorite_id");
					favoriteFlag = (favoriteId != 0);
				}
				product.setFavoriteFlag(favoriteFlag);

				productList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return productList;
	}

	/**
	 * カテゴリ名とソートで商品一覧を絞込検索
	 * 
	 * @param inputCategoryName カテゴリ名
	 * @param sort ソート
	 * @param userId 会員ID
	 * @return 商品リスト
	 * @throws ClassNotFoundException 
	 *         ドライバクラスが見つからなかった場合 
	 * @throws SQLException 
	 *         DB接続に失敗した場合 
	 */
	public List<Product> findAllWithCategoryNameOrderBySort(String inputCategoryName, String sort, Integer userId) {
		List<Product> productList = new ArrayList<>();
		// JDBCドライバを読み込む
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		// データベースへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = null;
			PreparedStatement pStmt = null;
			// 非ログイン
			if (userId == null) {
				sql = "SELECT p.product_id, c.category_id, c.category_name, p.product_name, p.price, p.description, p.stock, p.image_file, "
					+ "p.create_date, p.mod_date, p.del_date, p.del_flag "
					+ "FROM products p "
					+ "INNER JOIN categories c "
					+ "ON p.category_id = c.category_id "
					+ "WHERE c.category_name = ? "
					+ "ORDER BY " + sort;

				pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, inputCategoryName);
			} else {
				// ログイン
				sql = "SELECT p.product_id, c.category_id, c.category_name, p.product_name, p.price, p.description, p.stock, p.image_file, "
					+ "p.create_date, p.mod_date, p.del_date, p.del_flag, f.favorite_id "
					+ "FROM products p "
					+ "INNER JOIN categories c "
					+ "ON p.category_id = c.category_id "
					+ "LEFT JOIN favorites f "
					+ "ON p.product_id = f.product_id AND f.user_id = ? "
					+ "WHERE c.category_name = ? "
					+ "ORDER BY " + sort;

				pStmt = conn.prepareStatement(sql);
				pStmt.setInt(1, userId);
				pStmt.setString(2, inputCategoryName);
			}

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
				product.setCategoryName(categoryName);

				// ログインしているならお気に入りフラグをtrueにする
				boolean favoriteFlag = false;
				if (userId != null) {
					int favoriteId = rs.getInt("favorite_id");
					favoriteFlag = (favoriteId != 0);
				}
				product.setFavoriteFlag(favoriteFlag);

				productList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return productList;
	}

	/**
	 * 商品名とソートで商品一覧を絞込検索
	 * 
	 * @param inputProductName 商品名
	 * @param sort ソート
	 * @return 商品リスト
	 * @throws ClassNotFoundException 
	 *         ドライバクラスが見つからなかった場合 
	 * @throws SQLException 
	 *         DB接続に失敗した場合 
	 */
	public List<Product> findAllWithProductNameOrderBySort(String inputProductName, String sort, Integer userId) {
		List<Product> productList = new ArrayList<>();
		// JDBCドライバを読み込む
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		// データベースへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = null;
			PreparedStatement pStmt = null;
			// 非ログイン
			if (userId == null) {
				// SQLを準備
				sql = "SELECT p.product_id, c.category_id, c.category_name, p.product_name, p.price, p.description, p.stock, p.image_file, "
					+ "p.create_date, p.mod_date, p.del_date, p.del_flag "
					+ "FROM products p "
					+ "INNER JOIN categories c "
					+ "ON p.category_id = c.category_id "
					+ "WHERE p.product_name LIKE ? "
					+ "ORDER BY " + sort;

				pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, "%" + inputProductName + "%");
			} else {
				// ログイン
				sql = "SELECT p.product_id, c.category_id, c.category_name, p.product_name, p.price, p.description, p.stock, p.image_file, "
					+ "p.create_date, p.mod_date, p.del_date, p.del_flag, f.favorite_id "
					+ "FROM products p "
					+ "INNER JOIN categories c "
					+ "ON p.category_id = c.category_id "
					+ "LEFT JOIN favorites f "
					+ "ON p.product_id = f.product_id AND f.user_id = ? "
					+ "WHERE p.product_name LIKE ? "
					+ "ORDER BY " + sort;

				pStmt = conn.prepareStatement(sql);
				pStmt.setInt(1, userId);
				pStmt.setString(2, "%" + inputProductName + "%");
			}

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
				Integer quantity = rs.getInt("stock");
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

				// ログインしているならお気に入りフラグをtrueにする
				boolean favoriteFlag = false;
				if (userId != null) {
					int favoriteId = rs.getInt("favorite_id");
					favoriteFlag = (favoriteId != 0);
				}
				product.setFavoriteFlag(favoriteFlag);

				productList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return productList;
	}

	/**
	 * カテゴリ名と商品名とソートで商品一覧を絞込検索
	 * 
	 * @param inputCategoryName カテゴリ名
	 * @param inputProductName 商品名
	 * @param sort ソート
	 * @return 商品リスト
	 * @throws ClassNotFoundException 
	 *         ドライバクラスが見つからなかった場合 
	 * @throws SQLException 
	 *         DB接続に失敗した場合 
	 */
	public List<Product> findAllWithCategoryNameAndProductNameOrderBySort(String inputCategoryName,
			String inputProductName, String sort, Integer userId) {
		List<Product> productList = new ArrayList<>();
		// JDBCドライバを読み込む
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		// データベースへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = null;
			PreparedStatement pStmt = null;
			// 非ログイン
			if (userId == null) {
				sql = "SELECT p.product_id, c.category_id, c.category_name, p.product_name, p.price, p.description, p.stock, p.image_file, "
					+ "p.create_date, p.mod_date, p.del_date, p.del_flag "
					+ "FROM products p "
					+ "INNER JOIN categories c "
					+ "ON p.category_id = c.category_id "
					+ "WHERE c.category_name = ? AND p.product_name LIKE ? "
					+ "ORDER BY " + sort;

				pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, inputCategoryName);
				pStmt.setString(2, "%" + inputProductName + "%");
			} else {
				// ログイン
				sql = "SELECT p.product_id, c.category_id, c.category_name, p.product_name, p.price, p.description, p.stock, p.image_file, "
					+ "p.create_date, p.mod_date, p.del_date, p.del_flag, f.favorite_id "
					+ "FROM products p "
					+ "INNER JOIN categories c "
					+ "ON p.category_id = c.category_id "
					+ "LEFT JOIN favorites f "
					+ "ON p.product_id = f.product_id AND f.user_id = ? "
					+ "WHERE c.category_name = ? AND p.product_name LIKE ? "
					+ "ORDER BY " + sort;

				pStmt = conn.prepareStatement(sql);
				pStmt.setInt(1, userId);
				pStmt.setString(2, inputCategoryName);
				pStmt.setString(3, "%" + inputProductName + "%");
			}

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
				Integer quantity = rs.getInt("stock");
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

				// ログインしているならお気に入りフラグをtrueにする
				boolean favoriteFlag = false;
				if (userId != null) {
					int favoriteId = rs.getInt("favorite_id");
					favoriteFlag = (favoriteId != 0);
				}
				product.setFavoriteFlag(favoriteFlag);

				productList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return productList;
	}

	/**
	 * 在庫チェック(注文トランザクション①)
	 * 
	 * @param conn データベース接続
	 * @param productId 商品ID
	 * @return 在庫数
	 * @throws ClassNotFoundException 
	 *         ドライバクラスが見つからなかった場合 
	 * @throws SQLException 
	 *         DB接続に失敗した場合 
	 */
	public int getStock(Connection conn, int productId) {
		// データベースへ接続
		try {
			// SQLを準備
			String sql = "SELECT stock "
						+ "FROM products "
						+ "WHERE product_id = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, productId);

			// SQLを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				// 対象商品の在庫数を返す
				return rs.getInt("stock");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 商品が存在しない場合	
		return 0;
	}

	/**
	 * 在庫数を減らす(注文トランザクション③)
	 * 
	 * @param conn データベース接続
	 * @param productId 商品ID
	 * @param quantity 注文数
	 * @return 実行結果
	 * @throws ClassNotFoundException 
	 *         ドライバクラスが見つからなかった場合 
	 * @throws SQLException 
	 *         DB接続に失敗した場合 
	 */
	public boolean decreaseStock(Connection conn, int productId, int quantity) {
		// データベースへ接続
		try {
			// SQLを準備
			String sql = "UPDATE products "
						+ "SET stock = stock - ?, mod_date = CURRENT_TIMESTAMP "
						+ "WHERE product_id = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, quantity);
			pStmt.setInt(2, productId);

			// SQLを実行し、結果を取得
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
	 * 商品一覧（お気に入り情報付き）を取得する
	 * 
	 * @param userId 会員ID
	 * @return 商品リスト
	 * @throws ClassNotFoundException 
	 *         ドライバクラスが見つからなかった場合 
	 * @throws SQLException 
	 *         DB接続に失敗した場合 
	 */
	public List<Product> findAllWithFavorite(int userId) {
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
			String sql = "SELECT p.product_id, c.category_id, c.category_name, p.product_name, p.price, p.description, p.stock, p.image_file, "
						+ "p.create_date, p.mod_date, p.del_date, p.del_flag, f.favorite_id "
						+ "FROM products p "
						+ "INNER JOIN categories c "
						+ "ON p.category_id = c.category_id "
						+ "LEFT JOIN favorites f "
						+ "ON p.product_id = f.product_id "
						+ "AND f.user_id = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, userId);

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
				int favoriteFlagInt = rs.getInt("favorite_id");
				boolean favoriteFlag = false;
				if (favoriteFlagInt != 0) {
					favoriteFlag = true;
				}
				Product product = new Product(productId, categoryId, productName, price, description,
						stock, imageFile, createDate, modDate, delDate, delFlag);
				product.setCategoryName(categoryName);
				product.setFavoriteFlag(favoriteFlag);
				productList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return productList;
	}

	/**
	 * 在庫数を増やす(キャンセルトランザクション③)
	 * 
	 * @param conn データベース接続
	 * @param productId 商品ID
	 * @quantity 注文数
	 * @return 実行結果
	 * @return 商品リスト
	 * @throws ClassNotFoundException 
	 *         ドライバクラスが見つからなかった場合 
	 * @throws SQLException 
	 *         DB接続に失敗した場合 
	 */
	public boolean increaseStock(Connection conn, int productId, int quantity) {
		// データベースへ接続
		try {

			// SQLを準備
			String sql = "UPDATE products "
						+ "SET stock = stock + ?, mod_date = CURRENT_TIMESTAMP "
						+ "WHERE product_id = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, quantity);
			pStmt.setInt(2, productId);

			// SQLを実行し、結果を取得
			int result = pStmt.executeUpdate();

			if(result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
