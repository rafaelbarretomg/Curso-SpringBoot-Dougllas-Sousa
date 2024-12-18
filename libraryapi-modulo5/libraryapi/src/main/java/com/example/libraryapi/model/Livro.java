package com.example.libraryapi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

//Gera Getter, setters, constructors, hash e equals, tostring tudo no @Data
@Data
@Entity
@ToString(exclude = "autor")
@Table(name = "livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "isbn", length = 20, nullable = false)
    private String isbn;

    @Column(name = "titulo", length = 150, nullable = false)
    private String titulo;

    @Column(name = "data_publicacao", nullable = false)
    private LocalDate dataPublicacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "genero",length = 30, nullable = false)
    private GeneroLivro genero;

    @Column(name = "preco", precision = 18, scale = 2)
    private BigDecimal preco;

    @JoinColumn(name = "id_autor", nullable = false)
    @ManyToOne(
            //cascade = CascadeType.ALL
            fetch = FetchType.LAZY
    )
    private Autor autor;


}
