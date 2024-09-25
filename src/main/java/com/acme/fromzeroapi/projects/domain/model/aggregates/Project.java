package com.acme.fromzeroapi.projects.domain.model.aggregates;

import com.acme.fromzeroapi.profiles.domain.model.aggregates.Developer;
import com.acme.fromzeroapi.profiles.domain.model.aggregates.Company;
import com.acme.fromzeroapi.projects.domain.model.commands.CreateProjectCommand;
import com.acme.fromzeroapi.projects.domain.model.entity.Framework;
import com.acme.fromzeroapi.projects.domain.model.entity.ProgrammingLanguage;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Lob
    @Column(columnDefinition = "TEXT",nullable = false)
    private String description;

    @Setter
    private String state;

    @Setter
    private Double progress;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Setter
    @ManyToOne
    @JoinColumn(name = "developer_id")
    private Developer developer;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "project_candidates",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "developer_id")
    )
    //@JsonManagedReference
    private List<Developer> candidates;

    //many to many relationship
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "project_programming_languages",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "programming_language_id")
    )
    //@JsonManagedReference
    private List<ProgrammingLanguage> languages;

    //many to many relationship
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "project_frameworks",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "framework_id")
    )
    //@JsonManagedReference
    private List<Framework> frameworks;

    private String type;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String budget;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String methodologies;

    public Project(CreateProjectCommand command){
        this.name=command.name();
        this.description=command.description();
        this.state="En busqueda";
        this.progress=0.0;
        this.company =command.company();
        this.developer=null;
        this.languages=new ArrayList<>();
        this.frameworks=new ArrayList<>();
        this.type=command.type();
        this.budget=command.budget();
        this.methodologies=command.methodologies();

    }

    public Project(){

    }


}
