package br.edu.toledoprudente.Repository;

import org.springframework.stereotype.Repository;

import br.edu.toledoprudente.Entity.Cliente;

//CLASSE FILHA DE CLASSE ABSTRACT REPOSITORY
@Repository
public class ClienteRepository extends AbstractRepository<Cliente, Integer> {

}
