package rewards

import grails.gorm.transactions.Transactional

@Transactional
class CalculationsService {

    def welcome(params) {

        def firstName = params.id
        def totalPoints = params.points?.toInteger()
        def welcomeMessage = ""

        switch (totalPoints){
            case 5:
                welcomeMessage ="welcome Back $firstName. This drink is on us"
                break
            case 4:
                welcomeMessage = "Welcome Back $firstName. Your next drink is free"
                break
            case 2..3:
                welcomeMessage = "Welcome Back $firstName. you now have $totalPoints points"
                break
            default:
                welcomeMessage ="Wolcome $firstName. Thank you for registring"
        }

        return welcomeMessage

    }

    def createMessage(totalAwards, firstName) {

        def totalPoints = totalAwards
        def welcomeMessage = ""

        switch (totalPoints){
            case 5:
                welcomeMessage ="welcome Back $firstName. This drink is on us"
                break
            case 4:
                welcomeMessage = "Welcome Back $firstName. Your next drink is free"
                break
            case 1..3:
                welcomeMessage = "Welcome Back $firstName. you now have ${totalPoints +1} points"
                break
            default:
                welcomeMessage ="Wolcome $firstName. Thank you for registring"
        }

        return welcomeMessage
    }

    def getTotalPoints(customer){
        def totalAwards = 0;
        customer.awards.each{
            totalAwards += it.points
        }
        customer.totalPoints = totalAwards;
        return customer
    }

    def processCheckIn(Customer lookupInstance){
        def customer = Customer.findByPhone(lookupInstance.phone)

        if(customer == null){
            customer = lookupInstance
            customer.firstName = "Customer"
        }
        customer = getTotalPoints(customer)

        def welcomeMessage = createMessage(customer.totalPoints, customer.firstName)

        if(customer.totalPoints < 5){
            customer.addToAwards(new Award(awardDate: new Date(), type: "Purchase", points: 1))
        }else{
            customer.addToAwards(new Award(awardDate: new Date(), type: "Reward", points: -5))
        }
        customer.save()

        return [customer, welcomeMessage]
    }
}
