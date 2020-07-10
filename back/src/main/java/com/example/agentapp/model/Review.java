package com.example.agentapp.model;

import com.example.agentapp.model.enums.ReviewStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text", nullable = true)
    private String text;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "status", nullable = false)
    private ReviewStatus reviewStatus;

    @Column(name = "vehicle_id", nullable = false)
    private Long vehicleId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "date_posted", nullable = false)
    private LocalDateTime date;

    public Review() {
    }

    public Review(Long id, String text, int rating, ReviewStatus status, Long vehicleId, Long userId, LocalDateTime date) {
        this.id = id;
        this.text = text;
        this.rating = rating;
        this.reviewStatus = status;
        this.vehicleId = vehicleId;
        this.userId = userId;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public ReviewStatus getStatus() {
        return reviewStatus;
    }

    public void setStatus(ReviewStatus status) {
        this.reviewStatus = status;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", rating=" + rating +
                ", status=" + reviewStatus +
                ", vehicleId=" + vehicleId +
                ", userId=" + userId +
                ", date=" + date +
                '}';
    }
}
