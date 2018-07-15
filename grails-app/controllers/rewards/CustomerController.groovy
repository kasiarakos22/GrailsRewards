package rewards

class CustomerController {
    static  scaffold = Customer

    def checkin(){

    }

    def lookup(){
        def customerInstance = Customer.list(sort: "lastName", order: "desc", max: 5, offset: 0)
        [customerInstanceList: customerInstance]
    }


}
