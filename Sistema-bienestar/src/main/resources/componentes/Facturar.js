
import React, { useState } from "react";
import axios from "axios";

const GenerarFactura = ({ citaId }) => {
  const [monto, setMonto] = useState(0);
  const [impuesto, setImpuesto] = useState(0);
  const [factura, setFactura] = useState(null);

  const generar = () => {
    axios.post(`/api/facturas`, null, {
      params: { citaId, monto, impuesto }
    })
    .then(res => setFactura(res.data))
    .catch(err => {
      console.error(err);
      alert(err.response?.data?.message || "Error al generar factura");
    });
  };

  return (
    <div>
      <h2>Generar Factura</h2>
      <div>
        <label>
          Monto: 
          <input type="number" value={monto} onChange={e => setMonto(parseFloat(e.target.value))} />
        </label>
      </div>
      <div>
        <label>
          Impuesto: 
          <input type="number" value={impuesto} onChange={e => setImpuesto(parseFloat(e.target.value))} />
        </label>
      </div>
      <button onClick={generar}>Generar Factura</button>

      {factura && (
        <div style={{ marginTop: "20px" }}>
          <h3>Factura generada: #{factura.idFactura}</h3>
          <p>Monto: {factura.monto}</p>
          <p>Impuesto: {factura.impuesto}</p>
          <p>Total: {factura.total}</p>
          <p>Estado: {factura.estado}</p>
          <p>Fecha emisión: {new Date(factura.fechaEmision).toLocaleString()}</p>
        </div>
      )}
    </div>
  );
};

export default GenerarFactura;
