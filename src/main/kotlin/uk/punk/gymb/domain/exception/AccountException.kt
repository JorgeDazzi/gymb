package uk.punk.gymb.domain.exception

class AccountFinderException(message: String, cause: Throwable) : Throwable(message, cause)

class AccountCommitException(message: String, cause: Throwable) : Throwable(message, cause)

class AccountNoAvailableBalanceException(message: String) : Throwable(message)

class AccountCreditIllegalOperationException(message: String) : Throwable(message)