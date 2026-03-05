package com.rameel.To_Do_List_API.exception;

public class InvalidPassword extends RuntimeException{
    public InvalidPassword() {
        super("Invalid Password");
    }
}
