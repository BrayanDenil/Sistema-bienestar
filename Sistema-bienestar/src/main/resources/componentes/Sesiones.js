/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


import React, { useEffect, useState } from "react";
import axios from "axios";

const HistorialSesiones = ({ clienteId }) => {
    const [sesiones, setSesiones] = useState([]);

    useEffect(() => {
        axios.get(`/api/sesiones/cliente/${clienteId}`)
            .then(res => setSesiones(res.data))
            .catch(err => console.error(err));
    }, [clienteId]);

    return (
        <div>
            <h2>Historial de Sesiones</h2>
            <table>
                <thead>
                    <tr>
                        <th>Fecha</th>
                        <th>Servicio</th>
                        <th>Estado</th>
                        <th>Notas</th>
                        <th>Factura</th>
                    </tr>
                </thead>
                <tbody>
                    {sesiones.map(s => (
                        <tr key={s.id}>
                            <td>{new Date(s.fechaHora).toLocaleString()}</td>
                            <td>{s.servicio.nombre}</td>
                            <td>{s.estado}</td>
                            <td>{s.notas || "-"}</td>
                            <td>{s.factura ? <a href={`/facturas/${s.factura.id}`}>Ver</a> : "-"}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default HistorialSesiones;
