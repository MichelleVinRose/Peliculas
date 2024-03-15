package reyes.michelle.peliculas

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class RecuperacionActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    lateinit var et_correoRecuperacion:EditText
    lateinit var btn_recuperar:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperacion)
        // Obtén el color que deseas establecer para la barra de notificaciones
        val colorStatusBar = ContextCompat.getColor(this, R.color.melon)
        // Establece el color de la barra de notificaciones
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = colorStatusBar
        }

        btn_recuperar=findViewById(R.id.btn_recuperar)
        et_correoRecuperacion=findViewById(R.id.et_correoRecuperacion)
        auth= Firebase.auth

        btn_recuperar.setOnClickListener {
            var correo:String=et_correoRecuperacion.text.toString()

            if (!correo.isNullOrEmpty()){
                auth.sendPasswordResetEmail(correo)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            //Log.d(TAG, "Email sent.")
                            Toast.makeText(this,"Se ha enviado un correo electrónico", Toast.LENGTH_SHORT).show()
                            finish()
                        }else{
                            Toast.makeText(this,"Ha ocurrido un error", Toast.LENGTH_SHORT).show()
                        }
                    }
            }else{
                Toast.makeText(this,"Ingresar Correo", Toast.LENGTH_SHORT).show()
            }
        }

    }
}