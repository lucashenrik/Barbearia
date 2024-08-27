// src/App.js
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

import Home from './components/Home';
import CreateClientForm from './components/cliente/atendimentos/create/CreateClientForm';
import AtendimentoList from './components/cliente/atendimentos/AtendimentosList';
import CreateAtendimento from './components/cliente/atendimentos/createAtend/CreateAtendimento'; // Importe o componente CreateAtendimento
import AtualizarAtendimento from './components/cliente/atendimentos/update/AtualizarAtendimento';
import CancelarAtendimento from './components/cliente/atendimentos/cancelar/CancelarAtendimento';

import HomePage from './components/barbeiro/HomePage';
import CriarHorarioPage from './components/barbeiro/horarios/create/CreateHorarios';
import AtualizarHorarioPage from './components/barbeiro/horarios/update/AtualizarHorariospage';
import ServicosPage from './components/barbeiro/servicos/Servicospage';
import CriarServicoPage from './components/barbeiro/servicos/create/CriarServicospage';

import InicioPage from './components/barbeiro/registro/InicioPage';
import RegisterPage from './components/barbeiro/registro/Registropage';
import LoginPage from './components/barbeiro/registro/Loginpage';

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />

        <Route path="/novo-cliente" element={<CreateClientForm />} />
        <Route path="/atendimento" element={<AtendimentoList />} />
        <Route path="/criar-atendimento" element={<CreateAtendimento />} />
        <Route path="/atualizar-atendimento/:id" element={<AtualizarAtendimento />} />
        <Route path="/cancelar-atendimento/:id" element={<CancelarAtendimento />} />

        <Route path="/home" element={<HomePage />} />
        <Route path="/home/novo-horario" element={<CriarHorarioPage />} />
        <Route path="/home/atualizar-horario" element={<AtualizarHorarioPage />} />
        <Route path="/servicos" element={<ServicosPage />} />
        <Route path="/servicos/criar-servico" element={<CriarServicoPage />} />

        <Route path="/inicio" element={<InicioPage />} />
        <Route path="/registrar" element={<RegisterPage />} />
        <Route path="/login" element={<LoginPage />} />
      </Routes>
    </Router>
  );
};

export default App;

