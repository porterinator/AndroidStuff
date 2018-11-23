package merryweather.com.tech45.departments

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import kotlinx.android.synthetic.main.activity_departments.*
import merryweather.com.tech45.R
import merryweather.com.tech45.departments.tree.DepartmentNodeBinder
import merryweather.com.tech45.departments.tree.EmployeeNodeBinder
import merryweather.com.tech45.di.ComponentManager
import merryweather.com.tech45.di.factory.ViewModelFactory
import merryweather.com.tech45.employee.EmployeeActivity
import tellh.com.recyclertreeview_lib.TreeNode
import tellh.com.recyclertreeview_lib.TreeViewAdapter
import java.util.*
import javax.inject.Inject
import android.R.menu
import android.view.MenuInflater
import android.view.MenuItem
import merryweather.com.tech45.login.LoginActivity
import merryweather.com.tech45.utils.Utils
import android.support.v4.view.ViewCompat.animate




class DepartmentsActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var departmentsViewModel: DepartmentsViewModel
    lateinit var treeViewAdapter : TreeViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        ComponentManager.instance.getActivityComponent(this).inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_departments)
        departmentsViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(DepartmentsViewModel::class.java)
        departmentsViewModel.getDepartments();
        departmentsViewModel.nodes.subscribe {
            val employeeNodeBinder = EmployeeNodeBinder()
            employeeNodeBinder.employeeClick.subscribe {
                val intent = Intent(this, EmployeeActivity::class.java)
                intent.putExtra("employee", it)
                startActivity(intent)
            }
            treeViewAdapter = TreeViewAdapter(it, Arrays.asList(DepartmentNodeBinder(), employeeNodeBinder))
            treeViewAdapter.setOnTreeNodeListener(object : TreeViewAdapter.OnTreeNodeListener {
                override fun onClick(node: TreeNode<*>?, holder: RecyclerView.ViewHolder?): Boolean {
                    if (!node!!.isLeaf()) {
                        //Update and toggle the node.
                        onToggle(!node.isExpand(), holder);
                    }
                    return false;
                }

                override fun onToggle(isExpand: Boolean, holder: RecyclerView.ViewHolder?) {
                    val depViewHolder = holder as DepartmentNodeBinder.ViewHolder
                    val ivArrow = depViewHolder.ivArrow
                    val rotateDegree = if (isExpand) 90.0f else -90.0f
                    ivArrow.animate().rotationBy(rotateDegree)
                            .start()
                }
            })
            treeView.layoutManager = LinearLayoutManager(this)
            treeView.adapter = treeViewAdapter
        };
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        when (item.getItemId()) {
            R.id.logout -> {
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                Utils.savePermanentValue("login", "", this)
                Utils.savePermanentValue("password", "", this)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
