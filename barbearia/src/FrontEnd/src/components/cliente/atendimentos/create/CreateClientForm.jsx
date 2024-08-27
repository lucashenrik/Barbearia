import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
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
    const response = await axios.post('http://localhost:8080/cliente/criar', {
        nome: name,
        email,
        telefone: phone,
    });

    // Extrair o clienteId do corpo da resposta
    const clienteId = response.data;
    navigate(`/atendimento?clienteId=${clienteId}`);
  } catch (error) {
    console.error('Erro ao criar cliente:', error);
    alert("Erro ao criar cliente. Por favor, tente novamente.");
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
        <button className={styles.buttonmake} type="submit">Registrar</button>
      </form>
    </div>
  );
};

export default CreateClientForm;
