package org.wrongwrong.valueClass.ser.value.unbox

import org.wrongwrong.valueClass.ser.value.ValueSerBenchBase

open class ShortBenchmark : ValueSerBenchBase<ShortBenchmark.V, ShortBenchmark.W>() {
    @JvmInline
    value class V(val v: Short)

    data class W(val v: V)

    override val initValue: (Int) -> V = { V(it.toShort()) }
    override val initWrapper: (V) -> W = ::W
}
