package org.devinbutts.VenPalMo.dao;

import org.devinbutts.VenPalMo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


/**
 * Account Entity DAO, allowing for basic CRUD.
 */
@Repository
public interface AccountDAO extends JpaRepository<Account,Integer> {

    @Override
    List<Account> findAll();

    @Override
    List<Account> findAllById(Iterable<Integer> integers);

    @Override
    <S extends Account> S save(S entity);

    @Query("SELECT a FROM Account a WHERE a.userId = :id")
    List<Account> findByUserId(Integer id);

    @Query("SELECT a FROM Account a WHERE a.userId = :id AND a.defaultAccount = 1")
    Account findDefaultAccountByUserId(Integer id);


    @Override
    void deleteById(Integer integer);

    @Override
    void delete(Account entity);
}
