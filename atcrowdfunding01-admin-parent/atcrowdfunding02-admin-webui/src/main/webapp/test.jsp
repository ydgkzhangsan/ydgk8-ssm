<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-2.1.1.min.js"></script>
    <script>
        $(function(){
            $("button").click(function(){
                console.log("准备发送ajax请求！");
                var ajaxResult = $.ajax({
                    "url" : "${pageContext.request.contextPath}/user/sss.json",
                    "data": {"id":3},
                    "type": "post",
                    "contentType" : "json",
                    //"async" : false,
                    "success" : function(response){
                        // console.log(response)
                    },
                    "error" : function(response){
                        // console.log(response)
                    }
                });
                console.log(ajaxResult);
                console.log("ajax请求发送完毕了~")
            });
        })
    </script>
</head>
<body>
    <button>Send Ajax</button>
</body>
</html>
