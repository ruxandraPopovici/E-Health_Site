package com.example.demo.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }


    public List<Patient> getPatients(){
        return (List<Patient>) patientRepository.findAll();
    }

    public Patient getCurrentPatient(Long userID) {
        Optional<Patient> currentPatient = patientRepository.findPatientByUserID(userID);
        if(currentPatient.isPresent()){
            return currentPatient.get();
        }
        else{
            throw new IllegalStateException("No such patient");
        }

    }
    public void addNewPatient(Patient patient) {
        Optional<Patient> patientByCNP = patientRepository.findPatientByCNP(patient.getCNP());
        if(patientByCNP.isPresent()){
            throw new IllegalStateException("CNP taken");
        }
        patientRepository.save(patient);

        System.out.println(patient);
    }

    public void deletePatient(Long patientID) {
        boolean exists = patientRepository.existsById(patientID);

        if(!exists){
            throw new IllegalStateException("does not exist");
        }
        patientRepository.deleteById(patientID);
    }

    @Transactional
    public void updatePatient(Long patientID, Patient editPatient){
        Patient patient = patientRepository.findById(patientID).
                        orElseThrow(()-> new IllegalStateException("nu am gasit"));

        if (editPatient.getFirstName() != null && editPatient.getFirstName().length() > 0){
            patient.setFirstName(editPatient.getFirstName());
        }

        if (editPatient.getLastName() != null && editPatient.getLastName().length() > 0){
            patient.setLastName(editPatient.getLastName());
        }

        if (editPatient.getCNP() != null && editPatient.getCNP().length() > 0){
            patient.setCNP(editPatient.getCNP());
        }

        if (editPatient.getAddress() != null && editPatient.getAddress().length() > 0){
            patient.setAddress(editPatient.getAddress());
        }
        if (editPatient.getTelNo() != null && editPatient.getTelNo().length() > 0){
            patient.setTelNo(editPatient.getTelNo());
        }

    }
}
