data class Student(
    var firstName: String = "",
    var last_name: String = "",
    var year_of_birthday: String = "",
    var group_name: String = "",
    var course: Int = 1,
    var marksList: MutableList<Int> = mutableListOf()

    )
