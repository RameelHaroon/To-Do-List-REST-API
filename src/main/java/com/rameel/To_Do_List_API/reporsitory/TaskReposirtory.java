package com.rameel.To_Do_List_API.reporsitory;

import com.rameel.To_Do_List_API.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskReposirtory extends JpaRepository<Task, UUID> {
    List<Task> findByUserId(UUID userId);
}
