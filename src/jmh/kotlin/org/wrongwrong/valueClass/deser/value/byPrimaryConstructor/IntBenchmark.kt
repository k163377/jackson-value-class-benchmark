package org.wrongwrong.valueClass.deser.value.byPrimaryConstructor

import org.wrongwrong.valueClass.deser.value.ValueDeserBenchBase

open class IntBenchmark : ValueDeserBenchBase() {
    @JvmInline
    value class V(val v: Int)

    data class W(val v: V)

    override val valueClass: Class<*> = V::class.java
    override val wrappedClass: Class<*> = W::class.java
}
