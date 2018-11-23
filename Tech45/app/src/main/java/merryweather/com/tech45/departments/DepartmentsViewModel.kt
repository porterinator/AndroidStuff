package merryweather.com.tech45.departments

import android.arch.lifecycle.ViewModel
import android.content.Context
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import merryweather.com.tech45.api.TechService
import merryweather.com.tech45.departments.tree.DepartmentNode
import merryweather.com.tech45.departments.tree.EmployeeNode
import merryweather.com.tech45.model.Department
import merryweather.com.tech45.utils.Utils
import tellh.com.recyclertreeview_lib.TreeNode
import javax.inject.Inject

class DepartmentsViewModel  @Inject
constructor(private val mTechService: TechService, private val mContext: Context) : ViewModel() {

    val departments = BehaviorSubject.create<Department>()
    val nodes = BehaviorSubject.create<List<TreeNode<*>>>()
    val disposable = CompositeDisposable();

    fun getDepartments() {
        if (!departments.hasValue())
            disposable.add(
                    mTechService.getDepartments(Utils.getPermanentValue("login", mContext), Utils.getPermanentValue("password", mContext))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { response ->
                            val nodes = ArrayList<TreeNode<*>>()
                            val all = DepartmentNode(response.Name!!)
                            val allNode = TreeNode(all)
                            nodes.add(allNode);
                            convertTree(response, allNode)
                            this.nodes.onNext(nodes)
                        }


            )
    }

    fun convertTree(department : Department, node: TreeNode<*>) {
        department.Employees?.let {
            for (employee in department.Employees) {
                val employeeNode = TreeNode(EmployeeNode(employee.ID, employee.Name, employee.Title, employee.Phone, employee.Email))
                node.addChild(employeeNode)
            }
        }
        department.Departments?.let {
            for (child in department.Departments) {
                val childNode = TreeNode(DepartmentNode(child.Name!!))
                node.addChild(childNode)
                convertTree(child, childNode)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}