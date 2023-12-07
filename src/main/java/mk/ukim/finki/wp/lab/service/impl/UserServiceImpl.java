package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.repository.jpaUserRepository;
import mk.ukim.finki.wp.lab.service.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final jpaUserRepository userRepository;

    public UserServiceImpl(jpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User saveOrUpdateUser(String username) {
        User existingUser = findByUsername(username);
        if (existingUser != null) {
            return existingUser;
        } else {
            return userRepository.save(new User(username));
        }
    }

}
