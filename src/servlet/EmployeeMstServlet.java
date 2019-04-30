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

		// 社員ビジネスロジックのインスタンス化
		EmployeeMstBL bl = new EmployeeMstBL();
		// 社員フォームクラスのインスタンス化
		EmployeeForm employeeForm = new EmployeeForm();

		// 社員情報の取得
		ArrayList<String> employeeInfo = bl.searchEmpolyees();
		request.setAttribute("employeeInfo", employeeInfo);

		// 部署の取得
		request.setAttribute("departmentList", employeeForm.getDepartmentList());

		// グループの取得
		request.setAttribute("groupList", employeeForm.getGroupList());

		// 社員マスタページへ遷移
		request.getRequestDispatcher("employeeMst.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// 社員ビジネスロジックのインスタンス化
			EmployeeMstBL bl = new EmployeeMstBL();
			// 社員フォームクラスのインスタンス化
			EmployeeForm employeeForm = new EmployeeForm();

			// 社員情報の取得
			ArrayList<String> employeeInfo = bl.searchEmpolyees();
			request.setAttribute("employeeInfo", employeeInfo);

			// 部署の取得
			request.setAttribute("departmentList", employeeForm.getDepartmentList());

			// グループの取得
			request.setAttribute("groupList", employeeForm.getGroupList());

			String execution = request.getParameter("execute");
			if (execution == null) {
				// エラー発生時
				// TODO: ERROR

			} else if (execution.equals("list")) {
				// 一覧表示時
				System.out.println("OK!");

			} else if (execution.equals("select")) {
				// 社員選択時

			} else if (execution.equals("register")) {
				// 登録時

				// 値の取得
				employeeForm.setEmployee(request.getParameter("employeeId"));
				employeeForm.setOano(request.getParameter("oano"));
				employeeForm.setNameKanji(request.getParameter("employeeNameKanji"));
				employeeForm.setNamekana(request.getParameter("employeeNameKana"));
				employeeForm.setDepartment(request.getParameter("department"));
				employeeForm.setGroup(request.getParameter("group"));

				// バリデータによる値のチェック
				if (!employeeForm.validateInputData()) {
					// 値渡し
					request.setAttribute("employeeId", request.getParameter("employeeId"));
					request.setAttribute("oano", request.getParameter("oano"));

					// 社員マスタページへ遷移
					request.getRequestDispatcher("employeeMst.jsp").forward(request, response);
					return;

				}


	//			bl.registerEmpolyee(employeeForm);
				System.out.println("OK");
			} else if (execution.equals("update")) {
				// 更新時

			} else if (execution.equals("delete")) {
				// 削除時

			}

			// ボタン切り替えフラグを立てる
			request.setAttribute("editFlg", true);

			// 社員マスタページへ遷移
			request.getRequestDispatcher("employeeMst.jsp").forward(request, response);

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
