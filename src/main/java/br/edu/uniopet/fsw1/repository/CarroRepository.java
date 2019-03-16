package br.edu.uniopet.fsw1.repository;

import br.edu.uniopet.fsw1.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarroRepository extends JpaRepository <Carro, Integer>{

//    @Query(value = "Select * from Carro where marca = :Fiat", nativeQuery = true)
//    List<Carro> procurarMarca(@Param("Fiat")String Fiat);


}
