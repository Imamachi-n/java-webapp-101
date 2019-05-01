<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
  <title>物品管理システム - 物品マスタ</title>

  <!-- Bootstrap -->
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/base.css">

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
    <jsp:param name="pageTitle" value="item" />
  </jsp:include>

  <!-- コンテンツ -->
  <div class="container">
    <!-- 画面タイトル -->
    <div class="header clearfix">
      <h3 class="text-muted">
        <i class="glyphicon glyphicon-th-list"></i> 物品マスタ
      </h3>
    </div>

    <hr style="margin-top: 5px; margin-bottom: 15px;" />

    <!-- 情報メッセージ -->
    <c:if test="${not empty infoMsg}">
      <div class="alert alert-info alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="閉じる"><span
            aria-hidden="true">×</span></button>
        <ul>
          <c:forEach items="${ requestScope.infoMsg }" var="errorMsg">
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
    <form method="post" action="item" id="form1" name="form1">
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
            <label for="itemName">物品 - 選択</label>
            <select class="form-control" name="itemName" id="itemName" onchange="getList()">
              <c:forEach items="${ itemInfo }" var="itemItem">
                <option <c:if test="${itemItem == itemName}">selected</c:if>>
                  <c:out value="${itemItem}" />
                </option>
              </c:forEach>
            </select>
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col-lg-3">
          <div class="form-group">
            <label for="catId">品名</label>
            <input class="form-control" type="text" id="catId" size="4" placeholder="例）コーヒー" />
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col-lg-3">
          <div class="form-group">
            <label for="catId">品番・色</label>
            <input class="form-control" type="text" id="itemName" size="7" placeholder="例）ネイビー" />
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col-lg-3">
          <div class="form-group">
            <label for="catId">ASKUL申込番号</label>
            <input class="form-control" type="text" id="catId" size="40" placeholder="例）123456789" />
          </div>
        </div>

        <div class="col-lg-3">
          <div class="form-group">
            <label for="catId">カタログページ数</label>
            <input class="form-control" type="text" id="itemName" size="40" placeholder="例）343" />
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col-lg-3">
          <div class="form-group">
            <label for="catId">単価</label>
            <input class="form-control" type="text" id="itemName" size="40" placeholder="例）1200" />
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