package org.devinbutts.VenPalMo.dao;


import org.devinbutts.VenPalMo.model.Transact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface TransactDAO extends JpaRepository<Transact,Integer> {


    @Query("SELECT t FROM Transact t WHERE t.sendingUserId = :userId OR t.receivingUserId = :userId")
    List<Transact> findAllUserTransactions(Integer userId);

    @Override
    <S extends Transact> S save(S entity);

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(Transact entity);
}
