package org.hanuna.util


public fun <T: Any> T.check(predicate: (T) -> Boolean): T? = if (predicate(this)) this else null
