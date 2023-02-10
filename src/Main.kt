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
            override fun map(id: String): DomainObject {
                return DomainObject(id, System.currentTimeMillis())
            }

        }
    }

    fun <T> make(mapper: Mapper<T>): T

    data class Base(private val id: String) : MyCustomObject {
        override fun <T> make(mapper: Mapper<T>): T {
            return mapper.map(id)
        }
    }
}

data class DomainObject(private val id: String, private val time: Long)

fun main() {
    val myCustomObject = MyCustomObject.Base("101")
    myCustomObject.make(MyCustomObject.Mapper.Show())
    val domainObject = myCustomObject.make(MyCustomObject.Mapper.ToDomainObject())
    print(domainObject.toString())
}
