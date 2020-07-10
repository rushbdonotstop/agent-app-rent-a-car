package com.example.agentapp.repository;

import com.example.agentapp.model.Review;
import com.example.agentapp.model.enums.ReviewStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByVehicleId(Long vehicleId);

    List<Review> findAllByReviewStatus(ReviewStatus pending);

    List<Review> findAllByVehicleIdAndReviewStatus(Long vehicleId, ReviewStatus approved);

    List<Review> findAllByVehicleIdAndUserIdAndReviewStatusNot(Long vehicleId, Long userId, ReviewStatus rejected);
}
