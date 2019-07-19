var filterSelection = document.getElementById("priceRange");
filterSelection.addEventListener("change", () => {
    console.log("select: " + filterSelection.value)
    productFilter(filterSelection.value);
})

function productFilter(option) {
    var op = parseInt(option)
    for(var i = 0; i < productList.length; i++) {
        var price = productList[i].childNodes[5].childNodes[3].childNodes[0].data;
        price = price.split(" ");
        price = parseInt(price[0].split('.').join('/'));
        switch(op) {
            case 1: 
                if (price < 500000) {
                    productList[i].style.display = ''
                } else {
                    productList[i].style.display = 'none'
                }
                break;
            case 2: 
                if (price >= 500000 && price < 1000000) {
                    productList[i].style.display = ''
                    console.log("style: " + productList[i])
                } else {
                    productList[i].style.display = 'none'
                }
                break;
            case 3: 
                if (price >= 1000000) {
                    productList[i].style.display = ''
                    console.log("style: " + productList[i])
                } else {
                    productList[i].style.display = 'none'
                }
                break;
        }
    }
}