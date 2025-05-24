package com.backendguru.microservice.user.domain;

import java.time.Instant;
import java.util.Collections;
import java.util.Set;

//Aggregate
public class User {

    private final Long userId;
    private final Email email;
    private final Password password;
    private final ProfileInfo profileInfo;
    private final Set<Role> roles;
    private final Instant registrationDate;

    public User(Long userId, Email email, Password password, ProfileInfo profileInfo, Set<Role> roles, Instant registrationDate) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.profileInfo = profileInfo;
        this.roles = roles;
        this.registrationDate = registrationDate;
    }

    public boolean verifyPassword(String input) {
        return password.verify(input);
    }

    public boolean hasPrivilege(Privilege privilege) {
        return roles.stream().anyMatch(role -> role.getPrivileges().contains(privilege));
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public Set<Role> getRoles() {
        return Collections.unmodifiableSet(roles);
    }

    public Long getUserId() {
        return userId;
    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public ProfileInfo getProfileInfo() {
        return profileInfo;
    }

    public Instant getRegistrationDate() {
        return registrationDate;
    }
}
