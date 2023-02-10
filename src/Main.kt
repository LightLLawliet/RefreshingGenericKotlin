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

interface Y<X : Any, B : X> {
    fun show(value: X): B
}

class StringSuperClass : Y<CharSequence, String> {
    override fun show(value: CharSequence): String = ""
}

data class ClassOne(private val a: String) {
    fun <T> show(interactor: Interaction<T>): T {
        return interactor.showString(a)
    }
}

data class ClassTwo(private val x: Int) : Interaction<Int> {
    override fun showString(a: String): Int {
        for (i in 0..x) {
            print(a)
        }
        return x
    }
}

class ClassThree : Interaction<Double> {
    override fun showString(a: String): Double {
        return 0.9
    }
}


interface Interaction<T> {
    fun showString(a: String): T
}

interface MyCustomObject {
    interface Mapper<T> {
        fun map(id: String): T

        class Show : Mapper<Unit> {
            override fun map(id: String) {
                print(id)
            }
        }

        class ToDomainObject : Mapper<DomainObject> {

        }
    }

    fun <T> make(mapper: Mapper<T>): T

    data class Base(private val id: String) : MyCustomObject {
        override fun <T> make(mapper: Mapper<T>): T {
            return mapper.map(id)
        }
    }
}

data class DomainObject(private val id: String, time: Long)

fun main() {
    val myCustomObject = MyCustomObject.Base("101")
    myCustomObject.make(MyCustomObject.Mapper.Show())
//    val datasource = DataSource()
//    datasource.save("txt", "value")
//    datasource.save("digit", 15)
//    val text: String = datasource.read("txt")
//    val int: Int = datasource.read("digit")
//    val oneClass = ClassOne("a")
//    val x: Int = oneClass.show(ClassTwo(1))
//    val y: Double = oneClass.show(ClassThree())
}
