// src/components/atendimentos/update/AtualizarAtendimento.jsx
import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import CreateAtendimento from '../createAtend/CreateAtendimento'; // Caminho correto para o componente

const UpdateAtendimento = () => {
  const { id } = useParams();
  const [atendimento, setAtendimento] = useState(null);

  useEffect(() => {
    axios.get(`/atendimento/${id}`)
      .then(response => {
        setAtendimento(response.data);
      })
      .catch(error => console.error('Erro ao buscar atendimento:', error));
  }, [id]);

  return (
    <CreateAtendimento modo="atualizar" atendimento={atendimento} />
  );
};

export default UpdateAtendimento;
