<%@page contentType="text/html" pageEncoding="UTF-8" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
        data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="index">物品管理システム</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <!-- 購入申請書作成 -->
        <c:choose>
          <c:when test="${param.pageTitle == 'index'}">
            <li class="active">
              <a href="applicationForm"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> 購入申請書作成</a>
            </li>
          </c:when>
          <c:otherwise>
            <li>
              <a href="applicationForm"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> 購入申請書作成</a>
            </li>
          </c:otherwise>
        </c:choose>

        <!-- 購入履歴参照 -->
        <c:choose>
          <c:when test="${param.pageTitle == 'applicationForm'}">
            <li class="active">
              <a href="historyView"><span class="glyphicon glyphicon-book" aria-hidden="true"></span> 購入履歴参照</a>
            </li>
          </c:when>
          <c:otherwise>
            <li>
              <a href="historyView"><span class="glyphicon glyphicon-book" aria-hidden="true"></span> 購入履歴参照</a>
            </li>
          </c:otherwise>
        </c:choose>

        <!-- 物品マスタ -->
        <c:choose>
          <c:when test="${param.pageTitle == 'item'}">
            <li class="active">
              <a href="item"><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> 物品マスタ</a>
            </li>
          </c:when>
          <c:otherwise>
            <li>
              <a href="item"><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span> 物品マスタ</a>
            </li>
          </c:otherwise>
        </c:choose>

        <!-- 社員マスタ -->
        <c:choose>
          <c:when test="${param.pageTitle == 'employee'}">
            <li class="active">
              <a href="employee"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> 社員マスタ</a>
            </li>
          </c:when>
          <c:otherwise>
            <li>
              <a href="employee"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> 社員マスタ</a>
            </li>
          </c:otherwise>
        </c:choose>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>