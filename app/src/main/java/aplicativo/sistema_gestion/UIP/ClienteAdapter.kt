package aplicativo.sistema_gestion.UIP

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import aplicativo.sistema_gestion.R

data class Cliente(val nombre: String, val correo: String, val telefono: String)

class ClienteAdapter(private var listaClientes: List<Cliente>) :
    RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder>() {

    class ClienteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtNombre: TextView = view.findViewById(R.id.txtNombre)
        val txtCorreo: TextView = view.findViewById(R.id.txtCorreo)
        val txtTelefono: TextView = view.findViewById(R.id.txtTelefono)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClienteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cliente, parent, false)
        return ClienteViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClienteViewHolder, position: Int) {
        val cliente = listaClientes[position]
        holder.txtNombre.text = cliente.nombre
        holder.txtCorreo.text = cliente.correo
        holder.txtTelefono.text = cliente.telefono
    }

    override fun getItemCount(): Int = listaClientes.size

    fun updateData(newList: List<aplicativo.sistema_gestion.model.Cliente>?) {
        listaClientes = newList?.map {
            Cliente(
                nombre = it.nombre,
                correo = it.correo,
                telefono = it.telefono
            )
        } ?: emptyList()

        notifyDataSetChanged()
    }


    }