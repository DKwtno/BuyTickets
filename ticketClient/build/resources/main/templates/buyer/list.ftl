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


      <div class="row clearfix">
        <div class="col-md-12 column">
          <table class="table table-striped table-condensed">
            <thead>
            <tr class="success">
              <th>机票Id</th>
              <th>始发地</th>
              <th>到达地</th>
              <th>起飞时间</th>
              <th>票价</th>
              <th>余量</th>
              <th>航空公司</th>
              <#--<th colspan="2">操作</th>-->
              <th>操作</th>
            </tr>
            </thead>
            <tbody>
        <#list ticketInfoPage.content as ticketInfo>

        <tr class="warning">

          <td>${ticketInfo.ticketId}</td>
          <td>${ticketInfo.ticketDepart}</td>
          <td>${ticketInfo.ticketArrive}</td>
          <td>${ticketInfo.takeOff}</td>

          <td>${ticketInfo.ticketPrice}</td>
          <td>${ticketInfo.ticketStock}</td>
          <#--<td>${ticketInfo.airlineType}</td>-->
          <td>
          <#if ticketInfo.airlineType==0>
            上理航空
          <#elseif ticketInfo.airlineType==2>
            JAL日航
          <#else>
            东方航空
          </#if>
          </td>

          <td>
            <a href="/tickets/index/ticket/?ticketId=${ticketInfo.ticketId}">购买</a>
          </td>
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
                  <a href="/tickets/index/list?page=${currentPage-1}&size=${size}">上一页</a>
                </li>
        </#if>

          <#list 1..ticketInfoPage.getTotalPages() as index>
            <#if currentPage==index>
                <li class="disabled">
                  <a href="#">${index}</a>
                </li>
            <#else>
              <li>
                <a href="/tickets/index/list?page=${index}&size=${size}">${index}</a>
              </li>
            </#if>

          </#list>

        <#if currentPage gte ticketInfoPage.getTotalPages()>
            <li class="disabled">
              <a href="#">上一页</a>
            </li>
        <#else>
                <li>
                  <a href="/tickets/index/list?page=${currentPage+1}&size=${size}">下一页</a>
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
