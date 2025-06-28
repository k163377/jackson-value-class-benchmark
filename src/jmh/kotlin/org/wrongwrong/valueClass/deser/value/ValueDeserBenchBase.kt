package org.wrongwrong.valueClass.deser.value

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Level
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State
import org.wrongwrong.valueClass.mapper
import kotlin.random.Random

@State(Scope.Benchmark)
abstract class ValueDeserBenchBase {
    protected abstract val valueClass: Class<*>
    // If value classes in argument of the constructor,
    // there are at least two reflection calls, one for boxing and one for unboxing.
    protected abstract val wrappedClass: Class<*>

    lateinit var valueStr: String
        private set
    lateinit var json: String
        private set

    @Setup(Level.Trial)
    fun setUp() {
        // To minimize JSON processing time, the value should be of length 1
        valueStr = Random.nextInt(10).toString()
        json = """{"v":$valueStr}"""
    }

    // When returning a value class from a benchmark function, care must be taken to avoid unboxing.
    // This is the reason for setting the benchmark return value as Any.
    @Benchmark
    fun direct(): Any = mapper.readValue(valueStr, valueClass)
    @Benchmark
    fun wrapped(): Any = mapper.readValue(json, wrappedClass)
}
