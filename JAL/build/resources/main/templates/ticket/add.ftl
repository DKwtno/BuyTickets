<!DOCTYPE html>
<html>

    <#include "../common/header.ftl">

<body>

<div
    id="wrapper"
    class="toggled">

  <#include "../common/nav.ftl" >
  <div class="container-fluid">
    <div class="row clearfix">
      <div class="col-md-6 column">
        <form
            role="form"
            method="post"
            action="/jal/seller/ticket/newer">
          <div class="form-group">
            <label>始发城市</label>
            <input
                name="ticketDepart"
                type="text"
                class="form-control"
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
                name="ticketPrice"
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
            <label>起飞时间</label>
            <input
                type="datetime-local"
                name="takeOff"
                class="form-control"
                value="${(ticketInfo.takeOff)!''}">
          </div>

          <input
              hidden
              type="number"
              name="airlineType"
              value="2">

          <input
              hidden
              type="text"
              name="ticketId"
              value="${(ticketInfo.ticketId)!''}">
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
