<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<form class="form-horizontal">
		<div class="form-group">
			<label class="col-lg-3 control-label">serviceName:</label>

			<div class="col-lg-9">
				<input name="serviceName" style="display: inline; width: 94%;"
					class="form-control" type="text" id="serviceNameID" /> <span
					class="required">*</span>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">url:</label>

			<div class="col-lg-9">
				<input name="url" style="display: inline; width: 94%;"
					class="form-control" type="text" require="false" id="urlID" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">serviceVersion:</label>

			<div class="col-lg-9">
				<div class="btn-group select" id="serviceVersionID"></div>
				<input type="hidden" id="serviceVersionID_" name="serviceVersion" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">method:</label>

			<div class="col-lg-9">
				<div class="btn-group select" id="methodID"></div>
				<input type="hidden" id="methodID_" name="method" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">enable:</label>

			<div class="col-lg-9">
				<div class="btn-group select" id="enableID"></div>
				<input type="hidden" id="enableID_" name="enable" />
			</div>
		</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">isLocal:</label>

			<div class="col-lg-9">
				<div class="btn-group select" id="isLocalID"></div>
				<input type="hidden" id="isLocalID_" name="isLocal" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">serviceGroup:</label>

			<div class="col-lg-9">
				<div class="btn-group select" id="serviceGroupID"></div>
				<input type="hidden" id="serviceGroupID_" name="serviceGroup" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">type:</label>

			<div class="col-lg-9">
				<div class="btn-group select" id="typeID"></div>
				<input type="hidden" id="typeID_" name="type" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">scenario:</label>

			<div class="col-lg-9">
				<textarea name="scenario" style="display: inline; width: 94%;"
					class="form-control" type="text" id="scenarioID" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">businessParam:</label>

			<div class="col-lg-9">
				<textarea name="businessParam" style="display: inline; width: 94%;"
					class="form-control" type="text" id="businessParamID" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">response:</label>

			<div class="col-lg-9">
				<textarea name="response" style="display: inline; width: 94%;"
					class="form-control" type="text" id="responseID" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">errorCode:</label>

			<div class="col-lg-9">
				<textarea name="errorCode" style="display: inline; width: 94%;"
					class="form-control" type="text" id="errorCodeID" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">responseSample:</label>

			<div class="col-lg-9">
				<textarea name="responseSample" style="display: inline; width: 94%;"
					class="form-control" type="text" id="responseSampleID" />
			</div>
		</div>
	</form>
	<script type="text/javascript">
		var selectItems = {};
		var contents = [ {
			title : '请选择',
			value : ''
		} ];
		contents.push({
			title : 'V1.0',
			value : 'V1.0'
		});
		contents.push({
			title : 'V2.0',
			value : 'V2.0'
		});
		selectItems['serviceVersionID'] = contents;
		selectItems['enableID'] = [ {
			title : '请选择',
			value : ''
		}, {
			title : '是',
			value : '1'
		}, {
			title : '否',
			value : '0'
		} ];
		selectItems['methodID'] = [ {
			title : '请选择',
			value : ''
		}, {
			title : 'GET',
			value : 'GET'
		}, {
			title : 'POST',
			value : 'POST'
		}, {
			title : 'PUT',
			value : 'PUT'
		}, {
			title : 'DELETE',
			value : 'DELETE'
		} ];
		var contents = [ {
			title : '请选择',
			value : ''
		} ];
		contents.push({
			title : '是',
			value : '1'
		});
		contents.push({
			title : '否',
			value : '0'
		});
		selectItems['isLocalID'] = contents;
		var contents = [ {
			title : '请选择',
			value : ''
		} ];
		contents.push({
			title : '用户相关服务',
			value : 'User'
		});
		contents.push({
			title : '交易相关服务',
			value : 'Trade'
		});
		contents.push({
			title : '其它服务',
			value : 'Other'
		});
		contents.push({
			title : '照片相关服务',
			value : 'Photo'
		});
		selectItems['serviceGroupID'] = contents;
		var contents = [ {
			title : '请选择',
			value : ''
		} ];
		contents.push({
			title : 'HTTP同步服务',
			value : 'HTTP_SY'
		});
		contents.push({
			title : 'HTTP异步服务',
			value : 'HTTP_ASY'
		});
		contents.push({
			title : 'HTTPS同步服务',
			value : 'HTTPS_SY'
		});
		contents.push({
			title : 'HTTPS异步服务',
			value : 'HTTPS_ASY'
		});
		selectItems['typeID'] = contents;
	</script>
</body>
</html>