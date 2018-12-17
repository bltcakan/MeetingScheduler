package com.n11.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.n11.model.Meeting;

@Repository
public interface MeetingRepository extends CrudRepository<Meeting, Long> {

}
