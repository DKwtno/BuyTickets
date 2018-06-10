<!DOCTYPE html>
<html>

    <#include "../common/header.ftl">

<body>

<div id="wrapper" class="toggled">

  <#include "../common/nav.ftl" >
  <div class="container-fluid">
  <div class="row clearfix">
    <div class="col-md-6 column">
      <form role="form"  method="post" action="/tickets/seller/category/save">
        <div class="form-group">
          <label>航空公司名</label>
          <input name="airlineName" type="text" class="form-control"
              value="${(airlineInfo.airlineName)!''}"/>
        </div>

        <div class="form-group">
          <label>航空公司编号</label>
          <input
              name="airlineType"
              type="number"
              class="form-control"
              value="${(airlineInfo.airlineType)!''}"/>
        </div>

        <input hidden type="number" name="airlineId" value="${(airlineInfo.airlineId)!''}">
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
