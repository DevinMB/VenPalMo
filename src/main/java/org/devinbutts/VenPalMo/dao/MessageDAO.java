package org.devinbutts.VenPalMo.dao;

import org.devinbutts.VenPalMo.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageDAO extends JpaRepository<Message,Integer> {

    @Query("SELECT m FROM Message m WHERE m.toUser.id = :userId OR m.fromUser.id = :userId")
    List<Message> findUserMessages(Integer userId);

    @Override
    <S extends Message> S save(S entity);

    @Override
    void deleteById(Integer integer);
}
