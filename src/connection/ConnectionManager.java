package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	private static ConnectionManager instance = null; // 自インスタンス

	/*
	 * メソッド名：インスタンス生成、取得（singleton）
	 * 引数：なし
	 * 戻り値：ConnectionManagerのインスタンス
	 */
	public static ConnectionManager getConnectionManager() {
		if (instance == null) {
			instance = new ConnectionManager();
		}
		return instance;
	}

	/*
	 * メソッド名：引数なしのコンストラクタ
	 * 引数：なし
	 * 戻り値：なし
	 */
	private ConnectionManager() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 	}

	/*
	 * メソッド名：DBへの接続
	 * 引数：なし
	 * 戻り値：DBへのコネクション
	 */
	public synchronized Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/refresh?serverTimezone=UTC",
					"root", "password");
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return conn;
	}
}
