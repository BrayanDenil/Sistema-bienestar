package aplicativo.sistema_gestion.UIP

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import aplicativo.sistema_gestion.R
import aplicativo.sistema_gestion.ViewModel.ClienteViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: ClienteViewModel by viewModels()
    private lateinit var recyclerClientes: RecyclerView
    private lateinit var adapter: ClienteAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cliente_form)

        // Inicializar RecyclerView y Adapter
        recyclerClientes = findViewById(R.id.recyclerClientes)
        recyclerClientes.layoutManager = LinearLayoutManager(this)
        adapter = ClienteAdapter(emptyList())
        recyclerClientes.adapter = adapter

        // Observar cambios en la lista de clientes
        viewModel.listaClientes.observe(this) { clientes ->
            adapter.updateData(clientes)
        }

        // Cargar clientes desde el ViewModel
        viewModel.cargarClientes()

        /* Botón para registrar nuevo cliente
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        btnRegistrar.setOnClickListener {
            val nombre = findViewById<EditText>(R.id.etNombre).text.toString()
            val correo = findViewById<EditText>(R.id.etCorreo).text.toString()
            val telefono = findViewById<EditText>(R.id.etTelefono).text.toString()
            viewModel.agregarCliente(nombre, correo, telefono)*/
        }
    }

