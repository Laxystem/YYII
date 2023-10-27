package quest.laxla.yyii.internal

internal class NotImplementedByMixinError(message: String) : Error(message)

@Suppress("FunctionName")
internal fun MIXIN(message: String? = null): Nothing =
    throw NotImplementedByMixinError("Missing mixin implementation" + if (message.isNullOrBlank()) "" else ": $message")
