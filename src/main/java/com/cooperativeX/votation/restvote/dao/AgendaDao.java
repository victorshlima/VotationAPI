package com.cooperativeX.votation.restvote.dao;

        import com.cooperativeX.votation.restvote.domain.Agenda;
        import org.springframework.data.repository.CrudRepository;
        import org.springframework.stereotype.Repository;

        import java.util.List;


@Repository
public interface AgendaDao extends CrudRepository<Agenda, Long> {
    List<Agenda> findAll();
}