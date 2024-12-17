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
import java.util.List;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest(){
        Livro livro = new Livro();
        livro.setIsbn("90887-45648");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(1980,1,2));

        Autor autor = autorRepository
                .findById(UUID.fromString("32c22609-f0e1-4990-a01a-d4e74e7ddc82"))
                .orElse(null);

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void salvarAutorELivroTest(){
        Livro livro = new Livro();
        livro.setIsbn("90887-45548");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO 3");
        livro.setDataPublicacao(LocalDate.of(1980,1,2));

        Autor autor = new Autor();
        autor.setNome("Jose");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1953, 1, 31));

        autorRepository.save(autor);

        livro.setAutor(autor);

        repository.save(livro);
    }



    //Exemplo com Cascade, nao eh muito utilizado, tem que adicionar cascade no model livro
    @Test
    void salvarCascadeTest(){
        Livro livro = new Livro();
        livro.setIsbn("90887-47648");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO 2");
        livro.setDataPublicacao(LocalDate.of(1980,1,2));

        Autor autor = new Autor();
        autor.setNome("Joao");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1953, 1, 31));

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void atualizarAutorDoLivro(){
        UUID id = UUID.fromString("e2af6772-af9d-493d-8ed2-9627731baa8c");
        var livroParaAtualizar = repository.findById(id)
                .orElse(null);

        UUID idAutor = UUID.fromString("209e8136-f233-4b47-82f5-124516868fd1");
        Autor maria = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(maria);

        repository.save(livroParaAtualizar);
    }

    @Test
    void deletar(){
        UUID id = UUID.fromString("e2af6772-af9d-493d-8ed2-9627731baa8c");
        repository.deleteById(id);
    }

    @Test
    @Transactional
    void buscarLivroTest(){
        UUID id = UUID.fromString("b90380ca-abdc-443a-9fa3-a29b6509d952");
        Livro livro = repository.findById(id).orElse(null);
        System.out.println("Livro: ");
        System.out.println(livro.getTitulo());

        System.out.println("Autor: ");
        System.out.println(livro.getAutor().getNome());
    }

    @Test
    void pesquisarPorTituloTest(){
        List<Livro> lista = repository.findByTitulo("Estudo de Antonio");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisarPorIsbnTest(){
        List<Livro> lista = repository.findByIsbn("90887-47688");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisarPorTituloEPrecoTest(){
        var preco = BigDecimal.valueOf(100);
        String tituloPesquisa = "UFO";
        List<Livro> lista = repository.findByTituloAndPreco(tituloPesquisa, preco);
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisarPorTituloLike(){
        List<Livro> lista = repository.findByTituloLike("%UF%");
        lista.forEach(System.out::println);
    }

    @Test
    void listarLivrosComQueryJPQL(){
        var resultado = repository.listarTodosOrdenadoPorTituloAndPreco();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarAutoresDosLivros(){
        var resultado = repository.listarAutoresDosLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarGenerosDeLivrosAutoresBrasileiros(){
        var resultado = repository.listarGenerosAutoresBrasileiros();
        resultado.forEach(System.out::println);
    }





}