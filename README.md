# jackson-value-class-benchmark
Benchmarks for serialization and deserialization of `value class`.

Benchmark items are as follows

- Deserialize
  - as value
    - by JsonCreator (with type conversion)
    - by primary constructor
  - as Map key
- Serialize
  - as value
    - by JsonValue (with type conversion)
    - by unbox
  - as Map key
    - by JsonKey
    - by unbox

The values that the measurement target wraps around are the specially optimized `Int` and the unoptimized `Short`.

These were selected in consideration of the internal implementation of `kogera 2.19.0-beta25` after the `MethodHandle` change.  
Since the amount of improvement increases in proportion to the number of parameters or properties that are `value class`,
the case of one parameter or one property is verified here as the minimum amount of improvement.
