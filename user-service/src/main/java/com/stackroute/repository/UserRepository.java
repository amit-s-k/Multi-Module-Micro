package com.stackroute.repository;

//import com.stackroute.domain.Track;
import com.stackroute.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends MongoRepository<User,Integer> {
    public List<User> findByUserName(String username);
}


