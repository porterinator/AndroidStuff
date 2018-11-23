package merryweather.com.tech45.departments.tree

import merryweather.com.tech45.R
import tellh.com.recyclertreeview_lib.LayoutItemType
import java.io.Serializable

class EmployeeNode(val id : Integer, val name : String?, val title: String?, val phone: String?, val email : String?) : LayoutItemType, Serializable  {

    override fun getLayoutId(): Int {
        return R.layout.listitem_employee;
    }
}