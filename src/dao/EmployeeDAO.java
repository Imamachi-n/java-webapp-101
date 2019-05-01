package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import dto.Employee;
import form.EmployeeForm;

public class EmployeeDAO {

	private Connection con = null;

	// コンストラクタ
	public EmployeeDAO(Connection con) {
		this.con = con;
	}

	// 社員・選択情報の検索
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

	// 一覧表示用のすべての社員情報の検索
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

	// 特定の社員情報の検索
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

		String sql = "INSERT INTO pers (pers_employee, pers_oano, pers_sei, pers_mei, pers_name, pers_namek, "
				+ " pers_bu, pers_gr, pers_indate, pers_intime) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			pStmt = con.prepareStatement(sql);
			this.setRegisterParameters(pStmt, employeeForm);

			pStmt.executeUpdate();

			employeeForm.getInfoMessage().add("社員情報の登録に成功しました。");
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

		String sql = "UPDATE pers SET pers_oano = ?, pers_sei = ?, pers_mei = ?, "
				+ " pers_name = ?, pers_namek = ?, pers_bu = ?, pers_gr = ?, pers_update = ?, pers_uptime = ? "
				+ " WHERE pers_employee = ?";

		try {
			pStmt = con.prepareStatement(sql);
			this.setUpdateParameters(pStmt, employeeForm);

			pStmt.executeUpdate();

			employeeForm.getInfoMessage().add("社員情報の更新に成功しました。");
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			employeeForm.getErrorMessage().add("社員情報の更新時に予期せぬエラーが発生しました。");
			return false;
		}
	}

	// 社員情報の削除
	public boolean deleteEmployee(EmployeeForm employeeForm) {

		PreparedStatement pStmt = null;

		String sql = "DELETE FROM pers WHERE pers_employee = ?";

		try {
			pStmt = con.prepareStatement(sql);
			this.setDeleteParameters(pStmt, employeeForm);

			pStmt.executeUpdate();

			employeeForm.getInfoMessage().add("社員情報の削除に成功しました。");
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			employeeForm.getErrorMessage().add("社員情報の削除時に予期せぬエラーが発生しました。");
			return false;
		}
	}


	// 登録内容の設定
	public void setRegisterParameters(PreparedStatement pStmt, EmployeeForm employeeForm) throws SQLException {

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

		return;
	}

	// 更新内容の設定
	public void setUpdateParameters(PreparedStatement pStmt, EmployeeForm employeeForm) throws SQLException {

		pStmt.setString(1, employeeForm.getOano());
		pStmt.setString(2, "");
		pStmt.setString(3, "");
		pStmt.setString(4, employeeForm.getNameKanji());
		pStmt.setString(5, employeeForm.getNamekana());
		pStmt.setString(6, employeeForm.getDepartment());
		pStmt.setString(7, employeeForm.getGroup());
		LocalDateTime ldt = LocalDateTime.now();
		DateTimeFormatter currentDate = DateTimeFormatter.ofPattern("yyyyMMdd");
		DateTimeFormatter currentTime = DateTimeFormatter.ofPattern("HHmmss");
		pStmt.setString(8, ldt.format(currentDate).toString());
		pStmt.setString(9, ldt.format(currentTime).toString());
		pStmt.setString(10, employeeForm.getEmployee());

		return;
	}

	// 削除内容の設定
	public void setDeleteParameters(PreparedStatement pStmt, EmployeeForm employeeForm) throws SQLException {

		pStmt.setString(1, employeeForm.getEmployee());

		return;
	}
}
