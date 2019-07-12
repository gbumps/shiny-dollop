<%-- 
    Document   : searchresult
    Created on : Jul 12, 2019, 1:06:47 PM
    Author     : stephen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/homepage.css" type="text/css" />
        <link rel="stylesheet" href="CSS/productItem.css" type="text/css"/>
        <link rel="stylesheet" href="CSS/modal.css" type="text/css"/>
        <title>Kết quả tìm kiếm</title>
    </head>
    <body>
        <c:set var="searchData" value="${requestScope.SEARCH_DATA}" />
        <c:import var="xslt" url="XSLT/html/Product.xsl" charEncoding="UTF-8" />
        <div class="topnav">
            <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" id="Layer_1"
                x="0px" y="0px" viewBox="0 0 426.668 426.668" style="enable-background:new 0 0 426.668 426.668;"
                xml:space="preserve" width="30px" height="30px">
                <g>
                    <path
                        d="M401.788,74.476c-63.492-82.432-188.446-33.792-188.446,49.92  c0-83.712-124.962-132.356-188.463-49.92c-65.63,85.222-0.943,234.509,188.459,320.265  C402.731,308.985,467.418,159.698,401.788,74.476z"
                        data-original="#F05228" class="active-path" data-old_color="#F05228" fill="#FC2E51" />
                </g>
            </svg>
            <a href="NavigateServlet?page=homepage"> Trang chủ </a>
            <a id="myBtn">Gợi ý sản phẩm</a>
            <div class="search-container">
                <input size="450" id="search" type="text" placeholder="Tìm đầm bé gái, áo bé trai,....vv"/>
                <div id="searchProduct">
                </div>
            </div>
        </div>
        <div class="container">
            <div class="main">
                <h2>Kết quả tìm kiếm</h2> 
                <div class="container-item">
                    <c:if test="${not empty searchData}">
                        <xm:parse var="resultSearch" xml="${searchData}" scope="request" />
                        <c:if test="${not empty resultSearch}">
                            <xm:transform xml="${resultSearch}" xslt="${xslt}" />  
                        </c:if>
                    </c:if>
                </div>
            </div>
        </div>
    </body>
    <script type="text/javascript" src="JS/showModal.js"></script>
    <script src="JS/SearchProduct.js" type="text/javascript"></script>
</html>
