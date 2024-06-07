package uk.punk.gymb.adapter.outbound.database

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.andThen
import com.github.michaelbull.result.map
import com.github.michaelbull.result.mapError
import org.springframework.stereotype.Component
import uk.punk.gymb.adapter.mapper.toDomain
import uk.punk.gymb.adapter.mapper.toEntity
import uk.punk.gymb.adapter.outbound.database.repository.StatementRepository
import uk.punk.gymb.application.port.outbound.StatementPortOutbound
import uk.punk.gymb.domain.dto.Transaction
import uk.punk.gymb.domain.exception.StatementNotFoundException
import uk.punk.gymb.domain.exception.StatementSaveException
import java.util.*

@Component
class StatementComponent (
    private val statementRepository: StatementRepository
) : StatementPortOutbound {

    override fun findByAccountId(id: UUID): Result<List<Transaction>, Throwable> =
        statementRepository.findByAccountID(id)
            .toDomain()
            .mapError {
                StatementNotFoundException( "Could not find account id $id", it )
            }

    override fun save(transaction: Transaction): Result<Transaction, Throwable> =
        transaction
            .toEntity()
            .map { statementRepository.save(it) }
            .andThen { it.toDomain() }
            .mapError {
                StatementSaveException( "An issue occurred during save/update operation", it )
            }


}