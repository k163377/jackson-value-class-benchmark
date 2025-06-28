package org.wrongwrong.valueClass.ser.key.unbox

import org.wrongwrong.valueClass.ser.key.KeySerBenchBase

open class ShortBenchmark : KeySerBenchBase<ShortBenchmark.V>() {
    @JvmInline
    value class V(val v: Short)

    override val initValue: (Int) -> V = { V(it.toShort()) }
}
