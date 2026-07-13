package com.careercompass.controller;

import com.careercompass.entity.JobApplication;
import com.careercompass.service.JobApplicationService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class JobApplicationController {

    private final JobApplicationService service;

    public JobApplicationController(JobApplicationService service) {
        this.service = service;
    }

    // Home Page
    @GetMapping("/")
    public String home(Model model) {

        List<JobApplication> jobs = service.getAllJobs();

        long totalJobs = jobs.size();

        long interviews = jobs.stream()
                .filter(job -> job.getStatus() != null &&
                        job.getStatus().contains("Interview"))
                .count();

        long offers = jobs.stream()
                .filter(job -> job.getStatus() != null &&
                        job.getStatus().equals("Offer Received"))
                .count();

        long rejected = jobs.stream()
                .filter(job -> job.getStatus() != null &&
                        job.getStatus().equals("Rejected"))
                .count();

        model.addAttribute("jobs", jobs);
        model.addAttribute("totalJobs", totalJobs);
        model.addAttribute("interviews", interviews);
        model.addAttribute("offers", offers);
        model.addAttribute("rejected", rejected);

        return "index";
    }

    // Show Add Job Form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("jobApplication", new JobApplication());
        return "add-job";
    }

    // Save Job
    @PostMapping("/save")
    public String saveJob(@ModelAttribute JobApplication jobApplication) {

        service.saveJob(jobApplication);

        return "redirect:/";
    }

    // Show Edit Form
    @GetMapping("/edit/{id}")
    public String editJob(@PathVariable Long id, Model model) {

        JobApplication job = service.getJobById(id);

        model.addAttribute("jobApplication", job);

        return "edit-job";
    }

    // Delete Job
    @GetMapping("/delete/{id}")
    public String deleteJob(@PathVariable Long id) {

        service.deleteJob(id);

        return "redirect:/";
    }
}