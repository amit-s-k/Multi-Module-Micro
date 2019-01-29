package com.stackroute.microservice.user.repository;


import com.stackroute.domain.User;
import com.stackroute.repository.UserRepository;
import com.stackroute.service.UserServiceImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

//JUnit will invoke the class it references to run the tests in that class instead of the runner built into JUnit.
@RunWith(SpringJUnit4ClassRunner.class)
// Used when a test focuses only on MongoDB components.
@DataMongoTest
public class UserRepositoryIT {

    @Autowired
    private UserRepository userRepository;
    private User user;
    private List<User> list;

    @Before
    public void setUp() {
        user = new User();
        user.setUserId(10);
        user.setUserName("jayanthi");
        user.setUserComments("moms love");
        list = new ArrayList<>();
        list.add(user);

    }

    @After
    public void tearDown() {

        userRepository.deleteAll();
    }

    @Test
    public void testSaveUser() {
        userRepository.save(user);
        User fetchUser = userRepository.findById(user.getUserId()).get();
        Assert.assertEquals(10, fetchUser.getUserId());
    }

    @Test
    public void testSaveUserFailure() {
        User testUser = new User(10, "jayanthi", "moms love");
        userRepository.save(user);
        User fetchUser = userRepository.findById(user.getUserId()).get();
        Assert.assertNotSame(testUser, user);
    }

    @Test
    public void testGetAllUser() {
        User u = new User(10, "jayanthi", "moms love");
        User u1 = new User(20, "daddy", "dads love");
        userRepository.save(u);
        userRepository.save(u1);

        List<User> list = userRepository.findAll();
        Assert.assertEquals("jayanthi", list.get(0).getUserName());
    }

    @Test
    public void testGetAllUserFailure() {
        User u = new User(10,"jayanthi", "moms love");
        User u1 = new User(20, "daddy", "dads love");
        userRepository.save(u);
        userRepository.save(u1);

        List<User> list = userRepository.findAll();
        Assert.assertNotEquals("john", list.get(0).getUserName());
    }

    @Test
    public void testDeleteUserSuccess() {
        User u = new User(10, "jayanthi", "moms love");
        userRepository.save(u);
        userRepository.delete(u);
        Assert.assertEquals(Optional.empty(), userRepository.findById(10));
    }

    @Test
    public void testDeleteUserFailure() {
        User u = new User(10, "jayanth", "moms love");
        userRepository.save(u);
        userRepository.delete(u);
        Assert.assertNotEquals(u, userRepository.findById(10));
    }

    @Test
    public void testUpdateUserSuccess() {
        user.setUserComments("novel");
        userRepository.save(user);
        Assert.assertEquals("novel", userRepository.findById(10).get().getUserComments());
    }

    @Test
    public void testUpdateUserFailure() {
        user.setUserComments("novel");
        userRepository.save(user);
        Assert.assertNotEquals("novelist", userRepository.findById(10).get().getUserComments());
    }

    @Test
    public void testFindByUserIdSuccess() {
        userRepository.save(user);
        User output = userRepository.findById(10).get();
        System.out.println(output);
        Assert.assertEquals(user, output);
    }

    @Test(expected = NoSuchElementException.class)
    public void testFindByUserIdFailure() {
        User output = userRepository.findById(101).get();
        System.out.println(output);
        Assert.assertEquals(null, output);
    }

    @Test
    public void testFindByUserNameSuccess() {
        List<User> expectedOutput = new ArrayList<>();
        userRepository.save(user);
        expectedOutput.add(user);
        List<User> output = userRepository.findByUserName("jayanthi");
        System.out.println(output);
        System.out.println(expectedOutput);
        Assert.assertEquals(expectedOutput, output);
    }

    @Test
    public void testFindByUserNameFailure() {
        List<User> expectedOutput = new ArrayList<>();
        expectedOutput.add(user);
        List<User> output = userRepository.findByUserName("John");
        Assert.assertNotEquals(expectedOutput, output);
    }
}