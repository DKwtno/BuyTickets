<!DOCTYPE html>
<html>

    <#include "../common/header.ftl">

<body>

<div
    id="wrapper"
    class="toggled">

  <#include "../common/nav.ftl" >
  <div class="row clearfix">
    <div class="col-md-12 column">
      <div class="alert alert-dismissable alert-success">
        <button
            type="button"
            class="close"
            data-dismiss="alert"
            aria-hidden="true">×
        </button>
        <h4>
          Ohh! </h4> <strong>您成功购票</strong>

        <a
            href="${url}"
            class="alert-link">三秒后自动跳转
        </a>
      </div>
    </div>
  </div>
</div>

</body>

<script>
setTimeout('location.href="${url}"', 3000);
</script>


</html>
