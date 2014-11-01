<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<form class="form-horizontal">
		<div class="form-group" style="display: none">
			<label class="col-lg-3 control-label">userName:</label>
			<div class="col-lg-9">
				<input name="userName" style="display: inline; width: 94%;"
					class="form-control" type="text" id="userNameID"/>
			</div>
		</div>
        <div class="form-group">
            <label class="col-lg-3 control-label">nickName:</label>
            <div class="col-lg-9">
                <input name="nickName" style="display: inline; width: 94%;"
                       class="form-control" type="text" id="nickNameID" />
            </div>
        </div>
        <div class="form-group">
            <label class="col-lg-3 control-label">email:</label>
            <div class="col-lg-9">
                <input name="email" style="display: inline; width: 94%;"
                       class="form-control" type="text" id="emailID" />
            </div>
        </div>
		<div class="form-group">
			<label class="col-lg-3 control-label">realName:</label>
			<div class="col-lg-9">
				<input name="realName" style="display: inline; width: 94%;"
					class="form-control" type="text" id="realNameID" />
			</div>
		</div>
		<div class="form-group" style="display: none">
			<label class="col-lg-3 control-label">photoId:</label>
			<div class="col-lg-9">
				<input name="photoId" style="display: none; width: 94%;"
					class="form-control" type="text" id="photoIdID" />
			</div>
		</div>
		<div class="form-group" style="display: none">
			<label class="col-lg-3 control-label">photoPath:</label>
			<div class="col-lg-9">
				<input name="photoPath" style="display: none; width: 94%;"
					class="form-control" type="text" id="photoPathID" />
			</div>
		</div>
        <div class="form-group">
            <label class="col-lg-3 control-label">gender:</label>
            <div class="col-lg-9">
                <input name="gender" style="display: inline; width: 94%;"
                       class="form-control" type="text" id="genderID" />
            </div>
        </div>
		<div class="form-group">
			<label class="col-lg-3 control-label">cityId:</label>
			<div class="col-lg-9">
				<input name="cityId" style="display: inline; width: 94%;"
					class="form-control" type="text" id="cityIdID" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">birthday:</label>
			<div class="col-lg-9">
				<div class="input-group date form_datetime"
					style="width: 160px; float: left;">
					<input type="text" class="form-control" style="width: 160px;"
						name="birthday" id="birthdayID"> <span
						class="input-group-addon"><span
						class="glyphicon glyphicon-th"></span></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">mobile:</label>
			<div class="col-lg-9">
				<input name="mobile" style="display: inline; width: 94%;"
					class="form-control" type="text" id="mobileID" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">qq:</label>
			<div class="col-lg-9">
				<input name="qq" style="display: inline; width: 94%;"
					class="form-control" type="text" dataType="Number" require="false"
					id="qqID" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">webchart:</label>
			<div class="col-lg-9">
				<input name="webchart" style="display: inline; width: 94%;"
					class="form-control" type="text" id="webchartID" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">alipay:</label>
			<div class="col-lg-9">
				<input name="alipay" style="display: inline; width: 94%;"
					class="form-control" type="text" id="alipayID" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">tradeTimes:</label>
			<div class="col-lg-9">
				<input name="tradeTimes" style="display: inline; width: 94%;"
					class="form-control" type="text" id="tradeTimesID" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">activeness:</label>
			<div class="col-lg-9">
				<input name="activeness" style="display: inline; width: 94%;"
					class="form-control" type="text" id="activenessID" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">photoCount:</label>
			<div class="col-lg-9">
				<input name="photoCount" style="display: inline; width: 94%;"
					class="form-control" type="text" id="photoCountID" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">other:</label>
			<div class="col-lg-9">
				<input name="other" style="display: inline; width: 94%;"
					class="form-control" type="text" id="otherID" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">type:</label>
			<div class="col-lg-9">
				<div class="btn-group select" id="typeID"></div>
				<input type="hidden" id="typeID_" name="type" dataType="Require" />
				<span class="required">*</span>
			</div>
		</div>
	</form>
	<script type="text/javascript">
		var selectItems = {};
		var contents = [ {
			title : '请选择',
			value : ''
		} ];
        contents.push({title: '摄影师', value: 'PHOTOGRAPHER'});
        contents.push({title: '模特', value: 'MODEL'});
        contents.push({title: '普通用户', value: 'COMMON'});
		selectItems['typeID'] = contents;
	</script>
</body>
</html>