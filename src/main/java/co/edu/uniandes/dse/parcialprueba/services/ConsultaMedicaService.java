package co.edu.uniandes.dse.parcialprueba.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialprueba.entities.ConsultaMedicaEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.ConsultaMedicaRepository;


@Service
public class ConsultaMedicaService {
    @Autowired
    ConsultaMedicaRepository consultaMedicaRepository;
    
    @Transactional 
    public ConsultaMedicaEntity createConsultaMedica(ConsultaMedicaEntity consultaMedica) throws IllegalOperationException {
        Date fechaConsulta = consultaMedica.getFecha();
        Date fechaActual = new Date();
        if(!fechaConsulta.after(fechaActual)){
            throw new IllegalOperationException("La fecha de la consulta debe ser mayor a la fecha actual");
        } else{
            return consultaMedicaRepository.save(consultaMedica);
        }
    }
}
