/* src/components/CreateAtendimento.js */
/*
  useEffect(() => {
    axios.get('/atendimento/barbeiros-disponiveis')
      .then(response => setBarbeiros(response.data))
      .catch(error => console.error("Erro ao buscar barbeiros:", error));
  }, []);

  useEffect(() => {
    if (selectedBarbeiroId) {
      axios.get(`/atendimento/buscarPorIdBarbeiro?barbeiroId=${selectedBarbeiroId}`)
        .then(response => setServicos(response.data))
        .catch(error => console.error("Erro ao buscar serviços:", error));
    }
  }, [selectedBarbeiroId]);

  useEffect(() => {
    if (selectedBarbeiroId && selectedServicos.length) {
      const duracaoServico = 20; // Calcule a duração total dos serviços selecionados
      axios.get(`/atendimento/horarios-disponiveis?barbeiroId=${selectedBarbeiroId}&data=${1 /* Data selecionada *//*}&duracaoServico=${duracaoServico}`)
  .then(response => setHorarios(response.data))
  .catch(error => console.error("Erro ao buscar horários:", error));
}
}, [selectedBarbeiroId, selectedServicos]);
*/

import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import BarbeiroSelector from './selectors/BarbeiroSelector';
import ServicoSelector from './selectors/ServicoSelector';
import HorarioSelector from './selectors/HorarioSelector';
import styles from './CreateAtendimento.module.css';

const CreateAtendimento = ({ modo, atendimento }) => {
  const [barbeiros, setBarbeiros] = useState([]);
  const [selectedBarbeiroId, setSelectedBarbeiroId] = useState(atendimento?.barbeiroId || null);
  const [servicos, setServicos] = useState([]);
  const [selectedServicos, setSelectedServicos] = useState(atendimento?.servicos || []);
  const [horarios, setHorarios] = useState([]);
  const [selectedHorario, setSelectedHorario] = useState(atendimento?.horario || '');

  useEffect(() => {
    // Simulação para carregar barbeiros
    setBarbeiros([
      { id: 1, nomeBarbeiro: 'João' },
      { id: 2, nomeBarbeiro: 'Pedro' },
      { id: 3, nomeBarbeiro: 'Lucas' }
    ]);
  }, []);

  useEffect(() => {
    if (selectedBarbeiroId) {
      // Simulação para carregar serviços com base no barbeiro selecionado
      setServicos([
        { id: 1, nomeServico: 'Corte de Cabelo' },
        { id: 2, nomeServico: 'Barba' },
        { id: 3, nomeServico: 'Corte + Barba' }
      ]);
    }
  }, [selectedBarbeiroId]);

  useEffect(() => {
    if (selectedBarbeiroId && selectedServicos.length > 0) {
      // Simulação para carregar horários com base no barbeiro e serviços selecionados
      setHorarios(['09:00', '10:00', '11:00', '14:00', '15:00']);
    }
  }, [selectedBarbeiroId, selectedServicos]);

  const handleCreateAtendimento = () => {
    // Criação do atendimento
    console.log("Atendimento criado com sucesso!");
  };

  const showButton = selectedBarbeiroId !== null &&
    selectedServicos.length > 0 &&
    selectedHorario !== '';

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
            onSelect={setSelectedServicos}
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

      <Link to="/atendimento">
      {showButton && (
        <button className={styles.button} onClick={handleCreateAtendimento}>
          {modo === 'atualizar' ? 'Atualizar Atendimento' : 'Criar Agendamento'}
        </button>
      )}
      </Link>
    </div>
  );
};

export default CreateAtendimento;



