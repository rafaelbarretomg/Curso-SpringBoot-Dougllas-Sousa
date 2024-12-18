package com.example.libraryapi.service;

import com.example.libraryapi.model.Autor;
import com.example.libraryapi.model.GeneroLivro;
import com.example.libraryapi.model.Livro;
import com.example.libraryapi.repository.AutorRepository;
import com.example.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public void atualizarSemAtualizar(){
        var livro = livroRepository
                .findById(UUID.fromString("0e3b0ebf-780a-4d8f-8837-2aac770ae41e"))
                .orElse(null);
        livro.setDataPublicacao(LocalDate.of(2024,6,1));
    }

    @Transactional
    public void executar() {
        //salvar o autor
        Autor autor = new Autor();
        autor.setNome("Teste Francisca");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1953, 1, 31));

        autorRepository.save(autor);

        //salvar o livro
        Livro livro = new Livro();
        livro.setIsbn("90397-45548");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setTitulo("Livro Francisca");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        autorRepository.save(autor);

        livro.setAutor(autor);

        livroRepository.save(livro);

        if(autor.getNome().equals("Teste Francisco")){
            throw new RuntimeException("Rollback");
        }
    }

    @Transactional
    public void executarComErro() {
        //salvar o autor
        Autor autor = new Autor();
        autor.setNome("Teste Francisco");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1953, 1, 31));

        autorRepository.save(autor);

        //salvar o livro
        Livro livro = new Livro();
        livro.setIsbn("90397-45548");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setTitulo("Livro Francisca");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        autorRepository.save(autor);

        livro.setAutor(autor);

        livroRepository.save(livro);

        if(autor.getNome().equals("Teste Francisco")){
            throw new RuntimeException("Rollback");
        }
    }
}

