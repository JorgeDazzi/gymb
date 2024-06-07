package uk.punk.gymb.adapter.outbound.database.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import uk.punk.gymb.adapter.outbound.database.entity.TransactionEntity
import java.util.UUID

@Repository
interface StatementRepository : JpaRepository<TransactionEntity, UUID > {

    fun findByAccountID(id : UUID) : List<TransactionEntity>

}