/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

let doneTypingInteval = 2000;
let countTime;
let searchInput = document.getElementById("search");
searchInput.addEventListener('keyup', () => {
    clearTimeout(countTime);
    if (searchInput.value) {
        countTime = setTimeout(showProductSearch(searchInput.value),doneTypingInteval);
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

function showProductSearch(strSearchValue) {
    console.log("search: " ,strSearchValue);
    if (strSearchValue != null) {
        var request = getXMLHTTPRequest();
        var header = getResponseHeader(request, getXMLResponse);
        request.open("POST", "SearchProductServlet", true);
        request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        request.send("query=" + strSearchValue);
        request.onreadystatechange = header;
    } else {
        document.getElementById("searchProduct").innerHTML = "";
    }

}

function loadXSLDoc(filepath) {
    var xslHttp;
    if (window.ActiveXObject) {
        xslHttp = new ActiveXObject("Msxml2.XMLHTTP");
    } else {
        xslHttp = new XMLHttpRequest();
    }
    xslHttp.open("GET", filepath, false);
    try {
        xslHttp.responseType = "msxml-document"
    } catch (err) {
        console.log("err when loading XSL doc: ")
    }
    xslHttp.send("");
    return xslHttp.responseXML;
}

function getXMLResponse(xmlDoc) {
    //console.log("xmlDoc on client: " + JSON.stringify(xmlDoc))
    document.getElementById("searchProduct").innerHTML = "";
    var xsldoc = loadXSLDoc("XSLT/html/ResponsiveSearch.xsl");
    if (window.ActiveXObject || xsldoc.responseType == "msxml-document") {
        var e = xmlDoc.transformNode(xsldoc);
        document.getElementById("searchProduct").innerHTML = e;
    } else if (document.implementation && document.implementation.createDocument) {
        var xsltProcessor = new XSLTProcessor();
        xsltProcessor.importStylesheet(xsldoc);
        var res = xsltProcessor.transformToFragment(xmlDoc, document);
        console.log("res: " + res);
        document.getElementById("searchProduct").appendChild(res);
    }
}

function getResponseHeader(request, response) {
    return function () {
        if (request.status == 200 && request.readyState == 4) {
            response(request.responseXML);
        }
    }
}


