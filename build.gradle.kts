import me.champeau.jmh.JmhParameters
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

plugins {
    kotlin("jvm") version "2.0.21"
    id("me.champeau.jmh") version "0.7.3"
}

group = "org.wrongwrong"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { setUrl("https://jitpack.io") }
    maven { setUrl("https://oss.sonatype.org/content/repositories/snapshots") }
}

val isCi: Boolean = System.getenv().containsKey("CI") // True when executed in GitHub Actions
val ciFileName: String? = project.properties["fileName"] as String?

val target = (project.properties["mapper"] as? String) ?: "2.20.0-d81fb82"
val isSingleShot: Boolean = project.properties["isSingleShot"]?.let { (it as String).toBoolean() } ?: false

dependencies {
    jmhImplementation(kotlin("reflect"))

    // jmhImplementation("com.github.ProjectMapK:jackson-module-kogera:$target")
    jmhImplementation("com.fasterxml.jackson.core:jackson-databind:2.20.0-SNAPSHOT")
    jmhImplementation(files("./jars/jackson-module-kotlin-$target.jar"))

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(8)
}

fun JmhParameters.setThrptDetails() {
    if (isCi) {
        // For CI, the setting is focused on score stability.
        warmupForks = 2
        warmupBatchSize = 3
        warmupIterations = 3
        warmup = "1500ms"

        fork = 3
        batchSize = 3
        iterations = 3
        timeOnIteration = "1500ms"
    } else {
        // For local, the setting that produces the minimum stable score experienced on the author's PC.
        warmupForks = 2
        warmupBatchSize = 3
        warmupIterations = 3
        warmup = "1s"

        fork = 2
        batchSize = 3
        iterations = 2
        timeOnIteration = "1500ms"
    }
}

jmh {
    val mode: String

    if (isSingleShot) {
        mode = "ss"
        timeUnit = "ms"

        forceGC = true

        // For CI, the setting is focused on score stability.
        fork = if (isCi) {
            10
        } else {
            5
        }
    } else {
        mode = "thrpt"
        forceGC = false

        setThrptDetails()
    }
    benchmarkMode = listOf(mode)

    failOnError = true
    includeTests = false

    resultFormat = "CSV"

    val name = if (isCi) {
        ciFileName!!
    } else {
        val dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss").format(LocalDateTime.now())
        val targetDependency = target
        listOf(dateTime, targetDependency, mode).joinToString(separator = "_")
    }

    val outputDir = if (isCi) "${project.rootDir}/tmp" else "${project.rootDir}/jmh-reports"
    resultsFile = project.file("${outputDir}/${name}.csv")
    humanOutputFile = if (isCi) null else project.file("${outputDir}/${name}.txt")

    // Output options when jmh task
    if (project.gradle.startParameter.taskNames.contains("jmh")) {
        val props = this::class.java.methods
            .filter {
                it.returnType.run { this == Property::class.java || this == ListProperty::class.java } &&
                        it.parameterCount == 0
            }
            .associate {
                // All property names begin with "get" and are formatted with substring(3).
                it.name.substring(3) to when (val value = it.invoke(this)) {
                    is Property<*> -> value.orNull
                    is ListProperty<*> -> value.orNull
                    else -> throw IllegalStateException("${value::class.java} is not Property")
                }
            }

        logger.log(LogLevel.LIFECYCLE, "> JMH props:\n$props")
    }
}
