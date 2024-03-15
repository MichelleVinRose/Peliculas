package reyes.michelle.peliculas

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    lateinit var btn_ingresar:Button
    lateinit var btn_registro:Button
    lateinit var tv_recuperacion:TextView
    lateinit var et_correoInicio:TextView
    lateinit var et_contraInicio:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Obtén el color que deseas establecer para la barra de notificaciones
        val colorStatusBar = ContextCompat.getColor(this, R.color.melon)
        // Establece el color de la barra de notificaciones
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = colorStatusBar
        }

        btn_ingresar=findViewById(R.id.btn_ingresar)
        btn_registro=findViewById(R.id.btn_registrar)
        tv_recuperacion=findViewById(R.id.tv_recuperacion)
        et_contraInicio=findViewById(R.id.et_contraInicio)
        et_correoInicio=findViewById(R.id.et_correoInicio)

        auth= Firebase.auth

        btn_registro.setOnClickListener {
            var intent= Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }
        btn_ingresar.setOnClickListener {
            var correo:String=et_correoInicio.text.toString()
            var contra:String=et_contraInicio.text.toString()

            if(!correo.isNullOrEmpty() && !contra.isNullOrEmpty()){

                auth.signInWithEmailAndPassword(correo, contra)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithEmail:success")
                            val user = auth.currentUser
                            var intent= Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(
                                this,
                                "Datos erroneos.",
                                Toast.LENGTH_SHORT,
                            ).show()
                            //updateUI(null)
                        }
                    }

            }else{
                Toast.makeText(this,"Contraseña o correo vacío", Toast.LENGTH_SHORT).show()
            }


        }
        tv_recuperacion.setOnClickListener {
            var intent= Intent(this, RecuperacionActivity::class.java)
            startActivity(intent)
        }

    }
}