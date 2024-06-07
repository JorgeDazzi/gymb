package uk.punk.gymb.adapter.mapper

import uk.punk.gymb.adapter.outbound.database.entity.AccountEntity
import uk.punk.gymb.domain.dto.Account
import uk.punk.gymb.domain.extension.runMapper

fun AccountEntity.toDomain() = runMapper {
    Account(
        id = this.id,
        balance = this.balance
    )
}

fun Account.toEntity() = runMapper {
    AccountEntity(
        id = this.id,
        balance = this.balance
    )
}