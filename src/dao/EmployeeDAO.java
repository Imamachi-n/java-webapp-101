package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import dto.Employee;
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

	// 社員情報の検索
	public ArrayList<Employee> searchAllEmpolyees() {
		PreparedStatement pStmt = null;
		ArrayList<Employee> employeeList = new ArrayList<Employee>();

		String sql = "SELECT * FROM pers";

		// SQLの実行
		try {
			pStmt = con.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();

			// 社員情報の取得
			while (rs.next()) {
				Employee employee = new Employee();
				employee.getSQLResult(rs);
				employeeList.add(employee);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employeeList;
	}

	// 社員情報の検索
	public Employee searchEmpolyeeById(String id, EmployeeForm employeeForm) {
		PreparedStatement pStmt = null;
		Employee employee = new Employee();

		String sql = "SELECT * FROM pers WHERE pers_employee = ?";

		// SQLの実行
		try {
			pStmt = con.prepareStatement(sql);
			pStmt.setString(1, id);
			ResultSet rs = pStmt.executeQuery();

			// 対象の社員がいた場合
			if (rs.next()) {
				employee.getSQLResult(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			employeeForm.getErrorMessage().add("社員情報の取得時に予期せぬエラーが発生しました。");
		}

		return employee;
	}

	// 社員情報の登録
	public boolean registerEmployee(EmployeeForm employeeForm) {

		PreparedStatement pStmt = null;
		Employee employee = new Employee();

		String sql = "INSERT INTO pers (pers_employee, pers_oano, pers_sei, pers_mei, pers_name, pers_namek, "
				+ " pers_bu, pers_gr, pers_indate, pers_intime) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			pStmt = con.prepareStatement(sql);
			employee.setRegisterParameters(pStmt, employeeForm);

			pStmt.executeUpdate();

			return true;

		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			employeeForm.getErrorMessage().add("入力した社員番号はすでに登録されています。");
			return false;

		} catch (SQLException e) {
			e.printStackTrace();
			employeeForm.getErrorMessage().add("社員情報の登録時に予期せぬエラーが発生しました。");
			return false;
		}
	}

	// 社員情報の更新
	public boolean updateEmployee(EmployeeForm employeeForm) {

		PreparedStatement pStmt = null;
		Employee employee = new Employee();

		String sql = "UPDATE pers SET pers_oano = ?, pers_sei = ?, pers_mei = ?, "
				+ " pers_name = ?, pers_namek = ?, pers_bu = ?, pers_gr = ?, pers_update = ?, pers_uptime = ? "
				+ " WHERE pers_employee = ?";

		try {
			pStmt = con.prepareStatement(sql);
			employee.setUpdateParameters(pStmt, employeeForm);

			pStmt.executeUpdate();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			employeeForm.getErrorMessage().add("社員情報の更新時に予期せぬエラーが発生しました。");
			return false;
		}
	}
}
