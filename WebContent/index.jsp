<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>二维码生成</title>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
<!--
$(function(){
	$("#qrgen").click(function(){
		var url = "qrc?size=" + $("#qrsize").val() + "&code=";
		$("#outputimage").attr("src",url+$("#qrcode").val());
	});

	$("#qrgenzx").click(function(){
		var url = "zxg?w=" + $("#qrwidth").val() + "&h=" + $("#qrheight").val() + "&code=";
		$("#outputimage").attr("src",url+$("#qrcodezx").val());
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
	<span>信息:</span>
	<input type="text" id="qrcode" name="qrcode" value="" />
	<span>尺寸:</span>
	<input type="text" id="qrsize" name="qrsize" value="7" />
	<input type="button" id="qrgen" name="qrgen" value="Qrcode" />
</div>


<div>
	<span>信息:</span>
	<input type="text" id="qrcodezx" name="qrcodezx" value="" />
	<span>宽度:</span>
	<input type="text" id="qrwidth" name="qrwidth" value="200" />
	<span>高度:</span>
	<input type="text" id="qrheight" name="qrheight" value="200" />
	<input type="button" id="qrgenzx" name="qrgenzx" value="ZXing" />
</div>

<div>
Qrcode支持小于120个字符.
ZXing则支持更大的数据量,具体参数要看google上的说明.
</div>

<div id="div_imageoutput">
	<img id="outputimage" src="" />
</div>
</body>
</html>