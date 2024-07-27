package com.to_do_list_app.To_DoApp.controller;

import com.to_do_list_app.To_DoApp.dto.ToDoDTO;
import com.to_do_list_app.To_DoApp.model.ToDoItem;
import com.to_do_list_app.To_DoApp.repository.ToDoRepository;
import com.to_do_list_app.To_DoApp.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class ToDoController {
    private final ToDoService toDoService;
    private final ToDoRepository toDoRepository;

    @PostMapping("/addItem")
    public ResponseEntity<String> addToDoItem(@RequestBody ToDoDTO toDoDTO){
        toDoService.addToDoItem(toDoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("To-Do Item Created Successfully :)");
    }

    @GetMapping("/getAllItems")
    public ResponseEntity<List<ToDoDTO>> getAllItems(){
        return ResponseEntity.status(HttpStatus.OK).body(toDoService.getToDoItems());
    }

    @GetMapping("/getItemByTitle/{title}")
    public ResponseEntity<Optional<ToDoItem>> getItemByTitle(@PathVariable String title){
        return ResponseEntity.status(HttpStatus.OK).body(toDoService.getItemByTitle(title));
    }


    @PutMapping("/updateItem")
    public ResponseEntity<String> updateToDoItem(@RequestBody ToDoDTO toDoDTO){
        Optional<ToDoItem> existingItem = toDoRepository.findByTitle(toDoDTO.getTitle());
        if (existingItem.isPresent()){
            toDoService.updateToDoItem(toDoDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Item Updated Successfully :)");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Item was Found to be Updated :(");
        }
    }

    @DeleteMapping("deleteItem/{title}")
    public ResponseEntity<String> deleteItem(@PathVariable String title){
        Optional<ToDoItem> existingItem = toDoRepository.findByTitle(title);
        if (existingItem.isPresent()){
            toDoService.deleteToDoItem(title);
            return ResponseEntity.status(HttpStatus.OK).body("Item Deleted Successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Item was Found to be Deleted :(");
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAll(){
        toDoService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("All The Items have been Deleted Successfully.");
    }



}
