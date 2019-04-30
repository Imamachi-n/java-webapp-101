package form;

import java.util.ArrayList;

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
			if(!checkByte(this.getEmployee(), 4)) return false;
			if(!checkByte(this.getOano(), 7)) return false;
			if(!checkByte(this.getNameKanji(), 22)) return false;
			if(!checkByte(this.getNamekana(), 21)) return false;
			if(!checkByte(this.getDepartment(), 20)) return false;
			if(!checkByte(this.getGroup(), 30)) return false;

			return true;

		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
