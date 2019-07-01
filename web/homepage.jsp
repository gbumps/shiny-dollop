<%-- 
    Document   : homepage
    Created on : Jun 26, 2019, 3:14:30 PM
    Author     : stephen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="xm" uri="http://java.sun.com/jsp/jstl/xml" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0 user-scalable=yes"/>
        <link rel="stylesheet" href="CSS/homepage.css" type="text/css"/>
        <script src="JS/ProductItemSlide.js" type="text/javascript"></script>
        <title>Main Page</title>
    </head>
    <body>
        <c:set var="result" value="${requestScope.HomepageResult}" />
        <c:import var="ItemXSL" url="XSLT/html/Itemintro.xsl" charEncoding="UTF-8" /> 
        <div class="topnav">
            <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" id="Layer_1" x="0px" y="0px" viewBox="0 0 426.668 426.668" style="enable-background:new 0 0 426.668 426.668;" xml:space="preserve" width="30px" height="30px"><g><path d="M401.788,74.476c-63.492-82.432-188.446-33.792-188.446,49.92  c0-83.712-124.962-132.356-188.463-49.92c-65.63,85.222-0.943,234.509,188.459,320.265  C402.731,308.985,467.418,159.698,401.788,74.476z" data-original="#F05228" class="active-path" data-old_color="#F05228" fill="#FC2E51"/></g> </svg>
            <a href="javascript:location.href='NavigateServlet?page=homepage'"> Trang chủ </a>
            <a href="javascript:location.href='NavigateServlet?page=suggestion'">Gợi ý sản phẩm</a>
            <div class="search-container">
                <form action="search" method="POST">
                    <input class="input-search" type="text" placeholder="Đầm bé gái, áo bé trai,....vv"/>
                    <button type="submit" value="Tìm"/>
                </form>
            </div>
        </div>
        <div class="container">
          <div class="main">
            <xm:parse var="resultXML" xml="${result}" scope="request" />
            <c:if test="${not empty resultXML}">
                <xm:transform xml="${resultXML}" xslt="${ItemXSL}" />  
            </c:if>
            <div class="show-all">
                <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" viewBox="0 0 129 129" enable-background="new 0 0 129 129" width="10px" height="10px"><g><g>
                    <path d="m40.4,121.3c-0.8,0.8-1.8,1.2-2.9,1.2s-2.1-0.4-2.9-1.2c-1.6-1.6-1.6-4.2 0-5.8l51-51-51-51c-1.6-1.6-1.6-4.2 0-5.8 1.6-1.6 4.2-1.6 5.8,0l53.9,53.9c1.6,1.6 1.6,4.2 0,5.8l-53.9,53.9z" data-original="#000000" class="active-path" data-old_color="#000000" fill="#9F7DFE"/>
                    </g></g> 
                </svg>
            </div>
            <div class="show-all">
                <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" viewBox="0 0 129 129" enable-background="new 0 0 129 129" width="10px" height="10px"><g><g>
    <path d="m40.4,121.3c-0.8,0.8-1.8,1.2-2.9,1.2s-2.1-0.4-2.9-1.2c-1.6-1.6-1.6-4.2 0-5.8l51-51-51-51c-1.6-1.6-1.6-4.2 0-5.8 1.6-1.6 4.2-1.6 5.8,0l53.9,53.9c1.6,1.6 1.6,4.2 0,5.8l-53.9,53.9z" data-original="#000000" class="active-path" data-old_color="#000000" fill="#9F7DFE"/>
  </g></g> </svg>
            </div>
            </div>
        </div>
    </body>
</html>
