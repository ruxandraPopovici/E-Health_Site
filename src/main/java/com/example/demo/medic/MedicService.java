package com.example.demo.medic;

import com.example.demo.patient.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class MedicService {
    private final MedicRepository medicRepository;

    @Autowired
    public MedicService(MedicRepository medicRepository){
        this.medicRepository = medicRepository;
    }

    public void addNewMedic(Medic medic){
        Optional<Medic> medicByName = medicRepository.findMedicByName(medic.getName());
        if (medicByName.isPresent()){
            throw new IllegalStateException("Name sus.");
        }

        medicRepository.save(medic);

        System.out.println(medic);
    }

    public Medic getCurrentMedic(Long userID) {
        Optional<Medic> currentMedic = medicRepository.findMedicByUserID(userID);
        if(currentMedic.isPresent()){
            return currentMedic.get();
        }
        else{
            throw new IllegalStateException("No such patient");
        }
    }

    @Transactional
    public void updateMedic(Long medicID, Medic editMedic){
        Medic medic = medicRepository.findById(medicID).
                orElseThrow(()-> new IllegalStateException("nu am gasit"));

        if (editMedic.getName() != null && editMedic.getName().length() > 0){
            medic.setName(editMedic.getName());
        }

        if (editMedic.getCV() != null && editMedic.getCV().length() > 0){
            medic.setCV(editMedic.getCV());
        }

        if (editMedic.getSpecialization() != null && editMedic.getSpecialization().length() > 0){
            medic.setSpecialization(editMedic.getSpecialization());
        }

    }
}
