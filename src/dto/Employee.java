package dto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import form.EmployeeForm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

	private String employee;	// 社員番号
	private String oano;		// OA番号
	private String sei;		// 姓
	private String mei;		// 名字
	private String nameKanji;	// 氏名（漢字）
	private String namekana;	// 氏名（カナ）
	private String department;	// 所属部署
	private String group;		// 所属グルーブ
	private String indate;		// 作成日
	private String intime;		// 作成時間
	private String update;		// 更新日
	private String uptime;		// 更新時間

	// 検索結果の格納
	public void getSQLResult(ResultSet rs) throws SQLException {

		this.setEmployee(rs.getString("pers_employee"));
		this.setOano(rs.getString("pers_oano"));
		this.setSei(rs.getString("pers_sei"));
		this.setMei(rs.getString("pers_mei"));
		this.setNameKanji(rs.getString("pers_name"));
		this.setNamekana(rs.getString("pers_namek"));
		this.setDepartment(rs.getString("pers_bu"));
		this.setGroup(rs.getString("pers_gr"));
		this.setIndate(rs.getString("pers_indate"));
		this.setIntime(rs.getString("pers_intime"));
		this.setUpdate(rs.getString("pers_update"));
		this.setUptime(rs.getString("pers_uptime"));

		return;
	}

	// 登録内容の格納
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

	// 更新内容の格納
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

}
