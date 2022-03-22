package com.example.demo.patient;

import com.example.demo.user.UserService;
import com.example.demo.user.UserTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class PatientController {
    private final PatientService patientService;
    private final PatientRepository patientRepository;
    private final UserService userService;

    @Autowired
    public PatientController(PatientService patientService, PatientRepository patientRepository, UserService userService) {
        this.patientService = patientService;
        this.patientRepository = patientRepository;
        this.userService = userService;
    }


    @GetMapping("/patient/list")
    public List<Patient> getPatients(){
        return patientService.getPatients();
    }

    @RequestMapping("/dfg")
    public String patientHome(){
        return "dfg";
    }
    @RequestMapping("/patientSearch")
    public String patientSearch(){
        return "patientSearch";
    }

    @RequestMapping("/personalInfo")
    public String patientPersonalInfo(Model model){

        if(userService.getActiveUser().isPresent()){
            UserTable currentUser = userService.getActiveUser().get();
            Patient currentPatient = patientService.getCurrentPatient(currentUser.getUserID());
            model.addAttribute("userName", currentUser.getUserName());
            model.addAttribute("userID", currentUser.getUserID());
            model.addAttribute("fullName", currentPatient.getFirstName() + " " + currentPatient.getLastName());
            model.addAttribute("CNP", currentPatient.getCNP());
            model.addAttribute("address", currentPatient.getAddress());
            model.addAttribute("telNo", currentPatient.getTelNo());
            model.addAttribute("patientID", currentPatient.getPatientID());

            return "personalInfo";
        }
        else{
            return "nunu";
        }
    }

    @PostMapping("/add")
    public void registerNewPatient(@RequestParam String first, @RequestParam String last){
        Patient patient = new Patient();
        patient.setFirstName(first);
        patient.setLastName(last);
    patientService.addNewPatient(patient);
}
    @RequestMapping(method = RequestMethod.POST, value = "/deletePatient/{patientID}")
    public void deleteStudent(@PathVariable("patientID") Long patientId){
        patientService.deletePatient(patientId);
    }


    @GetMapping("/edit/{patientID}")
    public String editCurrentPatient(@PathVariable("patientID") String patientID, Model model){

        if(userService.getActiveUser().isPresent()) {
            UserTable currentUser = userService.getActiveUser().get();
            Patient currentPatient = patientService.getCurrentPatient(currentUser.getUserID());

            model.addAttribute("editPatient", currentPatient);
            return "updatePatient";
        }

        return "nunu";
    }

    @PostMapping("edit/{patientID}")
    public String updateCurrentPatient(@PathVariable("patientID") String patientID,
                                       @ModelAttribute("editPatient") Patient patient, Model model){

        Optional<Patient> distinctByID = patientRepository.findDistinctByPatientID(patient.getPatientID());

        if(distinctByID.isPresent() && patientRepository.findPatientByCNP(patient.getCNP()).equals(distinctByID) ){
            model.addAttribute("error_edit", "Invalid data: please enter unique CNP.");
        }
        else if(distinctByID.isPresent() && patientRepository.findPatientByTelNo(patient.getTelNo()).equals(distinctByID)){
            model.addAttribute("error_edit", "Invalid data: please enter unique telephone number.");
        }
        else{
            model.addAttribute("error_edit", "");
            patientService.updatePatient(patient.getPatientID(), patient);
        }

        return "updatePatient";
    }

    /*@RequestMapping(method = RequestMethod.POST, value = "/updatePatient")
    public String updateStudent(
            @PathVariable("patientID") Long patientID,
            @Valid Patient patient, BindingResult result, Model model){
        if(result.hasErrors()){
            return "redirect:/patient/personalInfo.html";
        }
        patientRepository.save(patient);
        return "redirect:/patient/personalInfo.html";
    }*/
}
