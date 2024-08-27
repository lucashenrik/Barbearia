import React, { useEffect, useState } from 'react';
import { Link, useLocation } from 'react-router-dom';
import axios from 'axios';
import styles from './AtendimentosList.module.css';

const AtendimentoList = () => {
  const [atendimentos, setAtendimentos] = useState([]);
  const [message, setMessage] = useState('');
  const location = useLocation();
  const query = new URLSearchParams(location.search);
  const clienteId = query.get('clienteId');
	
  useEffect(() => {
    if (clienteId) {
      axios.get(`/atendimento?clienteId=${clienteId}`)
        .then(response => {
          if (response.data.length === 0) {
            setMessage('Você ainda não tem atendimentos.');
          } else {
            setAtendimentos(response.data);
            setMessage('');
          }
        })
        .catch(error => {
          console.error("Erro ao buscar atendimentos:", error);
        });
    }
  }, [clienteId]);

  const handleConfirmCancel = (currentAtendimentoId) => {
    axios.delete(`/atendimento/cancelar?id=${currentAtendimentoId}`)
      .then(() => {
        alert('Atendimento cancelado com sucesso');
        setAtendimentos(atendimentos.filter(atendimento => atendimento.id !== currentAtendimentoId));
      })
      .catch(error => {
        console.error('Erro ao cancelar atendimento:', error);
      });
  };

  return (
    <div className={styles.container}>
      <div className={styles.main}>
        <p>Meus</p>
        <h1>Atendimentos</h1>
        {message ? (
          <p>{message}</p>
        ) : (
          <div className={styles.box}>
            <ul>
              {atendimentos.map(atendimento => (
                <li key={atendimento.id} className={styles.listItem}>
                  <div className={styles.details}>
                    <div className={styles.horario}>
                      <span className={styles.label}>{atendimento.horario}</span>
                    </div>
                    <div className={styles.barbeiroNome}>
                      <span className={styles.label}>{atendimento.barbeiroNome}</span>
                    </div>
                    <div className={styles.servicos}>
                      <span className={styles.nome}>{atendimento.servicos.join(', ')}</span>
                    </div>
                    <div className={styles.valor}>
                      <span className={styles.label}>Valor: R$ </span>
                      <span className={styles.nome}>{atendimento.valor.toFixed(2)}</span>
                    </div>
                  </div>
                  <div className={styles.buttonContainer}>
                    <Link to={`/atualizar-atendimento/${atendimento.id}`}>
                      <button className={styles.buttonUpdate}>
                        Atualizar
                      </button>
                    </Link>
                    <button
                      className={styles.buttonDelete}
                      onClick={() => handleConfirmCancel(atendimento.id)}
                    >
                      Cancelar
                    </button>
                  </div>
                </li>
              ))}
            </ul>
          </div>
        )}
      </div>
      <div>
        <Link to={`/criar-atendimento?clienteId=${clienteId}`}>
          <button className={styles.buttonUpdate}>
            Novo Atendimento
          </button>
        </Link>
      </div>
    </div>
  );
};

export default AtendimentoList;