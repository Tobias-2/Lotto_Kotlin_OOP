package MainObjects

data class PersonalData (
    val name: String,
    val surname: String,
    val age: Int
) {
    init {
        require(age > 17) {"Nice try, grow up first."}
    }
}