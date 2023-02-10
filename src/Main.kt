import sun.jvm.hotspot.oops.CellTypeState.value

interface Save<T : Any> {
    fun save(key: String, value: T)
}

interface Read {
    fun <T> read(key: String): T
}

class SaveInt : Save<Int> {
    override fun save(key: String, value: Int) {
        TODO("Not yet implemented")
    }
}

class X(private val saveInt: Save<Int>) : Save<String> {

    override fun save(key: String, value: String) {
        saveInt.save(key, value.length)
    }
}

class DataSource : Read, Save<Any> {

    private val map = mutableMapOf<String, Any>()

    override fun save(key: String, value: Any) {
        map[key] = value
    }

    override fun <T> read(key: String): T {
        return map[key] as T
    }
}

fun main() {
    val datasource = DataSource()
    datasource.save("txt", "value")
    datasource.save("digit", 15)
    val text: String = datasource.read("txt")
    val int: Int = datasource.read("digit")
}

