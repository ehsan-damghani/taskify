package com.taskify.model;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@SQLDelete(sql = "UPDATE tasks SET deleted_at = now() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getStatus() {
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
