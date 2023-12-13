package com.mansoorsyed.disasterassistantclient

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.disasterassistantclient.R
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.mansoorsyed.disasterassistantclient.model.UserLogin
import com.mansoorsyed.disasterassistantclient.model.UserRegister
import com.mansoorsyed.disasterassistantclient.retrofit.AuthApi
import com.mansoorsyed.disasterassistantclient.retrofit.RetroFitService
import retrofit2.Call
import retrofit2.Response
import java.util.logging.Level
import java.util.logging.Logger

class AuthenticationPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication_page)

        var viewPager : ViewPager2 = findViewById(R.id.viewPager)

        var pagerAdapter : AuthenticationPagerAdapter = AuthenticationPagerAdapter(supportFragmentManager, LoginFragment().lifecycle);

        pagerAdapter.addFragment(LoginFragment());
        pagerAdapter.addFragment(RegisterFragment());

        viewPager.adapter = pagerAdapter;
    }

    internal class AuthenticationPagerAdapter(fm: FragmentManager, lc: Lifecycle) : FragmentStateAdapter(fm, lc) {
        private val fragmentList: ArrayList<Fragment> = ArrayList<Fragment>()

        fun addFragment(fragment: Fragment) {
            fragmentList.add(fragment)
        }

        override fun getItemCount(): Int {
            return fragmentList.size;
        }

        override fun createFragment(position: Int): Fragment {
            return fragmentList[position]
        }
    }

    private fun initializeComponents() {
        var inputEditName : TextInputEditText = findViewById(R.id.et_name);
        var inputEditEmail : TextInputEditText = findViewById(R.id.et_email);
        var inputEditPassword : TextInputEditText = findViewById(R.id.et_password);
        var inputEditRepassword : TextInputEditText = findViewById(R.id.et_repassword);
        var buttonLogin : MaterialButton = findViewById(R.id.btn_login);
        var buttonRegister : MaterialButton = findViewById(R.id.btn_register);

        var retrofitService : RetroFitService =  RetroFitService();
        var authApi = retrofitService.retroFit?.create(AuthApi::class.java);

        buttonLogin.setOnClickListener { view ->
            var userLogin : UserLogin = UserLogin();
            userLogin.email  = inputEditEmail.text.toString();
            userLogin.password = inputEditPassword.text.toString();

            authApi?.login(userLogin)
                ?.enqueue(object : retrofit2.Callback<String?> {
                    override fun onResponse(call: Call<String?>, response: Response<String?>) {
                        Toast.makeText(this@AuthenticationPage, "Login successful!", Toast.LENGTH_SHORT)
                            .show()
                    }

                    override fun onFailure(call: Call<String?>, t: Throwable) {
                        Toast.makeText(this@AuthenticationPage, "Login failed!!!", Toast.LENGTH_SHORT)
                            .show()
                        Logger.getLogger(AuthenticationPage::class.java.name)
                            .log(Level.SEVERE, "Error occurred", t)
                    }
                })
        };

        buttonRegister.setOnClickListener { view ->
            if (inputEditPassword !== inputEditRepassword) {
                Toast.makeText(this@AuthenticationPage, "Register failed, password does not match!", Toast.LENGTH_SHORT)
                    .show()
                Logger.getLogger(AuthenticationPage::class.java.name)
                    .log(Level.SEVERE, "Error occurred", Throwable(message = "Register failed, password does not match!"))
            } else {
                var userRegister : UserRegister = UserRegister();
                userRegister.name = inputEditName.text.toString();
                userRegister.email  = inputEditEmail.text.toString();
                userRegister.password = inputEditPassword.text.toString();

                authApi?.register(userRegister)
                    ?.enqueue(object : retrofit2.Callback<String?> {
                        override fun onResponse(call: Call<String?>, response: Response<String?>) {
                            Toast.makeText(this@AuthenticationPage, "Register successful!", Toast.LENGTH_SHORT)
                                .show()
                        }

                        override fun onFailure(call: Call<String?>, t: Throwable) {
                            Toast.makeText(this@AuthenticationPage, "Register failed!!!", Toast.LENGTH_SHORT)
                                .show()
                            Logger.getLogger(AuthenticationPage::class.java.name)
                                .log(Level.SEVERE, "Error occurred", t)
                        }
                    })
            }

        };

    }

}