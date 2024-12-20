package com.example.libraryapi.controller;

import com.example.libraryapi.controller.dto.CadastroLivroDTO;
import com.example.libraryapi.controller.dto.ErroResposta;
import com.example.libraryapi.controller.mappers.LivroMapper;
import com.example.libraryapi.exceptions.RegistroDuplicadoException;
import com.example.libraryapi.model.Livro;
import com.example.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("livros")
@RequiredArgsConstructor
public class LivroController implements GenericController {

    private final LivroService livroService;
    private final LivroMapper mapper;

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid CadastroLivroDTO dto){
        try {
            Livro livro = mapper.toEntity(dto);
            livroService.salvar(livro);
           var url = gerarHeaderLocation(livro.getId());
            return ResponseEntity.created(url).build();
        }catch (RegistroDuplicadoException e){
            var erroDTO = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }
}
