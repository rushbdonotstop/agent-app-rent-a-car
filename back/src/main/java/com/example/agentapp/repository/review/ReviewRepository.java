package com.example.agentapp.repository.review;

import com.example.agentapp.model.review.Review;
import com.example.agentapp.model.enums.ReviewStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByVehicleId(Long vehicleId);

    List<Review> findAllByStatus(ReviewStatus pending);

    List<Review> findAllByVehicleIdAndStatus(Long vehicleId, ReviewStatus approved);

    List<Review> findAllByVehicleIdAndUserIdAndStatusNot(Long vehicleId, Long userId, ReviewStatus rejected);
}
