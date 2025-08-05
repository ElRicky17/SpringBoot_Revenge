package com.sinvida.revenge.models;

import com.sinvida.revenge.enums.SuccessLevel;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.time.LocalDate;

@Entity
@Table(name = "revenge_plans")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RevengePlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String description;

    @ManyToOne
    @JoinColumn(name = "bully_id")
    private Bully bully;

    private boolean isExecuted;

    private LocalDate datePlanned;

    @Enumerated(EnumType.STRING)
    private SuccessLevel successLevel;

    @OneToMany(mappedBy = "revengePlan", cascade = CascadeType.ALL)
    private List<Media> mediaList;
}
