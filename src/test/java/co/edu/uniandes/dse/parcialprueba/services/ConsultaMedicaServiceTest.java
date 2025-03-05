package co.edu.uniandes.dse.parcialprueba.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.parcialprueba.entities.ConsultaMedicaEntity;
import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import jakarta.transaction.Transactional;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import(ConsultaMedicaService.class)
public class ConsultaMedicaServiceTest {
    @Autowired
    private ConsultaMedicaService consultaMedicaService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();
    
    private ConsultaMedicaEntity consultaMedica;
    
    @BeforeEach
    void setUp(){
        clearData();
        insertData();
    }
    private void clearData(){
        entityManager.getEntityManager().createQuery("delete from ConsultaMedicaEntity").executeUpdate();
        entityManager.getEntityManager().createQuery("delete from PacienteEntity").executeUpdate();
    }
    private void insertData(){
        consultaMedica = factory.manufacturePojo(ConsultaMedicaEntity.class);
        entityManager.persist(consultaMedica);
        
    }

    @Test
    public void createConsultaMedicaTest() throws IllegalOperationException{
        ConsultaMedicaEntity consulta = consultaMedicaService.createConsultaMedica(consultaMedica);
        assertNotNull(consulta.getId());
    }

}
