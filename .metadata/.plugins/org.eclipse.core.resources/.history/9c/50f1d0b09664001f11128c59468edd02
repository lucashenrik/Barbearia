// src/components/selectors/BarbeiroSelector.js
/*import React from 'react';
import commonStyles from './Selector.module.css';

const BarbeiroSelector = ({ barbeiros, selectedBarbeiroId, onSelect }) => {
  return (
    <div>
      <p className={commonStyles.title}>SELECIONE O BARBEIRO:</p>
      <div className={commonStyles.box}>
        <ul className={commonStyles.list}>
          {barbeiros.map(barbeiro => (
            <li className={commonStyles.boxList}
              key={barbeiro.id}
              onClick={() => onSelect(barbeiro.id)}
              className1={selectedBarbeiroId === barbeiro.id ? commonStyles.selected : commonStyles.unselected}
            >
              {barbeiro.nomeBarbeiro}
            </li>

          ))}
        </ul>
      </div>
    </div>
  );
};

export default BarbeiroSelector;*/
import React from 'react';
import commonStyles from './Selector.module.css';

const BarbeiroSelector = ({ barbeiros, selectedBarbeiroId, onSelect }) => {
  return (
    <div>
      <p className={commonStyles.title}>ESCOLHA O BARBEIRO:</p>
      <div className={commonStyles.box}>
        <ul className={commonStyles.list}>
          {barbeiros.map(barbeiro => (
            <li
              key={barbeiro.id}
              onClick={() => onSelect(barbeiro.id)}
              className={`${commonStyles.barbeiroBoxList} ${commonStyles.barbeiroBoxList} ${selectedBarbeiroId === barbeiro.id ? commonStyles.selected : commonStyles.unselected}`}
            >
              {barbeiro.nomeBarbeiro}
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
};

export default BarbeiroSelector;

