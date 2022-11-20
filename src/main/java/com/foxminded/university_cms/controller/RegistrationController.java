package com.foxminded.university_cms.controller;

import com.foxminded.university_cms.config.Mappings;
import com.foxminded.university_cms.dto.StudentRegistrationDTO;
import com.foxminded.university_cms.entity.security.User;
import com.foxminded.university_cms.dto.TeacherRegistrationDTO;
import com.foxminded.university_cms.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping(Mappings.REGISTER_PAGE)
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping(Mappings.STUDENT_REGISTRATION_FORM)
    public String studentRegisterForm(Model model) {
        log.info("StudentRegisterForm start");
        model.addAttribute("studentRegistrationDTO", new StudentRegistrationDTO());
        return "studentRegistration";
    }

    @GetMapping(Mappings.TEACHER_REGISTRATION_FORM)
    public String teacherRegisterForm(Model model) {
        log.info("TeacherRegisterForm start");
        model.addAttribute("teacherRegistrationDTO", new TeacherRegistrationDTO());
        return "teacherRegistration";
    }

    @PostMapping(Mappings.STUDENT_FORM)
    public String processStudentRegistration(@Valid StudentRegistrationDTO studentRegistrationDTO,
                                             Errors errors,
                                             RedirectAttributes redirectAttributes) {
        log.info("ProcessStudentRegistration start with student reg. form:{}", studentRegistrationDTO);
        if (errors.hasErrors()) {
            log.info("Validation errors occurred");
            return "studentRegistration";
        }
        Optional<User> user = userService.getUserByUserName(studentRegistrationDTO.getUsername());
        if (user.isPresent()) {
            log.info("There is already a user registered with the user name({}) provided", studentRegistrationDTO.getUsername());
            errors.rejectValue("username", "error.studentRegistrationDTO",
                    "There is already a user registered with the user name provided");
            return "studentRegistration";
        }
        userService.saveUserAsStudent(studentRegistrationDTO);
        redirectAttributes.addAttribute("successRegistration", true);
        return "redirect:/login";
    }

    @PostMapping(Mappings.TEACHER_FORM)
    public String processTeacherRegistration(@Valid TeacherRegistrationDTO teacherRegistrationDTO,
                                             Errors errors,
                                             RedirectAttributes redirectAttributes) {
        log.info("ProcessTeacherRegistration start with teacher reg. form:{}", teacherRegistrationDTO);
        if (errors.hasErrors()) {
            log.info("Validation errors occurred");
            return "teacherRegistration";
        }
        Optional<User> user = userService.getUserByUserName(teacherRegistrationDTO.getUsername());
        if (user.isPresent()) {
            log.info("There is already a user registered with the user name({}) provided", teacherRegistrationDTO.getUsername());
            errors.rejectValue("username", "error.teacherRegistrationDTO",
                    "There is already a user registered with the user name provided");
            return "teacherRegistration";
        }
        userService.saveUserAsTeacher(teacherRegistrationDTO);
        redirectAttributes.addAttribute("successRegistration", true);
        return "redirect:/login";
    }
}
