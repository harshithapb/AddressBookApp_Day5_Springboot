package com.addressbook.app.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/addressbook")
public class AddressBookController {
	
	@GetMapping
	public String getAddressBookWelcome() {
        return "Welcome to the Address Book Application!";
    }
}
