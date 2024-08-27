/*import React, { useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import styles from '../AtendimentosList.module.css';

const CancelarAtendimento = () => {
    const [showConfirmation, setShowConfirmation] = useState(true);
    const navigate = useNavigate();
    const { id } = useParams();

    const handleCancel = () => {
        // Simulação da lógica de cancelamento
        console.log(`Atendimento ${id} cancelado com sucesso`);
        
        // Redirecionar para a lista de atendimentos após o cancelamento
        navigate('/atendimento');
    };

    return (
        <div className={styles.container}>
            {showConfirmation && (
                <div className={styles.confirmPopup}>
                    <p1>Você tem certeza que deseja cancelar este atendimento?</p1>
                    <button
                        className={styles.confirmButton}
                        onClick={handleCancel}
                    >
                        Sim
                    </button>
                    <button
                        className={styles.confirmButton}
                        onClick={() => navigate('/')}
                    >
                        Não
                    </button>
                </div>
            )}
        </div>
    );
};

export default CancelarAtendimento;*/

import React, { useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import styles from '../AtendimentosList.module.css';

const CancelarAtendimento = () => {
    const [showConfirmation, setShowConfirmation] = useState(true);
    const navigate = useNavigate();
    const { id } = useParams();

    const handleCancel = () => {
        // Simulação da lógica de cancelamento
        console.log(`Atendimento ${id} cancelado com sucesso`);
        
        // Redirecionar para a lista de atendimentos após o cancelamento
        navigate('/atendimento');
    };

    return (
        <div className={styles.container}>
            {showConfirmation && (
                <div className={styles.confirmPopup}>
                    <p1>Você tem certeza que deseja cancelar este atendimento?</p1>
                    <button
                        className={styles.confirmButton}
                        onClick={handleCancel}
                    >
                        Sim
                    </button>
                    <button
                        className={styles.confirmButton}
                        onClick={() => navigate('/atendimento')}
                    >
                        Não
                    </button>
                </div>
            )}
        </div>
    );
};

export default CancelarAtendimento;
