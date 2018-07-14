package rewards

class Customer {

    String firstName
    String lastName
    String phone
    String email
    Integer totalPoints

    static hasMany = [awards:Award, orders:OnlineOrder]

    static constraints = {
    }
}
