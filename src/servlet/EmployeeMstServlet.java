package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bl.EmployeeMstBL;
import dto.Employee;
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

		// 社員ビジネスロジックのインスタンス化
		EmployeeMstBL bl = new EmployeeMstBL();
		// 社員フォームクラスのインスタンス化
		EmployeeForm employeeForm = new EmployeeForm();

		// 部署の取得
		request.setAttribute("departmentList", employeeForm.getDepartmentList());

		// グループの取得
		request.setAttribute("groupList", employeeForm.getGroupList());

		String execution = request.getParameter("execute");
		if (execution == null) {
			// TODO: ERROR

		} else if (execution.equals("register")) {
			// 値の取得
			Employee employee = new Employee();
			employee.setEmployee(request.getParameter("employeeId"));
			employee.setOano(request.getParameter("oano"));

			// バリデータによる値のチェック



			bl.registerEmpolyee(employee);
			System.out.println("OK");
		}

		// ボタン切り替えフラグを立てる
		request.setAttribute("editFlg", true);

		// 社員マスタページへ遷移
		request.getRequestDispatcher("employeeMst.jsp").forward(request, response);
	}
}
