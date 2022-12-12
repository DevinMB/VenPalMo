package org.devinbutts.VenPalMo.dao;

import org.devinbutts.VenPalMo.model.Transact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Transact Entity DAO, allowing for basic CRUD.
 */
@Repository
public interface TransactDAO extends JpaRepository<Transact,Integer> {

    @Query("SELECT t FROM Transact t WHERE t.sendingUserId = :userId OR t.receivingUserId = :userId")
    List<Transact> findAllUserTransactions(Integer userId);

    @Query("SELECT t FROM Transact t WHERE (t.sendingUserId = :userId OR t.receivingUserId = :userId) AND t.status = 'CLEARED' ")
    List<Transact> findAllClearedUserTransactions(Integer userId);

    @Query("SELECT t FROM Transact t WHERE t.sendingUserId = :userId AND t.status = 'REQUESTED' ")
    List<Transact> findAllRequestedUserTransactions(Integer userId);

    @Query("SELECT t FROM Transact t WHERE t.id = :id")
    Transact findByTransactId(Integer id);

    @Override
    <S extends Transact> S save(S entity);

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(Transact entity);



}
