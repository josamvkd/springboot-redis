package com.josamtechie.redis.controller;

import com.josamtechie.redis.model.User;
import com.josamtechie.redis.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private  final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService service;

    @PostMapping("/user")
    public User create(@RequestBody User user){
        logger.debug("UserController :create() ",user.toString());
        return service.create(user);
    }

    @GetMapping("/users")
    public List<User> getAll(){
        logger.debug("UserController : getAll() : ");
        return service.getAll();
    }

    @GetMapping("/user/{id}")
    @Cacheable(value = "users", key = "#id")
    public User getUser(@PathVariable long id){
        logger.debug("UserController :getUser() {} call : ",id);
        return service.getUser(id);
    }

    @PutMapping("/update")
    @CachePut(value = "users",key = "#user.id")
    public User updateUser(@RequestBody User user){
        logger.debug("UserController :updateUser() : ",user.toString());
        return service.update(user);
    }

    @DeleteMapping("/delete/{id}")
    @CacheEvict(value = "users",allEntries = false,key = "#id")
    public void delete (@PathVariable Long id ){
        logger.debug("UserController : delete() before : ",id);
        service.delete(id);
        logger.debug("UserController : delete() : ",id);
    }
}
