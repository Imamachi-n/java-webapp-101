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
  <title>物品管理システム - 物品履歴参照</title>

  <!-- Bootstrap -->
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/base.css">
  <link rel="stylesheet" href="css/flatpickr.min.css">
  <script src="js/flatpickr.js"></script>

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
    <jsp:param name="pageTitle" value="historyView" />
  </jsp:include>

  <!-- コンテンツ -->
  <div class="container">
    <!-- 画面タイトル -->
    <div class="header clearfix">
      <h3 class="text-muted">
        <i class="glyphicon glyphicon-book"></i> 物品履歴参照
      </h3>
    </div>

    <hr style="margin-top: 5px; margin-bottom: 15px;" />

    <form method="post" action="historyView">
      <div class="row">
        <div class="col-lg-3">
          <label>申請番号</label>
          <div class="form-group">
            <input type="text" class="form-control" id="catId" placeholder="例）0343">
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col-lg-3">
          <label>日付(Form)</label>
          <div class="form-group">
            <input type="text" class="form-control" id="calendar" placeholder="クリックしてください">
          </div>
        </div>
        <div class="col-lg-3">
          <label>日付(To)</label>
          <div class="form-group">
            <input type="text" class="form-control" id="calendar" placeholder="クリックしてください">
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col-lg-3">
          <div class="checkbox">
            <label style="margin-bottom: 10px;">
              <input type="checkbox"> 削除含む
            </label>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-3">
          <button type="button" class="btn btn-primary">検索する</button>
        </div>
      </div>
    </form>

    <br />

    <!-- Table -->
    <div class="pull-right">
      <h4>合計金額
        <c:out value="12,000" />円</h4>
    </div>
    <div class="row">
      <div class="col-lg-12">
        <div class="form-group">
          <label for="catId">物品一覧</label>
          <table class="table table-striped table-hover table-bordered">
            <thead>
              <tr>
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
                <td>1</td>
                <td>ベージュ</td>
                <td>123456789</td>
                <td>0343</td>
                <td>2</td>
                <td>1200</td>
                <td>2400</td>
              </tr>
              <tr>
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
  </div>

  <script>
    // flatpickrの初期化
    flatpickr('#calendar');
  </script>
  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
  <script src="js/jquery-1.12.4.min.js"></script>
  <!-- Include all compiled plugins (below), or include individual files as needed -->
  <script src="js/bootstrap.min.js"></script>
</body>

</html>