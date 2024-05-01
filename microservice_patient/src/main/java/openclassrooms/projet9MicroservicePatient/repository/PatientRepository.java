package openclassrooms.projet9MicroservicePatient.repository;

import openclassrooms.projet9MicroservicePatient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
