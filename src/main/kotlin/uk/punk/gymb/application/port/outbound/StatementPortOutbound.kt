package uk.punk.gymb.application.port.outbound

import com.github.michaelbull.result.Result
import uk.punk.gymb.domain.dto.Transaction
import java.util.*

interface StatementPortOutbound  {

    fun findByAccountId( id : UUID ) : Result<List<Transaction>, Throwable>
    fun save(transaction: Transaction ) : Result<Transaction, Throwable>

}