package banquemisr.challenge05.Task.Repository;

import banquemisr.challenge05.Task.Models.Entities.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    Page<History> findByTaskId(Long taskId,Pageable pageable);

    Page<History> findByCompletedBy(String username, Pageable pageable);
}

