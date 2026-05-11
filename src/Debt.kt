data class Debt(val person: String, var amount: Int) {
    override fun toString(): String {
        return "$person\t$amount"
    }
}