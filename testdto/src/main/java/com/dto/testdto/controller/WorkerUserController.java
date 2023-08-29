package com.dto.testdto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dto.testdto.repository.UserRepository;
import com.dto.testdto.repository.WorkerRepository;
import com.dto.testdto.dto.UserWorkerDTO;
import com.dto.testdto.model.User;
import com.dto.testdto.model.Worker;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/user-worker")
public class WorkerUserController {

    //insert repo for the both model
    private UserRepository userRepo;
    private WorkerRepository workerRepo;

    //inject the both repo by creating argument constructor
    @Autowired
    public WorkerUserController(UserRepository userRepo, WorkerRepository workerRepo) {
        this.userRepo = userRepo;
        this.workerRepo = workerRepo;
    }

    //for getmapping first you must create arraylist
    //then findall 
    //loop foreach user find the worker by using worker repo and findbyuser
    //create instance for the DTO and then set what information that you want to get
    @GetMapping(value="/alluser")
    public ResponseEntity<List<UserWorkerDTO>> getUser(){
        List<UserWorkerDTO> workerUser = new ArrayList<>();

        List<User> users = userRepo.findAll();
        for (User user : users)
        {
            Worker worker = workerRepo.findByUser(user);

            UserWorkerDTO userWorkerDTO = new UserWorkerDTO();

            userWorkerDTO.setName(worker.getName());
            userWorkerDTO.setAddress(worker.getAddress());
            userWorkerDTO.setPhoneNumber(worker.getPhoneNumber());
            userWorkerDTO.setEmail(worker.getEmail());
            userWorkerDTO.setIcNumber(user.getIcNumber());
            userWorkerDTO.setRole(user.getRole());

            workerUser.add(userWorkerDTO);
        } 
        return ResponseEntity.ok(workerUser);
    }

    //for postmapping you must create the data based on the table 
    //create instance for each table and then put the column to be input inside the instance
    //must follow the order in the argument constructor if not the data will be switch when post request
    //save
    @PostMapping(value="/register")
    public Worker addUser(@RequestBody UserWorkerDTO userWorkerDTO){
        User user = new User(
            userWorkerDTO.getPassword(),
            userWorkerDTO.getIcNumber(),
            userWorkerDTO.getRole()
        );
        userRepo.save(user);
        
        Worker worker = new Worker(
          userWorkerDTO.getName(),
          userWorkerDTO.getAddress(),
          userWorkerDTO.getPhoneNumber(),
          userWorkerDTO.getGender(), 
          userWorkerDTO.getEmail(),
          user
        );
        workerRepo.save(worker);

        return worker;
    }

    //for putmapping first must get path variable which is id
    //then use optional and use worker repo to find the user by id
    //set statement if the optional is not null then create instance for worker 
    //then create intance for worker by using get method that already declared in worker model
    //set the column to be update by using setter and getter method then save it
    @PutMapping(value="/update-user/{idWorker}")
    public ResponseEntity<Worker> updateUser(@PathVariable String idWorker, @RequestBody UserWorkerDTO userWorkerDTO)
    {
        Optional<Worker> workerOptional = workerRepo.findById(idWorker);

        if(workerOptional.isPresent()){
            Worker worker = workerOptional.get();
            User user = worker.getUser();

            user.setIcNumber(userWorkerDTO.getIcNumber());
            user.setPassword(userWorkerDTO.getPassword());
            user.setRole(userWorkerDTO.getRole());

            userRepo.save(user);

            worker.setName(userWorkerDTO.getName());
            worker.setAddress(userWorkerDTO.getAddress());
            worker.setPhoneNumber(userWorkerDTO.getPhoneNumber());
            worker.setGender(userWorkerDTO.getGender());
            worker.setEmail(userWorkerDTO.getEmail());

            workerRepo.save(worker);
            return ResponseEntity.ok(worker);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    //for deletemapping first you must use path variable to get the id to be delete
    //then create optional for worker and find the worker using id
    //after that use if statement and get the worker and the user
    //you must delete the child first before delete the parent table or else it will throw error.
    @DeleteMapping(value="/delete-user/{idWorker}")
    public ResponseEntity<String> deleteUser(@PathVariable String idWorker)
    {
        Optional<Worker> optionalWorker = workerRepo.findById(idWorker);
        if(optionalWorker.isPresent())
        {
            Worker worker = optionalWorker.get();
            User user = worker.getUser();
            workerRepo.delete(optionalWorker.get());
            userRepo.delete(user);

            return ResponseEntity.ok("Deleted");
        }
        else 
        {
            return ResponseEntity.notFound().build();
        }
    }
}
