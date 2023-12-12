package com.document.system.controller;

import com.document.system.model.Item;
import com.document.system.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping(value = "/spaces", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Item> createSpace() {
        return new ResponseEntity<>(itemService.createSpace(), HttpStatus.CREATED);
    }

    @PostMapping(value = "/folders", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createFolder(@RequestHeader("email") String email) {
        return new ResponseEntity<>(itemService.createFolder(email), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Item> getFileMetadata(@PathVariable("id") Long id, @RequestHeader("email") String email) {
        return new ResponseEntity<>(itemService.getFileMetadata(id, email), HttpStatus.OK);
    }
}
