package co.edu.ufps.tiendasOnline.controller

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.edu.ufps.tiendasOnline.R
import co.edu.ufps.tiendasOnline.entity.Tienda
import com.squareup.picasso.Picasso

//aqui es solo lo que vamos a visualizar
class TiendaAdapter (val context:Context?, val dataSet: ArrayList<Tienda>, val recurso:Int): RecyclerView.Adapter<TiendaAdapter.TiendaViewHolder>(){
    class TiendaViewHolder(view:View):RecyclerView.ViewHolder(view){
        val nombre:TextView
        val descripcion: TextView
        val imagen: ImageView
        val telefono:TextView
        init{
            nombre = view.findViewById(R.id.nombre_tienda)
            descripcion = view.findViewById(R.id.descripcion_tienda)
            imagen = view.findViewById(R.id.imagen_tienda)
            telefono = view.findViewById(R.id.telefono_tienda)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TiendaViewHolder {
    val view = LayoutInflater.from(context)
        .inflate(recurso, parent, false)
        return TiendaViewHolder(view)

    }

    override fun onBindViewHolder(holder: TiendaViewHolder, position: Int) {
        holder.nombre.text = dataSet.get(position).nombre
        holder.descripcion.text = dataSet.get(position).descripcion
        holder.telefono.text = dataSet.get(position).telefono
        Picasso.get().load(dataSet.get(position).imagen).into(holder.imagen);

    }

    //Devolvemos el tamaño del dataset
    override fun getItemCount() = dataSet.size
    }
