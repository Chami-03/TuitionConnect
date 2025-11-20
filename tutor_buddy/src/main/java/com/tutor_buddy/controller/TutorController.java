package com.tutor_buddy.controller;


import com.tutor_buddy.model.Tutor;
import com.tutor_buddy.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/tutors")
    public class TutorController {
        @Autowired
        private TutorService tutorService;

        @PostMapping("/register")
        public Tutor registerTutor(@RequestBody Tutor tutor) {
            return tutorService.registerTutor(tutor);
        }

        @GetMapping
        public List<Tutor> getAllTutors(@RequestParam(required = false) String subject,
                                        @RequestParam(required = false) String status,
                                        @RequestParam(required = false) String sortBy) {
            List<Tutor> tutors;
            if (subject != null) {
                tutors = tutorService.getTutorsBySubject(subject);
            } else if (status != null) {
                tutors = tutorService.getTutorsByStatus(status);
            } else {
                tutors = tutorService.getAllTutors();
            }
            if ("expertise".equals(sortBy)) {
                tutors = tutorService.sortTutorsByExpertise(tutors);
            }
            return tutors;
        }

        @GetMapping("/{id}")
        public Tutor getTutorById(@PathVariable Long id) {
            return tutorService.getTutorById(id);
        }

        @PutMapping("/{id}")
        public Tutor updateTutor(@PathVariable Long id, @RequestBody Tutor tutor) {
            return tutorService.updateTutor(id, tutor);
        }

        @DeleteMapping("/{id}")
        public void deleteTutor(@PathVariable Long id) {
            tutorService.deleteTutor(id);
        }
    }

