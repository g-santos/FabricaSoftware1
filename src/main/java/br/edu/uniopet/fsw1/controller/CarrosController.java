package br.edu.uniopet.fsw1.controller;

import br.edu.uniopet.fsw1.model.Carro;
import br.edu.uniopet.fsw1.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarrosController {

    @Autowired
    private CarroService carroService;

    @GetMapping
    public List<Carro> findAll(){
        return carroService.findall();
    }

    @GetMapping("/{id}")
    public Carro findById(@PathVariable Integer id){
        return  carroService.findById(id);
    }

    @PostMapping
    public String create (@RequestBody Carro carro){
        return carroService.saveOrUpdate(carro);
    }

    @PutMapping("/{id}")
    public String upDate (@RequestBody Carro carro, @PathVariable Integer id){
        carro.setId(id);
        return carroService.saveOrUpdate(carro);
    }

   @DeleteMapping("/{id}")
    public String delete (@PathVariable Integer id){
        return carroService.delete(id);

   }


}
