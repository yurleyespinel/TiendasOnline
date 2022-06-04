package co.edu.ufps.tiendasOnline

import android.R.attr.password
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import co.edu.ufps.tiendasOnline.vista.InicioActivity
import co.edu.ufps.tiendasOnline.vista.RegistroActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    val TAG : String = "MainActivity"
        //la TAg se usa para validar errores
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = findViewById<TextInputEditText>(R.id.textUser);
        val password = findViewById<TextInputEditText>(R.id.texPassword);
        val sesion = findViewById<Button>(R.id.sesion);

        mAuth = FirebaseAuth.getInstance();

            //Eventos del botón -- implícito
            sesion.setOnClickListener {
                sigIn(user.text.toString(), password.text.toString())
            }
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth!!.currentUser
        //updateUI(currentUser) //este metodo debemos crearlo nosotros
    }

    //este método lo sacamos de la documentación de firebase
    fun sigIn(email: String, password: String){
        mAuth?.signInWithEmailAndPassword(email, password)
            ?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    Toast.makeText(baseContext, "Authentication exitosa.",
                        Toast.LENGTH_SHORT).show()
                    irInicio()
                    val user = mAuth?.currentUser
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    //updateUI(null)
                }
            }
    }

    //metodo para llevarnos al nuevo activity
    fun irRegistro (view: View){
        val intent = Intent(this, RegistroActivity::class.java)
        startActivity(intent)
    }
    //No le ponemos view porque no lo llamamos del xml
    fun irInicio (){
        val intent = Intent(this, InicioActivity::class.java)
        startActivity(intent)
    }

}