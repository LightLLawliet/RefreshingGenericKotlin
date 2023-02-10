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
