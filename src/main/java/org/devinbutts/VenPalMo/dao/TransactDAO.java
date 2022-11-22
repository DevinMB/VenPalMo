package org.devinbutts.VenPalMo.dao;


import org.devinbutts.VenPalMo.model.Transact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface TransactDAO extends JpaRepository<Transact,Integer> {

    @Override
    List<Transact> findAll();

    @Override
    List<Transact> findAllById(Iterable<Integer> integers);

    @Override
    <S extends Transact> S save(S entity);

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(Transact entity);
}
