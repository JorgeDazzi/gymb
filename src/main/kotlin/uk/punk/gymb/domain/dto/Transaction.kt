package uk.punk.gymb.domain.dto

import java.math.BigDecimal
import java.util.*


data class Transaction(

    var id: UUID? = null,
    val balance: BigDecimal,
    val operationAmount: BigDecimal,
    val accountID: UUID,
    val targetAccount: UUID?,
    val operation: Operation

)


