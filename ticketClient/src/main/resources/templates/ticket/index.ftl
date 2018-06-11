<!DOCTYPE html>
<html>

    <#include "../common/header.ftl">

<body>

<div id="wrapper" class="toggled">

  <#include "../common/nav.ftl" >
  <div class="container-fluid">
  <div class="row clearfix">
    <div class="col-md-6 column">
      <form role="form"  method="post" action="/tickets/seller/ticket/save">
        <div class="form-group">
          <label>始发城市</label>
          <input name="ticketDepart" type="text" class="form-control"
              value="${(ticketInfo.ticketDepart)!''}"/>
        </div>

        <div class="form-group">
          <label>到达城市</label>
          <input
              name="ticketArrive"
              type="text"
              class="form-control"
              value="${(ticketInfo.ticketArrive)!''}"/>
        </div>

        <div class="form-group">
          <label>价格</label>
          <input
              name="ticketPrice"∑
              type="text"
              class="form-control"
              value="${(ticketInfo.ticketPrice)!''}"/>
        </div>


        <div class="form-group">
          <label>余量</label>
          <input
              name="ticketStock"
              type="number"
              class="form-control"
              value="${(ticketInfo.ticketStock)!''}"/>
        </div>

        <div class="form-group">
          <label>航空公司</label>
          <select name="airlineType" class="form-control">
            <#list airlineInfoList as airlineInfo>
              <option value="${airlineInfo.airlineType}"
                ${airlineInfo.airlineName}
                 <#if (ticketInfo.airlineType)?? && ticketInfo.airlineType == airlineInfo.airlineType> selected
                 </#if>
                >${airlineInfo.airlineName}
              </option>
            </#list>
          </select>
        </div>

        <div class="form-group">
          <label>起飞时间</label>
          <input
              name="takeOff" type="datetime-local"  class="form-control"
          value="${(ticketInfo.takeOff)!''}">
        </div>


        <input hidden type="text" name="ticketId" value="${(ticketInfo.ticketId)!''}">
        <button
            type="submit"
            class="btn btn-default btn-warning">Submit
        </button>
      </form>
    </div>
  </div>
  </div>



</div>

</body>

<script src="https://cdn.bootcss.com/jquery/3.3.0/jquery.min.js"></script>

</html>
