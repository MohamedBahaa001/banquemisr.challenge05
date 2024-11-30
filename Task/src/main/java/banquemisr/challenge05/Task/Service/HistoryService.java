package banquemisr.challenge05.Task.Service;

import banquemisr.challenge05.Task.Models.Entities.History;
import banquemisr.challenge05.Task.Repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    // Fetch history for a specific user
    public Page<History> getUserHistory(String username, Pageable pageable) {
        return historyRepository.findByCompletedBy(username, pageable);
    }

    // Fetch all history entries
    public Page<History> getAllHistory(Pageable pageable) {
        return historyRepository.findAll(pageable);
    }
}
