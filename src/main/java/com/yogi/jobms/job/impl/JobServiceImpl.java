package com.yogi.jobms.job.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.*;
import com.yogi.jobms.job.dto.JobWithCompanyDto;
import com.yogi.jobms.job.external.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yogi.jobms.job.Job;
import com.yogi.jobms.job.JobRepo;
import com.yogi.jobms.job.JobService;
import org.springframework.web.client.RestTemplate;

import static org.hibernate.internal.util.collections.ArrayHelper.forEach;

@Service
public class JobServiceImpl implements JobService {
    // private List<Job> jobs = new ArrayList<>();

    @Autowired
    private JobRepo jobRepo;

    @Override
    public List<JobWithCompanyDto> findAll() {
        // TODO Auto-generated method stub

        List<Job>jobs=jobRepo.findAll();
        List<JobWithCompanyDto>jobWithCompanyDtos=new ArrayList<>();

        RestTemplate restTemplate=new RestTemplate();

        for(Job j : jobs){
            JobWithCompanyDto jobWithCompanyDto=new JobWithCompanyDto();
            jobWithCompanyDto.setJob(j);
            Company company=  restTemplate.getForObject("http://localhost:8081/companies/"+j.getCompany_id(), Company.class);
            jobWithCompanyDto.setCompany(company);
            jobWithCompanyDtos.add(jobWithCompanyDto);
        }

//      Company company=  restTemplate.getForObject("http://localhost:8081/companies/1", Company.class);
//        System.out.println("Company : "+ company.getName());
//        System.out.println("id: "+ company.getId());
        return jobWithCompanyDtos;
        // throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public void createJob(Job job) {
        // TODO Auto-generated method stub
        jobRepo.save(job);
        // throw new UnsupportedOperationException("Unimplemented method 'createJob'");
    }

    @Override
    public Job getJobById(Long id) {
        Optional<Job> jb = jobRepo.findById(id);
        if (jb.isPresent()) {
            return jb.get();
        }
        return null;
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getJobById'");
    }

    @Override
    public boolean deleteJobById(Long id) {

        Optional<Job> jb = jobRepo.findById(id);
        if (jb.isPresent()) {
            Job myJob = jb.get();
            jobRepo.delete(myJob);
            return true;
        }
        return false;
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'deleteJobById'");
    }

    @Override
    public boolean updateJob(Long id, Job jb) {
        Optional<Job> jb2 = jobRepo.findById(id);
        if (jb2.isPresent()) {
            Job j = jb2.get();
            j.setTitle(jb.getTitle());
            j.setDescription(jb.getDescription());
            j.setMaxSalary(jb.getMaxSalary());
            j.setMinSalary(jb.getMinSalary());
            j.setLocation(jb.getLocation());
            j.setCompany_id(jb.getCompany_id());
            jobRepo.save(j);
            return true;
        }
        return false;
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'updateJob'");
    }

}
