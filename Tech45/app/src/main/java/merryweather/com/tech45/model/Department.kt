package merryweather.com.tech45.model

data class Department(val ID: Integer) {
    val Name: String? = null
    val Departments: ArrayList<Department>? = null
    val Employees : ArrayList<Employee>? = null

    data class Employee(val ID: Integer) {
        val Name: String? = null
        val Title: String? = null
        val Email: String? = null
        val Phone: String? = null
    }

}