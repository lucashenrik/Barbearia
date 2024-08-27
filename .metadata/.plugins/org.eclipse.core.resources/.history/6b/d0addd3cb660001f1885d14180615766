// src/components/CreateClientForm.js
import React, { useState } from 'react';
import {Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import styles from './CreateClientForm.module.css';

const CreateClientForm = () => {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [phone, setPhone] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      const response = await axios.post('/cliente/criar', {
        name,
        email,
        phone,
      });

      // Redirecionar para a página de atendimentos com o clienteId
      const clienteId = new URL(response.headers.location).searchParams.get('clienteId');
      navigate(`/atendimento?clienteId=${clienteId}`);
    } catch (error) {
      console.error('Erro ao criar cliente:', error);
    }
  };

  return (
    <div className={styles.principal}>
      <p className={styles.title}>Registrar</p>
      <p className={styles.subtitle}>Crie sua nova conta</p>
      <form className={styles.form} onSubmit={handleSubmit}>
        <div className={styles.input1}>
          <input className={styles.input}
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
            required placeholder='Nome'
          />
        </div>
        <div>
          <input className={styles.input}
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required placeholder='Email'
          />
        </div>
        <div>
          <input className={styles.input}
            type="tel"
            value={phone}
            onChange={(e) => setPhone(e.target.value)}
            required placeholder='Telefone'
          />
        </div>
        <Link to="/atendimento">
        <button className={styles.buttonmake} type="submit">Registrar</button>
        </Link>
      </form>
    </div>
  );
};

export default CreateClientForm;
