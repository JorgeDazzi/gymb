package uk.punk.gymb.domain.extension

import java.util.UUID

fun String.toUUID() = UUID.fromString(this)