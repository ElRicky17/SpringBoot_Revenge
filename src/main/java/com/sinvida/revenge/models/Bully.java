package com.sinvida.revenge.models;

import com.sinvida.revenge.enums.HighSchoolRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Entity
@Table(name = "bullies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bully {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String nickname;

    @Enumerated(EnumType.STRING)
    private HighSchoolRole highSchoolRole;

    @ManyToOne
    @JoinColumn(name = "clique_id")
    private Clique clique;

    private String bullyingReason;

    @Min(1)
    @Max(10)
    private int levelOfAnnoyance;


    @OneToMany(mappedBy = "bully", cascade = CascadeType.ALL)
    private List<RevengePlan> revengePlans;
}
