var orderItem = {}


function getProduct(data) {

    var productId = data.getAttribute("product_id")

        fetch(`/drinkandfood/${productId}`).then(async (req) => {
            const data = req.json();
            console.log(data);
        })


        // fetch(`/products/${productId}`)
        // .then(response => response.json())
        // .then(data => {
  
        //   var product = {
        //       id: data.id,
        //       name: data.name,
        //       description: data.description,
  
        //       imageUrl: data.imageUrl,
        //       lastOrder: data.lastOrder
        //   }
        //   console.log(data);
        //   console.log(product);
        //   // Perform further operations with the product data
        // })
        // .catch(error => {
        //   // Handle any errors
        //   console.error(error);
        // });
  

      
  }