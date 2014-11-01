<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form class="form-horizontal">
	           <div class="form-group">
                    <label class="col-lg-3 control-label">userName:</label>
                    <div class="col-lg-9">
                           <input name="userName" style="display:inline; width:94%;" class="form-control"  type="text"  id="userNameID" />
    </div>
</div>
	           <div class="form-group">
                    <label class="col-lg-3 control-label">evaluationRank:</label>
                    <div class="col-lg-9">
                           <input name="evaluationRank" style="display:inline; width:94%;" class="form-control"  type="text"  id="evaluationRankID" />
    </div>
</div>
	           <div class="form-group">
                    <label class="col-lg-3 control-label">evaluationTimes:</label>
                    <div class="col-lg-9">
                           <input name="evaluationTimes" style="display:inline; width:94%;" class="form-control"  type="text"  id="evaluationTimesID" />
    </div>
</div>
	           <div class="form-group">
                    <label class="col-lg-3 control-label">serviceStatus:</label>
                    <div class="col-lg-9">
                           <div class="btn-group select" id="serviceStatusID"></div>
	                       <input type="hidden" id="serviceStatusID_" name="serviceStatusAsString" />                    </div>
    </div>
</div>
	           <div class="form-group">
                    <label class="col-lg-3 control-label">other:</label>
                    <div class="col-lg-9">
                           <input name="other" style="display:inline; width:94%;" class="form-control"  type="text"  id="otherID" />
    </div>
</div>
	           <div class="form-group">
                    <label class="col-lg-3 control-label">verifyType:</label>
                    <div class="col-lg-9">
                           <input name="verifyType" style="display:inline; width:94%;" class="form-control"  type="text"  id="verifyTypeID" />
    </div>
</div>
</form>
<script type="text/javascript">
    var selectItems = {};
                                    selectItems['serviceStatusID'] = [
                   {title: '请选择', value: ''},
                   {title: '是', value: '1'},
                   {title: '否', value: '0'}
                ];
                        </script>
</body>
</html>