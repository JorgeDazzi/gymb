package uk.punk.gymb.application.port.inbound

import com.github.michaelbull.result.Result
import uk.punk.gymb.domain.dto.Account
import uk.punk.gymb.domain.dto.DebitOperation
import java.math.BigDecimal
import java.util.*

interface BankOperationPlatformPortInbound  {

//    fun getBankStatement( id : UUID ) : Result< Any, Throwable >
    fun debit( debitOperation: DebitOperation ) : Result< Account, Throwable >
    fun credit( debitAccountID : UUID, creditAccountID : UUID, amount : BigDecimal) : Result< Account, Throwable >

}