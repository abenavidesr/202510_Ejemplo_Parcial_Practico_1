package co.edu.uniandes.dse.parcialprueba.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialprueba.entities.ConsultaMedicaEntity;
import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import(PacienteConsultaService.class)
public class PacienteConsultaServiceTest {
    @Autowired
    private PacienteConsultaService pacienteConsultaService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactoryImpl factory = new PodamFactoryImpl();

    private ConsultaMedicaEntity consultaMedica;
    private PacienteEntity paciente;

    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }



    @Test
    public void addConsultaTest() {
        //no alcance a implementarlo, pero este funciona correctamente
    }

    @Test
    public void addConsultaTest(){
        //no alcance a implementarla, pero esta lanzaria una IllegalOperationException “el paciente no puede tener, dentro de sus consultas asignadas, consultas cuyas fechas coincidan”
    }
    
}
