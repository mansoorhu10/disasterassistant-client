package com.mansoorsyed.disasterassistantclient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.disasterassistantclient.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.mansoorsyed.disasterassistantclient.model.UserRegister
import com.mansoorsyed.disasterassistantclient.retrofit.AuthApi
import com.mansoorsyed.disasterassistantclient.retrofit.RetroFitService
import retrofit2.Call
import retrofit2.Response
import java.util.logging.Level
import java.util.logging.Logger


class RegisterFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        var view : View = inflater.inflate(R.layout.fragment_register, container, false);
        var registerButton : Button = view.findViewById(R.id.btn_register);

        var inputEditName : TextInputEditText? = view.findViewById(R.id.et_name);
        var inputEditEmail : TextInputEditText? = view.findViewById(R.id.et_email);
        var inputEditPassword : TextInputEditText? = view.findViewById(R.id.et_password);
        var inputEditRepassword : TextInputEditText? = view.findViewById(R.id.et_repassword);

        var retrofitService : RetroFitService =  RetroFitService();
        var authApi = retrofitService.retroFit?.create(AuthApi::class.java);

        registerButton.setOnClickListener { view ->
            if (inputEditPassword !== inputEditRepassword) {
                Toast.makeText(context, "Register failed, password does not match!", Toast.LENGTH_SHORT)
                    .show()
                Logger.getLogger(AuthenticationPage::class.java.name)
                    .log(Level.SEVERE, "Error occurred", Throwable(message = "Register failed, password does not match!"))
            } else {
                var userRegister : UserRegister = UserRegister();
                userRegister.name = inputEditName?.text.toString();
                userRegister.email  = inputEditEmail?.text.toString();
                userRegister.password = inputEditPassword?.text.toString();

                authApi?.register(userRegister)
                    ?.enqueue(object : retrofit2.Callback<String?> {
                        override fun onResponse(call: Call<String?>, response: Response<String?>) {
                            Toast.makeText(context, "Register successful!", Toast.LENGTH_SHORT)
                                .show()
                        }

                        override fun onFailure(call: Call<String?>, t: Throwable) {
                            Toast.makeText(context, "Register failed!!!", Toast.LENGTH_SHORT)
                                .show()
                            Logger.getLogger(AuthenticationPage::class.java.name)
                                .log(Level.SEVERE, "Error occurred", t)
                        }
                    })
            }

        };
        return view;
    }
}