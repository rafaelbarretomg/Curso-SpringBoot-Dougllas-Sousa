package com.example.libraryapi.repository;

import com.example.libraryapi.model.Autor;
import com.example.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {

    //Query Method
    //select * livro where id_autor = id
    List<Livro> findByAutor(Autor autor);

    //select * from livro where titulo = titulo
    List<Livro> findByTitulo(String titulo);

    //select * from livro where isbn = ?
    List<Livro> findByIsbn(String isbn);

    //select * from livro where titulo = ? and preco = ?
    List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);

    //select * from livro where titulo = ? or isbn = ?
    List<Livro> findByTituloOrIsbn(String titulo, String isbn);

    //select * from livro where data_publicacao between ? and ?
    List<Livro> findByDataPublicacaoBetween(LocalDate inicio, LocalDate fim);

    //select * from livro where titulo like ? //%uf%
    List<Livro> findByTituloLike(String text);

    //JPQL -> referencia as entidades e as propriedades
    //select l.* from livro as l order by l.titulo
    @Query(" select l from Livro as l order by l.titulo, l.preco ")
    List<Livro> listarTodosOrdenadoPorTituloAndPreco();

    @Query(" select a from Livro l join l.autor a ")
    List<Autor> listarAutoresDosLivros();

    @Query("""
            select l.genero
            from Livro l
            join l.autor a
            where a.nacionalidade = 'Brasileira'
            order by l.genero
            """)
    List<String> listarGenerosAutoresBrasileiros();
}
