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
}
