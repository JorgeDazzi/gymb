package uk.punk.gymb.application.mapper

import uk.punk.gymb.domain.dto.Account
import uk.punk.gymb.domain.dto.Operation
import uk.punk.gymb.domain.dto.Transaction
import uk.punk.gymb.domain.extension.runMapper
import java.math.BigDecimal
import java.util.UUID

fun Account.toTransaction(opt : Operation, amount : BigDecimal, targetAccount: UUID) = runMapper {
    Transaction(
        balance = this.balance,
        operationAmount = amount,
        accountID = this.id,
        targetAccount = targetAccount,
        operation = opt
    )
}