package com.stackroute.microservice.user.service;





import com.stackroute.domain.User;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.UserRepository;
import com.stackroute.service.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class MuzixServiceTest {
    List<User> list = null;
    private User user;
    //Create a mock for MuzixRepository
    @Mock
    private UserRepository userRepository;
    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    private UserServiceImpl userService;

    //Initialising the mock object
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setUserId(10);
        user.setUserName("jayanthi");
        user.setUserComments("moms love");
        list = new ArrayList<>();
        list.add(user);
    }

    @Test
    public void saveUserTestSuccess() throws TrackAlreadyExistsException {
        when(userRepository.save(any())).thenReturn(user);
        User savedUser = userService.saveUser(user);
        Assert.assertEquals(user, savedUser);

        //verify here verifies that userRepository save method is only called once
        verify(userRepository, times(1)).save(user);
    }

    @Test(expected = TrackAlreadyExistsException.class)
    public void saveUserTestFailure() throws TrackAlreadyExistsException {
        when(userRepository.save(ArgumentMatchers.any())).thenReturn(null);
        User savedUser = userService.saveUser(user);
//        System.out.println("saveduser" + saveduser);
        Assert.assertEquals(user, savedUser);

        //verify here verifies that userRepository save method is only called once
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void getAllUsers() {
        userRepository.save(user);
        //stubbing the mock to return specific data
        when(userRepository.findAll()).thenReturn(list);
        List<User> userlist = userService.getAllUsers();
        Assert.assertEquals(list, userlist);

        //verify here verifies that userRepository save method is only called once
        verify(userRepository, times(1)).save(user);
    }
//    @Test(expected = userNotFoundException.class)
//    public void getAllUsersFailure() throws userNotFoundException{
//        List<user> userlist = muzixService.getAllusers();
//        Assert.assertNull(userlist);
//    }

    @Test
    public void deleteById() throws TrackNotFoundException {
        when(userRepository.existsById(anyInt())).thenReturn(true);
//        when(muzixRepository.save((Track)any())).thenReturn(track);
        userRepository.save(user);
        when(userRepository.findAll()).thenReturn(list);
        List<User> list1 = userService.removeById(user.getUserId());
        Assert.assertEquals(list, list1);

        //verify here verifies that userRepository save method is only called once
        verify(userRepository, times(1)).save(user);
    }

    @Test(expected = TrackNotFoundException.class)
    public void deleteByIdFailure() throws TrackNotFoundException {
        when(userRepository.existsById(2)).thenReturn(true);
        userRepository.save(user);
        when(userRepository.findAll()).thenReturn(list);
        List<User> list1 = userService.removeById(user.getUserId());
        Assert.assertEquals(list, list1);
    }

    @Test
    public void updateUser() throws TrackNotFoundException {
        when(userRepository.existsById(anyInt())).thenReturn(true);
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        when(userRepository.save(any())).thenReturn(user);
        User user1 = userService.updateUser(user.getUserId(), anyString());
        Assert.assertEquals(user1, user);

        //verify here verifies that userRepository save method is only called once
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void userByUserId() throws TrackNotFoundException {
        when(userRepository.existsById(anyInt())).thenReturn(true);
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        userRepository.save(user);
        User user1 = userService.userByuserid(user.getUserId());
        Assert.assertEquals(user1, user);

        //verify here verifies that userRepository save method is only called once
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void userByUserName() throws TrackNotFoundException {
        when(userRepository.findByUserName(anyString())).thenReturn(list);
        List<User> muzixlist = userService.userByusername("jayanthi");
        Assert.assertEquals(list, muzixlist);

        //verify here verifies that userRepository save method is only called twice
        verify(userRepository, times(2)).findByUserName("jayanthi");
    }
}
