package rewards

class Customer {

    String firstName
    String lastName
    String phone
    String email
    Integer totalPoints

    static hasMany = [awards:Award, orders:OnlineOrder]

    static constraints = {

        phone()
        firstName(nullable: true)
        lastName(nullable: true)
        email(nullable: true, email: true )
        totalPoints(nullable: true, max: 10)
    }
}
