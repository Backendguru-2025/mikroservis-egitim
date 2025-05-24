package com.backendguru.microservice.user.domain;

//ValueObject
public class ProfileInfo {

    private final String name;
    private final String surname;
    private final String profilePicture;

    public ProfileInfo(String name, String surname, String profilePicture) {
        this.name = name;
        this.surname = surname;
        this.profilePicture = profilePicture;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getProfilePicture() {
        return profilePicture;
    }
}
