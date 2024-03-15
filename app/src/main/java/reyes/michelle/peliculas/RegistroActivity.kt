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


class RegistroActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    lateinit var et_correoRegistro:EditText
    lateinit var et_ContraRegistro:EditText
    lateinit var et_ContraRegistro2:EditText
    lateinit var btn_registrarNuevo:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        // Obtén el color que deseas establecer para la barra de notificaciones
        val colorStatusBar = ContextCompat.getColor(this, R.color.melon)
        // Establece el color de la barra de notificaciones
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = colorStatusBar
        }

        et_correoRegistro=findViewById(R.id.et_correoRegistro)
        et_ContraRegistro=findViewById(R.id.et_ContraRegistro)
        et_ContraRegistro2=findViewById(R.id.et_ContraRegistro2)
        btn_registrarNuevo=findViewById(R.id.btn_registrarNuevo)

        auth=Firebase.auth

        btn_registrarNuevo.setOnClickListener {
            var correo:String= et_correoRegistro.text.toString()
            var contra1:String=et_ContraRegistro.text.toString()
            var contra2:String=et_ContraRegistro2.text.toString()

            if(!correo.isNullOrEmpty() && !contra1.isNullOrEmpty() && !contra2.isNullOrEmpty()){

                if(contra1==contra2){
                    auth.createUserWithEmailAndPassword(correo, contra1)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                val user = auth.currentUser
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("exito", "createUserWithEmail:success")
                                Toast.makeText(
                                    baseContext,
                                    "Se ha registrado correctamente, ${user?.email}",
                                    Toast.LENGTH_SHORT,
                                ).show()
                                finish()
                                //updateUI(user)
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("error", "createUserWithEmail:failure", task.exception)
                                Toast.makeText(
                                    baseContext,
                                    "No se pudo registrar el usuario.",
                                    Toast.LENGTH_SHORT,
                                ).show()
                                //updateUI(null)
                            }
                        }
                }else{
                    Toast.makeText(this,"Las contraseñas no coinciden",Toast.LENGTH_SHORT).show()
                }

            }else{
                Toast.makeText(this,"Contraseña o correo vacío",Toast.LENGTH_SHORT).show()
            }
        }

    }
}