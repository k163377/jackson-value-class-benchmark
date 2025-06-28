package org.wrongwrong.valueClass.ser.value

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Level
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State
import org.wrongwrong.valueClass.mapper
import kotlin.random.Random

@State(Scope.Benchmark)
abstract class ValueSerBenchBase<V : Any, W : Any> {
    lateinit var v: V
        private set
    lateinit var w: W
        private set

    abstract val initValue: (Int) -> V
    abstract val initWrapper: (V) -> W

    @Setup(Level.Trial)
    fun setUp() {
        v = initValue(Random.nextInt(10))
        w = initWrapper(v)
    }

    @Benchmark
    fun direct(): String = mapper.writeValueAsString(v)
    @Benchmark
    fun wrapped(): String = mapper.writeValueAsString(w)
}
