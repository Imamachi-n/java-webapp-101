package form;

import java.util.ArrayList;

import dto.Employee;
import lombok.Getter;
import lombok.Setter;
import validator.BaseValidator;

@Getter
@Setter
public class EmployeeForm extends BaseValidator {

	private ArrayList<String> departmentList;	// 部署名のリスト
	private ArrayList<String> groupList;		// グループ名のリスト

	private String employee;	// 社員番号
	private String oano;		// OA番号
	private String nameKanji;	// 氏名（漢字）
	private String namekana;	// 氏名（カナ）
	private String department;	// 所属部署
	private String group;		// 所属グルーブ

	// コンストラクタ
	public EmployeeForm() {
		super();

		this.departmentList = new ArrayList<>();
		this.departmentList.add("システム開発部");
		this.departmentList.add("ラボシステム部");
		this.departmentList.add("システム管理部");

		this.groupList = new ArrayList<>();
		this.groupList.add("SIG");
		this.groupList.add("運用G");
	}

	// バリデータ
	public boolean validateInputData() {

		try {
			// バイト数チェック
			checkByte(this.getEmployee(), 4, "社員番号は半角4文字以内で入力してください。");
			checkByte(this.getOano(), 7, "OA番号は半角7文字以内で入力してください。");
			checkByte(this.getNameKanji(), 22, "氏名（漢字）は全角11文字以内で入力してください。");
			checkByte(this.getNamekana(), 21, "氏名（カナ）は半角21文字以内で入力してください。");
			checkByte(this.getDepartment(), 20, "部署は全角10文字以内で入力してください。");
			checkByte(this.getGroup(), 30, "グループ名は全角15文字以内で入力してください。");
			if(this.isHasError()) return false;

			// 数値チェック
			isNumber(this.getEmployee(), "社員番号は数字で入力してください。");
			isNumber(this.getOano(), "OA番号は数値で入力してください。");
			if(this.isHasError()) return false;

			return true;

		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// SQL実行結果から値を取得
	public void mapSQLResult(Employee employee) {

		this.setEmployee(employee.getEmployee());
		this.setOano(employee.getOano());
		this.setNameKanji(employee.getNameKanji());
		this.setNamekana(employee.getNamekana());
		this.setDepartment(employee.getDepartment());
		this.setGroup(employee.getGroup());

		return;
	}
}
