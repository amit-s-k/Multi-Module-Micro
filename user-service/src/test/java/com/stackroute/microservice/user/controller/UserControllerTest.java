package com.stackroute.microservice.user.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.controller.UserController;
import com.stackroute.domain.User;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private User user;
    @MockBean
    private UserService userService;
    @InjectMocks
    private UserController userController;
    private List<User> list = null;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        user = new User();
        user.setUserId(10);
        user.setUserName("Jenny");
        user.setUserComments("Jonny123");
        list = new ArrayList();
        list.add(user);
    }

    @Test
    public void saveUser() throws Exception {
        when(userService.saveUser(any())).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void saveUserFailure() throws Exception {
        when(userService.saveUser(any())).thenThrow(TrackAlreadyExistsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteById() throws Exception {
        when(userService.removeById(user.getUserId())).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/user/10")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteByIdFailure() throws Exception {
        when(userService.removeById(user.getUserId())).thenThrow(TrackNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/user/10")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateUser() throws Exception {
        when(userService.updateUser(user.getUserId(), user.getUserComments())).thenReturn(user);
        when(userService.saveUser(any())).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/user/10")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateUserFailure() throws Exception {
        when(userService.updateUser(user.getUserId(), user.getUserComments())).thenThrow(TrackNotFoundException.class);
        when(userService.saveUser(any())).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/user/10")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getById() throws Exception {
        when(userService.userByuserid(user.getUserId())).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/10")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andDo(MockMvcResultHandlers.print());
    }

//    @Test
//    public void getByIdFailure() throws Exception {
//        when(userService.userByuserid(user.getUserid())).thenThrow(TrackNotFoundException.class);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/User/10")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
//                .andExpect(MockMvcResultMatchers.status().isConflict())
//                .andDo(MockMvcResultHandlers.print());
//    }
////
//    @Test
//    public void getByName() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/Jenny")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }

    @Test
    public void getByNameFailure() throws Exception {
        when(userService.userByusername(user.getUserName())).thenThrow(TrackNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/Jenny")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
}