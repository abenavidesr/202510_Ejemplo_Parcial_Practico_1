package co.edu.uniandes.dse.parcialprueba.services;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialprueba.entities.ConsultaMedicaEntity;
import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.ConsultaMedicaRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.PacienteRepository;

@Service
public class PacienteConsultaService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ConsultaMedicaRepository consultaMedicaRepository;

    @Transactional
    public PacienteEntity addConsulta(Long idPaciente, Long idConsulta)throws EntityNotFoundException, IllegalOperationException{
        if(pacienteRepository.findById(idPaciente).isEmpty()){
            throw new EntityNotFoundException("No existe un paciente con ese id");
        }
        PacienteEntity paciente = pacienteRepository.findById(idPaciente).get();
        if(consultaMedicaRepository.findById(idConsulta).isEmpty()){
            throw new EntityNotFoundException("No existe una consulta medica con ese id");
        }
        ConsultaMedicaEntity consulta = consultaMedicaRepository.findById(idConsulta).get();

        Date fechaConsulta = consulta.getFecha();
        ArrayList<ConsultaMedicaEntity> consultas = paciente.getConsultasMedicas();
        for(ConsultaMedicaEntity consultaMedica: consultas){
            if(consultaMedica.getFecha().equals(fechaConsulta)){
                throw new IllegalOperationException("Ya existe una consulta medica con la misma fecha");
            }
        }
        paciente.getConsultasMedicas().add(consulta);
        return paciente;
    }

    @Transactional
    public ArrayList<ConsultaMedicaEntity> getConsultasProgramadas(Long idPaciente)throws EntityNotFoundException{
        if(pacienteRepository.findById(idPaciente).isEmpty()){
            throw new EntityNotFoundException("No existe un paciente con ese id");
        }
        PacienteEntity paciente = pacienteRepository.findById(idPaciente).get();
        ArrayList<ConsultaMedicaEntity> consultas = paciente.getConsultasMedicas();
        ArrayList<ConsultaMedicaEntity> consultasProgramadas = new ArrayList<>();
        Date fechaActual = new Date();
        for(ConsultaMedicaEntity consulta: consultas){
            if(consulta.getFecha().after(fechaActual)){
                consultasProgramadas.add(consulta);
            }
        }
        return consultasProgramadas;
    }
}
