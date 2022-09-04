package com.rostik.andrusiv.microrecipient.repository;

import com.rostik.andrusiv.microrecipient.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
