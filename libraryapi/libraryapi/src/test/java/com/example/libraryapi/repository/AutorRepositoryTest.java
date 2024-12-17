package com.example.libraryapi.repository;

import com.example.libraryapi.model.Autor;
import com.example.libraryapi.model.GeneroLivro;
import com.example.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("Maria");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1953, 1, 31));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor Salvo: " + autorSalvo);
    }

    @Test
    public void atualizarTest() {
        var id = UUID.fromString("646e983b-abf4-4deb-87c0-68ddb8d5233d");

        Optional<Autor> possivelAutor = repository.findById(id);
        if (possivelAutor.isPresent()) {
            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do autor:");
            System.out.println(autorEncontrado);

            autorEncontrado.setDataNascimento(LocalDate.of(1960, 1, 30));
            autorEncontrado.setNome("Maria dos Santos");
            repository.save(autorEncontrado);
        }
    }

    @Test
    public void listarTest() {
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest() {
        System.out.println("Contagem de autores: " + repository.count());
    }

    @Test
    public void deletePorIdTest() {
        var id = UUID.fromString("646e983b-abf4-4deb-87c0-68ddb8d5233d");
        repository.deleteById(id);
    }

    @Test
    public void deleteTest() {
        var id = UUID.fromString("b7e529a8-9cc6-4bd4-bd7d-2fb5cd542305");
        var maria = repository.findById(id).get();
        repository.delete(maria);
    }

    @Test
    void salvarAutorComLivrosTest() {
        Autor autor = new Autor();
        autor.setNome("Antonio");
        autor.setNacionalidade("Portugues");
        autor.setDataNascimento(LocalDate.of(1953, 1, 31));

        Livro livro = new Livro();
        livro.setIsbn("90887-47688");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setTitulo("Antonio da Silva");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("90887-47698");
        livro2.setPreco(BigDecimal.valueOf(100));
        livro2.setGenero(GeneroLivro.CIENCIA);
        livro2.setTitulo("Estudo de Antonio");
        livro2.setDataPublicacao(LocalDate.of(1978, 1, 2));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        repository.save(autor);


        //livroRepository.saveAll(autor.getLivros());
    }

    @Test
    void listarLivrosAutor(){
        var id = UUID.fromString("c6a1b417-9400-44f8-a135-5775b933f12d");
        var autor = repository.findById(id).get();

        //Buscar livros do autor

        List<Livro> livrosLista = livroRepository.findByAutor(autor);
        autor.setLivros(livrosLista);

        autor.getLivros().forEach(System.out::println);
    }
}
