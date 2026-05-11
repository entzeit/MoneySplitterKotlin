import kotlin.math.absoluteValue

class TransactionManager(val debtManager: DebtManager) {
    //todo: Injection? CDI?
    var transactions = mutableListOf<Transaction>()

    fun calculateTransactions() {
        while (!debtManager.debts.isEmpty()) {
            println(debtManager.debts.size)
            Thread.sleep(100)
            val min: Debt = debtManager.debts.values.minBy { it.amount }
            val max: Debt = debtManager.debts.values.maxBy { it.amount }
            val amount = calculateTransactionAmount(min.amount, max.amount)
            val transaction = Transaction(min.person, max.person, amount)
            transactions.add(transaction)
            updateDebts(transaction)
        }
    }

    private fun calculateTransactionAmount(min: Int, max: Int) : Int {
        val sum = min + max
        if (sum >= 0) {
            return min.absoluteValue
        } else {
            return max
        }
    }

    private fun updateDebts(transaction: Transaction) {
        val from = debtManager.debts[transaction.from]!!
        var amount = from.amount + transaction.amount
        from.amount = amount
        if (amount == 0) {debtManager.debts.remove(from.person)}
        val to = debtManager.debts[transaction.to]!!
        amount = to.amount - transaction.amount
        to.amount = amount
        if (amount == 0) {debtManager.debts.remove(to.person)}
    }

    override fun toString(): String =
        transactions.joinToString("\n") { transaction ->
            "${transaction.from} -> ${transaction.to}: ${toEuro(transaction.amount)}"
        }

    private fun toEuro(amount: Int) : String {
        val euro: Int = amount / 100
        val cents: Int = amount - euro * 100
        var centString = ""
        if (cents < 10) { centString = "0" }
        return "${euro},${centString}${cents}"
    }
}