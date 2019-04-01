package com.example.demo.service.UserServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.User;
import com.example.demo.entity.security.Role;
import com.example.demo.repository.RolesRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;



@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
//    @Autowired
//    private AccountService accountService;
	
	public void save(User user) {
		userRepository.save(user);
    }

    public User findByUsername(String username) {
    	Optional<User> usersOptional = userRepository.findByUsername(username);
        if(!usersOptional.isPresent())
        {
        	return null;
        }
                
        return usersOptional.get();
    }

    public User findByEmail(String email) {
    	Optional<User> usersOptional = userRepository.findByEmail(email);
    	 if(!usersOptional.isPresent())
         {
         	return null;
         }
        return usersOptional.get();
    }
    
    
	@Override
    public User createUser(User user) {
    	
    	
        User localUser = findByUsername(user.getUsername());

        if (localUser != null) {
            LOG.info("User with username {} already exist. Please try a new username. ", user.getUsername());
        } else {
            String encryptedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword); 
            
            Set<Role> roles = new HashSet<>(); 
            Role role =rolesRepository.findByName("ROLE_USER");
            roles.add(role);
            user.setRoles(roles);         
            //rolesRepository.saveAll(userRoles);

//            user.setPrimaryAccount(accountService.createPrimaryAccount());
//            user.setSavingsAccount(accountService.createSavingsAccount());

            localUser = userRepository.save(user);
        }

        return localUser;
    }
    
    public boolean checkUserExists(String username, String email){
        if (checkUsernameExists(username) || checkEmailExists(username)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkUsernameExists(String username) {
        if (null != findByUsername(username)) {
            return true;
        }

        return false;
    }
    
    public boolean checkEmailExists(String email) {
        if (null != findByEmail(email)) {
            return true;
        }

        return false;
    }

    public User saveUser (User user) {
        return userRepository.save(user);
    }
    
    public List<User> findUserList() {
        return userRepository.findAll();
    }

    public void enableUser (String username) {
        User user = findByUsername(username);
        user.setEnabled(true);
        userRepository.save(user);
    }

    public void disableUser (String username) {
        User user = findByUsername(username);
        user.setEnabled(false);
        System.out.println(user.isEnabled());
        userRepository.save(user);
        System.out.println(username + " is disabled.");
    }


}
