package br.edu.toledoprudente.Repository;

import org.springframework.stereotype.Repository;

import br.edu.toledoprudente.Entity.Clientes;

//CLASSE FILHA DE CLASSE ABSTRACT REPOSITORY
@Repository
public class ClientesRepository extends AbstractRepository<Clientes, Integer> {

}
