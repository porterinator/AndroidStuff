package merryweather.com.tech45.departments.tree

import android.view.View
import android.widget.ImageView
import android.widget.TextView

import merryweather.com.tech45.R
import tellh.com.recyclertreeview_lib.TreeNode
import tellh.com.recyclertreeview_lib.TreeViewBinder

class DepartmentNodeBinder : TreeViewBinder<DepartmentNodeBinder.ViewHolder>() {
    override fun provideViewHolder(itemView: View): ViewHolder {
        return ViewHolder(itemView)
    }

    override fun bindView(holder: ViewHolder, position: Int, node: TreeNode<*>) {
        val department = node.content as DepartmentNode
        holder.tvName.text = department.name
    }

    override fun getLayoutId(): Int {
        return R.layout.listitem_department
    }

    inner class ViewHolder(rootView: View) : TreeViewBinder.ViewHolder(rootView) {
        var tvName: TextView
        var ivArrow: ImageView

        init {
            this.tvName = rootView.findViewById<View>(R.id.name) as TextView
            this.ivArrow = rootView.findViewById<View>(R.id.ivArrow) as ImageView
        }

    }
}