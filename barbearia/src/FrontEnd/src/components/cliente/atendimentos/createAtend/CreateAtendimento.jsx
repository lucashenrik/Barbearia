import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate, useLocation } from 'react-router-dom';
import BarbeiroSelector from './selectors/BarbeiroSelector';
import ServicoSelector from './selectors/ServicoSelector';
import HorarioSelector from './selectors/HorarioSelector';
import styles from './CreateAtendimento.module.css';

const CreateAtendimento = ({ modo }) => {
  const [barbeiros, setBarbeiros] = useState([]);
  const [selectedBarbeiroId, setSelectedBarbeiroId] = useState(null);
  const [servicos, setServicos] = useState([]);
  const [selectedServicos, setSelectedServicos] = useState([]);
  const [horarios, setHorarios] = useState([]);
  const [selectedHorario, setSelectedHorario] = useState('');

  useEffect(() => {
    axios.get('http://localhost:8080/atendimento/barbeiros-disponiveis')
      .then(response => setBarbeiros(response.data))
      .catch(error => console.error("Erro ao buscar barbeiros:", error));
  }, []);

  useEffect(() => {
    if (selectedBarbeiroId) {
      axios.get(`http://localhost:8080/atendimento/buscarPorIdBarbeiro?barbeiroId=${selectedBarbeiroId}`)
        .then(response => setServicos(response.data))
        .catch(error => console.error("Erro ao buscar serviços:", error));
    }
  }, [selectedBarbeiroId]);

  useEffect(() => {
    if (selectedBarbeiroId && selectedServicos.length) {
      const duracaoServico = selectedServicos.reduce((acc, servico) => acc + servico.duracao, 0);
      axios.get(`http://localhost:8080/atendimento/horarios-disponiveis?barbeiroId=${selectedBarbeiroId}&data=${new Date().toISOString().split('T')[0]}&duracaoServico=${duracaoServico}`)
        .then(response => setHorarios(response.data))
        .catch(error => console.error("Erro ao buscar horários:", error));
    }
  }, [selectedBarbeiroId, selectedServicos]);

  const handleCreateAtendimento = () => {
    console.log("Atendimento criado com sucesso!");
  };

  const showButton = selectedBarbeiroId !== null && selectedServicos.length > 0 && selectedHorario !== '';

  return (
    <div className={styles.container}>
      <p className={styles.title}>
        {modo === 'atualizar' ? 'Alterar Atendimento' : 'Criar Atendimento'}
      </p>

      <div className={styles.selectorContainer}>
        <BarbeiroSelector
          barbeiros={barbeiros}
          selectedBarbeiroId={selectedBarbeiroId}
          onSelect={setSelectedBarbeiroId}
        />
      </div>

      {selectedBarbeiroId && (
        <div className={styles.selectorContainer}>
          <ServicoSelector
            servicos={servicos}
            selectedServicos={selectedServicos}
            onSelect={setSelectedServicos} // Atualiza o estado com o barbeiro selecionado
          />
        </div>
      )}

      {selectedServicos.length > 0 && (
        <div className={styles.selectorContainer}>
          <HorarioSelector
            horarios={horarios}
            selectedHorario={selectedHorario}
            onSelect={setSelectedHorario}
          />
        </div>
      )}

      {showButton && (
        <button className={styles.button} onClick={handleCreateAtendimento}>
          {modo === 'atualizar' ? 'Atualizar Atendimento' : 'Criar Atendimento'}
        </button>
      )}
    </div>
  );
};

export default CreateAtendimento;
