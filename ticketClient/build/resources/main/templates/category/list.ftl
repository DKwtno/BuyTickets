<html>

    <#include "../common/header.ftl">

<body>

<div
    id="wrapper"
    class="toggled">

  <#include "../common/nav.ftl" >

  <div id="page-content-wrapper">


    <div class="container-fluid">
      <div class="row clearfix">
        <div class="col-md-12 column">
          <table class="table table-bordered table-condensed">
            <thead>
            <tr class="success">
              <th>航空公司Id</th>
              <th>航空公司名</th>
              <th>航空公司编号</th>
              <th>创建时间</th>
              <th>修改时间</th>
              <th>操作</th>
            </tr>
            </thead>
            <tbody>
        <#list airlineInfoList as airlineInfo>

        <tr class="warning">

          <td>${airlineInfo.airlineId}</td>
          <td>${airlineInfo.airlineName}</td>
          <td>${airlineInfo.airlineType}</td>
          <td>${airlineInfo.createTime}</td>
          <td>${airlineInfo.updateTime}</td>
          <td>
            <a href="/tickets/seller/category/index/?airlineId=${airlineInfo.airlineId}">修改</a>
          </td>
        </tr>
        </#list>
            </tbody>
          </table>
        </div>



      </div>
    </div>


  </div>


</div>
</body>
<script src="https://cdn.bootcss.com/jquery/3.3.0/jquery.min.js"></script>

</html>

<#--<#list orderDTOPage.content as orderDTO>-->
<#--${orderDTO.orderId}<br>-->



<#--</#list>-->
