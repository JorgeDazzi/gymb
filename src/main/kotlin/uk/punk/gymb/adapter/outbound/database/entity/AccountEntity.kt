package uk.punk.gymb.adapter.outbound.database.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.util.UUID


@Entity
@Table(name = "account")
data class AccountEntity(

    @Id
    @Column(columnDefinition = "uuid")
    val id: UUID,
    val balance: BigDecimal

)
