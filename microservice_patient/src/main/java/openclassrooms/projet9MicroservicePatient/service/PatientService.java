package openclassrooms.projet9MicroservicePatient.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import openclassrooms.projet9MicroservicePatient.model.Patient;
import openclassrooms.projet9MicroservicePatient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    public List<Patient> findAll(){
        return patientRepository.findAll();
    }

    public Patient add(Patient patient){
        return patientRepository.save(patient);
    }

    public String update(int id, Patient patient){
        Optional<Patient> opt = patientRepository.findById(id);

        if(opt.isPresent()){
            patient.setId(opt.get().getId());
            patientRepository.save(patient);
            return "patient updated";
        }else {
            return "patient not found";
        }

    }

    public String delete(int id) {
        Optional<Patient> opt = patientRepository.findById(id);

        if(opt.isPresent()){
            patientRepository.delete(opt.get());
        }else {
            return "invalid patient id";
        }

        return "patient deleted";
    }

    public String get(int id) throws JsonProcessingException {
        Optional<Patient> opt = patientRepository.findById(id);

        if(opt.isPresent()){
            ObjectMapper mapper = new ObjectMapper();
            String patient = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(opt.get());
            return patient;
        }else {
            return "invalid patient id";
        }
    }
}
