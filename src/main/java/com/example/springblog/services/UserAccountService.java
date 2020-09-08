package com.example.springblog.services;

import com.example.springblog.model.UserAccount;
import com.example.springblog.repositories.UserAccountRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class UserAccountService {

    private UserAccountRepository userAccountRepository;


    public Optional<UserAccount> findById(Long userId){
        return userAccountRepository.findById(userId);
    }

    public Optional<UserAccount> findByUserPass(String username, String password) {
        return userAccountRepository.findByUsernameAndPassword(username, password);
    }

    public List<UserAccount> findAllUsers() {
        return userAccountRepository.findAll();
    }

    public Boolean hasUserEmail(UserAccount userAccount) {

        if(userAccountRepository.existsByUsername(userAccount.getUsername()) || userAccountRepository.existsByEmail(userAccount.getEmail()))
            return true;

        return false;
    }

    public UserAccount saveUserAccount(UserAccount newUserAccount) throws IllegalArgumentException {

        return userAccountRepository.save(newUserAccount);

    }

    public Optional<UserAccount> updateUserAccount(Long userId, UserAccount newUserAccount) throws IllegalArgumentException {

        Optional<UserAccount> currentUserAccount = findById(userId);
        if(currentUserAccount.isPresent()) {
            currentUserAccount.get().setUsername(newUserAccount.getUsername());
            currentUserAccount.get().setPassword(newUserAccount.getPassword());
            currentUserAccount.get().setEmail(newUserAccount.getEmail());
            userAccountRepository.save(currentUserAccount.get());
        }
        return currentUserAccount;
    }

    public boolean deleteUserAccountById(Long userId) {

        Optional<UserAccount> currentAccount = findById(userId);
        if(currentAccount.isPresent()) {
            userAccountRepository.deleteById(userId);
            return true;
        }
        return false;
    }
}
