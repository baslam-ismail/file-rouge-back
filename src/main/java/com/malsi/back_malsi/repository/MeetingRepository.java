package com.malsi.back_malsi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malsi.back_malsi.model.Meeting;

public interface MeetingRepository extends JpaRepository<Meeting, Integer> {

}
