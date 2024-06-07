package uk.punk.gymb.domain.exception

class StatementNotFoundException(message: String, cause: Throwable) : Throwable(message, cause)
class StatementSaveException(message: String, cause: Throwable) : Throwable(message, cause)