package rewards

class Product {

    String name;
    String sku;
    float price;

    static hasMany = [orderItems:OrderItem]

    static constraints = {

    }
}
