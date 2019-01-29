package com.stackroute.service;

//import com.stackroute.domain.Track;
import com.stackroute.domain.User;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;

import java.util.List;
//MuzixService interface//
public interface UserService {
    public User saveUser(User user) throws TrackAlreadyExistsException;
    public List<User> getAllUsers() ;
    public List<User> removeById(int userid) throws TrackNotFoundException;
    public User updateUser(int userid,String userComments) throws TrackNotFoundException;
    public List<User> userByusername(String username)throws TrackNotFoundException;
    public User userByuserid(int userid)throws TrackNotFoundException;
}
