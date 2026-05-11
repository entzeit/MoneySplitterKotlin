class DebtManager {
    val debts: MutableMap<String, Debt> = mutableMapOf()
    fun calculateDebts(bills: List<Bill>) {
        bills.forEach { bill ->
            debts.putIfAbsent(bill.payer, Debt(bill.payer, 0))
            bill.debtors.forEach { debtor ->
                debts.putIfAbsent( debtor, Debt(debtor, 0))
            }
        }
        bills.forEach { bill ->
            val debt: Int = (bill.amount / bill.debtors.size) * bill.debtors.size
            debts.compute(bill.payer) { _, value ->
                Debt(value!!.person, value.amount + debt)
            }
            bill.debtors.forEach { debtor ->
                val debtProportion = bill.amount / bill.debtors.size
                debts.compute(debtor) { _, value ->
                    Debt(debtor, value!!.amount - debtProportion)
                }
            }
        }
    }
}