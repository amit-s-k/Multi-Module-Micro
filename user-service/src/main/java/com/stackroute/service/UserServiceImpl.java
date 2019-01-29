package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Overrided method for saveTrack//
    @Override
    public User saveUser(User user) throws TrackAlreadyExistsException {

        if (userRepository.existsById(user.getUserId())) {
            throw new TrackAlreadyExistsException("user already exists");
        }
        User savedUser = userRepository.save(user);
        if (savedUser == null) {
            throw new TrackAlreadyExistsException("user already exists");
        }
        return savedUser;
    }

    //Overrided method for getAllTracks//
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //Overrided method for deleteById//
    @Override
    public List<User> removeById(int userid) throws TrackNotFoundException {
        if (!userRepository.existsById(userid)) {
            throw new TrackNotFoundException("user not found");
        }
        userRepository.deleteById(userid);
        return userRepository.findAll();
    }

    //Overrided method for updateTrack //
    @Override
    public User updateUser(int userid, String comment) throws TrackNotFoundException {

        if (!userRepository.existsById(userid)) {
            throw new TrackNotFoundException("user not found to update");
        }
        Optional<User> muzix = userRepository.findById(userid);
        User muzix1 = muzix.get();
        muzix1.setUserComments(comment);



        User savedMuzix = userRepository.save(muzix1);
        return savedMuzix;
    }

    //Overrided method for findByTrackId//
    @Override
    public User userByuserid(int userid) throws TrackNotFoundException {
        if (!userRepository.existsById(userid)) {
            throw new TrackNotFoundException("user not found to update");
        }
        Optional<User> muzix1 = userRepository.findById(userid);
        User user = muzix1.get();
        return user;
    }

    //Overrided method for findByTrackName//
    public List<User> userByusername(String username) throws TrackNotFoundException {
        if (userRepository.findByUserName(username).isEmpty()) {
            throw new TrackNotFoundException("user with given name is not found");
        }
        return userRepository.findByUserName(username);
    }
}
