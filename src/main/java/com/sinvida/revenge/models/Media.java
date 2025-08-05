package com.sinvida.revenge.models;

import com.sinvida.revenge.enums.MediaType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "revenge_plan_id")
    private RevengePlan revengePlan;

    @Enumerated(EnumType.STRING)
    private MediaType type;

    private String url;

    private String caption;
}
