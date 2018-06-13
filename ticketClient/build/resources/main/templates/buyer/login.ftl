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
    <div class="col-md-6 column">
      <form method="post"
          action="/tickets/index/login"
          class="form-horizontal"
          role="form">
        <div class="form-group">
          <label
              for="inputEmail3"
              class="col-sm-2 control-label">Username
          </label>
          <div class="col-sm-10">
            <input
                type="text"
                class="form-control"
                name="buyerName"/>
          </div>
        </div>
        <div class="form-group">
          <label
              for="inputPassword3"
              class="col-sm-2 control-label">Password
          </label>
          <div class="col-sm-10">
            <input
                type="password"
                class="form-control"
                name="password"/>
          </div>
        </div>
        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
            <div class="checkbox">
              <label>
                <input type="checkbox" name="remember"/>
                Remember me
              </label>
            </div>
          </div>
        </div>
        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
            <button
                type="submit"
                class="btn btn-default">Sign in
            </button>
          </div>
        </div>
      </form>
    </div>
    <div class="col-md-6 column"></div>
  </div>
    </div>
</div>
 </div>

</body>


</html>
