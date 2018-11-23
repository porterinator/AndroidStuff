package merryweather.com.tech45.employee

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_employee.*
import merryweather.com.tech45.R
import merryweather.com.tech45.departments.tree.EmployeeNode
import merryweather.com.tech45.utils.Utils

class EmployeeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee)
        val employee = intent.getSerializableExtra("employee") as? EmployeeNode
        employee?.let {
            Glide.with(this).load(String.format(
                    "https://contact.taxsee.com/Contacts.svc/GetWPhoto?login=%s&password=%s&id=%d",
                    Utils.getPermanentValue("login", this),
                    Utils.getPermanentValue("password", this),
                    it.id
            )).into(image)
            name.text = it.name
            titl.text = it.title
            phone.text = it.phone

            phone.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL);
                intent.data = Uri.parse("tel:" + employee.phone)
                startActivity(intent);
            }

            email.text = it.email
            email.setOnClickListener {
                val i = Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , arrayOf(employee.email));
                startActivity(Intent.createChooser(i, "Send mail..."));
            }
        }

    }
}
