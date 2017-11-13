<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script type="text/javascript" src="js/jquery-1.9.1.js" ></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style type="text/css">
        body, td, div {font-size: 12px; font-familly: 宋体; }
        #progressBar {width: 400px; height: 12px; background: #FFFFFF; border: 1px solid #000000; padding: 1px; }
        #progressBarItem {width: 1%; height: 100%; background: #FF0000; }
    </style>
</head>
<body>
<form method="post" action="/upload/uploadFile.do" enctype="multipart/form-data">
    选择一个文件:
    <input type="file" name="uploadFile" />
    <br/><br/>
    <input type="submit" value="上传" />
</form>
<div id="status">
    上传进度条：
    <div id="progressBar"><div id="progressBarItem"></div></div>
    <div id="statusInfo"></div>
</div>
<script type="text/javascript">
    var progress = 1;
    setInterval("requestStatus()", 5000);
    function requestStatus(){
//        if(progress == 100){
//            return
//        }
//        progress++;
//        $('#progressBarItem').css("width",progress + "%");
        getProgess();
    }

    function getProgess(){
        $.ajax({
            type:'get',
            url:"localhost:8080/upload/getProgess.do",
            success:function(data){//返回json结果
                alert(data);
            }});
    }
</script>
</body>
</html>