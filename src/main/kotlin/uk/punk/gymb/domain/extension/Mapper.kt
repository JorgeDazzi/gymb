package uk.punk.gymb.domain.extension

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import uk.punk.gymb.domain.exception.MapperException


inline fun <reified V : Any> runMapper(instruction: () -> V): Result< V, Throwable > =
    try {
        Ok( instruction() )
    } catch ( ex : Throwable ){
        Err( MapperException("An issue occurred during mapper at object = ${V::class.simpleName}", ex) )
    }
