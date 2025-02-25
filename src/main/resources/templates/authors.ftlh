<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title> Список всех авторов</title>
<link rel="stylesheet" href="static.style.css">
</head>
<body>
<div class="container">
<table>
<thead>
<tr>
<th>Id</th>
<th>Name</th>
<th>Surname</th>
</tr>
</thead>
<tbody>
<#list authors as author>
<tr>
<td>${author.id}</td>
<td>${author.name}</td>
<td>${author.surname}</td>
</tr>
</#list>
</tbody>
</table>
</div>
</body>
</html>

