package com.mansoorsyed.disasterassistantclient

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.disasterassistantclient.R
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.JsonObject
import com.mansoorsyed.disasterassistantclient.model.Token
import com.mansoorsyed.disasterassistantclient.model.UserLogin
import com.mansoorsyed.disasterassistantclient.retrofit.AuthApi
import com.mansoorsyed.disasterassistantclient.retrofit.RetroFitService
import com.mansoorsyed.disasterassistantclient.retrofit.SessionManager
import retrofit2.Call
import retrofit2.Response
import java.util.Objects
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
        var sessionManager : SessionManager? = context?.let { SessionManager(it) };

        var inputEditEmail: TextInputEditText? = view.findViewById(R.id.et_email);
        var inputEditPassword: TextInputEditText? = view.findViewById(R.id.et_password);

        var retrofitService: RetroFitService = RetroFitService(requireContext());
        var authApi = retrofitService.retroFit?.create(AuthApi::class.java);

        loginButton.setOnClickListener { view ->
            var userLogin: UserLogin = UserLogin();
            userLogin.email = inputEditEmail?.text.toString();
            userLogin.password = inputEditPassword?.text.toString();

            authApi?.login(userLogin)
                ?.enqueue(object : retrofit2.Callback<Token?> {
                    override fun onResponse(call: Call<Token?>, response: Response<Token?>) {
                        var loginResponse = response.body()

                        if (response.code() != 200) {
                            Toast.makeText(context, "Login failed!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(
                                context,
                                "Login successful!",
                                Toast.LENGTH_SHORT
                            ).show()

                            sessionManager?.saveAuthToken(loginResponse?.token.toString());

                            val intent = Intent(context, FloodListActivity::class.java)
                            startActivity(intent)
                        }
                    }

                    override fun onFailure(call: Call<Token?>, t: Throwable) {
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