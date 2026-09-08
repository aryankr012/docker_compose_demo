package com.profile.studentstore.repo;

import com.profile.studentstore.pojo.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends MongoRepository<Student, String> {

    List<Student> findByName(String name);
}
