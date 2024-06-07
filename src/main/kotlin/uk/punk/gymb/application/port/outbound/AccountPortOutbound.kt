package uk.punk.gymb.application.port.outbound

import com.github.michaelbull.result.Result
import uk.punk.gymb.domain.dto.Account
import java.util.*

interface AccountPortOutbound  {

    fun findById( id : UUID ) : Result<Account, Throwable>

    fun updateAccount(account: Account) : Result<Account, Throwable>

}