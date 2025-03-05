package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.PacienteRepository;



@Service
public class PacienteService {
    @Autowired
    PacienteRepository pacienteRepository;

    @Transactional
    public PacienteEntity createPaciente(PacienteEntity paciente) throws IllegalOperationException {
        //No habia restricciones de negocio especificadas en el doc, entonces solo voy a asegurarme que no existiera ningún paciente con el mismo id
        if(!pacienteRepository.findById(paciente.getId()).isEmpty()){
            throw new IllegalOperationException("Ya existe un paciente con el mismo id.");
        }
        return pacienteRepository.save(paciente);
    }

}
