package uk.punk.gymb.adapter.outbound.database.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import uk.punk.gymb.domain.dto.Operation
import java.math.BigDecimal
import java.util.*


@Entity
@Table(name = "statement")
data class TransactionEntity(

    @Id
    @Column(columnDefinition = "uuid")
    val id: UUID,
    val balance: BigDecimal,
    @Column(name = "operation_amount") val operationAmount: BigDecimal,
    @Column(name = "account_id", columnDefinition = "uuid") val accountID: UUID,
    @Column(name = "target_account", columnDefinition = "uuid") val targetAccount: UUID?,
    val operation: Operation

)

