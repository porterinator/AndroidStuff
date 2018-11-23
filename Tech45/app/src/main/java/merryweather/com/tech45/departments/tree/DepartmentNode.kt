package merryweather.com.tech45.departments.tree

import merryweather.com.tech45.R
import tellh.com.recyclertreeview_lib.LayoutItemType

class DepartmentNode(val name : String) : LayoutItemType {

    override fun getLayoutId(): Int {
        return R.layout.listitem_department;
    }
}