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
      <br><h1>您账户余额为${balance}</h1><br>
      <h1>机票价格为${ticketPrice}</h1><br>
      <h1>是否购买？</h1><br>

      <div class="row clearfix">
        <div class="col-md-12 column">


          <a href="/tickets/index/success?orderId=${orderId}">
            <button
                type="button"
                class="btn btn-default btn-success">立即付款</button>
          </a>


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
