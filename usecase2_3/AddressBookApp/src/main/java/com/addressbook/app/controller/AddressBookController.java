package com.addressbook.app.controller;

import com.addressbook.app.dto.AddressBookDTO;
import com.addressbook.app.model.AddressBookModel;
import com.addressbook.app.service.IAddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addressbook")
public class AddressBookController {

 
    @Autowired
    private IAddressBookService addressBookService;

    @GetMapping
    public ResponseEntity<String> getAddressBookWelcome() {
        // Delegate to service layer
        return new ResponseEntity<>(addressBookService.getWelcomeMessage(), HttpStatus.OK);
    }

    @GetMapping("/entries")
    public ResponseEntity<List<AddressBookModel>> getAllEntries() {
        List<AddressBookModel> entries = addressBookService.getAllAddressBookEntries();
        if (entries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(entries, HttpStatus.OK);
    }

    @GetMapping("/entries/{id}")
    public ResponseEntity<AddressBookModel> getEntryById(@PathVariable Long id) {
        AddressBookModel entry = addressBookService.getAddressBookEntryById(id);
        if (entry != null) {
            return new ResponseEntity<>(entry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/entries")
    public ResponseEntity<AddressBookModel> createEntry(@RequestBody AddressBookDTO addressBookDTO) {
        AddressBookModel newEntry = addressBookService.createAddressBookEntry(addressBookDTO);
        return new ResponseEntity<>(newEntry, HttpStatus.CREATED);
    }

    @PutMapping("/entries/{id}")
    public ResponseEntity<AddressBookModel> updateEntry(@PathVariable Long id, @RequestBody AddressBookDTO addressBookDTO) {
        AddressBookModel updatedEntry = addressBookService.updateAddressBookEntry(id, addressBookDTO);
        if (updatedEntry != null) {
            return new ResponseEntity<>(updatedEntry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/entries/{id}")
    public ResponseEntity<Void> deleteEntry(@PathVariable Long id) {
        boolean removed = addressBookService.deleteAddressBookEntry(id);
        if (removed) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}