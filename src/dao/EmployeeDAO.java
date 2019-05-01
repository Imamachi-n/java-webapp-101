package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import form.EmployeeForm;

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
	public boolean registerEmployee(EmployeeForm employeeForm) {

		PreparedStatement pStmt = null;

		String sql = "INSERT INTO pers (pers_employee, pers_oano, pers_sei, pers_mei, pers_name, pers_namek, "
				+ " pers_bu, pers_gr, pers_indate, pers_intime) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			pStmt = con.prepareStatement(sql);
			pStmt.setString(1, employeeForm.getEmployee());
			pStmt.setString(2, employeeForm.getOano());
			pStmt.setString(3, "");
			pStmt.setString(4, "");
			pStmt.setString(5, employeeForm.getNameKanji());
			pStmt.setString(6, employeeForm.getNamekana());
			pStmt.setString(7, employeeForm.getDepartment());
			pStmt.setString(8, employeeForm.getGroup());
			LocalDateTime ldt = LocalDateTime.now();
			DateTimeFormatter currentDate = DateTimeFormatter.ofPattern("yyyyMMdd");
			DateTimeFormatter currentTime = DateTimeFormatter.ofPattern("HHmmss");
			pStmt.setString(9, ldt.format(currentDate).toString());
			pStmt.setString(10, ldt.format(currentTime).toString());

			pStmt.executeUpdate();

			return true;

		} catch (SQLIntegrityConstraintViolationException e) {
			employeeForm.getErrorMessage().add("入力した社員番号はすでに登録されています。");
			return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
