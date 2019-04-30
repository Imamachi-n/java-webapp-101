package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import dto.Employee;

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
	public boolean registerEmployee(Employee employee) {

		PreparedStatement pStmt = null;
		boolean success = false;

		String sql = "INSERT INTO pers (pers_employee, pers_oano, pers_sei, pers_mei, pers_name, pers_namek, "
				+ " pers_bu, pers_gr, pers_indate, pers_intime) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			pStmt = con.prepareStatement(sql);
			pStmt.setString(1, employee.getEmployee());
			pStmt.setString(2, employee.getOano());
			pStmt.setString(3, employee.getSei());
			pStmt.setString(4, employee.getMei());
			pStmt.setString(5, employee.getNameKanji());
			pStmt.setString(6, employee.getNamekana());
			pStmt.setString(7, employee.getDepartment());
			pStmt.setString(8, employee.getGroup());
			LocalDateTime ldt = LocalDateTime.now();
			DateTimeFormatter currentDate = DateTimeFormatter.ofPattern("yyyyMMdd");
			DateTimeFormatter currentTime = DateTimeFormatter.ofPattern("HHmmss");
			pStmt.setString(9, ldt.format(currentDate).toString());
			pStmt.setString(9, ldt.format(currentTime).toString());

			pStmt.executeUpdate();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
