package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PhoneBook {
    private List<Contact> contacts;
    private File phoneBookStorage;

    public PhoneBook(File phoneBookStorage) {
        this.phoneBookStorage = phoneBookStorage;
    }

    public List<Contact> getAllContacts() throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(phoneBookStorage));

        try {
            String line;
            contacts = new ArrayList<>();

            while ((line = br.readLine()) != null) {
               Contact contact = new Contact(line);
               contacts.add(contact);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
        }
        return contacts;
    }

    public void save(Contact contact) throws ContactAlreadyExist, IOException{
        List<Contact> allContacts = getAllContacts();

        boolean isFound = allContacts.stream().anyMatch(ct ->
        ct.getName().equals(contact.getName())
        || ct.getPhone().equals(contact.getPhone()));

        if (isFound) {
            throw new ContactAlreadyExist("Контакт уже существует!");
        }
        allContacts.add(contact);

        BufferedWriter writer =
        new BufferedWriter(new FileWriter(phoneBookStorage));

        try {
            List<String> collect = allContacts.stream().map(ct ->
                    ct.getName() + ";"
                    + contact.getPhone()).collect(Collectors.toList());

            String fullLine = String.join("\r\n", collect);
            writer.write(fullLine);
        } finally {
            writer.close();
        }

    }
}

