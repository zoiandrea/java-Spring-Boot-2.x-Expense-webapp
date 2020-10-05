package com.expense.app.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "project")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String description;
	private Long expense;
	private Long duration;
	private LocalDateTime creation;
	private LocalDateTime modification;
	private String createdby;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getExpense() {
		return expense;
	}
	public void setExpense(Long expense) {
		this.expense = expense;
	}
	public Long getDuration() {
		return duration;
	}
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	public LocalDateTime getCreation() {
		return creation;
	}
	public void setCreation(LocalDateTime creation) {
		this.creation = creation;
	}
	public LocalDateTime getModification() {
		return modification;
	}
	public void setModification(LocalDateTime modification) {
		this.modification = modification;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", title=" + title + ", description=" + description + ", expense=" + expense
				+ ", duration=" + duration + ", creation=" + creation + ", modification=" + modification
				+ ", createdby=" + createdby + "]";
	}

}
