package br.edu.uniopet.fsw1.model;

import lombok.*;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter @Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "Carro")
public class Carro {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "idCarro")
    private Integer id;

    @NonNull
    private String nome;

    @NonNull
    private String modelo;

    @NonNull
    private String marca;



}
