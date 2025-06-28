package org.wrongwrong.valueClass.ser.value.jsonValue

import com.fasterxml.jackson.annotation.JsonValue
import org.wrongwrong.valueClass.ser.value.ValueSerBenchBase

open class IntBenchmark : ValueSerBenchBase<IntBenchmark.V, IntBenchmark.W>() {
    @JvmInline
    value class V(val v: Int) {
        @JsonValue
        override fun toString(): String = v.toString()
    }

    data class W(val v: V)

    override val initValue: (Int) -> V = ::V
    override val initWrapper: (V) -> W = ::W
}