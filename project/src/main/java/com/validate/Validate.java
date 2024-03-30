package com.validate;

public class Validate {

	    public static boolean validateAge(int age) {
	        if(age >= 25 && age <= 40) {
	            return true;
	        } else {
	            System.out.println("Invalid age. Age must be between 25 and 40.");
	            return false;
	        }
	    }

	    public static boolean validateName(String name) {
	        if(name.matches("[a-zA-Z]+")) {
	            return true;
	        } else {
	            System.out.println("Invalid name. Name must contain only alphabets.");
	            return false;
	        }
	    }
}
