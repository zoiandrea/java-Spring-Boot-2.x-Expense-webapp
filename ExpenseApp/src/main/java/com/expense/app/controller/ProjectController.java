package com.expense.app.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.app.model.Project;
import com.expense.app.repository.ProjectRepository;

@RestController
@RequestMapping("/project")
public class ProjectController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

	@Autowired
	private ProjectRepository projectRepository;

	@GetMapping("/findall")
	public Map<String, Object> findAllProjects() {
		LOGGER.info("Getting List Of All Projects....");
		Map<String, Object> projectList = new HashMap<String, Object>();
		projectList.put("data", projectRepository.findAll());
		return projectList;
	}

	@PostMapping("/save/{username}")
	public ResponseEntity<?> saveProject(@RequestBody Project project, @PathVariable("username") String username) {
		LOGGER.info("Saving Project ===> " + project);
		project.setCreatedby(username);
		project.setCreation(LocalDateTime.now());
		project.setModification(LocalDateTime.now());
		projectRepository.save(project);
		return ResponseEntity.ok("Project Saved Successfully");
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProject(@PathVariable("id") Long id) {
		LOGGER.info("Deleting Project ID ===>" + id);
		projectRepository.deleteById(id);
		return ResponseEntity.ok("Project Deleted Successfully");
	}

}
