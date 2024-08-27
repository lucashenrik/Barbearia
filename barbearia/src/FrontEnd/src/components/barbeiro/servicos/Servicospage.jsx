import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import styles from "./Servicospage.module.css";

const ServicosPage = () => {
  const [servicos, setServicos] = useState([]);
  const [barbeiroId, setBarbeiroId] = useState(1); // Substitua pelo ID real do barbeiro logado
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    // Função para simular dados
    const fetchServicos = () => {
      // Dados simulados
      const simulatedServicos = [
        { id: 1, nomeServico: 'Corte de Cabelo', preco: '50' },
        { id: 2, nomeServico: 'Barba', preco: '15' },
        { id: 3, nomeServico: 'Corte e Barba', preco: '50' },
        { id: 4, nomeServico: 'Sombrancelha', preco: '15' },
        { id: 5, nomeServico: 'Navalhado', preco: '40' }
      ];

      // Simula um atraso de 1 segundo
      setTimeout(() => {
        setServicos(simulatedServicos);
        setLoading(false);
      }, 1000);
    };

    fetchServicos();
  }, [barbeiroId]);

  /*useEffect(() => {
    const fetchServicos = async () => {
      try {
        const response = await axios.get(`/home/servicos?barbeiroId=${barbeiroId}`);
        setServicos(response.data);
      } catch (error) {
        console.error('Erro ao buscar serviços', error);
      }
    };

    fetchServicos();
  }, [barbeiroId]);

  const handleDelete = async (servicoId) => {
    try {
      await axios.delete(`/home/servicos/excluir`, { data: servicoId });
      setServicos(servicos.filter(servico => servico.id !== servicoId));
    } catch (error) {
      console.error('Erro ao excluir serviço', error);
    }
  };*/

  const handleDelete = async (servicoId) => {
    if (window.confirm('Tem certeza que deseja excluir este serviço?')) {
      try {
        // Simula a exclusão de um serviço
        setServicos(servicos.filter(servico => servico.id !== servicoId));
      } catch (error) {
        setError('Erro ao excluir serviço');
        console.error('Erro ao excluir serviço', error);
      }
    }
  };

  return (
    <div className={styles.body}>
      <p>Serviços</p>
      {loading ? (
        <p>Carregando...</p>
      ) : error ? (
        <p>{error}</p>
      ) : servicos.length === 0 ? (
        <p>Nenhum serviço encontrado.</p>
      ) : (
        <ul>
          {servicos.map(servico => (
            <li key={servico.id} className={styles.listItem}>
              <div className={styles.details}>
                  <span className={styles.nomeServico}>
                    {servico.nomeServico}
                  </span>
                
                    <span className={styles.valor}>
                    R$ {servico.preco},00
                    </span>

              </div>
              <button onClick={() => handleDelete(servico.id)} className={styles.buttonDelete}>Excluir</button>
            </li>
          ))}
        </ul>
      )}

      <Link to="/servicos/criar-servico">
        <button className={styles.button}>Criar Novo Serviço</button>
      </Link>
    </div>
  );
};

export default ServicosPage;
