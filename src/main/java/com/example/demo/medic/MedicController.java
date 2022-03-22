package com.example.demo.medic;

import com.example.demo.user.UserService;
import com.example.demo.user.UserTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class MedicController {

    private final MedicService medicService;
    private final UserService userService;

    @Autowired
    public MedicController(MedicService medicService, UserService userService) {
        this.medicService = medicService;

        this.userService = userService;
    }

    @RequestMapping("/medicProfile")
    public String patientPersonalInfo(Model model){

        if(userService.getActiveUser().isPresent()){
            UserTable currentUser = userService.getActiveUser().get();
            Medic currentMedic = medicService.getCurrentMedic(currentUser.getUserID());
            model.addAttribute("userName", currentUser.getUserName());
            model.addAttribute("userID", currentUser.getUserID());
            model.addAttribute("medicID", currentMedic.getMedicID());
            model.addAttribute("fullName", currentMedic.getName());
            model.addAttribute("spec", currentMedic.getSpecialization());
            model.addAttribute("rating", currentMedic.getRating());
            model.addAttribute("review", currentMedic.getReview());
            model.addAttribute("cv", currentMedic.getCV());

            return "medicProfile";
        }
        else{
            return "nunu";
        }
    }

    @RequestMapping("/diseaseMedic")
    public String medicDisease(){
        return "disease";
    }

    @RequestMapping("/chatMedic")
    public String medicChat(){
        return "medicChat";
    }
    @RequestMapping("/medicMain")
    public String medicHomePage(){
        return "medicMain";
    }
    /*
    UPDATE
    */

    @GetMapping("/editm/{medicID}")
    public String editCurrentPatient(@PathVariable("medicID") String medicID, Model model){

        if(userService.getActiveUser().isPresent()) {
            UserTable currentUser = userService.getActiveUser().get();
            Medic currentMedic = medicService.getCurrentMedic(currentUser.getUserID());

            model.addAttribute("editMedic", currentMedic);
            return "personalInfoMedic";
        }

        return "nunu";
    }

    @PostMapping("editm/{medicID}")
    public String updateCurrentPatient(@PathVariable("medicID") String medicID,
                                       @ModelAttribute("editMedic") Medic medic, Model model){

        model.addAttribute("error_edit", "");
        medicService.updateMedic(medic.getMedicID(), medic);

        return "personalInfoMedic";
    }

}
