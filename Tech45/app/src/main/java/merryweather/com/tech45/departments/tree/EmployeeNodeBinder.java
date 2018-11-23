package merryweather.com.tech45.departments.tree;

import android.view.View;
import android.widget.TextView;

import io.reactivex.subjects.PublishSubject;
import merryweather.com.tech45.R;
import tellh.com.recyclertreeview_lib.TreeNode;
import tellh.com.recyclertreeview_lib.TreeViewBinder;

public class EmployeeNodeBinder extends TreeViewBinder<EmployeeNodeBinder.ViewHolder> {

    public PublishSubject<EmployeeNode> employeeClick = PublishSubject.create();

    @Override
    public ViewHolder provideViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public void bindView(ViewHolder holder, int position, TreeNode node) {
        EmployeeNode employee = (EmployeeNode) node.getContent();
        holder.tvName.setText(employee.getName());
        holder.tvTitle.setText(employee.getTitle());
        holder.tvPhone.setText(employee.getPhone());
        holder.tvEmail.setText(employee.getEmail());
        holder.itemView.setOnClickListener(v -> {
            employeeClick.onNext(employee);
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.listitem_employee;
    }

    public class ViewHolder extends TreeViewBinder.ViewHolder {
        public TextView tvName;
        public TextView tvTitle;
        public TextView tvPhone;
        public TextView tvEmail;

        public ViewHolder(View rootView) {
            super(rootView);
            this.tvName = (TextView) rootView.findViewById(R.id.name);
            this.tvTitle = (TextView) rootView.findViewById(R.id.title);
            this.tvPhone = (TextView) rootView.findViewById(R.id.phone);
            this.tvEmail = (TextView) rootView.findViewById(R.id.email);
        }

    }
}