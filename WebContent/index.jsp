<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
  <title>物品管理システム - トップページ</title>

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
    <jsp:param name="pageTitle" value="index" />
  </jsp:include>

  <div id="container">
    <div class="row">
      <div class="col-lg-3">
        <h3>
          <button type="button" class="btn btn-sq-lg btn-info" style="width: 100%; height: 240px;">
            <i class="fa fa-user fa-5x"></i>
            <span class="glyphicon glyphicon-edit gi-5x" aria-hidden="true" style="margin-top: 25px;"></span>
            <br />
            <h3>購入申請書作成</h3>
          </button>
        </h3>
      </div>

      <div class="col-lg-3">
        <h3>
          <button type="button" class="btn btn-sq-lg btn-info" style="width: 100%; height: 240px;">
            <i class="fa fa-user fa-5x"></i>
            <span class="glyphicon glyphicon-book gi-5x" aria-hidden="true" style="margin-top: 25px;"></span>
            <br />
            <h3>購入履歴参照</h3>
          </button>
        </h3>
      </div>

      <div class="col-lg-3">
        <h3>
          <button type="button" class="btn btn-sq-lg btn-warning" style="width: 100%; height: 240px;">
            <i class="fa fa-user fa-5x"></i>
            <span class="glyphicon glyphicon-th-list gi-5x" aria-hidden="true" style="margin-top: 25px;"></span>
            <br />
            <h3>物品マスタ</h3>
          </button>
        </h3>
      </div>

      <div class="col-lg-3">
        <h3>
          <button type="button" class="btn btn-sq-lg btn-warning" style="width: 100%; height: 240px;">
            <i class="fa fa-user fa-5x"></i>
            <span class="glyphicon glyphicon-user gi-5x" aria-hidden="true" style="margin-top: 25px;"></span>
            <br />
            <h3>社員マスタ</h3>
          </button>
        </h3>
      </div>

    </div>
  </div>

  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
  <script src="js/jquery-1.12.4.min.js"></script>
  <!-- Include all compiled plugins (below), or include individual files as needed -->
  <script src="js/bootstrap.min.js"></script>
</body>

</html>