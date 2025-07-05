package org.wrongwrong.valueClass.deser

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Level
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State
import org.wrongwrong.valueClass.mapper
import kotlin.random.Random

@State(Scope.Benchmark)
open class KeyBenchmark {
    @JvmInline
    value class I(val v: Int)

    @JvmInline
    value class S(val v: Short)

    val intRef = jacksonTypeRef<Map<I, Int>>()
    val shortRef = jacksonTypeRef<Map<S, Int>>()

    lateinit var json: String

    @Setup(Level.Trial)
    fun setUp() {
        json = """{"${Random.nextInt(10)}":0}"""
    }

    @Benchmark
    fun _int(): Map<I, Int> = mapper.readValue(json, intRef)

    @Benchmark
    fun _short(): Map<S, Int> = mapper.readValue(json, shortRef)
}
