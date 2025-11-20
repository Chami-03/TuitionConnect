package com.tutor_buddy.service;

import com.tutor_buddy.model.Tutor;
import com.tutor_buddy.model.TutorBST;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorService {
    private TutorBST tutorBST;

    public TutorService() {
        this.tutorBST = new TutorBST();
    }

    public Tutor registerTutor(Tutor tutor) {
        tutor.setStatus("pending");
        tutorBST.insert(tutor);
        return tutor;
    }

    public List<Tutor> getAllTutors() {
        return tutorBST.getAllTutors();
    }

    public Tutor getTutorById(Long id) {
        Tutor tutor = tutorBST.find(id);
        if (tutor == null) {
            throw new RuntimeException("Tutor not found");
        }
        return tutor;
    }

    public Tutor updateTutor(Long id, Tutor updatedTutor) {
        Tutor tutor = tutorBST.find(id);
        if (tutor == null) {
            throw new RuntimeException("Tutor not found");
        }
        tutor.setFullName(updatedTutor.getFullName());
        tutor.setEmail(updatedTutor.getEmail());
        tutor.setPhone(updatedTutor.getPhone());
        tutor.setHourlyRate(updatedTutor.getHourlyRate());
        tutor.setBio(updatedTutor.getBio());
        tutor.setEducation(updatedTutor.getEducation());
        tutor.setExperienceYears(updatedTutor.getExperienceYears());
        tutor.setSubjects(updatedTutor.getSubjects());
        tutor.setAvailability(updatedTutor.getAvailability());
        tutor.setStatus(updatedTutor.getStatus());
        tutor.setProfilePicture(updatedTutor.getProfilePicture());
        tutor.setRating(updatedTutor.getRating());
        tutor.setSubjectExpertise(updatedTutor.getSubjectExpertise());
        tutorBST.update(tutor);
        return tutor;
    }

    public void deleteTutor(Long id) {
        tutorBST.delete(id);
    }

    public List<Tutor> getTutorsBySubject(String subject) {
        return tutorBST.getTutorsBySubject(subject);
    }

    public List<Tutor> getTutorsByStatus(String status) {
        return tutorBST.getTutorsByStatus(status);
    }

    public List<Tutor> sortTutorsByExpertise(List<Tutor> tutors) {
        if (tutors == null || tutors.size() <= 1) {
            return tutors;
        }
        Tutor[] tutorArray = tutors.toArray(new Tutor[0]);
        mergeSort(tutorArray, 0, tutorArray.length - 1);
        return List.of(tutorArray);
    }

    private void mergeSort(Tutor[] array, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    private void merge(Tutor[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Tutor[] leftArray = new Tutor[n1];
        Tutor[] rightArray = new Tutor[n2];

        // Copy data to temp arrays
        for (int i = 0; i < n1; i++) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = array[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i].getSubjectExpertise() && !rightArray[j].getSubjectExpertise()) {
                array[k] = leftArray[i];
                i++;
            } else if (!leftArray[i].getSubjectExpertise() && rightArray[j].getSubjectExpertise()) {
                array[k] = rightArray[j];
                j++;
            } else {
                if (leftArray[i].getRating() >= rightArray[j].getRating()) {
                    array[k] = leftArray[i];
                    i++;
                } else {
                    array[k] = rightArray[j];
                    j++;
                }
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }
}