package bl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.ConnectionManager;
import dao.EmployeeDAO;
import dto.Employee;
import form.EmployeeForm;

public class EmployeeMstBL {

	public final static String DEFAULT_ITEM = "--- 社員を選択 ---";

	// 社員・選択情報の検索
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
			employeeInfo.add(0, DEFAULT_ITEM);

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

	// 一覧表示用のすべての社員情報の検索
	public ArrayList<Employee> searchAllEmpolyees() {
		Connection con = null;
		ArrayList<Employee> employeeList = new ArrayList<Employee>();

		try {
			// DBへの接続
			ConnectionManager cm = ConnectionManager.getConnectionManager();
			con = cm.getConnection();

			// 社員情報の取得
			EmployeeDAO dao = new EmployeeDAO(con);
			employeeList = dao.searchAllEmpolyees();

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
		return employeeList;
	}

	// 特定の社員情報の検索
	public EmployeeForm searchEmpolyeeById(String employeeInfo) {
		Connection con = null;
		EmployeeForm employeeForm = new EmployeeForm();

		try {
			// DBへの接続
			ConnectionManager cm = ConnectionManager.getConnectionManager();
			con = cm.getConnection();

			// 社員情報の取得
			EmployeeDAO dao = new EmployeeDAO(con);
			Employee employee = dao.searchEmpolyeeById(employeeInfo.split(" ")[0], employeeForm);

			// フォームオブジェクトへのマッピング
			employeeForm.mapSQLResult(employee);

		} catch (Exception e) {
			e.printStackTrace();
			employeeForm.getErrorMessage().add("DB接続時に予期せぬエラーが発生しました。");

		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				employeeForm.getErrorMessage().add("DB切断時に予期せぬエラーが発生しました。");
			}
		}
		return employeeForm;
	}

	// 社員情報の登録
	public boolean registerEmpolyee(EmployeeForm employeeForm) {
		Connection con = null;
		boolean result = false;

		try {
			// DBへの接続
			ConnectionManager cm = ConnectionManager.getConnectionManager();
			con = cm.getConnection();

			// 社員情報の取得
			EmployeeDAO dao = new EmployeeDAO(con);
			result = dao.registerEmployee(employeeForm);

		} catch (Exception e) {
			e.printStackTrace();
			employeeForm.getErrorMessage().add("DB接続時に予期せぬエラーが発生しました。");
			result = false;

		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				employeeForm.getErrorMessage().add("DB切断時に予期せぬエラーが発生しました。");
				result = false;
			}
		}
		return result;
	}

	// 社員情報の更新
	public boolean updateEmpolyee(EmployeeForm employeeForm) {
		Connection con = null;
		boolean result = false;

		try {
			// DBへの接続
			ConnectionManager cm = ConnectionManager.getConnectionManager();
			con = cm.getConnection();

			// 社員情報の取得
			EmployeeDAO dao = new EmployeeDAO(con);
			result = dao.updateEmployee(employeeForm);

		} catch (Exception e) {
			e.printStackTrace();
			employeeForm.getErrorMessage().add("DB接続時に予期せぬエラーが発生しました。");
			result = false;

		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				employeeForm.getErrorMessage().add("DB切断時に予期せぬエラーが発生しました。");
				result = false;
			}
		}
		return result;
	}

	// 社員情報の削除
	public boolean deleteEmpolyee(EmployeeForm employeeForm) {
		Connection con = null;
		boolean result = false;

		try {
			// DBへの接続
			ConnectionManager cm = ConnectionManager.getConnectionManager();
			con = cm.getConnection();

			// 社員情報の取得
			EmployeeDAO dao = new EmployeeDAO(con);
			result = dao.deleteEmployee(employeeForm);

		} catch (Exception e) {
			e.printStackTrace();
			employeeForm.getErrorMessage().add("DB接続時に予期せぬエラーが発生しました。");
			result = false;

		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				employeeForm.getErrorMessage().add("DB切断時に予期せぬエラーが発生しました。");
				result = false;
			}
		}
		return result;
	}
}
