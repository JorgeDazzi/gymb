package uk.punk.gymb.domain.dto

data class Statement(
    val account: Account,
    val transactions: List<Transaction>
) {
    fun getStatementComplete() =
        "Gimme Your Money Bank ;) \n" +
                "Account ID: ${this.account.id}\n" +
                "Account Balance: ${this.account.balance}\n" +
                "Transactions: \n" +
                this.transactions.map {
                    " ${if (it.operation == Operation.DEBIT)'-' else '+'} ${it.operationAmount}.......${it.targetAccount}\n"
                }
                    .toString()
                    .replace("[\\[\\],]".toRegex(), "")
                    .replace(" ", "")
}
