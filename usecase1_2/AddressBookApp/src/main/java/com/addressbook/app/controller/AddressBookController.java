package com.addressbook.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/addressbook")
public class AddressBookController { 
	 private Map<String, String> addressBookEntries = new HashMap<>();
	 private long nextId = 1;
	
	
	 @GetMapping
	    public ResponseEntity<String> getAddressBookWelcome() {
	        return new ResponseEntity<>("Welcome to the Address Book Application!", HttpStatus.OK);
	    } 
	 
	 @GetMapping("/entries")
	    public ResponseEntity<Map<String, String>> getAllEntries() {
	        if (addressBookEntries.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
	        }
	        return new ResponseEntity<>(addressBookEntries, HttpStatus.OK); // 200 OK
	    }
	 
	 @GetMapping("/entries/{id}")
	    public ResponseEntity<String> getEntryById(@PathVariable String id) {
	       
	        String entry = addressBookEntries.get(id);
	        if (entry != null) {
	            return new ResponseEntity<>("Entry with ID " + id + ": " + entry, HttpStatus.OK); // 200 OK
	        } else {
	            return new ResponseEntity<>("Entry with ID " + id + " not found.", HttpStatus.NOT_FOUND); // 404 Not Found
	        }
	    }

	    @PostMapping("/entries")
	    public ResponseEntity<String> createEntry(@RequestBody String entryData) {
	        // In a real app, this would save data via a service/database
	        String id = String.valueOf(nextId++);
	        addressBookEntries.put(id, entryData);
	        return new ResponseEntity<>("Entry created with ID: " + id + ", Data: " + entryData, HttpStatus.CREATED); // 201 Created
	    }

	    @PutMapping("/entries/{id}")
	    public ResponseEntity<String> updateEntry(@PathVariable String id, @RequestBody String updatedEntryData) {
	       
	        if (addressBookEntries.containsKey(id)) {
	            addressBookEntries.put(id, updatedEntryData);
	            return new ResponseEntity<>("Entry with ID " + id + " updated to: " + updatedEntryData, HttpStatus.OK); // 200 OK
	        } else {
	            return new ResponseEntity<>("Entry with ID " + id + " not found for update.", HttpStatus.NOT_FOUND); // 404 Not Found
	        }
	    }

	    @DeleteMapping("/entries/{id}")
	    public ResponseEntity<String> deleteEntry(@PathVariable String id) {
	        // In a real app, this would delete data via a service/database
	        if (addressBookEntries.containsKey(id)) {
	            addressBookEntries.remove(id);
	            return new ResponseEntity<>("Entry with ID " + id + " deleted.", HttpStatus.NO_CONTENT); // 204 No Content
	        } else {
	            return new ResponseEntity<>("Entry with ID " + id + " not found for deletion.", HttpStatus.NOT_FOUND); // 404 Not Found
	        }
	 
   }
}
