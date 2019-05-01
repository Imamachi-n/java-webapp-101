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
			// コンボボックスのデータ取得
			initComboBox(request, bl, employeeForm);

			String execution = request.getParameter("execute");
			if (execution == null) {
				// エラー発生時
				employeeForm.getErrorMessage().add("不正なフォーム入力が発生しました。");

			}else {
				switch(execution) {
					// 一覧表示時
					case "list":
						System.out.println("!!");
						break;

					// 社員選択時
					case "select":
						request.setAttribute("employeeName", request.getParameter("employeeName"));
						// ボタン切り替えフラグを立てる
						request.setAttribute("editFlg", true);
						break;

					// 登録時
					case "register":
						// フォームオブジェクトへの値渡し
						setEmployeeForm(request, employeeForm);

						// バリデータによる値のチェック
						if (!employeeForm.validateInputData()) break;
						if (!bl.registerEmpolyee(employeeForm)) break;
						break;

					// 更新時
					case "update":
						break;

					// 削除時
					case "delete":
						break;
				}
			}

			// エラーメッセージの表示
			if (employeeForm.getErrorMessage().size() > 0) setErrorMsg(request, employeeForm);

			// 社員マスタページへ遷移
			request.getRequestDispatcher("employeeMst.jsp").forward(request, response);

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	// Request値渡し
	protected void setEmployeeForm(HttpServletRequest request) {

		request.setAttribute("employeeId", request.getParameter("employeeId"));
		request.setAttribute("oano", request.getParameter("oano"));
		request.setAttribute("employeeNameKanji", request.getParameter("employeeNameKanji"));
		request.setAttribute("employeeNameKana", request.getParameter("employeeNameKana"));
		request.setAttribute("department", request.getParameter("department"));
		request.setAttribute("group", request.getParameter("group"));

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

	// エラー発生時
	protected void setErrorMsg(HttpServletRequest request, EmployeeForm employeeForm) {

		// エラーメッセージ
		request.setAttribute("errorMsg", employeeForm.getErrorMessage());
		// 値渡し
		setEmployeeForm(request);
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
