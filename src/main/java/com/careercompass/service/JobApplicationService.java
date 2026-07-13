package com.careercompass.service;

import com.careercompass.entity.JobApplication;
import com.careercompass.repository.JobApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobApplicationService {

    private final JobApplicationRepository repository;

    public JobApplicationService(JobApplicationRepository repository) {
        this.repository = repository;
    }

    public List<JobApplication> getAllJobs() {
        return repository.findAll();
    }

    public JobApplication saveJob(JobApplication jobApplication) {
        return repository.save(jobApplication);
    }

    public JobApplication getJobById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteJob(Long id) {
        repository.deleteById(id);
    }

    public void updateJob(JobApplication jobApplication) {
        repository.save(jobApplication);
    }

    public JobApplication findById(Long id) {
        return repository.findById(id).orElse(null);
    }

}

