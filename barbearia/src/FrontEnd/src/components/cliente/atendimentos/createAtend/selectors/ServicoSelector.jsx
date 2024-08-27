// src/components/atendimentos/createAtend/selectors/ServicoSelector.js
/*import React from 'react';
import commonStyles from './Selector.module.css';

const ServicoSelector = ({ servicos, selectedServicos, onSelect }) => {
  return (
    <div>
      <p className={commonStyles.title}>SELECIONE OS SERVIÇOS:</p>
      {servicos.map(servico => (
        <div 
          key={servico.id} 
          onClick={() => onSelect(servico.id)} 
          className={selectedServicos.includes(servico.id) ? commonStyles.selected : commonStyles.unselected}
          style={{ cursor: 'pointer' }}
        >
          {servico.nomeServico}
        </div>
      ))}
    </div>
  );
};

export default ServicoSelector;*/

// src/components/selectors/ServicoSelector.js
import React from 'react';
import commonStyles from './Selector.module.css';

const ServicoSelector = ({ servicos, selectedServicos, onSelect }) => {
  const handleSelect = (servicoId) => {
    if (selectedServicos.includes(servicoId)) {
      onSelect(selectedServicos.filter(id => id !== servicoId));
    } else {
      onSelect([...selectedServicos, servicoId]);
    }
  };

  return (
    <div>
      {servicos.length > 0 && (
      <p className={commonStyles.title}>ESCOLHA O SERVIÇO:</p>
    )}
      <div className={commonStyles.box}>
        <ul className={commonStyles.list}>
          {servicos.map(servico => (
            <li
              key={servico.id}
              onClick={() => handleSelect(servico.id)}
              className={`${commonStyles.boxList} ${commonStyles.servicoBoxList} ${selectedServicos.includes(servico.id) ?  commonStyles.selected : commonStyles.unselected}`}
            >
              {servico.nomeServico}
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
};

export default ServicoSelector;
