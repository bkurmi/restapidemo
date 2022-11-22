package com.techforum.api.restapidemo.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techforum.api.restapidemo.beans.Billionair;
import com.techforum.api.restapidemo.service.DemoService;

@RestController
public class DemoController {

	@Autowired
	DemoService demoService;

	@GetMapping("/billionairs")
	public ResponseEntity<List<Billionair>> getAllBillionairs() {
		List<Billionair> billionairs = demoService.getAllBillionairs();
		if (billionairs != null) {
			return ResponseEntity.ok().body(billionairs);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/billionairs/{billionairId}")
	public ResponseEntity<?> getBillionairById(@PathVariable Integer billionairId) {
		Billionair billionair = demoService.getBillionairById(billionairId);
		if (billionair != null) {
			return ResponseEntity.ok().body(billionair);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/billionair")
	public ResponseEntity<Billionair> addBillionair(@RequestBody Billionair billionair) {
		Billionair newBillionair = demoService.addBillionair(billionair);

		try {
			return ResponseEntity.created(new URI("/billionair/" + newBillionair.getId())).body(newBillionair);
		} catch (URISyntaxException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@DeleteMapping("/billionairs/{billionairId}")
	public ResponseEntity<?> deleteBillionair(@PathVariable Integer billionairId) {
		Billionair existingBillionair = demoService.getBillionairById(billionairId);
		if (existingBillionair != null) {
			demoService.deleteBillionair(billionairId);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}