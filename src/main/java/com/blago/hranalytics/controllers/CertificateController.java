package com.blago.hranalytics.controllers;

import com.blago.hranalytics.models.MedicalExamination;
import com.blago.hranalytics.models.NarcologistCertificate;
import com.blago.hranalytics.models.PsychiatristCertificate;
import com.blago.hranalytics.services.EmployeeService;
import com.blago.hranalytics.services.MedicalExaminationService;
import com.blago.hranalytics.services.NarcologistCertificateService;
import com.blago.hranalytics.services.PsychiatristCertificateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class CertificateController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MedicalExaminationService medicalExaminationService;

    @Autowired
    private PsychiatristCertificateService psychiatristCertificateService;

    @Autowired
    private NarcologistCertificateService narcologistCertificateService;

    @GetMapping("/choose-employee-certificate")
    ModelAndView createCertificate() {
        return new ModelAndView("certificate/choose-employee")
                .addObject("employees", employeeService.getAll());
    }

    @GetMapping("/create-medical-certificate")
    public ModelAndView createMedicalCertificate(@RequestParam Integer employeeId) {
        if (employeeService.getFullNameById(employeeId).isPresent()) {
            String employeeName = employeeService.getFullNameById(employeeId).get();
            MedicalExamination medicalExamination = new MedicalExamination();
            medicalExamination.setEmployeeId(employeeId);
            return new ModelAndView("certificate/medical-examination/create-form")
                    .addObject("employeeName", employeeName)
                    .addObject("newMedicalExamination", medicalExamination)
                    .addObject("validMedicalExaminations", medicalExaminationService.getValidMedicalExaminationsByEmployeeId(employeeId));
        }
        log.error("Employee not found");
        return new ModelAndView("certificate/creating-error");
    }

    @PostMapping("/save-medical-certificate")
    String saveMedicalCertificate(@ModelAttribute MedicalExamination examination) {
        if (medicalExaminationService.save(examination)) {
            return "redirect:/create-medical-certificate?employeeId=" + examination.getEmployeeId();
        }
        log.error("Medical examination saving error");
        return "certificate/creating-error";
    }

    @GetMapping("/create-psychiatric-certificate")
    ModelAndView createPsychiatristCertificate(@RequestParam Integer employeeId) {
        if (employeeService.getFullNameById(employeeId).isPresent()) {
            String employeeName = employeeService.getFullNameById(employeeId).get();
            PsychiatristCertificate psychiatristCertificate = new PsychiatristCertificate();
            psychiatristCertificate.setEmployeeId(employeeId);
            return new ModelAndView("certificate/psychiatric/create-form")
                    .addObject("employeeName", employeeName)
                    .addObject("newPsychiatristCertificate", psychiatristCertificate)
                    .addObject("validPsychiatristCertificates",
                            psychiatristCertificateService.getValidPsychiatristCertificatesByEmployeeId(employeeId));
        }
        log.error("Employee not found");
        return new ModelAndView("certificate/creating-error");
    }

    @PostMapping("/save-psychiatric-certificate")
    String savePsychiatristCertificate(@ModelAttribute PsychiatristCertificate psychiatristCertificate) {
        if (psychiatristCertificateService.save(psychiatristCertificate)) {
            return "redirect:/create-psychiatric-certificate?employeeId=" + psychiatristCertificate.getEmployeeId();
        }
        log.error("Psychiatrist certificate saving error");
        return "certificate/creating-error";
    }

    @GetMapping("/create-narcologist-certificate")
    ModelAndView createNarcologistCertificate(@RequestParam Integer employeeId) {
        if (employeeService.getFullNameById(employeeId).isPresent()) {
            String employeeName = employeeService.getFullNameById(employeeId).get();
            NarcologistCertificate narcologistCertificate = new NarcologistCertificate();
            narcologistCertificate.setEmployeeId(employeeId);
            return new ModelAndView("certificate/narcologist/create-form")
                    .addObject("employeeName", employeeName)
                    .addObject("newNarcologistCertificate", narcologistCertificate)
                    .addObject("validNarcologistCertificates", narcologistCertificateService.getValidCertificatesByEmployeeId(employeeId));

        }
        log.error("Employee not found");
        return new ModelAndView("certificate/creating-error");
    }

    @PostMapping("/save-narcologist-certificate")
    String saveNarcologistCertificate(@ModelAttribute NarcologistCertificate narcologistCertificate) {
        if (narcologistCertificateService.save(narcologistCertificate)) {
            return "redirect:/create-narcologist-certificate?employeeId=" + narcologistCertificate.getEmployeeId();
        }
        log.error("Narcologist certificate saving error");
        return "certificate/creating-error";
    }

    @GetMapping("/certificate-list")
    ModelAndView certificateList(@RequestParam Integer employeeId) {
        if (employeeService.getFullNameById(employeeId).isPresent()) {
            String employeeName = employeeService.getFullNameById(employeeId).get();
            return new ModelAndView("certificate/certificate-list")
                    .addObject("employeeName", employeeName)
                    .addObject("medicalExaminations", medicalExaminationService.getAllByEmployeeId(employeeId))
                    .addObject("psychiatristCertificates", psychiatristCertificateService.getAllByEmployeeId(employeeId))
                    .addObject("narcologistCertificates", narcologistCertificateService.getAllByEmployeeId(employeeId));
        }
        log.error("Employee not found");
        return new ModelAndView("certificate/creating-error");
    }

    @GetMapping("/certificate-profile")
    ModelAndView getCertificateProfile(@RequestParam Integer id, @RequestParam String type) {
        switch (type) {
            case "medical":
                return new ModelAndView("certificate/medical-examination/profile")
                        .addObject("medicalExamination", medicalExaminationService.getById(id).get());
            case "psychiatric":
                return new ModelAndView("certificate/psychiatric/profile")
                        .addObject("psychiatristCertificate", psychiatristCertificateService.getById(id).get());
            case "narcologist":
                return new ModelAndView("certificate/narcologist/profile")
                        .addObject("narcologistCertificate", narcologistCertificateService.getById(id).get());
            default:
                log.error("Certificate type not found");
                return new ModelAndView("certificate/creating-error");
        }
    }

    @PostMapping("update-medical-certificate")
    String updateMedicalCertificate(@ModelAttribute MedicalExamination medicalExamination) {
        if (medicalExaminationService.update(medicalExamination)) {
            return "redirect:/certificate-profile?id=" + medicalExamination.getMedicalId() + "&type=medical";
        }
        log.error("Medical examination updating error");
        return "certificate/creating-error";
    }

    @PostMapping("update-psychiatric-certificate")
    String updatePsychiatristCertificate(@ModelAttribute PsychiatristCertificate psychiatristCertificate) {
        if (psychiatristCertificateService.update(psychiatristCertificate)) {
            return "redirect:/certificate-profile?id=" + psychiatristCertificate.getId() + "&type=psychiatric";
        }
        log.error("Psychiatrist certificate updating error");
        return "certificate/creating-error";
    }

    @PostMapping("update-narcologist-certificate")
    String updateNarcologistCertificate(@ModelAttribute NarcologistCertificate narcologistCertificate) {
        if (narcologistCertificateService.update(narcologistCertificate)) {
            return "redirect:/certificate-profile?id=" + narcologistCertificate.getId() + "&type=narcologist";
        }
        log.error("Narcologist certificate updating error");
        return "certificate/creating-error";
    }

    @GetMapping("/delete-certificate")
    String deleteCertificate(@RequestParam Integer id, String type){
        switch (type) {
            case "medical":
                Integer medicalEmployeeId = medicalExaminationService.getById(id).get().getEmployeeId();
                if (medicalExaminationService.delete(id)) {
                    return "redirect:/certificate-list?employeeId=" + medicalEmployeeId;
                }
                log.error("Medical examination deleting error");
                break;
            case "psychiatric":
                Integer psychiatricEmployeeId = psychiatristCertificateService.getById(id).get().getEmployeeId();
                if(psychiatristCertificateService.delete(id)){
                    return "redirect:/certificate-list?employeeId=" + psychiatricEmployeeId;
                }
                log.error("Psychiatrist certificate deleting error");
                break;
            case "narcologist":
                Integer narcologistEmployeeId = narcologistCertificateService.getById(id).get().getEmployeeId();
                if(narcologistCertificateService.delete(id)){
                    return "redirect:/certificate-list?employeeId=" + narcologistEmployeeId;
                }
                log.error("Narcologist certificate deleting error");
                break;
            default:
                return "certificate/creating-error";
        }
        return "certificate/creating-error";
    }
}

