package com.presentation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.exception.UserException;
import com.service.User;


public class App {
	
    public static void main(String[] args) throws UserException, IOException {
    	System.out.println();
    	System.out.println("************WELCOME TO THE APPLICATION***************");
    	new User();
    	System.out.println();
    	System.out.println("******************END OF APPLICATION*****************");

    }    
    
}



