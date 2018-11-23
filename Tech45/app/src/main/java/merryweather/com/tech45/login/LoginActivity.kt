package merryweather.com.tech45.login

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

import javax.inject.Inject

import merryweather.com.tech45.R
import merryweather.com.tech45.departments.DepartmentsActivity
import merryweather.com.tech45.di.ComponentManager
import merryweather.com.tech45.di.factory.ViewModelFactory
import merryweather.com.tech45.utils.Utils

class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        ComponentManager.instance.getActivityComponent(this).inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
         if (!"".equals(Utils.getPermanentValue("login", this))) {
             val intent = Intent(this, DepartmentsActivity::class.java);
             startActivity(intent)
             finish()
         }
        /*val binding: ActivityLoginBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_login)*/

        loginViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(LoginViewModel::class.java);

        signin.setOnClickListener {
            loginViewModel.login(login.text.toString(), password.text.toString())
        }

        loginViewModel.login.subscribe {
            if (it.Success) {
                Utils.savePermanentValue("login", login.text.toString(), this)
                Utils.savePermanentValue("password", password.text.toString(), this)
                val intent = Intent(this, DepartmentsActivity::class.java)
                startActivity(intent)
            }

        }
    }
}
