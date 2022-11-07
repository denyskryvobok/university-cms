package com.foxminded.university_cms.controller;

import com.foxminded.university_cms.config.Mappings;
import com.foxminded.university_cms.entity.security.User;
import com.foxminded.university_cms.registration.form.StudentRegistrationForm;
import com.foxminded.university_cms.registration.form.TeacherRegistrationForm;
import com.foxminded.university_cms.service.UserService;
import lombok.extern.slf4j.Slf4j;
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
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(Mappings.STUDENT_REGISTRATION_FORM)
    public String studentRegisterForm(Model model) {
        log.info("StudentRegisterForm start");
        model.addAttribute("studentRegistrationForm", new StudentRegistrationForm());
        return "studentRegistration";
    }

    @GetMapping(Mappings.TEACHER_REGISTRATION_FORM)
    public String teacherRegisterForm(Model model) {
        log.info("TeacherRegisterForm start");
        model.addAttribute("teacherRegistrationForm", new TeacherRegistrationForm());
        return "teacherRegistration";
    }

    @PostMapping(Mappings.STUDENT_FORM)
    public String processStudentRegistration(@Valid StudentRegistrationForm studentRegistrationForm,
                                             Errors errors,
                                             RedirectAttributes redirectAttributes) {
        log.info("ProcessStudentRegistration start with student reg. form:{}", studentRegistrationForm);
        if (errors.hasErrors()) {
            log.info("Validation errors occurred");
            return "studentRegistration";
        }
        Optional<User> user = userService.getUserByUserName(studentRegistrationForm.getUsername());
        if (user.isPresent()) {
            log.info("There is already a user registered with the user name({}) provided", studentRegistrationForm.getUsername());
            errors.rejectValue("username", "error.studentRegistrationForm",
                    "There is already a user registered with the user name provided");
            return "studentRegistration";
        }
        userService.saveUserAsStudent(studentRegistrationForm);
        redirectAttributes.addAttribute("successRegistration", true);
        return "redirect:/login";
    }

    @PostMapping(Mappings.TEACHER_FORM)
    public String processTeacherRegistration(@Valid TeacherRegistrationForm teacherRegisterForm,
                                             Errors errors,
                                             RedirectAttributes redirectAttributes) {
        log.info("ProcessTeacherRegistration start with teacher reg. form:{}", teacherRegisterForm);
        if (errors.hasErrors()) {
            log.info("Validation errors occurred");
            return "teacherRegistration";
        }
        Optional<User> user = userService.getUserByUserName(teacherRegisterForm.getUsername());
        if (user.isPresent()) {
            log.info("There is already a user registered with the user name({}) provided", teacherRegisterForm.getUsername());
            errors.rejectValue("username", "error.teacherRegisterForm",
                    "There is already a user registered with the user name provided");
            return "teacherRegistration";
        }
        userService.saveUserAsTeacher(teacherRegisterForm);
        redirectAttributes.addAttribute("successRegistration", true);
        return "redirect:/login";
    }
}
