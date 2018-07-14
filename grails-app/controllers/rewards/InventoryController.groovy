package rewards

class InventoryController {

    def index() {
        render "Hello World"
    }

    def edit(){
        def productName = "Breakfast blend"
        def sku = "BB01"
        [product:productName, sku:sku]
    }

    def list(){
        def allProducts = Product.list()
        [allProducts: allProducts]
    }
}
