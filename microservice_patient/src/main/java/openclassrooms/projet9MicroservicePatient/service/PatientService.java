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

    public String update(int id, Patient patient) throws JsonProcessingException {
        Optional<Patient> opt = patientRepository.findById(id);

        if(opt.isPresent()){
            patient.setId(opt.get().getId());
            patientRepository.save(patient);
            ObjectMapper mapper = new ObjectMapper();
            String response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(opt.get());
            return response;
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

    public Patient get(int id) throws Exception {
        Optional<Patient> opt = patientRepository.findById(id);

        if(opt.isPresent()){

             return opt.get();

        }else {
            throw(new Exception("invalid patient."));
        }
    }
}
