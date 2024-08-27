import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import styles from './Auth.module.css'; // Importe o novo arquivo CSS

const LoginPage = () => {
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');
  const [error, setError] = useState('');
  const [showPassword, setShowPassword] = useState(false); // Estado para mostrar/ocultar senha
  const navigate = useNavigate();

  /*const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('/auth/login', { email, senha });
      const { token } = response.data;
      // Armazenar o token para autenticação futura
      localStorage.setItem('authToken', token);
      navigate('/home'); // Redirecionar para a página inicial
    } catch (error) {
      setError('Usuário inexistente ou senha inválida');
    }
  };*/

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      // Simula o login bem-sucedido independentemente das credenciais
      // No futuro, substitua isso pelo código real de autenticação
      navigate('/home'); // Redirecionar para a página inicial
    } catch (error) {
      setError('Erro ao realizar login');
    }
  };

  return (
    <div className={styles.container}>
      <h1 className={styles.title}>Login</h1>
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
        <button 
          type="button" 
          onClick={() => setShowPassword(!showPassword)} 
          className={styles.togglePassword}
        >
          {showPassword ? 'Ocultar Senha' : 'Mostrar Senha'}
        </button>
        <button type="submit" className={styles.button}>Login</button>
        {error && <p className={styles.error}>{error}</p>}
      </form>
    </div>
  );
};

export default LoginPage;
