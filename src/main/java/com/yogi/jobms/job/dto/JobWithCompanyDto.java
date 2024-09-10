package com.yogi.jobms.job.dto;

import com.yogi.jobms.job.Job;
import com.yogi.jobms.job.external.Company;

public class JobWithCompanyDto {
    private Job job;
    private Company company;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
