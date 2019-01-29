package com.stackroute.controller;

import com.stackroute.domain.User;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.Comment;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1")
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Saving track information such as trackId,trackcomments,trackname//
    @PostMapping("user")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        ResponseEntity responseEntity;
        try {
            userService.saveUser(user);
            responseEntity = new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    //Displaying saved track.//
    @GetMapping("users")
    public ResponseEntity<?> getAllUsers() {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    //Removing track.//
    @DeleteMapping("user/{userid}")
    public ResponseEntity<?> deleteById(@PathVariable int userid) {
        ResponseEntity responseEntity;
        try {
            userService.removeById(userid);
            responseEntity = new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.FOUND);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    //Update track.//
    @PutMapping("user/{userid}")
    public ResponseEntity<?> updateUser(@PathVariable int userid,@RequestBody User user) throws TrackNotFoundException {
        ResponseEntity responseEntity;
        try {
            userService.updateUser(userid, user.getUserComments());
            responseEntity = new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    //Method to track by id//
    @GetMapping("user/{userid}")
    public ResponseEntity<?> getById(@PathVariable int userid) throws TrackNotFoundException {
        ResponseEntity responseEntity;
        try {
            return new ResponseEntity(userService.userByuserid(userid), HttpStatus.FOUND);
        }catch (Exception ex){
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    //Method to track by name//
    @GetMapping("tracks/{trackName}")
    public ResponseEntity<?> getByName(@PathVariable String username) throws TrackNotFoundException {
        ResponseEntity responseEntity;
        try {
            return new ResponseEntity<List<User>>(userService.userByusername(username), HttpStatus.OK);
        }catch (Exception ex){
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }
}

