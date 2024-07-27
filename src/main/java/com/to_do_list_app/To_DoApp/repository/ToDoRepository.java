package com.to_do_list_app.To_DoApp.repository;

import com.to_do_list_app.To_DoApp.model.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ToDoRepository extends JpaRepository<ToDoItem, Long> {
    Optional<ToDoItem> findByTitle(String title);
    void deleteByTitle(String title);
}
