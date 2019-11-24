package com.cooperativeX.votation.restvote.service;



import com.cooperativeX.votation.restvote.domain.ContaCorrente;
import com.cooperativeX.votation.restvote.dao.ContaDao;
import org.springframework.stereotype.Component;

import java.util.List;

//@Service
@Component
public interface ContaService {


 //   @Autowired
  //  ContaDao contaDao;

    void save(ContaDao contaDao);

    List<ContaCorrente> findAll();

}