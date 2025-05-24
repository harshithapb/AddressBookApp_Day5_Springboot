package com.addressbook.app.controller;

import com.addressbook.app.dto.AddressBookDTO;
import com.addressbook.app.model.AddressBookModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/addressbook")
public class AddressBookController {

    private List<AddressBookModel> addressBookList = new ArrayList<>();
    private long nextId = 1; // Simple ID generator

    @GetMapping
    public ResponseEntity<String> getAddressBookWelcome() {
        return new ResponseEntity<>("Welcome to the Address Book Application!", HttpStatus.OK);
    }

    @GetMapping("/entries")
    public ResponseEntity<List<AddressBookModel>> getAllEntries() {
        if (addressBookList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        }
        // In this UC, Controller directly returns Model. Later, Service will handle DTO conversion.
        return new ResponseEntity<>(addressBookList, HttpStatus.OK); // 200 OK
    }

    @GetMapping("/entries/{id}")
    public ResponseEntity<AddressBookModel> getEntryById(@PathVariable Long id) {
        AddressBookModel entry = addressBookList.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (entry != null) {
            return new ResponseEntity<>(entry, HttpStatus.OK); // 200 OK
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }

    @PostMapping("/entries")
    public ResponseEntity<AddressBookModel> createEntry(@RequestBody AddressBookDTO addressBookDTO) {
        // Convert DTO to Model
        AddressBookModel newEntry = new AddressBookModel(
                addressBookDTO.getName(),
                addressBookDTO.getPhoneNumber(),
                addressBookDTO.getEmail()
        );
        newEntry.setId(nextId++); // Assign a simple ID

        addressBookList.add(newEntry); // Add to in-memory list
        return new ResponseEntity<>(newEntry, HttpStatus.CREATED); // 201 Created
    }

    @PutMapping("/entries/{id}")
    public ResponseEntity<AddressBookModel> updateEntry(@PathVariable Long id, @RequestBody AddressBookDTO addressBookDTO) {
        AddressBookModel existingEntry = addressBookList.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (existingEntry != null) {
            // Update existing entry with data from DTO
            existingEntry.setName(addressBookDTO.getName());
            existingEntry.setPhoneNumber(addressBookDTO.getPhoneNumber());
            existingEntry.setEmail(addressBookDTO.getEmail());
            return new ResponseEntity<>(existingEntry, HttpStatus.OK); // 200 OK
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }

    @DeleteMapping("/entries/{id}")
    public ResponseEntity<Void> deleteEntry(@PathVariable Long id) {
        boolean removed = addressBookList.removeIf(entry -> entry.getId().equals(id));
        if (removed) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }
}