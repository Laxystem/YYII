package quest.laxla.yyii

import org.quiltmc.loader.api.ModInternal
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@ModInternal
object Common {
    const val NAMESPACE = "yyii"
    val logger: Logger = LoggerFactory.getLogger(NAMESPACE.uppercase())
}
