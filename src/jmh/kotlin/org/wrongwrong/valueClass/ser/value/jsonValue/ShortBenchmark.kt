package org.wrongwrong.valueClass.ser.value.jsonValue

import com.fasterxml.jackson.annotation.JsonValue
import org.wrongwrong.valueClass.ser.value.ValueSerBenchBase

open class ShortBenchmark : ValueSerBenchBase<ShortBenchmark.V, ShortBenchmark.W>() {
    @JvmInline
    value class V(val v: Short) {
        @JsonValue
        override fun toString(): String = v.toString()
    }

    data class W(val v: V)

    override val initValue: (Int) -> V = { V(it.toShort()) }
    override val initWrapper: (V) -> W = ::W
}