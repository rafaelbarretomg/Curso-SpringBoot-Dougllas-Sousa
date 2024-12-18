package com.example.libraryapi.repository;

import com.example.libraryapi.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TransacoesTest {

    @Autowired
    TransacaoService transacaoService;

    /**
     * Commit -> confirmar as alteracoes
     * Rollback -> desfazer as alteracoes
     */
    @Test
    void transacaoSimples(){
        transacaoService.executar();
    }

    @Transactional
    public void salvarLivroComFoto(){
        //salva livro
        //repository.save(livro);

        //pega o id do livro = livro.getId();
        //var id = livro.getId();

        //salva foto do livreo -> bucket na nuvem
        //bucketService.salvar(livro.getFoto(), id + ".png");

        //atualizar o nome arquivo que foi salvo
        //livro.setNomeArquivoFoto(id + ".png");
    }


    @Test
    void transcaoComErro(){
        transacaoService.executarComErro();
    }

    @Test
    void TransacaoEstadoManaged(){
        transacaoService.atualizarSemAtualizar();
    }
}
