<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
    <script type="text/javascript" src="${ctx}/static/js/jquery-1.9.1.min.js "></script>
    <title>domac词霸</title>

    <script>
        function keyfun(event) {
            var keyCode = event.keyCode;
            if(keyCode == 13) {
                translate();
            }
        }

        function translate() {
            var word = $('#worldpress').val();
            $.ajax({
                url:'${ctx}/pageTranslate/'+word,
                success:function(data) {
                    $('#translateDiv').html('翻译结果:'+data);
                }
            })
        }

        function buttonClick() {
            translate();
        }
    </script>
</head>
<body>
<table width="100%" style="text-align: center">
    <tr>
        <td>
            <input style="width: 600px;" id="worldpress" name="worldpress" onkeyup="keyfun(event)" type="text"  value=""/>
            <input id="mb" type="button" onclick="buttonClick()" value="查询">
        </td>
    </tr>

    <tr>
        <td style="width: 100%;vertical-align: text-bottom ">
            <div id="translateDiv">
            </div>
        </td>
    </tr>
</table>
</body>
</html>
