package com.josamtechie.redis.service;

import com.josamtechie.redis.model.User;
import com.josamtechie.redis.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository repository;

    public User create(User user) {
        logger.debug("UserService : create() Enter create");
        User userToRet = repository.save(user);
        logger.debug("UserService : create() exit create");
       return userToRet;
    }

    public List<User> getAll() {
        logger.debug("UserService : getAll() : Enter getAll");
        logger.debug("UserService : getAll() : Exit getAll");
        return repository.findAll();
    }

    public User getUser(Long id) {
        logger.debug("UserService : getUser() Enter getUser");
        Optional<User> userOpt = repository.findById(id);
        if(userOpt !=null){
            logger.debug("UserService : getUser() Exit getUser");
            return userOpt.get();
        }else{
            logger.debug(" << UserService : getUser(), No Such User Exists : Exit getUser");
            return null;
        }
    }

    public User update(User user) {
        logger.debug("UserService : update() Enter update");
        Long id = user.getId();
        User userInDb = getUser(id);
        if(userInDb !=null){
            logger.debug("UserService :update() : Exit update");
            return create(user);
        }else{
            return null;
        }
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
