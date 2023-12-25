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

}