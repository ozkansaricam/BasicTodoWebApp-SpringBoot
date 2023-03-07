package com.ozkans.springboot.practisewebapp.todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {
    private static List<Todo> todoList = new ArrayList<>();
    private static int todosCount = 0;

    static {
        todoList.add(new Todo(++todosCount, "ozkan", "Learn Go Language", LocalDate.now().plusYears(1), false));
        todoList.add(new Todo(++todosCount, "ozkan", "Learn Spanish", LocalDate.now().plusYears(2), false));
        todoList.add(new Todo(++todosCount, "ozkan", "Learn FullStack Development", LocalDate.now().plusYears(3), false));
    }

    public List<Todo> findByUsername(String username){
        Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);
        return todoList.stream().filter(predicate).toList();
    }

    public void addTodo(String username, String description, LocalDate targetDate, boolean done){
        Todo todo = new Todo(++todosCount,username,description,targetDate,done);
        todoList.add(todo);
    }

    public void deleteById(int id){
        todoList.removeIf(todo -> todo.getId()==id);
    }

    public Todo findById(int id) {

        return todoList.stream().filter(todo -> todo.getId()==id).findFirst().get();
    }

    public void updateTodo(Todo todo) {
        deleteById(todo.getId());
        todoList.add(todo);
    }
}