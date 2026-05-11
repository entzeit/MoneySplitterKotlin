data class Bill(
    val payer: String,
    val amount: Int,          // in cents
    val debtors: List<String>
) {
    override fun toString(): String {
        return "Payer: $payer\tAmount: $amount\tDebtors: $debtors"
    }
}
