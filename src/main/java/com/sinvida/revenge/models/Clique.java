package com.sinvida.revenge.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "cliques")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Clique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;


    @OneToMany(mappedBy = "clique", cascade = CascadeType.ALL)
    private List<Bully> bullies;
}
