package com.example.agentapp.repository.vehicle;

import com.example.agentapp.model.vehicle.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {


    Vehicle findOneById(Long Id);

    void removeById(Long Id);

    List<Vehicle> findByCollisionProtection(boolean collisionProtection);

    List<Vehicle> findByChildrenSeats(int childrenSeats);

    List<Vehicle> findByStartDate(Date startDate);

    List<Vehicle> findByEndDate(Date endDate);

    List<Vehicle> findByFuelTypeId(Long Id);

    List<Vehicle> findByMakeId(Long Id);

    List<Vehicle> findByModelId(Long Id);

    List<Vehicle> findByStyleId(Long Id);

    List<Vehicle> findByTransmissionId(Long Id);

    List<Vehicle> findAllByUserId(Long id);


}
