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
  <title>物品管理システム - 購入申請書作成</title>

  <!-- Bootstrap -->
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/base.css">

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
    <jsp:param name="pageTitle" value="applicationForm" />
  </jsp:include>

  <!-- コンテンツ -->
  <div class="container">
    <!-- 画面タイトル -->
    <div class="header clearfix">
      <h3 class="text-muted">
        <i class="glyphicon glyphicon-book"></i> 購入申請書作成
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

    <form method="post" action="applicationForm">
      <div class="pull-right">
        <button type="submit" class="btn btn-primary btn-lg">
          申請する
        </button>
      </div>

      <div class="row">
        <div class="col-lg-3">
          <div class="form-group">
            <label for="catId">申請番号: 0343</label>
            <input class="form-control" type="hidden" name="catId" />
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col-lg-3">
          <div class="form-group">
            <label for="catId">申請者（社員番号）</label>
            <input class="form-control" type="text" id="catId" size="4" placeholder="例）0343" />
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col-lg-12">
          <div class="form-group">
            <label for="catId">使用目的</label>
            <textarea class="form-control" id="textarea" placeholder="例）リフレッシュスペースの物品購入のため。"></textarea>
          </div>
        </div>
      </div>

      <div class="form-row">
        <label>物品選択</label>
        <div class="input-group col-lg-8">
          <select class="form-control" name="group" id="group">
            <c:forEach items="${ groupList }" var="groupItem">
              <option <c:if test="${groupItem == group}">selected</c:if>>
                <c:out value="${groupItem}" />
              </option>
            </c:forEach>
          </select>
          <span class="input-group-btn">
            <button type="submit" class="btn btn-info">追加する</button>
          </span>
        </div>

        <div class="pull-right">
          <h4>合計金額
            <c:out value="12,000" />円</h4>
        </div>
      </div>

      <br />

      <!-- Table -->

      <div class="row">
        <div class="col-lg-12">
          <div class="form-group">
            <label for="catId">物品一覧</label>
            <table class="table table-striped table-hover table-bordered">
              <thead>
                <tr>
                  <th>選択</th>
                  <th>No.</th>
                  <th>品番・色</th>
                  <th>ASKUL申込番号</th>
                  <th>カタログページ数</th>
                  <th>数量</th>
                  <th>単価（税抜）</th>
                  <th>合計（税抜）</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>
                    <label style="margin: 0;">
                     <input type="checkbox" class="checkbox" value="checkboxA">
                    </label>
                  </td>
                  <td>1</td>
                  <td>ベージュ</td>
                  <td>123456789</td>
                  <td>0343</td>
                  <td>2</td>
                  <td>1200</td>
                  <td>2400</td>
                </tr>
                <tr>
                  <td>
                     <label style="margin: 0;">
                      <input type="checkbox" value="checkboxA">
                    </label>
                  </td>
                  <td>1</td>
                  <td>ベージュ</td>
                  <td>123456789</td>
                  <td>0343</td>
                  <td>2</td>
                  <td>1200</td>
                  <td>2400</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col-lg-12">
          <button type="submit" class="btn btn-danger">
            削除する
          </button>
        </div>
      </div>
    </form>
  </div>

  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
  <script src="js/jquery-1.12.4.min.js"></script>
  <!-- Include all compiled plugins (below), or include individual files as needed -->
  <script src="js/bootstrap.min.js"></script>
</body>

</html>