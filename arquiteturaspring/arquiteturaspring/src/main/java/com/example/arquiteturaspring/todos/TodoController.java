package com.example.arquiteturaspring.todos;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("todos")
public class TodoController {
    
    private TodoService sevice;

    public TodoController(TodoService sevice) {
        this.sevice = sevice;
    }

    @PostMapping    
    public TodoEntity salvar(@RequestBody TodoEntity todo){
        try{
            return this.sevice.salvar(todo);
        }catch(IllegalArgumentException e){
            var mensagemErro = e.getMessage();
            throw new ResponseStatusException(HttpStatus.CONFLICT, mensagemErro);
        }
      
    }

    @PutMapping("/{id}")
    public void atualizarStatus(@PathVariable Integer id, @RequestBody TodoEntity todo){
        todo.setId(id);
        sevice.atualizarStatus(todo);
    }

    @GetMapping("/{id}")
    public TodoEntity buscar(@PathVariable Integer id){
        return sevice.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        sevice.delete(id);
    }

}
