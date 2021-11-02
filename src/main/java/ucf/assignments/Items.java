/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Noah Taylor
 */

package ucf.assignments;

/*
This class is used to create new items based on a few user inputs
    Declare properties
    Create the items
    Set them equal to what the user inputs
    Add getters and setters to return and update values
 */

public class Items {

    // Declaring class properties
    private int number;
    private String date;
    private String description;

    // Constructor to create items
    public Items(){

        // Creates blank items with these default values
        this.number = 0;
        this.date = "";
        this.description = "";
    }

    // Constructor to set values equal to what user passes in
    public Items(int number, String date, String description){
        this.number = number;
        this.date = date;
        this.description = description;
    }

    // Adds getters and setters
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
