package org.wrongwrong.valueClass.ser.key.jsonKey

import com.fasterxml.jackson.annotation.JsonKey
import org.wrongwrong.valueClass.ser.key.KeySerBenchBase

open class ShortBenchmark : KeySerBenchBase<ShortBenchmark.V>() {
    @JvmInline
    value class V(val v: Short) {
        @JsonKey
        override fun toString(): String = v.toString()
    }

    override val initValue: (Int) -> V = { V(it.toShort()) }
}
