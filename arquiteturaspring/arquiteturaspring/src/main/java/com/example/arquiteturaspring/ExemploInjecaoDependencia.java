// package com.example.arquiteturaspring;


// import java.sql.Connection;

// import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
// import org.springframework.jdbc.datasource.DriverManagerDataSource;

// import com.example.arquiteturaspring.todos.MailSender;
// import com.example.arquiteturaspring.todos.TodoEntity;
// import com.example.arquiteturaspring.todos.TodoRepository;
// import com.example.arquiteturaspring.todos.TodoService;
// import com.example.arquiteturaspring.todos.TodoValidator;

// import jakarta.persistence.EntityManager;

// public class ExemploInjecaoDependencia {

//     //Exemplo sem utilizar Injecao de Dependencia
//     public static void main(String[] args) {
//         DriverManagerDataSource dataSource = new DriverManagerDataSource();
//         dataSource.setUrl("url");
//         dataSource.setUsername("user");
//         dataSource.setPassword("password");

//         Connection connection = dataSource.getConnection();

//         EntityManager entityManager = null;

//         TodoRepository repository = new SimpleJpaRepository<TodoEntity, Integer>(null, null)
//         TodoValidator todoValidator = new TodoValidator(repository);
//         MailSender sender = new MailSender();

//         TodoService todoService = new TodoService(repository, todoValidator, sender);
//     }

// }
