import axios from "axios";

const API_URL = "http://localhost:8080/api/clientes";

export const getClientes = () => axios.get(API_URL);
export const crearCliente = (cliente) => axios.post(API_URL, cliente);
export const actualizarCliente = (id, cliente) => axios.put(`${API_URL}/${id}`, cliente);
export const eliminarCliente = (id) => axios.delete(`${API_URL}/${id}`);/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


