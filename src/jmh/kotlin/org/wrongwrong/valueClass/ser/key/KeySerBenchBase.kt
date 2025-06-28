package org.wrongwrong.valueClass.ser.key

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Level
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State
import org.wrongwrong.valueClass.mapper
import kotlin.random.Random

@State(Scope.Benchmark)
abstract class KeySerBenchBase<V : Any> {
    lateinit var v: Map<V, Int>
        private set

    abstract val initValue: (Int) -> V

    @Setup(Level.Trial)
    fun setUp() {
        v = mapOf(initValue(Random.nextInt(10)) to 0)
    }

    @Benchmark
    fun benchmark(): String = mapper.writeValueAsString(v)
}
