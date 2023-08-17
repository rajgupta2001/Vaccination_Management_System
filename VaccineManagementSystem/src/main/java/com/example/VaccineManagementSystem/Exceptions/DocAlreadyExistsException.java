package com.example.VaccineManagementSystem.Exceptions;

//This exception is defined by us
public class DocAlreadyExistsException extends Exception{
    public DocAlreadyExistsException(String message){
        super(message);
    }
}
