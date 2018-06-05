<html>

    <#include "../common/header.ftl">

<body>

<div id="wrapper" class="toggled">

  <#include "../common/nav.ftl" >

  <div id="page-content-wrapper">


    <div class="container-fluid">
      <div class="row clearfix">
        <div class="col-md-12 column">
          <table class="table table-bordered table-condensed">
            <thead>
            <tr>
              <th>机票Id</th>
              <th>始发地</th>
              <th>到达地</th>
              <th>起飞时间</th>
              <th>票价</th>
              <th>余量</th>
              <th>航空公司编号</th>
              <th colspan="2">操作</th>
            </tr>
            </thead>
            <tbody>
        <#list ticketInfoPage.content as ticketInfo>

        <tr>

          <td>${ticketInfo.ticketId}</td>
          <td>${ticketInfo.ticketDepart}</td>
          <td>${ticketInfo.ticketArrive}</td>
          <td>${ticketInfo.takeOff}</td>
          <td>${ticketInfo.ticketPrice}</td>
          <td>${ticketInfo.ticketStock}</td>
          <td>${ticketInfo.airlineType}</td>
          <td>修改</td>
          <td>下架</td>
        </tr>
        </#list>
            </tbody>
          </table>
        </div>

        <div class="col-md-12 column">
          <ul class="pagination pull-right">
        <#if currentPage lte 1>
            <li class="disabled">
              <a href="#">上一页</a>
            </li>
        <#else>
                <li>
                  <a href="/tickets/seller/ticket/list?page=${currentPage-1}&size=${size}">上一页</a>
                </li>
        </#if>

          <#list 1..ticketInfoPage.getTotalPages() as index>
              <#if currentPage==index>
                <li class="disabled">
                  <a href="#">${index}</a>
                </li>
              <#else>
              <li>
                <a href="/tickets/seller/ticket/list?page=${index}&size=${size}">${index}</a>
              </li>
              </#if>

          </#list>

        <#if currentPage gte ticketInfoPage.getTotalPages()>
            <li class="disabled">
              <a href="#">上一页</a>
            </li>
        <#else>
                <li>
                  <a href="/tickets/seller/ticket/list?page=${currentPage+1}&size=${size}">下一页</a>
                </li>
        </#if>
          </ul>
        </div>


      </div>
    </div>


  </div>


</div>


</body>

</html>

<#--<#list orderDTOPage.content as orderDTO>-->
    <#--${orderDTO.orderId}<br>-->



<#--</#list>-->
