package com.chaves.apicontatos.repository;

import com.chaves.apicontatos.dto.ContatoDTO;
import com.chaves.apicontatos.entity.Contato;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static com.chaves.apicontatos.builder.ContatoBuilder.umContato;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
@ExtendWith(SpringExtension.class)
public class ContatoRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    ContatoRepository repository;

    @Test
    @DisplayName("Deve retornar uma lista com 2 contatos do banco")
    public void findAll(){
        entityManager.persist(umContato().comId(null).agora());
        entityManager.persist(umContato().comId(null).agora());

        List<Contato> list = repository.findAll();

        assertThat(list).hasSize(2);
    }

    @Test
    @DisplayName("Deve retornar uma lista VAZIA do banco de dados")
    public void findAllEmpty(){
        List<Contato> list = repository.findAll();

        assertThat(list).isEmpty();
    }

    @Test
    @DisplayName("Deve retornar um contato do banco pelo id informado")
    public void findById(){
        Contato contato = umContato().comId(null).agora();
        entityManager.persist(contato);

        Optional<Contato> contatoFound = repository.findById(contato.getId());

        assertThat(contatoFound.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Deve retornar VAZIO do banco, pois não encontrou o contato para oo id informado")
    public void findByIdEmpty(){
        Optional<Contato> contato = repository.findById(1L);

        assertThat(contato.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Deve deletar um contato pelo id")
    public void deleteById(){
        Contato contato = umContato().comId(null).agora();
        entityManager.persist(contato);

        Contato contatoFound = entityManager.find( Contato.class, contato.getId());
        repository.deleteById(contatoFound.getId());

        Contato contatoDeleted = entityManager.find( Contato.class, contato.getId());
        assertThat(contatoDeleted).isNull();
    }

    @Test
    @DisplayName("Deve salvar um contato no banco")
    public void save(){
        Contato contato = umContato().comId(null).agora();

        contato = repository.save(contato);

        assertThat(contato.getId()).isNotNull();
    }
}
