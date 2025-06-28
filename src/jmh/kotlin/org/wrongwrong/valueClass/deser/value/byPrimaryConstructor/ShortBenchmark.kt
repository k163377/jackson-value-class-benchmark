package org.wrongwrong.valueClass.deser.value.byPrimaryConstructor

import org.wrongwrong.valueClass.deser.value.ValueDeserBenchBase

open class ShortBenchmark : ValueDeserBenchBase() {
    @JvmInline
    value class V(val v: Short)

    data class W(val v: V)

    override val valueClass: Class<*> = V::class.java
    override val wrappedClass: Class<*> = W::class.java
}
