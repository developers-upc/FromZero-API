package com.acme.fromzeroapi.deliverables.domain.model.aggregates;

import com.acme.fromzeroapi.deliverables.domain.model.commands.CreateDeliverableCommand;
import com.acme.fromzeroapi.projects.domain.model.aggregates.Project;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Entity
public class Deliverable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Lob
    @Column(nullable = false,columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private LocalDate date;

    @Setter
    @Column(nullable = false)
    private String state;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Setter
    @Lob
    @Column(columnDefinition = "TEXT")
    private String developerMessage;

    public Deliverable(CreateDeliverableCommand command) {
        this.name=command.name();
        this.description=command.description();
        this.date=command.date();
        this.state="Pending";
        this.developerMessage=null;
        this.project=command.project();
    }

    public Deliverable() {

    }

}
