<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
姓名：${name}
<br>

性别：
<#if sex=="1">
    男
<#elseif sex=="0">
    女
<#else>
    其他
</#if>
<br>

姓名遍历：
<#list userlist as user>
    ${user},
</#list>
</body>
</html>