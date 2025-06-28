package org.wrongwrong.valueClass.deser.value.byJsonCreator

import com.fasterxml.jackson.annotation.JsonCreator
import org.wrongwrong.valueClass.deser.value.ValueDeserBenchBase

open class ShortBenchmark : ValueDeserBenchBase() {
    @JvmInline
    value class V(val v: Short) {
        @JsonCreator constructor(s: String) : this(s.toShort())
    }

    data class W(val v: V)

    override val valueClass: Class<*> = V::class.java
    override val wrappedClass: Class<*> = W::class.java
}
