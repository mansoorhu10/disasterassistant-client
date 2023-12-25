package com.mansoorsyed.disasterassistantclient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.disasterassistantclient.R
import com.google.android.material.textfield.TextInputEditText
import com.mansoorsyed.disasterassistantclient.model.UserLogin
import com.mansoorsyed.disasterassistantclient.retrofit.AuthApi
import com.mansoorsyed.disasterassistantclient.retrofit.RetroFitService
import retrofit2.Call
import retrofit2.Response
import java.util.logging.Level
import java.util.logging.Logger


class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_login, container, false);
        var loginButton: Button = view.findViewById(R.id.btn_login);

        var inputEditEmail: TextInputEditText? = view.findViewById(R.id.et_email);
        var inputEditPassword: TextInputEditText? = view.findViewById(R.id.et_password);

        var retrofitService: RetroFitService = RetroFitService();
        var authApi = retrofitService.retroFit?.create(AuthApi::class.java);

        loginButton.setOnClickListener { view ->
            var userLogin: UserLogin = UserLogin();
            userLogin.email = inputEditEmail?.text.toString();
            userLogin.password = inputEditPassword?.text.toString();

            authApi?.login(userLogin)
                ?.enqueue(object : retrofit2.Callback<String?> {
                    override fun onResponse(call: Call<String?>, response: Response<String?>) {
                        Toast.makeText(context, "Login successful!", Toast.LENGTH_SHORT).show()
                    }

                    override fun onFailure(call: Call<String?>, t: Throwable) {
                        Toast.makeText(context, "Login failed!!!", Toast.LENGTH_SHORT)
                            .show()
                        Logger.getLogger(AuthenticationPage::class.java.name)
                            .log(Level.SEVERE, "Error occurred", t)
                    }
                })
        };
        return view;
    }
}