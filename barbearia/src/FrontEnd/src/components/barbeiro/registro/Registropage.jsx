import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import styles from './Auth.module.css'; // Importe o novo arquivo CSS

const RegisterPage = () => {
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');
  const [confirmarSenha, setConfirmarSenha] = useState('');
  const [showPassword, setShowPassword] = useState(false); // Estado para mostrar/ocultar senha
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (senha !== confirmarSenha) {
      setError('As senhas não coincidem');
      return;
    }
    try {
      await axios.post('/auth/registrar', { email, senha });
      navigate('/login'); // Redirecionar para a página de login após registro bem-sucedido
    } catch (error) {
      setError('Erro ao registrar novo barbeiro');
    }
  };

  return (
    <div className={styles.container}>
      <h1 className={styles.title}>Registrar</h1>
      <form onSubmit={handleSubmit} className={styles.form}>
        <div className={styles.inputContainer}>
          <input 
            type="email" 
            value={email} 
            onChange={(e) => setEmail(e.target.value)} 
            required 
            className={styles.input}
            placeholder="Email"
          />
        </div>
        <div className={styles.inputContainer}>
          <input 
            type={showPassword ? 'text' : 'password'} 
            value={senha} 
            onChange={(e) => setSenha(e.target.value)} 
            required 
            className={styles.input}
            placeholder="Senha"
          />
        </div>
        <div className={styles.inputContainer}>
          <input 
            type={showPassword ? 'text' : 'password'} 
            value={confirmarSenha} 
            onChange={(e) => setConfirmarSenha(e.target.value)} 
            required 
            className={styles.input}
            placeholder="Confirmar Senha"
          />
        </div>
        <button 
          type="button" 
          onClick={() => setShowPassword(!showPassword)} 
          className={styles.togglePassword}
        >
          {showPassword ? 'Ocultar Senha' : 'Mostrar Senha'}
        </button>
        <button type="submit" className={styles.button}>Registrar</button>
        {error && <p className={styles.error}>{error}</p>}
      </form>
    </div>
  );
};

export default RegisterPage;
