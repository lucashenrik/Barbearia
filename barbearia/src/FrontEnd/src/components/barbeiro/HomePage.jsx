/*useEffect(() => {
    const fetchAtendimentos = async () => {
        try {
            const response = await axios.get(`/home?barbeiroId=${barbeiroId}`);
            setAtendimentos(response.data);
        } catch (error) {
            console.error('Erro ao buscar atendimentos', error);
        }
    };
    
    const fetchHorarios = async () => {
        try {
            const response = await axios.get(`/home/horarios?barbeiroId=${barbeiroId}&data=${new Date().toISOString()}`);
            setHorariosDisponiveis(response.data);
        } catch (error) {
            console.error('Erro ao buscar horários', error);
        }
    };
*/import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import atendimentoStyles from './HomePage.module.css';

const HomePage = () => {
  const [atendimentos, setAtendimentos] = useState([]);
  const [horariosDisponiveis, setHorariosDisponiveis] = useState([]);
  const [barbeiroId, setBarbeiroId] = useState(1); // Substitua pelo ID real do barbeiro logado
  const [selectedAtendimentoId, setSelectedAtendimentoId] = useState(null); // Estado para armazenar o atendimento selecionado

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
        clienteNome: 'Marcio',
        barbeiroNome: 'Mardia',
        servicos: ['Corte de cabelo'],
        horario: '11:00 09/10/2024',
        valor: 30.00,
      },
      {
        id: 4,
        clienteNome: 'Marcsos',
        barbeiroNome: 'Mardia',
        servicos: ['Corte de cabelo'],
        horario: '11:00 09/10/2024',
        valor: 30.00,
      },
      {
        id: 5,
        clienteNome: 'Eduardo',
        barbeiroNome: 'Mardia',
        servicos: ['Corte de cabelo'],
        horario: '11:00 09/10/2024',
        valor: 30.00,
      }
    ]);

    // Simular horários fictícios para testes
    setHorariosDisponiveis([
      '09:00',
      '10:00',
      '11:00',
      '12:00',
      '14:00',
      '15:00',
      '16:00',
      '17:00',
      '18:00',
      '19:00',
      '20:00',
      '21:00'
    ]);

  }, [barbeiroId]);

  const handleSelectAtendimento = (id) => {
    setSelectedAtendimentoId(id === selectedAtendimentoId ? null : id); // Permite desmarcar o atendimento
  };

  return (
    <div className={atendimentoStyles.container}>
      <div className={atendimentoStyles.main}>
        <h1>Página Inicial</h1>

        <h2>Atendimentos</h2>

        <div className={atendimentoStyles.box}>
          <ul>
            {atendimentos.map(atendimento => (
              <li
                key={atendimento.id}
                className={`${atendimentoStyles.listItem} ${selectedAtendimentoId === atendimento.id ? atendimentoStyles.selected : ''}`}
                onClick={() => handleSelectAtendimento(atendimento.id)}
              >
                <div className={atendimentoStyles.details}>
                  <div className={atendimentoStyles.horario}>
                    <span className={atendimentoStyles.label}>{atendimento.horario}</span>
                  </div>
                  <div className={atendimentoStyles.barbeiroNome}>
                    <span className={atendimentoStyles.label}>{atendimento.clienteNome}</span>
                  </div>
                  <div className={atendimentoStyles.servicos}>
                    <span className={atendimentoStyles.nome}>{atendimento.servicos.join(', ')}</span>
                  </div>
                  <div className={atendimentoStyles.valor}>
                    <span className={atendimentoStyles.label}>Valor: R$ </span>
                    <span className={atendimentoStyles.nome}>{atendimento.valor.toFixed(2)}</span>
                  </div>
                </div>
                {selectedAtendimentoId === atendimento.id && (
                  <div className={atendimentoStyles.buttonContainer}>
                    <Link to={`/atualizar-atendimento/${atendimento.id}`}>
                      <button className={atendimentoStyles.buttonUpdate}>
                        Atualizar
                      </button>
                    </Link>
                    <Link to={`/cancelar-atendimento/${atendimento.id}`}>
                      <button className={atendimentoStyles.buttonDelete}>
                        Cancelar
                      </button>
                    </Link>
                  </div>
                )}
              </li>
            ))}
          </ul>
        </div>

        <div>
          <h2>Horários Disponíveis</h2>
          <div className={atendimentoStyles.horarioContainer}>
            {horariosDisponiveis.map((hora, index) => (
              <div key={index} className={atendimentoStyles.horarioBarbeiro}>
                <span className={atendimentoStyles.label}>{hora}</span>
              </div>
            ))}
          </div>
        </div>

        <div className={atendimentoStyles.buttonContainer2}>
          <Link to="/home/novo-horario">
            <button className={atendimentoStyles.button2}>Criar Novo Horário</button>
          </Link>

          <Link to="/home/atualizar-horario">
            <button className={atendimentoStyles.button2}>Atualizar Horário</button>
          </Link>

          <Link to="/servicos">
            <button className={atendimentoStyles.button2}>Ver Serviços</button>
          </Link>
        </div>
      </div>
    </div>
  );
};

export default HomePage;