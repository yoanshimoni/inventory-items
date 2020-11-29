package com.example.rest.demorest.item;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



@RestController
public class ItemController {
	
//	@Autowired
//	private ItemDaoService service;
	
	@Autowired
	private ItemRepository itemRepository;
	
	// get all items
	@GetMapping("/items")
	public List<Item> retrieveAllItems() { 
		return itemRepository.findAll();
	}
	
	// get item
	@GetMapping("/items/{itemNo}")
	public ResponseEntity<Object> retrieveUser(@PathVariable Long itemNo) { 
		Optional<Item> item = itemRepository.findById(itemNo);
		
		if(!item.isPresent()) {
			throw new ItemNotFoundException("itemNo-" + itemNo);
		}
		
		return ResponseEntity.ok().body(item);
	}
	
	// delete
	@DeleteMapping("/items/{itemNo}")
	public void deleteItem(@PathVariable Long itemNo) { 
		itemRepository.deleteById(itemNo);
	}

	// create
	@PostMapping("/items")
	public ResponseEntity<Object> createItem(@Valid @RequestBody Item item) { 
		Item savedItem = itemRepository.save(item);
		
		return ResponseEntity.ok().body(savedItem);
	}


	@PutMapping("/deposit/{itemNo}/{amount}")
	@Transactional
	public void deposit(@PathVariable("itemNo") Long itemNo, @PathVariable int amount) throws Exception {
		if (amount > 0) {
			itemRepository.deposit(itemNo, amount);
		} else {
			throw new Exception("Amount should be greater than zero");
		}
	}
	
	@PutMapping("/withdrawal/{itemNo}/{amount}")
	@Transactional
	public void withdrawal(@PathVariable("itemNo") Long itemNo, @PathVariable int amount) throws Exception {
		Optional<Item> item = itemRepository.findById(itemNo);
		if(!item.isPresent()) {
			throw new ItemNotFoundException("itemNo-" + itemNo);
		}
		
		if (item.get().getAmount() - amount >= 0) {
			itemRepository.withdrawal(itemNo, amount);
		} else {
			throw new Exception("Amount should be greater than zero");
		}
	}

}
