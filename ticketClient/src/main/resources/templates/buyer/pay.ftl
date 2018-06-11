<!DOCTYPE html>
<html>

<#include "../common/header_user.ftl">

<body>

<div
    id="wrapper"
    class="toggled">

  <#include "../common/nav_user.ftl" >
  <div id="page-content-wrapper">
    <div class="container-fluid">

        <br>
      <br>
      <br>您账户余额为${}<br>
      机票价格为${ticketPrice}<br>
      是否购买？<br>

      <div class="row clearfix">
        <div class="col-md-12 column">
          <button
              href="/tickets/index/success"
              type="button"
              class="btn btn-default btn-success">立即付款
          </button>
          <button
              type="button"
              class="btn btn-default btn-danger">考虑下
          </button>


        </div>
      </div>

    </div>
  </div>
</div>

</body>


</html>
