var orderItem = {}


function getProduct(data) {

    var productId = data.getAttribute("product_id")

    fetch(`/drinkandfood/${productId}`, method).then(async (req) => {
        const data = req.json();
        console.log(data);
    })


      
  }