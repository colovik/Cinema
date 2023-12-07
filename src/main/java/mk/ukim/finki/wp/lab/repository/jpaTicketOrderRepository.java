package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.TicketOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface jpaTicketOrderRepository extends JpaRepository<TicketOrder, Integer> {

    List<TicketOrder> findAllByUserUsername(String username);

    //findBetween
    @Query("SELECT t FROM TicketOrder t WHERE t.dateCreated BETWEEN :from AND :to")
    List<TicketOrder> findOrdersInTimeInterval(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);
}
