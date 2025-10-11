import React, { useState } from "react";
import Login from "./components/Login";
import Clientes from "./components/Clientes";
import Servicios from "./components/Servicios";

function App() {
  const [usuario, setUsuario] = useState(null);

  if (!usuario) {
    return <Login onLogin={setUsuario} />;
  }

  return (
    <div>
      <h1>Bienvenido {usuario.nombre}</h1>
      <Clientes />
      <Servicios />
    </div>
  );
}

export default App;
/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


