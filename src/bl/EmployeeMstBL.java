package bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.ConnectionManager;
import dto.Test;

public class EmployeeMstBL {

	/*
	 * カテゴリとキーワードによる商品検索
	 * メソッド名：商品の検索
	 * 引数：カテゴリ名、キーワード 戻り値：検索結果
	 */
	public ArrayList<Test> selectProducts(String category, String keyword) {
		Connection con = null;
		ArrayList<Test> array = new ArrayList<>();

		try {
			ConnectionManager cm = ConnectionManager.getConnectionManager();
			con = cm.getConnection();

//			SearchDAO dao = new SearchDAO(con);

//			array = dao.selectProducts(category, keyword);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return array;
	}
}
