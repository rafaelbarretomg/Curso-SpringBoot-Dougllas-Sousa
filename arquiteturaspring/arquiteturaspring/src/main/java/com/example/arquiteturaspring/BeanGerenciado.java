package com.example.arquiteturaspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.arquiteturaspring.todos.TodoEntity;
import com.example.arquiteturaspring.todos.TodoValidator;

@Component
public class BeanGerenciado {

    //Injecao somente com o Autowired
    @Autowired
    private TodoValidator validator;

    //Injecao com Construtor nao eh obrigatorio o autowired, o mais recomendado.
    //@Autowired
    public BeanGerenciado(TodoValidator validator){
        this.validator = validator;
    }

    public void utilizar(){
        var todo = new TodoEntity();
        validator.validar(todo);
    }


    //Injeçao com Set
    public void setValidator(TodoValidator validator){
        this.validator = validator;
    }

}
