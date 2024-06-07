package uk.punk.gymb.adapter.mapper

import com.github.michaelbull.result.Result
import uk.punk.gymb.adapter.outbound.database.entity.TransactionEntity
import uk.punk.gymb.domain.dto.Transaction
import uk.punk.gymb.domain.extension.runMapper
import java.util.*

fun TransactionEntity.toDomain() = runMapper {
    Transaction(
        id = this.id,
        balance = this.balance,
        operationAmount = this.operationAmount,
        accountID = this.accountID,
        targetAccount = this.targetAccount,
        operation = this.operation
    )
}

fun List<TransactionEntity>.toDomain(): Result<List<Transaction>, Throwable> = runMapper {
    this.map {
        Transaction(
            id = it.id,
            balance = it.balance,
            operationAmount = it.operationAmount,
            accountID = it.accountID,
            targetAccount = it.targetAccount,
            operation = it.operation
        )
    }.toList()

}

fun Transaction.toEntity() = runMapper {
    TransactionEntity(
        id = this.id ?: UUID.randomUUID(),
        balance = this.balance,
        operationAmount = this.operationAmount,
        accountID = this.accountID,
        targetAccount = this.targetAccount,
        operation = this.operation
    )
}
