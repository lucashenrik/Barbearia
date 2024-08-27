import React from 'react';
import { Link } from 'react-router-dom';
import './Home.module.css'; // Certifique-se de que o caminho está correto e que o arquivo existe

const HomePage = () => {
  return (
    <div className="home-container">
      <p className="welcome-text">Bem-vindo</p>
      <h2>Como deseja entrar?</h2>
      <div className="button-container">
        <Link to="/novo-cliente">
          <button className="home-button">CLIENTE</button>
        </Link>
        <Link to="/inicio">
          <button className="home-button">BARBEIRO</button>
        </Link>
      </div>
    </div>
  );
};

export default HomePage;
