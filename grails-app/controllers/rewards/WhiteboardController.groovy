package rewards

class WhiteboardController {

    def calculationsService

    def index() { }

    def variables(){
        def myTotal = 1
        render("Total: $myTotal")
        render("<br/>"+ myTotal.class)

        def firstName ="kasiarakos"
        render("<br/> Name: $firstName")
        render("<br/> "+firstName.class)


        def today = new Date("2/1/2018")
        render("<br/> Today is: $today")
        render("<br/> "+today.class)

    }

    def condition(){
        def welcomeMessage = calculationsService.welcome(params)
        render(welcomeMessage)
    }
}
