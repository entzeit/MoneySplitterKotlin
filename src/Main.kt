fun main(args: Array<String>) {
    if (args.size != 1) {
        println("Usage: java Splitter <file.txt>")
        return
    }
    val billManager = BillManager.fromFile(args[0])
    billManager.printBills()
    val debtManager = DebtManager()
    debtManager.calculateDebts(billManager.bills)
    val transactionManager = TransactionManager(debtManager)
    transactionManager.calculateTransactions()
    println(transactionManager)
}