fun String.toBill(): Bill {
    val parts = this.split(" ")
    val payer = parts[0].lowercase()
    val amount = parts[1].toCents()
    val debtors = parts[2].split(",").map { it.trim().lowercase() }
    return Bill(payer, amount, debtors)
}

private fun String.toCents(): Int {
    val (euros, centsPart) = this.split(",").let { it[0] to it.getOrNull(1) }
    val cents = when {
        centsPart == null -> 0
        centsPart.length == 1 -> centsPart.toInt() * 10
        else -> centsPart.toInt()
    }
    return euros.toInt() * 100 + cents
}
