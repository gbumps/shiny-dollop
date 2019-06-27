<%-- 
    Document   : homepage
    Created on : Jun 26, 2019, 3:14:30 PM
    Author     : stephen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="xm" uri="http://java.sun.com/jsp/jstl/xml" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0 user-scalable=yes"/>
        <link rel="stylesheet" href="CSS/homepage.css" type="text/css"/>
        <title>Main Page</title>
  
    </head>
    <body>
        <c:set var="ListProduct" value="${requestScope.ProductList}" />
        <c:import var="ProductXSL" url="XSLT/html/Product.xsl" charEncoding="UTF-8" />
        <div class="header"> 
            <h2>Trang web tìm và so sánh các loại đồ em bé  ...,</h2>
            <div class="search">
                
                <input class="header_search" id="search" onkeyup="showProductSearch();" type="text" placeholder="tim SSD, RAM, phu kien, ...."/>
                <button title="Tìm kiếm nâng cao" />
            </div>
        </div>
        <div class="main">
            <div class="top-10-boy">
                <xm:parse xml="${ListProduct}" var="resultXMLTransform" scope="request" />
                <c:if test="${not empty ListProduct}">
                    <xm:transform xml="${resultXMLTransform}" xslt="${ProductXSL}" />
                </c:if>
            </div>
            <div class="top-10-girl">
                
            </div>
        </div>
    </body>
</html>
