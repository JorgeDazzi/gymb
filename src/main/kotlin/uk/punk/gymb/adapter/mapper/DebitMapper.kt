package uk.punk.gymb.adapter.mapper

import uk.punk.gymb.adapter.inbound.rest.dto.request.DebitOperationRequest
import uk.punk.gymb.domain.dto.DebitOperation
import uk.punk.gymb.domain.extension.runMapper

fun DebitOperationRequest.toDomain() = runMapper {
    DebitOperation(
        accountDebitID = this.accountDebitID,
        accountCreditID = this.accountCreditID,
        amount = this.amount
    )
}