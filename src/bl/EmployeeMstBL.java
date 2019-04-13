package bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.ConnectionManager;
import dao.EmployeeDAO;
import dto.Employee;

public class EmployeeMstBL {

	// 社員情報の検索
	public ArrayList<String> searchEmpolyees() {
		Connection con = null;
		ArrayList<String> employeeInfo = new ArrayList<>();

		try {
			// DBへの接続
			ConnectionManager cm = ConnectionManager.getConnectionManager();
			con = cm.getConnection();

			// 社員情報の取得
			EmployeeDAO dao = new EmployeeDAO(con);
			employeeInfo = dao.searchEmpolyees();

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
		return employeeInfo;
	}

	// 社員情報の登録
		public boolean registerEmpolyee(Employee employee) {
			Connection con = null;
			boolean result = false;

			try {
				// DBへの接続
				ConnectionManager cm = ConnectionManager.getConnectionManager();
				con = cm.getConnection();

				// 社員情報の取得
				EmployeeDAO dao = new EmployeeDAO(con);
				result = dao.registerEmployee(employee);

			} catch (Exception e) {
				e.printStackTrace();
				result = false;

			} finally {
				try {
					if (con != null) {
						con.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
					result = false;
				}
			}
			return result;
		}
}
