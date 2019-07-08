<%-- 
    Document   : suggestion
    Created on : Jun 30, 2019, 1:42:27 PM
    Author     : stephen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="xm" uri="http://java.sun.com/jsp/jstl/xml"%>

<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="CSS/homepage.css" type="text/css" />
    <link rel="stylesheet" href="CSS/productItem.css" type="text/css"/>
    <link rel="stylesheet" href="CSS/modal.css" type="text/css"/>
    <title>Suggestion Page</title>
</head>

<body>
    <c:set var="suggestionData" value="${requestScope.DATA}" />
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
            <form action="search" method="POST">
                <input class="input-search" type="text" placeholder="Đầm bé gái, áo bé trai,....vv" />
                <button type="submit" value="Tìm" />
            </form>
        </div>
    </div>
    <h1>Kết quả gợi ý</h1> 
    <div class="container">
        <div class="main">
            <c:if test="${not empty suggestionData}">
                <xm:parse var="resultSuggestion" xml="${suggestionData}" scope="request" />
                <c:if test="${not empty resultSuggestion}">
                    <xm:transform xml="${resultSuggestion}" xslt="${xslt}" />  
                </c:if>
            </c:if>
        </div>
    </div>
    <div id="myModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h1>Gợi ý</h1>
                <form method="POST" action="SuggestionServlet">
                    <p>Bạn hãy chọn giới tính của con mình</p>
                    <select name="sex">
                        <option value="1">Nam</option>
                        <option value="0">Nữ</option>
                    </select>
                    <p>Tuổi của con bạn là bao nhiêu ?(1 - 14)</p>
                    <select name="yearOld">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                        <option value="13">13</option>
                    </select>
                    <p>Bạn muốn chọn loại đồ nào ?</p>
                    <select name="type">
                        <option value="1">Áo</option>
                        <option value="2">Quần</option>
                        <option value="3">Combo</option>
                        <option value="4">Váy</option>
                    </select>
                    <p>Bạn mong muốn mức giá bao nhiêu ?</p>
                    <select name="desirePrice">
                        <option value="1">0-500k</option>
                        <option value="2">500k-1tr</option>
                        <option value="3">1tr-1tr6</option>
                    </select>
                    <p>Chọn mức độ ưu tiên theo: (Giá thành rẻ đã bao gồm khuyến mãi)</p>
                    <select name="suggestionOption">
                        <option value="1">Giá thành rẻ - Đánh gía tốt - Nhiều bình luận</option>
                        <option value="2">Đánh gía tốt - Giá thành rẻ - Nhiều bình luận</option>
                        <option value="3">Nhiều bình luận - Đánh giá tốt - Giá thành rẻ</option>
                    </select>
                    <input type="submit" value="Suggest" />
                </form>
        </div>
    </div>
    </body>
    <script type="text/javascript" src="JS/showModal.js"></script>
</html>