fun main() {
    val list = createList()

    sorted(list)

    var map = FindBestStudent(list)

    averageMarks(map)

    MinMaxStudent(list)

}

private fun createList(): MutableList<Student> {
    val list = mutableListOf<Student>()

    list.add(Student("Ivan", "Ivanov", "2001", "PINZH-20", 2, mutableListOf(6, 4, 8, 10, 5)))
    list.add(Student("Avan", "Ivanov", "2001", "PINZH-20", 2, mutableListOf(6, 4, 8, 10, 5)))
    list.add(Student("Sergey", "Sergeevich", "2001", "PINZH-20", 2, mutableListOf(8, 8, 6, 6, 8)))
    list.add(Student("Ivan", "Ivanov", "2002", "PINZH-19", 3, mutableListOf(8, 3, 7, 6, 4)))
    return list
}

private fun sorted(list: MutableList<Student>) {
    println()
    println("Сортировка по курсу, потом по имени, потом по фамилли")

    list.sortedWith(compareBy(Student::course, Student::firstName, Student::last_name)).forEach { println(it) }
}

private fun FindBestStudent(list: MutableList<Student>): Map<String, List<Student>> {
    println()
    println("поиск лучшего студента по успеваемости")
    println()


    var map = list.groupBy(Student::group_name)

    for (group in map) {
        group.value.stream().max(compareBy { it -> it.marksList.average() })
            .ifPresent { println("Group: ${group.key} best student : $it") }
    }
    return map
}

private fun averageMarks(map: Map<String, List<Student>>) {
    println()
    println("Среднее значение всех оценок в группе")
    println()

    for (group in map) {
        println("${group.key} среднее значение всех оценок " + group.value.stream().map { it.marksList.average() }
            .toList().average())
    }
}

private fun MinMaxStudent(list: MutableList<Student>) {
    println()
    println("Поиск самого старшего и самого младшего")
    println()

    list.stream().min(compareBy { student -> student.year_of_birthday.toInt() }).ifPresent {
        println("Самый старший : $it")
    }

    list.stream().max(compareBy { student -> student.year_of_birthday.toInt() }).ifPresent {
        println("Самый младший : $it")
    }
}