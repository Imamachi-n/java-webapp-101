<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
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
    // コンボボックスで選択した社員情報の取得
    function getList() {
      var form = this.document.forms.form1;
      var input = this.document.createElement('input');
      input.setAttribute('name', 'execute');
      input.setAttribute('value', 'select');
      form.appendChild(input);
      form.submit();
    }

    // Disabled状態のテキストボックスを一時的に解除し、POST送信時に変数として対象の入力値を渡す
    function unDisabled() {
      document.getElementById("employeeId").disabled = false;
      return true;
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

    <!-- 情報メッセージ -->
    <c:if test="${not empty infoMsg}">
      <div class="alert alert-success alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="閉じる"><span
            aria-hidden="true">×</span></button>
        <ul>
          <c:forEach items="${ requestScope.infoMsg }" var="infoMsg">
            <li>
              <c:out value="${infoMsg}" />
            </li>
          </c:forEach>
        </ul>
      </div>
    </c:if>

    <!-- エラーメッセージ -->
    <c:if test="${not empty errorMsg}">
      <div class="alert alert-warning alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="閉じる"><span
            aria-hidden="true">×</span></button>
        <ul>
          <c:forEach items="${ requestScope.errorMsg }" var="errorMsg">
            <li>
              <c:out value="${errorMsg}" />
            </li>
          </c:forEach>
        </ul>
      </div>
    </c:if>

    <!-- 入力フォーム -->
    <form method="post" action="employee" id="form1" name="form1">
      <!-- 処理の選択 -->
      <div class="row">
        <div class="col-lg-3">
          <div class="form-group">
            <button type="submit" class="btn btn-info" name="execute" value="list">一覧表示</button>
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col-lg-3">
          <div class="form-group">
            <label for="employee">社員 - 選択</label>
            <select class="form-control" name="employeeName" id="employeeName" onchange="getList()">
              <c:forEach items="${ employeeInfo }" var="employeeItem">
                <option <c:if test="${employeeItem == employeeName}">selected</c:if>>
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
            <input class="form-control" type="text" id="employeeId" name="employeeId" size="4"
              value="${requestScope.employeeId}" placeholder="例）0343" <c:if
              test="${not empty editFlg and editFlg}">disabled="disabled"</c:if>
            />
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col-lg-3">
          <div class="form-group">
            <label for="oano">OA番号</label>
            <input class="form-control" type="text" id="oano" name="oano" value="${requestScope.oano}" size="7"
              placeholder="例）0250417" />
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col-lg-3">
          <div class="form-group">
            <label for="employeeNameKanji">氏名（漢字）</label>
            <input class="form-control" type="text" name="employeeNameKanji" size="40"
              value="${requestScope.employeeNameKanji}" placeholder="例）山田　太郎" />
          </div>
        </div>

        <div class="col-lg-3">
          <div class="form-group">
            <label for="employeeNameKana">氏名（カナ）</label>
            <input class="form-control" type="text" name="employeeNameKana" size="40"
              value="${requestScope.employeeNameKana}" placeholder="例）ヤマダ　タロウ" />
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
          <button type="submit" class="btn btn-primary" name="execute" value="update"
            onclick="unDisabled()">更新する</button>
          <button type="submit" class="btn btn-danger" name="execute" value="delete"
            onclick="unDisabled()">削除する</button>
        </c:when>
        <c:otherwise>
          <button type="submit" class="btn btn-primary" name="execute" value="register">
            登録する
          </button>
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