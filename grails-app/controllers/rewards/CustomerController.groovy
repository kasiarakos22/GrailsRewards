package rewards

class CustomerController {
    static  scaffold = Customer

    def checkin(){

    }

    def lookup(){

        def customerInstance = Customer.findByPhone(params.id)
        //def customerInstance = Customer.findAllByTotalPoints(5, [sort: "lastName"])
        [customerInstanceList: customerInstance]
    }


}
