
package com.company;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        File file = new File("test.csv");
        try {
            file.createNewFile();

            PhoneBook phoneBook = new PhoneBook(file);
            Contact contact = new Contact("Olesya", "333333");
            try {
                phoneBook.save(contact);
            } catch (ContactAlreadyExist e) {
                System.out.println(e.getMessage());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
