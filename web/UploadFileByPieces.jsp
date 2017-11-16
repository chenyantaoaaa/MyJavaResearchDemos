<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/11/15
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form id="myForm" method="post" action="/upload/uploadFile.do" enctype="multipart/form-data">
    选择一个文件:
    <input id="fileIn" type="file" name="uploadFile" />
    <br/><br/>
    <input type="submit" value="上传" onclick="sub"/>
</form>

<script type="text/javascript" src="js/jquery-form.js"></script>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript">
    var BYTES_PER_CHUNK = 1024 * 1024; // 每个文件切片大小定为1MB .
    var slices;
    var totalSlices;

    //发送请求
    function sendRequest() {
        var blob = document.getElementById("fileIn").files[0];
        var start = 0;
        var end;
        var index = 0;


// 计算文件切片总数
        slices = Math.ceil(blob.size / BYTES_PER_CHUNK);
        totalSlices= slices;
        while(start < blob.size) {
            end = start + BYTES_PER_CHUNK;
            if(end > blob.size) {
                end = blob.size;
            }
            uploadFile(blob, index, start, end);
            start = end;
            index++;
            if ( index>=totalSlices )
                location="reboot.htm";
        }
    }

    //上传文件
    function uploadFile(blob, index, start, end) {
        var xhr;
        var fd;
        var chunk;
        var sliceIndex=blob.name+index;
        chunk =blob.slice(start,end);//切割文件

        fd = new FormData();
        fd.append("UpgradeFileName", chunk, sliceIndex);
        var xhr = new XMLHttpRequest();
        xhr.open('POST', 'cmd.cgi?cmd_tag=firmware_upgrade&group_tag=upgrade', false);//false指同步上传
        xhr.send(fd);
        if((xhr.status >=200 && xhr.status < 300) || xhr.status == 304){
            setTimeout("",10);
        }else{
            uploadFile(blob, index, start, end);
        }
    }
</script>
</body>
</html>
