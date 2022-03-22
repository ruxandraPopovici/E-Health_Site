package com.example.demo.user;

import com.example.demo.medic.Medic;
import com.example.demo.medic.MedicService;
import com.example.demo.patient.Patient;
import com.example.demo.patient.PatientService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {
    private final UserService userService;
    private final PatientService patientService;
    private final MedicService medicService;

    @Autowired
    public UserController(UserService userService, PatientService patientService, MedicService medicService){
        this.userService = userService;
        this.patientService = patientService;
        this.medicService = medicService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addUser")
    public String registerNewUser(@RequestParam String userName, @RequestParam String password,
                                                @RequestParam String isPatient){

        try{
            UserTable newUser = new UserTable();
            newUser.setUserName(userName);
            newUser.setPassword(password);
            if(isPatient.compareTo("Medic") == 0){
                newUser.setPatient(false);

            }
            else if (isPatient.compareTo("Patient") == 0){
                newUser.setPatient(true);
            }

            userService.addNewUser(newUser);
            if(newUser.isPatient()){
                Patient newPatient = new Patient("Please fill form.", "Please fill form.");
                newPatient.setUserID(newUser.getUserID());
                patientService.addNewPatient(newPatient);
            }
            else{
                Medic newMedic = new Medic("Please fill form.");
                newMedic.setUserID(newUser.getUserID());
                medicService.addNewMedic(newMedic);
            }

            System.out.println("E bine");
            return "index";
        }
        catch(IllegalStateException ex){
            System.out.println("Nu-i bine");
            return "index";
        }

    }
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String getUser(){
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public String logIn(@ModelAttribute(name = "user") UserTable user, Model model){
        try{
            boolean isPatient = userService.logIn(user);
            System.out.println("Intra success");

            model.addAttribute("loggedUser", user);

            model.addAttribute("error_login", "");

            if(isPatient){
                return "dfg";
            }
            else{
                return "medicMain";
            }
        }
        catch(IllegalStateException ex){
            model.addAttribute("error_login", "Invalid username or password. <3");
            return "index";
        }
    }

}
