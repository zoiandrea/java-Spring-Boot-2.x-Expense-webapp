package com.expense.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.expense.app.model.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

}
