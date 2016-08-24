<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
<!--
$(function(){
	$("#qrdecoderzxByUrl").click(function(){
		var url = "zxd?url=" + $("#qrimageUrl").val();
		$.post( url, 
				function(data){
					$("#div_stringoutput").text(data);	
		});
	});

	$("#qrdecoderzxByFile").click(function(){
		$("#div_stringoutput").find("iframe").remove();
		$("#div_stringoutput").text("");
		$("<iframe />").attr("id", "qrimageframe").attr("frameborder",0).attr("width","50%").appendTo("#div_stringoutput");
		$("#formQR").submit();
	});

});
//-->
</script>
</head>
<body>
<div>
	<p>
		<a href="index.jsp">编码</a>	
		<a href="decode.jsp">解码</a>	
	</p>
</div>

<div>
	<span>文件URL</span>
	<input type="text" id="qrimageUrl" name="qrimageUrl" value="" />
	<input type="button" id="qrdecoderzxByUrl" name="qrdecoderzxByUrl" value="Decode" />
</div>
Or
<div>
	<span>选择本地文件</span>
	<form id="formQR" method="post" action="zxd" enctype="multipart/form-data" target="qrimageframe">
		<input type="file" id="qrimagefile" name="qrimagefile" value="" />
		<input type="submit" id="qrdecoderzxByFile" name="qrdecoderzxByFile" value="Decode" />
	</form>
</div>

<div id="div_stringoutput">
	
</div>
</body>
</html>