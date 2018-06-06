<!DOCTYPE html>


      <#include "../common/header.ftl">

<body>


<div id="wrapper" class="toggled">

    <#include "../common/nav.ftl" >

  <div id="page-content-wrapper">
    <div class="container-fluid">

    <div class="col-md-4 column">
      <table class="table">
        <thead>
        <tr>
          <th>订单编号</th>
          <th>金额</th>
        </tr>
        </thead>
        <tbody>
        <tr class="success">
          <td>
            ${orderDTO.getOrderId()}
          </td>
          <td>
            ${orderDTO.getOrderAmount()}
          </td>

        </tr>
        </tbody>
      </table>
    </div>


      <div class="column pull-right">
        <a href="./list"
            type="button"
            class="btn btn-default btn-info">返回上一页
        </a>
      </div>


    <div class="col-md-12 column">
      <table class="table" >
        <thead>
        <tr>
          <th>机票订购识别码</th>
          <th>机票编号</th>
          <th>起飞地</th>
          <th>到达地</th>
          <th>机票单价</th>
          <th>数量</th>
        </tr>
        </thead>
        <tbody>

        <#list orderDTO.getOrderDetailList() as orderDetail>

            <tr class="warning">

              <th>${orderDetail.getDetailId()}</th>
              <th>${orderDetail.getTicketId()}</th>
              <th>${orderDetail.getTicketDepart()}</th>
              <th>${orderDetail.getTicketArrive()}</th>
              <th>${orderDetail.getTicketPrice()}</th>
              <th>${orderDetail.getTicketQuantity()}</th>

            </tr>
        </#list>


        </tbody>
      </table>

      <div class="row clearfix">
        <div class="col-md-12 column">
          <button
              type="button"
              class="btn btn-default btn-success">完成订单
          </button>
          <button
              type="button"
              class="btn btn-default btn-danger">取消订单
          </button>




        </div>
      </div>

    </div>
    </div>

  </div>
</div>


</body>
</html>
