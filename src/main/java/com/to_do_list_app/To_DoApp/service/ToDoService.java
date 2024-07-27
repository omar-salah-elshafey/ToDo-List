package com.to_do_list_app.To_DoApp.service;

import com.to_do_list_app.To_DoApp.dto.ToDoDTO;
import com.to_do_list_app.To_DoApp.mapper.ToDoMapper;
import com.to_do_list_app.To_DoApp.model.ToDoItem;
import com.to_do_list_app.To_DoApp.repository.ToDoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ToDoService {
    private final ToDoMapper toDoMapper;
    private final ToDoRepository toDoRepository;

    public void addToDoItem(ToDoDTO toDoDTO){
        ToDoItem toDoItem = toDoMapper.toDoDTOtoToDoItem(toDoDTO);
        toDoItem.setCreatedAt(new Date());
        toDoItem.setUpdatedAt(new Date());
        toDoItem.setIsCompleted(false);
        toDoRepository.save(toDoItem);
    }

    public List<ToDoDTO> getToDoItems(){
        return toDoRepository.findAll().stream().map(toDoMapper::toDoItemtoToDoDTO).toList();
    }

    public Optional<ToDoItem> getItemByTitle(String title){
        return toDoRepository.findByTitle(title);
    }

    public void updateToDoItem(ToDoDTO toDoDTO) {
        Optional<ToDoItem> existingToDoItem = toDoRepository.findByTitle(toDoDTO.getTitle());
        if (existingToDoItem.isPresent()) {
            ToDoItem toDoItemUpdate = existingToDoItem.get();
            toDoMapper.updateToDoItem(toDoDTO, toDoItemUpdate);
            toDoItemUpdate.setUpdatedAt(new Date());
            toDoRepository.save(toDoItemUpdate);
        }
    }

    @Transactional
    public void deleteToDoItem(String title){
        Optional<ToDoItem> existingToDoItem = toDoRepository.findByTitle(title);
        if (existingToDoItem.isPresent()){
            toDoRepository.deleteByTitle(title);
        }
    }

    @Transactional
    public void deleteAll(){
        toDoRepository.deleteAll();
    }


}
