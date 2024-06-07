package uk.punk.gymb.domain.dto

import java.math.BigDecimal
import java.util.*

data class DebitOperation(
    val accountDebitID : UUID,
    val accountCreditID : UUID,
    val amount: BigDecimal
)
