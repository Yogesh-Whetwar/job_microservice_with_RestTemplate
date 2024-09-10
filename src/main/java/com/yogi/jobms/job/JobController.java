package com.yogi.jobms.job;

import java.util.ArrayList;
import java.util.List;

import com.yogi.jobms.job.dto.JobWithCompanyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yogi.jobms.job.impl.JobServiceImpl;

@RequestMapping("/jobs")
@RestController
public class JobController {  

   @Autowired
   private JobServiceImpl jobServiceImpl;

      @GetMapping
     public ResponseEntity<List<JobWithCompanyDto>> findAll(){
        return new ResponseEntity<>(jobServiceImpl.findAll(),HttpStatus.OK);
     }

   @PostMapping
   public  ResponseEntity<String> createJob( @RequestBody Job jb  ){  
       jobServiceImpl.createJob(jb);
       return new ResponseEntity<>("Job Created Successfully",HttpStatus.OK);
   } 

   @GetMapping("/{id}")
   public ResponseEntity<Job> getJobById(@PathVariable Long id ){
       Job job=jobServiceImpl.getJobById(id);
       if(job!=null)
       return new ResponseEntity<>(job,HttpStatus.OK);
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }
   
   @DeleteMapping("/{id}")
   public ResponseEntity<String> deleteJobById(@PathVariable Long id){
    boolean jb=jobServiceImpl.deleteJobById(id);
    if(jb)
    return new ResponseEntity<>("Job deleted Succesfully",HttpStatus.OK);
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }  

   @PutMapping("/{id}")
   public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job jb){
      boolean updated=jobServiceImpl.updateJob(id,jb);
      if(updated)
      return new ResponseEntity<>("Job Updated Succesfully",HttpStatus.OK);
    
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }
}
