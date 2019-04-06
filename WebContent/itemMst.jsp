<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

    <!-- 入力フォーム -->
    <form method="post" action="item">
      <div class="row">
        <div class="col-lg-3">
          <div class="form-group">
            <label for="catId">社員番号</label>
            <input class="form-control" type="text" id="catId" size="4" placeholder="例）0343" />
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col-lg-3">
          <div class="form-group">
            <label for="catId">OA番号</label>
            <input class="form-control" type="text" id="itemName" size="7" placeholder="例）0250417" />
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col-lg-3">
          <div class="form-group">
            <label for="catId">氏名（漢字）</label>
            <input class="form-control" type="text" id="catId" size="40" placeholder="例）山田　太郎" />
          </div>
        </div>

        <div class="col-lg-3">
          <div class="form-group">
            <label for="catId">氏名（カナ）</label>
            <input class="form-control" type="text" id="itemName" size="40" placeholder="例）ヤマダ　タロウ" />
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col-lg-3">
          <div class="form-group">
            <label for="catId">部署</label>
            <input class="form-control" type="text" id="catId" size="40" placeholder="例）システム開発部" />
          </div>
        </div>

        <div class="col-lg-3">
          <div class="form-group">
            <label for="catId">所属グループ</label>
            <input class="form-control" type="text" id="itemName" size="40" placeholder="例）SIG" />
          </div>
        </div>
      </div>

      <button type="submit" class="btn btn-primary">登録する</button>
      <button type="reset" class="btn btn-light">リセット</button>
    </form>
  </div>

  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
  <script src="js/jquery-1.12.4.min.js"></script>
  <!-- Include all compiled plugins (below), or include individual files as needed -->
  <script src="js/bootstrap.min.js"></script>
</body>

</html>