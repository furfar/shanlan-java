<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<form class="form-horizontal">
		<div class="form-group">
			<label class="col-lg-3 control-label">creator:</label>
			<div class="col-lg-9">
				<input name="creator" style="display: inline; width: 94%;"
					class="form-control" type="text" id="creatorID" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">title:</label>
			<div class="col-lg-9">
				<input name="title" style="display: inline; width: 94%;"
					class="form-control" type="text" id="titleID" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">content:</label>
			<div class="col-lg-9">
				<input name="content" style="display: inline; width: 94%;"
					class="form-control" type="text" id="contentID" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">viewTimes:</label>
			<div class="col-lg-9">
				<input name="viewTimes" style="display: inline; width: 94%;"
					class="form-control" type="text" id="viewTimesID" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">status:</label>
			<div class="col-lg-9">
				<div class="btn-group select" id="statusID"></div>
				<input type="hidden" id="statusID_" name="statusAsString" />
			</div>
		</div>
	</form>
	<script type="text/javascript">
		var selectItems = {};
		selectItems['statusID'] = [ {
			title : '请选择',
			value : ''
		}, {
			title : '是',
			value : '1'
		}, {
			title : '否',
			value : '0'
		} ];
	</script>
</body>
</html>