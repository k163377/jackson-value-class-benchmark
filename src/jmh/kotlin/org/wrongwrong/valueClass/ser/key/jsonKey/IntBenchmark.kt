package org.wrongwrong.valueClass.ser.key.jsonKey

import com.fasterxml.jackson.annotation.JsonKey
import org.wrongwrong.valueClass.ser.key.KeySerBenchBase

open class IntBenchmark : KeySerBenchBase<IntBenchmark.V>() {
    @JvmInline
    value class V(val v: Int) {
        @JsonKey
        override fun toString(): String = v.toString()
    }

    override val initValue: (Int) -> V = ::V
}
