package com.midterm.devops.task3.repo;

import com.midterm.devops.task3.domain.DemoMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemoMessageRepository extends JpaRepository<DemoMessage, Long> {
}
