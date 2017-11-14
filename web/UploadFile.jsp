<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script type="text/javascript" src="js/jquery-1.9.1.js" ></script>
    <script type="text/javascript" src="js/jquery-form.js" ></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style type="text/css">
        body, td, div {font-size: 12px; font-familly: 宋体; }
        #progressBar {width: 400px; height: 12px; background: #FFFFFF; border: 1px solid #000000; padding: 1px; }
        #progressBarItem {width: 0%; height: 100%; background: #FF0000; }
    </style>
</head>
<body>
<form id="myForm" method="post" action="/upload/uploadFile.do" enctype="multipart/form-data">
    选择一个文件:
    <input id="fileIn" type="file" name="uploadFile" />
    <br/><br/>
    <input type="submit" value="上传" onclick="sub"/>
</form>
<div id="status">
    上传进度条：
    <div id="progressBar"><div id="progressBarItem"></div></div>
    <div id="statusInfo"></div>
</div>
<script type="text/javascript">
    var finished = true;
    var pro;
    // 使用ajaxForm
    $(function(){
        /** 验证文件是否导入成功  */
        $("#myForm").ajaxForm(function(data){
        });
    });

    $("#myForm").submit(function(){
        finished = false;
        pro = setInterval("requestStatus()",1000);
    });


    var progress = "1";
    function requestStatus(){
        getProgess();
    }

    function getProgess(){
        $.ajax({
            type:'post',
            url:"http://localhost:8080/upload/progress.do",
            success:function(data){//返回json结果
                progress = data;
                $('#progressBarItem').css("width",progress);
                if(progress == "100%"){
                    finished = true;
                    clearInterval(pro);
                }
            }});
    }
</script>
</body>
</html>