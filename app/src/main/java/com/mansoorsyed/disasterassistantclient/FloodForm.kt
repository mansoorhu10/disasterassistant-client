package com.mansoorsyed.disasterassistantclient

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.disasterassistantclient.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.mansoorsyed.disasterassistantclient.model.Flood
import com.mansoorsyed.disasterassistantclient.retrofit.FloodApi
import com.mansoorsyed.disasterassistantclient.retrofit.RetroFitService
import java.util.logging.Level
import java.util.logging.Logger
import retrofit2.Call
import retrofit2.Response
import java.lang.Double.parseDouble


class FloodForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeComponents()
    }
    private fun initializeComponents() {
        var inputEditName : TextInputEditText = findViewById(R.id.form_textFieldName);
        var inputEditLongitude : TextInputEditText = findViewById(R.id.form_textFieldLongitude);
        var inputEditLatitude : TextInputEditText = findViewById(R.id.form_textFieldLatitude);
        var inputEditMagnitude : TextInputEditText = findViewById(R.id.form_textFieldMagnitude);
        var buttonSave : MaterialButton = findViewById(R.id.form_buttonSave);

        var retrofitService : RetroFitService =  RetroFitService(this@FloodForm)
        var floodApi = retrofitService.retroFit?.create<FloodApi>(FloodApi::class.java)

        buttonSave.setOnClickListener { view ->
            var name : String = inputEditName.text.toString();
            var longitude : Double = parseDouble(inputEditLongitude.text.toString());
            var latitude : Double = parseDouble(inputEditLatitude.text.toString());
            var magnitude : Int = Integer.parseInt(inputEditMagnitude.text.toString());

            var flood : Flood = Flood();
            flood.name = name;
            flood.longitude = longitude;
            flood.latitude = latitude;
            flood.magnitude = magnitude;

            floodApi?.save(flood)
                ?.enqueue(object : retrofit2.Callback<Flood?> {
                    override fun onResponse(call: Call<Flood?>?, response: Response<Flood?>?) {
                        Toast.makeText(this@FloodForm, "Save successful!", Toast.LENGTH_SHORT)
                            .show()
                    }

                    override fun onFailure(call: Call<Flood?>?, t: Throwable?) {
                        Toast.makeText(this@FloodForm, "Save failed!!!", Toast.LENGTH_SHORT)
                            .show()
                        Logger.getLogger(FloodForm::class.java.name)
                            .log(Level.SEVERE, "Error occurred", t)
                    }
                })
        };
    }
}