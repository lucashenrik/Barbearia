import React from 'react';
import { Link } from 'react-router-dom';

const InicioPage = () => {
  return (
    <div className="home-container">
      <h1>Voce precisa se conectar para proseguir</h1>
      <div className="button-container">
        <Link to="/registrar">
          <button className="home-button">Registrar</button>
        </Link>
        <Link to="/login">
          <button className="home-button">Login</button>
        </Link>
      </div>
    </div>
  );
};

export default InicioPage;