package com.company.controller;

import com.company.dto.ResponseDTO;
import com.company.dto.UserDTO;
import com.company.entity.User;
import com.company.service.inter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

//-------------------------
@RestController
public class UserRestController  {
    @Autowired
    private UserServiceInter userService;

    @Transactional
    @CrossOrigin(origins = "*")
    @GetMapping("/users")
    public ResponseEntity<ResponseDTO> getUsers(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "surname", required = false) String surname,
            @RequestParam(name = "age", required = false) Integer age
    ) {
        List<User> users = userService.getAll(name, surname, age);

        List<UserDTO> userDTOS = new ArrayList<>();

        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            userDTOS.add(new UserDTO(u));
        }

        return ResponseEntity.ok(ResponseDTO.of(userDTOS));
    }

    @Transactional
    @CrossOrigin(origins = "*")
    @GetMapping("/users/{id}")
    public ResponseEntity<ResponseDTO> getUserById(@PathVariable("id") int id) {
        User user = userService.getById(id);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(ResponseDTO.of(new UserDTO(user)));
    }

    @Transactional
    @CrossOrigin(origins = "*")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable("id") int id) {
        User user = userService.getById(id);
        userService.removeUser(id);

        return ResponseEntity.ok(ResponseDTO.of(new UserDTO(user), "Successfully deleted"));
    }
    @Transactional
    @CrossOrigin(origins = "*", methods = RequestMethod.POST)
    @PostMapping("/users")
    public ResponseEntity<ResponseDTO> addUser(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
//  sonra duzelis edecem
//        user.setPhone(userDTO.getPhone());
//        user.setProfileDesc(userDTO.getProfileDesc());
//        user.setAddress(userDTO.getAddress());
//        user.setBirthdate(userDTO.getBirthdate());


        userService.addUser(user);

        UserDTO  userDto = new UserDTO();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setEmail(user.getEmail());

        return ResponseEntity.ok(ResponseDTO.of(userDto, "Successful added"));
    }


}


