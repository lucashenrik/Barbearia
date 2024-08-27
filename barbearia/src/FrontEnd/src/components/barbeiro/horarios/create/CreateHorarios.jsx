/*import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import styles from './CreateHorarios.module.css'; // Importe o CSS atualizado
const CriarHorarioPage = () => {
  const [diasTrabalho, setDiasTrabalho] = useState([]);
  const [horaInicio, setHoraInicio] = useState('');
  const [horaFim, setHoraFim] = useState('');
  const [horaInicioAlmoco, setHoraInicioAlmoco] = useState('');
  const [horaFimAlmoco, setHoraFimAlmoco] = useState('');
  const [barbeiroId, setBarbeiroId] = useState(1); // Substitua pelo ID real do barbeiro logado
  const navigate = useNavigate();*/

import React, { useState } from 'react';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import styles from './CreateHorarios.module.css'; // Certifique-se de importar o arquivo CSS

const CriarHorarioPage = () => {
  const [diasTrabalho, setDiasTrabalho] = useState([]);
  const [horaInicio, setHoraInicio] = useState('');
  const [horaFim, setHoraFim] = useState('');
  const [horaInicioAlmoco, setHoraInicioAlmoco] = useState('');
  const [horaFimAlmoco, setHoraFimAlmoco] = useState('');
  const [horarios, setHorarios] = useState(generateHorarios()); // Adicione a função para gerar horários
  const [barbeiroId, setBarbeiroId] = useState(1); // Substitua pelo ID real do barbeiro logado
  const navigate = useNavigate();


  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post('/home/novo-horario', {
        barbeiroId,
        diasTrabalho,
        horaInicio,
        horaFim,
        horaInicioAlmoco,
        horaFimAlmoco,
      });
      navigate('/home');
    } catch (error) {
      console.error('Erro ao criar horário', error);
    }
  };

  const handleSelectDia = (dia) => {
    if (diasTrabalho.includes(dia)) {
      setDiasTrabalho(diasTrabalho.filter(diaTrabalho => diaTrabalho !== dia));
    } else {
      setDiasTrabalho([...diasTrabalho, dia]);
    }
  };

  return (
    <div className="container">
      <h1>Criar Novo Horário</h1>
      <form onSubmit={handleSubmit}>
        <label>
          <h2>Selecione os dias:</h2>
          <div className={styles.box}>
            <ul className={styles.list}>
              {['Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado', 'Domingo'].map(dia => (
                <li
                  key={dia}
                  onClick={() => handleSelectDia(dia)}
                  className={`${styles.boxList} ${styles.diaBoxList} ${diasTrabalho.includes(dia) ? styles.selected : styles.unselected}`}
                >
                  {dia}
                </li>
              ))}
            </ul>
          </div>
        </label>
        <div className={styles.formContainer}>
          <label>
            Início:
            <select value={horaInicio} onChange={(e) => setHoraInicio(e.target.value)}>
              {horarios.map((horario) => (
                <option key={horario} value={horario}>
                  {horario}
                </option>
              ))}
            </select>
          </label>

          <label>
            Fim:
            <select value={horaFim} onChange={(e) => setHoraFim(e.target.value)}>
              {horarios.map((horario) => (
                <option key={horario} value={horario}>
                  {horario}
                </option>
              ))}
            </select>
          </label>
        </div>
        <div className={styles.formContainer}>
          <label>
            Início Almoço:
            <select value={horaInicioAlmoco} onChange={(e) => setHoraInicioAlmoco(e.target.value)}>
              {horarios.map((horario) => (
                <option key={horario} value={horario}>
                  {horario}
                </option>
              ))}
            </select>
          </label>
          <label>
            Fim Almoço:
            <select value={horaFimAlmoco} onChange={(e) => setHoraFimAlmoco(e.target.value)}>
              {horarios.map((horario) => (
                <option key={horario} value={horario}>
                  {horario}
                </option>
              ))}
            </select>
          </label>
        </div>
        <Link to="/home">
        <button type="submit">Salvar</button>
      </Link>
    </form>
    </div >
  );
};

// Função para gerar horários com intervalos de 30 minutos a partir das 6:00
const generateHorarios = () => {
  const startHour = 6;
  const endHour = 22; // Até 22:00
  const intervals = 30; // Intervalos de 30 minutos
  const horarios = [];
  for (let hour = startHour; hour <= endHour; hour++) {
    for (let minute = 0; minute < 60; minute += intervals) {
      const formattedHour = hour.toString().padStart(2, '0');
      const formattedMinute = minute.toString().padStart(2, '0');
      horarios.push(`${formattedHour}:${formattedMinute}`);
    }
  }
  return horarios;
};

export default CriarHorarioPage;