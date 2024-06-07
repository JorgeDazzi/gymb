package uk.punk.gymb.domain.dto

import uk.punk.gymb.domain.exception.AccountCreditIllegalOperationException
import uk.punk.gymb.domain.exception.AccountNoAvailableBalanceException
import java.math.BigDecimal
import java.util.*


data class Account(
    val id: UUID,
    var balance: BigDecimal
) {

    fun availableBalance( amount: BigDecimal ) : Boolean =
        this.balance.minus( amount ) >= BigDecimal.ZERO


    fun negativeValue( amount: BigDecimal ) : Boolean =
        amount >= BigDecimal.ZERO

    fun debit( amount: BigDecimal ) : Account =
        if( availableBalance(amount) ){
            this.balance = this.balance.minus(amount)
            this
        } else {
            throw AccountNoAvailableBalanceException("No Available balance, current balance is ${this.balance}")
        }

    fun credit( amount: BigDecimal ) : Account =
        if( negativeValue(amount) ){
            this.balance = this.balance.plus(amount)
            this
        } else {
            throw AccountCreditIllegalOperationException("Negatives values is not allowed in credit operation [$amount]")
        }

}
