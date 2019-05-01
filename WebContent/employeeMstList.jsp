<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!-- EmployeeFormクラスのImport -->
<%@ page import="dto.Employee"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>物品管理システム - 社員マスタ</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/base.css" />

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script>
        function getList() {
            var form = this.document.forms.form1;
            var input = this.document.createElement('input');
            input.setAttribute('name', 'execute');
            input.setAttribute('value', 'select');
            form.appendChild(input);
            form.submit();
        }
    </script>
</head>

<body style="padding-top: 65px">
    <!-- ヘッダー -->
    <jsp:include page="components/header.jsp" flush="true">
        <jsp:param name="pageTitle" value="employee" />
    </jsp:include>

    <!-- コンテンツ -->
    <div class="container">
        <!-- 画面タイトル -->
        <div class="header clearfix">
            <h3 class="text-muted">
                <i class="glyphicon glyphicon-user"></i> 社員マスタ
            </h3>
        </div>

        <hr style="margin-top: 5px; margin-bottom: 15px;" />

        <!-- Table -->
        <div class="row">
            <div class="col-lg-12">
                <div class="form-group">
                    <label for="catId">物品一覧</label>
                    <table class="table table-striped table-hover table-bordered">
                        <thead>
                            <tr>
                                <th>社員番号</th>
                                <th>OA番号</th>
                                <th>氏名（漢字）</th>
                                <th>氏名（カナ）</th>
                                <th>部署</th>
                                <th>所属グループ</th>
                            </tr>
                        </thead>
                        <tbody>
                          <c:forEach items="${ employeeList }" var="employee">
                            <tr>
                                <td><c:out value="${employee.employee}" /></td>
                                <td><c:out value="${employee.oano}" /></td>
                                <td><c:out value="${employee.nameKanji}" /></td>
                                <td><c:out value="${employee.namekana}" /></td>
                                <td><c:out value="${employee.department}" /></td>
                                <td><c:out value="${employee.group}" /></td>
                            </tr>
                          </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery-1.12.4.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>

</html>