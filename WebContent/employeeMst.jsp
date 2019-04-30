<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>

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

      <!-- 入力フォーム -->
      <form method="post" action="employee">
        <!-- 処理の選択 -->
        <button type="submit" class="btn btn-primary">一覧表示</button>

        <div class="row">
          <div class="col-lg-3">
            <div class="form-group">
              <label for="employee">社員 - 選択</label>
              <select
                class="form-control"
                name="employeeName"
                id="employeeName"
              >
                <c:forEach items="${ employeeInfo }" var="employeeItem">
                  <option <c:if test="${employeeItem == employee}">selected</c:if>>
                    <c:out value="${employeeItem}" />
                  </option>
                </c:forEach>
              </select>
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-lg-3">
            <div class="form-group">
              <label for="employeeId">社員番号</label>
              <input
                class="form-control"
                type="text"
                name="employeeId"
                size="4"
                value="${requestScope.employeeId}"
                placeholder="例）0343"
              />
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-lg-3">
            <div class="form-group">
              <label for="oano">OA番号</label>
              <input class="form-control" type="text" id="oano" name="oano"
              value="${requestScope.oano}" size="7"
              placeholder="例）0250417"/>
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-lg-3">
            <div class="form-group">
              <label for="employeeNameKanji">氏名（漢字）</label>
              <input
                class="form-control"
                type="text"
                name="employeeNameKanji"
                size="40"
                value="${requestScope.employeeNameKanji}"
                placeholder="例）山田　太郎"
              />
            </div>
          </div>

          <div class="col-lg-3">
            <div class="form-group">
              <label for="employeeNameKana">氏名（カナ）</label>
              <input
                class="form-control"
                type="text"
                name="employeeNameKana"
                size="40"
                value="${requestScope.employeeNameKana}"
                placeholder="例）ヤマダ　タロウ"
              />
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-lg-3">
            <div class="form-group">
              <label for="department">部署</label>
              <select class="form-control" name="department" id="department">
                <c:forEach items="${ departmentList }" var="departmentItem">
                   <option <c:if test="${departmentItem == department}">selected</c:if>>
                     <c:out value="${departmentItem}" />
                   </option>
                </c:forEach>
              </select>
            </div>
          </div>

          <div class="col-lg-3">
            <div class="form-group">
              <label for="group">所属グループ</label>
              <select class="form-control" name="group" id="group">
                <c:forEach items="${ groupList }" var="groupItem">
                  <option <c:if test="${groupItem == group}">selected</c:if>>
                    <c:out value="${groupItem}" />
                  </option>
                </c:forEach>
              </select>
            </div>
          </div>
        </div>

        <c:choose>
          <c:when test="${not empty editFlg and editFlg}">
            <button type="submit" class="btn btn-primary">更新する</button>
            <button type="submit" class="btn btn-danger">削除する</button>
          </c:when>
          <c:otherwise>
            <button
              type="submit"
              class="btn btn-primary"
              name="execute"
              value="register"
            >
              登録する
            </button>
            <button type="reset" class="btn btn-light">リセット</button>
          </c:otherwise>
        </c:choose>
      </form>
    </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery-1.12.4.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>
