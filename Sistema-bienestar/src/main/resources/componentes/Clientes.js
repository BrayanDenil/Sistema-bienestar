import React, { useEffect, useState } from "react";
import { getClientes, crearCliente, actualizarCliente, eliminarCliente } from "../services/apiClientes";

const Clientes = () => {
  const [clientes, setClientes] = useState([]);
  const [nuevoCliente, setNuevoCliente] = useState({ nombre: "", correo: "" });
  const [error, setError] = useState("");

  useEffect(() => {
    cargarClientes();
  }, []);

  const cargarClientes = async () => {
    try {
      const response = await getClientes();
      setClientes(response.data);
    } catch (err) {
      console.error(err);
    }
  };

  const handleCrear = async () => {
    if (!nuevoCliente.nombre || !nuevoCliente.correo) {
      setError("Todos los campos son obligatorios");
      return;
    }
    try {
      await crearCliente(nuevoCliente);
      setNuevoCliente({ nombre: "", correo: "" });
      setError("");
      cargarClientes();
    } catch (err) {
      setError("Error al crear cliente");
    }
  };

  return (
    <div>
      <h2>Clientes</h2>
      <div>
        <input
          placeholder="Nombre"
          value={nuevoCliente.nombre}
          onChange={(e) => setNuevoCliente({ ...nuevoCliente, nombre: e.target.value })}
        />
        <input
          placeholder="Correo"
          value={nuevoCliente.correo}
          onChange={(e) => setNuevoCliente({ ...nuevoCliente, correo: e.target.value })}
        />
        <button onClick={handleCrear}>Crear Cliente</button>
        {error && <p style={{ color: "red" }}>{error}</p>}
      </div>
      <ul>
        {clientes.map((c) => (
          <li key={c.id}>
            {c.nombre} - {c.correo} 
            <button onClick={() => eliminarCliente(c.id).then(cargarClientes)}>Eliminar</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Clientes;
/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


