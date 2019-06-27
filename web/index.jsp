<%-- 
    Document   : index
    Created on : May 24, 2019, 11:57:42 AM
    Author     : stephen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crawl Page</title>
        <script> 
            function changeButton() {
                var btn = document.getElementById("btnCrawl"); 
                btn.style.display = "none";  
                document.getElementById("craw_status").style.display = "block"; 
                if (document.getElementById("craw_result").style.display === "block") {
                    document.getElementById("craw_result").style.display = "none";
                }
            }
        </script>
    </head>
    <body>
        <h1>Welcome to crawler page</h1>
        <h2>Press the crawl button below to continue !</h2>
        
        <form action="CrawlServlet" method="POST">
            <input id="btnCrawl" type="submit" value="Crawl" onclick="changeButton()"/>
        </form>
        <form action="HomePageServlet" method="POST">
            <input type="submit" value="Go to Homepage"/>
        </form>
        <p id="craw_result">${requestScope.CRAWL_STATUS}</p>
        <p id="craw_status" style="display: none">Crawling, please wait....</p>
    </body>
</html>
