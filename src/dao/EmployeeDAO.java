package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAO {

	private Connection con = null;

	// コンストラクタ
	public EmployeeDAO(Connection con) {
		this.con = con;
	}

	// 社員情報の検索
	public ArrayList<String> searchEmpolyees() {
		PreparedStatement pStmt = null;
		ArrayList<String> employeeInfo = new ArrayList<>();

		String sql = "SELECT pers_employee, pers_name FROM pers";

		try {
			pStmt = con.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				employeeInfo.add(rs.getString("pers_employee") + " " + rs.getString("pers_name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employeeInfo;
	}

	// 社員情報の登録
	public boolean registerEmployee() {
		return true;
	}
}
