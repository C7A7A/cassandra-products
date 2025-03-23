package io.github.c7a7a.cassandraproducts.services;

import io.github.c7a7a.cassandraproducts.data.Role;
import io.github.c7a7a.cassandraproducts.data.User;
import io.github.c7a7a.cassandraproducts.exceptions.UserAlreadyExistException;
import io.github.c7a7a.cassandraproducts.repositories.UserRepository;
import io.github.c7a7a.cassandraproducts.utils.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(User user) {
        String username = user.getUsername();
        if (userRepository.findByUsername(username) != null) {
            throw new UserAlreadyExistException("User with username " + username + " already exist");
        }

        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User with username " + username + " not found");
        }

        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User with username " + username + " not found");
        }

        return userMapper.mapToUserDetails(user);
    }
}
