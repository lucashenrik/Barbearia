import React, { useState } from 'react';
import axios from 'axios';
import {Link, useNavigate } from 'react-router-dom';
import styles from "../Servicospage.module.css";

const CriarServicoPage = () => {
  const [nomeServico, setNomeServico] = useState('');
  const [preco, setPreco] = useState('');
  const [barbeiroId, setBarbeiroId] = useState(1); // Substitua pelo ID real do barbeiro logado
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post('/home/novo-servico', {
        barbeiroId,
        nomeServico,
        preco,
      });
      navigate('/servicos');
    } catch (error) {
      console.error('Erro ao criar serviço', error);
    }
  };

  return (
    <div className={styles.formContainer}>
      <h1>Criar</h1>
      <p className={styles.h2}>Novo Serviço</p>
      <form onSubmit={handleSubmit}>
        <label>
          <input type="text" onChange={(e) => setNomeServico(e.target.value)} 
           required 
           className={styles.label}
           placeholder="Nome"/>
        </label>
        <label>
          <input type="number" onChange={(e) => setPreco(e.target.value)} 
           required 
           className={styles.label}
           placeholder="Valor"/>
        </label>
        <Link to="/servicos">
        <button type="submit" className={styles.button}>Salvar</button>
        </Link>
      </form>
    </div>
  );
};

export default CriarServicoPage;