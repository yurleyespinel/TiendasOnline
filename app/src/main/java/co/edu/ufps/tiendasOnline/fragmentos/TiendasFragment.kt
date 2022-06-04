package co.edu.ufps.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



import co.edu.ufps.tiendasOnline.R
import co.edu.ufps.tiendasOnline.controller.TiendaAdapter
import co.edu.ufps.tiendasOnline.entity.Tienda
import co.edu.ufps.tiendasOnline.vista.RegistarTiendaActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TiendasFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TiendasFragment : Fragment() {


    lateinit var  contenedorTienda: RecyclerView
    lateinit var tiendaAdapter: TiendaAdapter
    lateinit var  database : FirebaseDatabase
    lateinit var  addTienda: FloatingActionButton
    var   TAG = "TiendasFragment"
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_tiendas, container, false)
        contenedorTienda = view.findViewById(R.id.contenedor_tienda)
        addTienda = view.findViewById(R.id.add_tienda)
        var linearlayout = LinearLayoutManager(context)

        database = FirebaseDatabase.getInstance()



        linearlayout.orientation = LinearLayoutManager.VERTICAL
        contenedorTienda.layoutManager = linearlayout
        tiendaAdapter = TiendaAdapter(context, dataSetFirebase(), R.layout.card)
        contenedorTienda.adapter = tiendaAdapter
        // Inflate the layout for this fragment
        addTienda.setOnClickListener{irAddTienda()}
        return view
    }

    private fun irAddTienda() {

            var intent = Intent(context,RegistarTiendaActivity::class.java)
        startActivity(intent)
    }

    private  fun dataSetFirebase(): ArrayList<Tienda> {
        var tiendas : ArrayList<Tienda> = ArrayList()
        val myRef = database.getReference()
        // var tiendas = ArrayList<Tienda>()
        // Read from the database
        // Read from the database
        myRef.child("tiendas").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()){
                    tiendas.clear()
                    for(data in dataSnapshot.children){
                        var tienda = data.getValue(Tienda::class.java)
                        if (tienda != null) {
                            tiendas.add(tienda as Tienda)
                        }
                        tiendaAdapter.notifyDataSetChanged()
                    }

                }
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
        return tiendas
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TiendasFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TiendasFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}