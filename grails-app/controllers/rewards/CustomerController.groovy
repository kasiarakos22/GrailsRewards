package rewards

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK

@Transactional
class CustomerController {


    static scaffold = Customer
    CalculationsService calculationsService;

    def checkin() {
    }

    def lookup() {

        //def customerInstance = Customer.findByPhone(params.id)
        //def customerInstance = Customer.findAllByTotalPoints(5, [sort: "lastName"])
        //def customerInstance = Customer.findAllByLastNameLike("B%", [sort: "lastName" , order: "desc"]) /*case Sensitive*/
        //def customerInstance = Customer.findAllByLastNameILike("B%", [sort: "lastName" , order: "desc"]) /*case Insensitive*/
        def customerInstance = Customer.findAllByTotalPointsGreaterThan(3, [sort: "lastName"])


        [customerInstanceList: customerInstance]
    }

    def index() {
        params.max = 10
        respond Customer.list(params), model: [customerCount: Customer.count()]
    }

    def create() {
        respond new Customer()
    }

    def save(Customer customer) {
        if (customer == null) {
            notFound()
            return
        }
        try {
            customer.save()
        } catch (ValidationException e) {
            respond customer.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(default: 'Customer'), customer.id])
                redirect customer
            }
            '*' { respond customer, [status: CREATED] }
        }
    }

    def update(Long id) {
        def customer = Customer.get(id)
        customer.properties = params
        customer.save(flush: true, failOnError: true)
        redirect(action: "show", id: id)
    }

    def edit(Long id) {
        respond Customer.get(id)
    }

    def show(Long id) {
         def customer = Customer.get(id)
        customer = calculationsService.getTotalPoints(customer)
        respond customer
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(default: 'Customer'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
    def delete(Long id){
        def customer = Customer.get(id)
        customer.delete()
        redirect(action: "index")
    }
}
