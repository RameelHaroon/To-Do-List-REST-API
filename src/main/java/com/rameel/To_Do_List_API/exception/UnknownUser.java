package com.rameel.To_Do_List_API.exception;

public class UnknownUser extends RuntimeException{
    public UnknownUser(String email) {
        super("User with email " + email + " does not exist.");
    }
}
