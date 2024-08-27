/*useEffect(() => {

    // Simular um atendimento fictício para testes
    setAtendimentos([{
      id: 1, barbeiroNome: 'João', horario: '2024-08-10T10:00'
    },
    { id: 2, barbeiroNome: 'Maria', horario: '2024-08-11T11:00' }]);

    // Buscar atendimentos do cliente
    /*axios.get(`/atendimento?clienteId=${clienteId}`)
      .then(response => {
        setAtendimentos(response.data);
      })
      .catch(error => {
        console.error("Erro ao buscar atendimentos:", error);
      });*//*
}, [clienteId]);


const handleConfirmCancel = () => {
axios.delete(`/atendimento/cancelar?id=${currentAtendimentoId}`)
.then(response => {
  alert('Atendimento cancelado com sucesso');
  setAtendimentos(atendimentos.filter(atendimento => atendimento.id !== currentAtendimentoId));
  setShowConfirmPopup(false);
})
.catch(error => {
  console.error('Erro ao cancelar atendimento:', error);
  setShowConfirmPopup(false); // Fechar o pop-up mesmo se houver erro
});
};
*/


import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import styles from './AtendimentosList.module.css';

const AtendimentoList = () => {
  const [atendimentos, setAtendimentos] = useState([]);

  useEffect(() => {
    // Simular um atendimento fictício para testes com todos os dados necessários
    setAtendimentos([
      {
        id: 1,
        clienteNome: 'Carlos',
        barbeiroNome: 'João',
        servicos: ['Corte de cabelo', 'Barba'],
        horario: '10:00 08/10/2024',
        valor: 50.00,
      },
      {
        id: 2,
        clienteNome: 'Ana',
        barbeiroNome: 'Maria',
        servicos: ['Tintura', 'Corte de cabelo'],
        horario: '11:00 09/10/2024',
        valor: 80.00,
      },
      {
        id: 3,
        clienteNome: 'Ana',
        barbeiroNome: 'Maria',
        servicos: ['Tintura', 'Corte de cabelo'],
        horario: '11:00 09/10/2024',
        valor: 80.00,
      }
    ]);
  }, []);

  return (
    <div className={styles.container}>
      <div className={styles.main}>
        <p>Meus</p>
        <h1>Atendimentos</h1>
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
                    <span className={styles.label}>Valor: R$  </span>
                    <span className={styles.nome}>{atendimento.valor.toFixed(2)}</span>
                  </div>
                </div>
                <div className={styles.buttonContainer}>
                  <Link to={`/atualizar-atendimento/${atendimento.id}`}>
                    <button className={styles.buttonUpdate}>
                      Atualizar
                    </button>
                  </Link>
                  <Link to={`/cancelar-atendimento/${atendimento.id}`}>
                    <button className={styles.buttonDelete}>
                      Cancelar
                    </button>
                  </Link>
                </div>
              </li>
            ))}
          </ul>
        </div>
      </div>
      <div>
            <Link to="/criar-atendimento"> 
            <button className={styles.buttonUpdate}>
                      Novo Atendimento
                    </button>
            </Link>
      </div>
    </div>

  );
};

export default AtendimentoList;
