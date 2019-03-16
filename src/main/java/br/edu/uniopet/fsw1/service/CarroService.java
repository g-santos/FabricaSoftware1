package br.edu.uniopet.fsw1.service;


import br.edu.uniopet.fsw1.model.Carro;
import br.edu.uniopet.fsw1.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public List<Carro> findall() {
        return carroRepository.findAll();

    }

    public Carro findById(Integer id) {
        return carroRepository.findById(id).get();
    }

    public String saveOrUpdate(Carro carro) {
        if (carroRepository.save(carro) != null) {
            return "Objeto salvo com sucesso";
        }
        return "Erro";
    }

    public String delete (Integer id){

        if (carroRepository.existsById(id)){
            carroRepository.deleteById(id);
             return !carroRepository.existsById(id) ? "Recurso deletado" : "Erro no delete";
        }

        return  "O recurso não foi encontrado";
    }


}
