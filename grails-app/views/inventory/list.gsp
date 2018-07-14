<html>
<head>
    <title>Edit Inventory</title>

</head>

<body>

${allProducts}

<table border="1">
    <g:each in="${allProducts}" var="thisProduct">
        <tr>
            <td>${thisProduct.name}</td>
        </tr>

    </g:each>

</table>
</body>

</html>