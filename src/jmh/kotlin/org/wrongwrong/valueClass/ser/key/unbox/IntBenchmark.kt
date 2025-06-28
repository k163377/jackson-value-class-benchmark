package org.wrongwrong.valueClass.ser.key.unbox

import org.wrongwrong.valueClass.ser.key.KeySerBenchBase

open class IntBenchmark : KeySerBenchBase<IntBenchmark.V>() {
    @JvmInline
    value class V(val v: Int)

    override val initValue: (Int) -> V = ::V
}
