// src/components/atendimentos/createAtend/selectors/HorarioSelector.js
/*import React from 'react';
import commonStyles from './Selector.module.css';

const HorarioSelector = ({ horarios, selectedHorario, onSelect }) => {
  return (
    <div>
      <p className={commonStyles.title}>SELECIONE O HORÁRIO:</p>
      <ul>
        {horarios.map(horario => (
          <li
            key={horario}
            onClick={() => onSelect(horario)}
            className={selectedHorario === horario ? commonStyles.selected : commonStyles.unselected}
          >
            {horario}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default HorarioSelector;*/

// src/components/selectors/HorarioSelector.js
import React from 'react';
import commonStyles from './Selector.module.css';

const HorarioSelector = ({ horarios, selectedHorario, onSelect }) => {
  return (
    <div>
      {horarios.length > 0 && (
        <p className={commonStyles.title}>ESCOLHA O HORÁRIO:</p>
      )}
      <div className={commonStyles.box}>
        <ul className={commonStyles.list}>
          {horarios.map(horario => (
            <li
              key={horario}
              onClick={() => onSelect(horario)}
              className={`${commonStyles.boxList} ${commonStyles.horarioBoxList} ${selectedHorario === horario ? commonStyles.selected : commonStyles.unselected}`}
            >
              {horario}
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
};

export default HorarioSelector;
