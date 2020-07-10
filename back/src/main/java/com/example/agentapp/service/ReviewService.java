package com.example.agentapp.service;

import com.example.agentapp.model.Notification;
import com.example.agentapp.model.Review;
import com.example.agentapp.model.enums.ReviewStatus;
import com.example.agentapp.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    public List<Review> getByVehicleId(Long vehicleId) {
        return reviewRepository.findAllByVehicleIdAndReviewStatus(vehicleId, ReviewStatus.APPROVED);
    }

    public List<Review> userPosted(Long vehicleId, Long userId) {
        return reviewRepository.findAllByVehicleIdAndUserIdAndReviewStatusNot(vehicleId, userId, ReviewStatus.REJECTED);
    }

    public Notification update(Review review) {
        Notification notification = new Notification("Review can't be updated!");
        try{
            if(review.getRating() <= 0 || review.getRating() > 5){
                notification.setText("Rating is mandatory.");
                return notification;
            }

            if(reviewRepository.findById(review.getId()).isPresent()){
                reviewRepository.save(review);
                notification.setText("Updated review.");
            }
            else{
                notification.setText("Review does not exist.");
            }
        }
        catch (Exception e){
            notification.setText("Exception occured.");
        }
        return notification;
    }

    public Notification create(Review review) {
        Notification notification = new Notification("Review can't be created!");
        try{
            if(review.getRating() <= 0 || review.getRating() > 5){
                notification.setText("Rating is mandatory.");
                return notification;
            }

            review.setId(null);
            review.setStatus(ReviewStatus.PENDING);
            reviewRepository.save(review);
            notification.setText("Created review. Waiting for admin to approve.");
        }
        catch (Exception e){
            notification.setText("Exception occured.");
        }
        return notification;
    }

    public Notification delete(Long reviewId) {
        Notification notification = new Notification("Review can't be deleted!");
        try{
            if(reviewRepository.findById(reviewId).isPresent()) {
                reviewRepository.deleteById(reviewId);
                notification.setText("Deleted review.");
            }
            else{
                notification.setText("Review does not exist.");
            }
        }
        catch (Exception e){
            notification.setText("Exception occured.");
        }
        return notification;
    }

    public Review get(Long reviewId) {
        if(reviewRepository.findById(reviewId).isPresent()){
            return reviewRepository.findById(reviewId).get();
        }
        return null;
    }

    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    public List<Review> getPending() {
        return reviewRepository.findAllByReviewStatus(ReviewStatus.PENDING);
    }
}
