package form;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeForm {

	private ArrayList<String> departmentList;	// 部署名のリスト
	private ArrayList<String> groupList;		// グループ名のリスト

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
}
