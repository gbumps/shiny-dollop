var textInputSearch = document.getElementById("search");
var timeout = null;
textInputSearch.addEventListener("keyup", (e) => {
    var spinner = document.createElement("div");
    spinner.className = "loader";
    document.getElementById("searchProduct").appendChild(spinner);
    if (e.keyCode == 13) {
        window.location.href = "SearchProductServlet?navigate=true&query=" + textInputSearch.value;
    }//Enter 
    if (textInputSearch.value) {
        clearTimeout(timeout);
        timeout = setTimeout(() => {
            console.log("search value: " + textInputSearch.value)
            searchProduct(textInputSearch.value);
        },700)
    }
})

function getXMLHTTPRequest() {
    var xmlHttp = null
    if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    } else {
        xmlHttp = new ActiveXObject();
    }
    return xmlHttp;
}

function searchProduct(strSearchValue) {
    //var strSearchValue = document.getElementById("search").value;
    if (strSearchValue != null) {
        var request = getXMLHTTPRequest();
        var header = getResponseHeader(request, getXMLResponse);
        request.open("POST", "SearchProductServlet", true);
        request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        request.send("query=" + strSearchValue + "&navigate=false");
        request.onreadystatechange = header;
    } else {
        document.getElementById("searchProduct").innerHTML = "";
    }

}

function getXMLResponse(xmlDoc) {
    document.getElementById("searchProduct").innerHTML = "";
    if (xmlDoc) {
        var parser = new DOMParser();
        var res    = parser.parseFromString(xmlDoc,"text/xml");
        var products =  res.documentElement.childNodes; //products
        for (var i = 0; i < products.length; i++) {
            var product = products[i].childNodes,
                id = product[0].childNodes[0].textContent,
                type = product[1].childNodes[0].textContent,
                name = product[2].childNodes[0].textContent, // Name tag
                p = document.createElement('p');
            p.className = "search-result-element"
            p.innerHTML = name;
            p.onclick = () => window.location.href = "ProductDetailServlet?id="+id+"&type="+type;
            document.getElementById("searchProduct").appendChild(p);
        }
    } else {
        var p = document.createElement('p');
        
        p.innerHTML = "Không tìm thấy sản phẩm"
        document.getElementById("searchProduct").appendChild(p);
    }
}

function getResponseHeader(request, response) {
    return function () {
        if (request.status == 200 && request.readyState == 4) {
            console.log("response text: " + request.responseText);
            response(request.responseText);
        }
    }
}
