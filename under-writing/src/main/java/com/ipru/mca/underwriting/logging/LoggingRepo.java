package com.ipru.mca.underwriting.logging;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface LoggingRepo extends JpaRepository<UWLogging, Long> {

}
