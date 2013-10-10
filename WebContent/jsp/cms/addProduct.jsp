<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">
	window.UEDITOR_HOME_URL = "/uediter/";
	function submitProduct() {
		var html = UE.getEditor('editor').getContent();
		var tag=$("#tag1").val()+"&"+$("#tag2").val()+"&"+$("#tag3").val()+"&"+$("#tag4").val()+"&"+$("#tag5").val();
	alert(tag);
	}
</script>
<script type="text/javascript" charset="utf-8"
	src="<%=request.getContextPath()%>/js/jquery.js"></script>

<script type="text/javascript" charset="utf-8"
	src="<%=request.getContextPath()%>/uediter/editor_config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=request.getContextPath()%>/uediter/editor_all_min.js"></script>
</head>
<body>
	<div>
		<script id="editor" type="text/plain">请输入内容</script>
	</div>
	类别：<select id="tag1">
	<option value="日用百货">日用百货</option>
	<option value="服饰鞋包">服饰鞋包</option>
	<option value="数码电脑">数码电脑</option>
	<option value="家用电器">家用电器</option>
	<option value="食品酒茶">食品酒茶</option>
	<option value="美容美体">美容美体</option>
	<option value="母婴儿童">母婴儿童</option>
	<option value="手表饰品">手表饰品</option>	
	<option value="图书软件">图书软件</option>
	<option value="其他">其他</option>
	</select>
	<br>
	Tag:
	<input type="text" id="tag2" />
	<input type="text" id="tag3" />
	<input type="text" id="tag4" />
	<input type="text" id="tag5" />
	<button onclick="submitProduct()">提交</button>
	<script type="text/javascript">
		//实例化编辑器
		var ue = UE.getEditor('editor');

		ue.addListener('ready', function() {
			this.focus()
		});
	</script>
</body>
</html>