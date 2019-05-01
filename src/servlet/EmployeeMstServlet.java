package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bl.EmployeeMstBL;
import form.EmployeeForm;

/**
 * Servlet implementation class EmployeeMstServlet
 */
@WebServlet("/employee")
public class EmployeeMstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeMstServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// インスタンス化
		EmployeeMstBL bl = new EmployeeMstBL();				// 社員ビジネスロジックのインスタンス化
		EmployeeForm employeeForm = new EmployeeForm();	// 社員フォームクラスのインスタンス化

		try {
			// コンボボックスのデータ取得
			initComboBox(request, bl, employeeForm);

			// フォームの初期化
			initEmployeeForm(request);

			// 社員マスタページへ遷移
			request.getRequestDispatcher("employeeMst.jsp").forward(request, response);

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// インスタンス化
		EmployeeMstBL bl = new EmployeeMstBL();				// 社員ビジネスロジックのインスタンス化
		EmployeeForm employeeForm = new EmployeeForm();	// 社員フォームクラスのインスタンス化

		try {
			String execution = request.getParameter("execute");
			if (execution == null) {
				// エラー発生時
				employeeForm.getErrorMessage().add("不正なフォーム入力が発生しました。");

			}else {
				switch(execution) {
					// 一覧表示時 --------------------------------------------------------------
					case "list":
						request.setAttribute("employeeList", bl.searchAllEmpolyees());

						// 社員一覧ページへ遷移
						request.getRequestDispatcher("employeeMstList.jsp").forward(request, response);
						return;

					// 社員選択時 --------------------------------------------------------------
					case "select":
						// 社員情報の取得
						String employeeName = request.getParameter("employeeName");
						employeeForm = bl.searchEmpolyeeById(employeeName);

						// 社員情報の取得時にエラーが発生した場合
						if (employeeForm.getErrorMessage().size() > 0) break;

						// JSPへの値渡し
						request.setAttribute("employeeName", employeeName);
						setAttributeFromEmployeeForm(request, employeeForm);

						// 更新・削除ボタンを表示する(デフォルト値でない場合)
						if(!employeeName.equals(EmployeeMstBL.DEFAULT_ITEM)) request.setAttribute("editFlg", true);

						break;

					// 登録時 --------------------------------------------------------------
					case "register":
						// フォームオブジェクトへの値渡し
						setEmployeeForm(request, employeeForm);

						// バリデータによる値のチェック
						if (!employeeForm.validateInputData()) break;

						// 登録
						if (!bl.registerEmpolyee(employeeForm)) break;

						break;

					// 更新時 --------------------------------------------------------------
					case "update":
						// フォームオブジェクトへの値渡し
						setEmployeeForm(request, employeeForm);

						// バリデータによる値のチェック
						if (!employeeForm.validateInputData()) {
							request.setAttribute("editFlg", true);	// 更新・削除ボタンを表示する
							break;
						}

						// 更新
						if (!bl.updateEmpolyee(employeeForm)) {
							request.setAttribute("editFlg", true);	// 更新・削除ボタンを表示する
							break;
						}

						break;

					// 削除時 --------------------------------------------------------------
					case "delete":
						// フォームオブジェクトへの値渡し
						setEmployeeForm(request, employeeForm);

						// 削除
						if (!bl.deleteEmpolyee(employeeForm)) {
							request.setAttribute("editFlg", true);	// 更新・削除ボタンを表示する
							break;
						}

						break;
				}
			}

			// エラーメッセージの表示
			if (employeeForm.getErrorMessage().size() > 0) setErrorMsg(request, employeeForm);
			// 情報メッセージの表示
			if (employeeForm.getInfoMessage().size() > 0) setInfoMsg(request, employeeForm);

			// コンボボックスのデータ取得
			initComboBox(request, bl, employeeForm);

			// 社員マスタページへ遷移
			request.getRequestDispatcher("employeeMst.jsp").forward(request, response);

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	// Request値渡し
	protected void setAttributeFromEmployeeForm(HttpServletRequest request, EmployeeForm employeeForm) {

		request.setAttribute("employeeId", employeeForm.getEmployee());
		request.setAttribute("oano", employeeForm.getOano());
		request.setAttribute("employeeNameKanji", employeeForm.getNameKanji());
		request.setAttribute("employeeNameKana", employeeForm.getNamekana());
		request.setAttribute("department", employeeForm.getDepartment());
		request.setAttribute("group", employeeForm.getGroup());

	}

	// フォームオブジェクトへの値渡し
	protected void setEmployeeForm(HttpServletRequest request, EmployeeForm employeeForm) {
		// 値の取得
		employeeForm.setEmployee(request.getParameter("employeeId"));
		employeeForm.setOano(request.getParameter("oano"));
		employeeForm.setNameKanji(request.getParameter("employeeNameKanji"));
		employeeForm.setNamekana(request.getParameter("employeeNameKana"));
		employeeForm.setDepartment(request.getParameter("department"));
		employeeForm.setGroup(request.getParameter("group"));
	}

	// 情報メッセージ表示時
	protected void setInfoMsg(HttpServletRequest request, EmployeeForm employeeForm) {

		// 情報メッセージ
		request.setAttribute("infoMsg", employeeForm.getInfoMessage());
	}

	// エラー発生時
	protected void setErrorMsg(HttpServletRequest request, EmployeeForm employeeForm) {

		// エラーメッセージ
		request.setAttribute("errorMsg", employeeForm.getErrorMessage());
		// 値渡し
		setAttributeFromEmployeeForm(request, employeeForm);
	}

	// フォームの初期化
	protected void initEmployeeForm(HttpServletRequest request) {

		request.setAttribute("employeeId", "");
		request.setAttribute("oano", "");
		request.setAttribute("employeeNameKanji", "");
		request.setAttribute("employeeNameKana", "");
		request.setAttribute("department", "");
		request.setAttribute("group", "");

	}

	// コンボボックスの初期化
	protected void initComboBox(HttpServletRequest request, EmployeeMstBL bl, EmployeeForm employeeForm) {

		// コンボボックスのデータ取得
		ArrayList<String> employeeInfo = bl.searchEmpolyees();
		request.setAttribute("employeeInfo", employeeInfo);							// 社員情報の取得
		request.setAttribute("departmentList", employeeForm.getDepartmentList());	// 部署の取得
		request.setAttribute("groupList", employeeForm.getGroupList());				// グループの取得

	}
}
