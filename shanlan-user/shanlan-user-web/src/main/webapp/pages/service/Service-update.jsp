<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form class="form-horizontal">
	           <div class="form-group">
                    <label class="col-lg-3 control-label">name:</label>
                    <div class="col-lg-9">
                           <input name="name" style="display:inline; width:94%;" class="form-control"  type="text"  dataType="English" require="false" id="nameID" />
    </div>
</div>
	           <div class="form-group">
                    <label class="col-lg-3 control-label">url:</label>
                    <div class="col-lg-9">
                           <input name="url" style="display:inline; width:94%;" class="form-control"  type="text"  dataType="URL" require="false" id="urlID" />
    </div>
</div>
	           <div class="form-group">
                    <label class="col-lg-3 control-label">serviceVersion:</label>
                    <div class="col-lg-9">
                           <div class="btn-group select" id="serviceVersionID"></div>
	                       <input type="hidden" id="serviceVersionID_"  name="serviceVersion" />
    </div>
</div>
	           <div class="form-group">
                    <label class="col-lg-3 control-label">enable:</label>
                    <div class="col-lg-9">
                           <div class="btn-group select" id="enableID"></div>
	                       <input type="hidden" id="enableID_" name="enableAsString" />                    </div>
    </div>
</div>
	           <div class="form-group">
                    <label class="col-lg-3 control-label">type:</label>
                    <div class="col-lg-9">
                           <div class="btn-group select" id="typeID"></div>
	                       <input type="hidden" id="typeID_"  name="type" />
    </div>
</div>
	           <div class="form-group">
                    <label class="col-lg-3 control-label">group:</label>
                    <div class="col-lg-9">
                           <div class="btn-group select" id="groupID"></div>
	                       <input type="hidden" id="groupID_"  name="group" />
    </div>
</div>
	           <div class="form-group">
                    <label class="col-lg-3 control-label">isLocal:</label>
                    <div class="col-lg-9">
                           <div class="btn-group select" id="isLocalID"></div>
	                       <input type="hidden" id="isLocalID_"  name="isLocal" />
    </div>
</div>
</form>
<script type="text/javascript">
    var selectItems = {};
                            var contents = [{title:'请选择', value: ''}];
        contents.push({title:'V1.0' , value:'V1.0'});
        contents.push({title:'V2.0' , value:'V2.0'});
        selectItems['serviceVersionID'] = contents;
                selectItems['enableID'] = [
                   {title: '请选择', value: ''},
                   {title: '是', value: '1'},
                   {title: '否', value: '0'}
                ];
                var contents = [{title:'请选择', value: ''}];
        contents.push({title:'HTTP异步服务' , value:'HTTP_ASY'});
        contents.push({title:'HTTPS异步服务' , value:'HTTPS_ASY'});
        contents.push({title:'HTTPS同步服务' , value:'HTTPS_SY'});
        contents.push({title:'HTTP同步服务' , value:'HTTP_SY'});
        selectItems['typeID'] = contents;
                var contents = [{title:'请选择', value: ''}];
        contents.push({title:'用户相关服务' , value:'User'});
        contents.push({title:'交易相关服务' , value:'Trade'});
        contents.push({title:'其它服务' , value:'Other'});
        contents.push({title:'照片相关服务' , value:'Photo'});
        selectItems['groupID'] = contents;
                var contents = [{title:'请选择', value: ''}];
        contents.push({title:'0' , value:'false'});
        contents.push({title:'1' , value:'true'});
        selectItems['isLocalID'] = contents;
        </script>
</body>
</html>