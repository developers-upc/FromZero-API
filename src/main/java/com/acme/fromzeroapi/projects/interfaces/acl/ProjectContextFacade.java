package com.acme.fromzeroapi.projects.interfaces.acl;

import com.acme.fromzeroapi.projects.domain.model.aggregates.Project;
import com.acme.fromzeroapi.projects.domain.model.commands.UpdateProjectProgressCommand;
import com.acme.fromzeroapi.projects.domain.model.queries.GetProjectByIdQuery;
import com.acme.fromzeroapi.projects.domain.services.ProjectCommandService;
import com.acme.fromzeroapi.projects.domain.services.ProjectQueryService;
import org.springframework.stereotype.Service;

@Service
public class ProjectContextFacade {
    private final ProjectQueryService projectQueryService;
    private final ProjectCommandService projectCommandService;

    public ProjectContextFacade(ProjectQueryService projectQueryService,
                                ProjectCommandService projectCommandService) {
        this.projectQueryService = projectQueryService;
        this.projectCommandService = projectCommandService;
    }

    public Project getProjectById(Long id){
        var getProjectByIdQuery = new GetProjectByIdQuery(id);
        var project = this.projectQueryService.handle(getProjectByIdQuery);
        return project.orElse(null);
    }

    public Project updateProjectProgress(Long projectId,Long completedDeliverables, Integer totalDeliverables){
        double percentComplete = (double) completedDeliverables / totalDeliverables * 100;
        var getProjectByIdQuery = new GetProjectByIdQuery(projectId);
        try {
            var project = this.projectQueryService.handle(getProjectByIdQuery);
            if(project.isEmpty())throw new IllegalArgumentException();
            var updateProjectProgress = new UpdateProjectProgressCommand(project.get(),percentComplete);
            var updatedProject = this.projectCommandService.handle(updateProjectProgress);
            if(updatedProject.isEmpty()) throw new IllegalArgumentException();
            return updatedProject.get();
        }catch (IllegalArgumentException e){
            return null;
        }
    }
}
