package com.dto.testdto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
 
import com.dto.testdto.model.User;
import com.dto.testdto.model.Worker;

public interface WorkerRepository extends JpaRepository <Worker,String>{

	Worker findByUser(User user);
    
}
