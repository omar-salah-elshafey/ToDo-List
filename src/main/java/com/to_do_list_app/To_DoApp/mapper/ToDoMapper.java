package com.to_do_list_app.To_DoApp.mapper;

import com.to_do_list_app.To_DoApp.dto.ToDoDTO;
import com.to_do_list_app.To_DoApp.model.ToDoItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;

@Mapper(componentModel = "spring")
public interface ToDoMapper {
    ToDoDTO toDoItemtoToDoDTO(ToDoItem toDoItem);
    ToDoItem toDoDTOtoToDoItem(ToDoDTO toDoDTO);
    @Mapping(target = "createdAt", ignore = true)
    void updateToDoItem(ToDoDTO toDoDTO, @MappingTarget ToDoItem toDoItem);
}
