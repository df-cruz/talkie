package com.dfcruz.talkie.util

import kotlinx.coroutines.CancellationException

sealed class Either<out A, out B> {

    abstract val isLeft: Boolean
    abstract val isRight: Boolean

    data class Left<out A>(val value: A) : Either<A, Nothing>() {
        override val isLeft
            get() = true
        override val isRight
            get() = false

        override fun toString(): String = "Either.Left($value)"

        companion object {
            operator fun <A> invoke(a: A): Either<A, Nothing> = Left(a)
        }
    }

    data class Right<out B>(val value: B) : Either<Nothing, B>() {
        override val isLeft
            get() = false
        override val isRight
            get() = true

        override fun toString(): String = "Either.Right($value)"

        companion object {
            operator fun <B> invoke(b: B): Either<Nothing, B> = Right(b)
        }
    }

    inline fun <C> fold(ifLeft: (A) -> C, ifRight: (B) -> C): C = when (this) {
        is Right -> ifRight(value)
        is Left -> ifLeft(value)
    }

    fun orNull(): B? = fold({ null }, { it })

    fun leftOrNull(): A? = fold({ it }, { null })

    companion object {
        inline fun <R> catch(f: () -> R): Either<Throwable, R> =
            try {
                f().right()
            } catch (t: Throwable) {
                t.nonFatalOrThrow().left()
            }
    }

}

fun <A> A.left(): Either<A, Nothing> = Either.Left(this)

fun <A> A.right(): Either<Nothing, A> = Either.Right(this)

fun Throwable.nonFatalOrThrow(): Throwable =
    if (isNonFatal(this)) this else throw this

private fun isNonFatal(t: Throwable): Boolean =
    when (t) {
        is VirtualMachineError, is ThreadDeath, is InterruptedException, is LinkageError, is CancellationException -> false
        else -> true
    }