/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


import React, { useEffect, useState } from "react";
import axios from "axios";

const Servicios = () => {
    const [servicios, setServicios] = useState([]);

    useEffect(() => {
        axios.get("/api/servicios/activos")
            .then(res => setServicios(res.data))
            .catch(err => console.error("Error al cargar servicios:", err));
    }, []);

    return (
        <div>
            <h2>Servicios Disponibles</h2>
            <ul>
                {servicios.map(servicio => (
                    <li key={servicio.id}>
                        <strong>{servicio.nombre}</strong> - {servicio.descripcion} | Precio: Q{servicio.precio} | Duración: {servicio.duracion} min
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default Servicios;
