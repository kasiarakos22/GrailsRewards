package rewards

class BootStrap {

    def init = { servletContext ->
        new Product(name: "Morning Blend", sku: "MO1", Price: 24.34).save()
        new Product(name: "Kasiarakos Blend", sku: "MO1", Price: 24.34).save()
        new Product(name: "Sofias Blend", sku: "MO1", Price: 24.34).save()
    }
    def destroy = {
    }
}
