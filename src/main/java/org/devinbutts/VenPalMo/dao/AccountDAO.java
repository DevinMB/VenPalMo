package org.devinbutts.VenPalMo.dao;

import org.devinbutts.VenPalMo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountDAO extends JpaRepository<Account,Integer> {

    @Override
    List<Account> findAll();

    @Override
    List<Account> findAllById(Iterable<Integer> integers);

    @Override
    <S extends Account> S save(S entity);

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(Account entity);
}
