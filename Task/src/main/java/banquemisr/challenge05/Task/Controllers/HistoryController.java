package banquemisr.challenge05.Task.Controllers;

import banquemisr.challenge05.Task.Service.HistoryService;

import banquemisr.challenge05.Task.Models.Entities.History;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/history")
@Validated
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    // Fetch history table (ADMIN ONLY)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/allHistory")
    public ResponseEntity<Page<History>> getAllHistory(Pageable pageable) {
        Page<History> histories = historyService.getAllHistory(pageable);
        return new ResponseEntity<>(histories, HttpStatus.OK);
    }

    // Fetch history of tasks for user (USER ONLY)
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/user/myHistory")
    public ResponseEntity<Page<History>> getUserHistory(Pageable pageable) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Page<History> histories = historyService.getUserHistory(username, pageable);
        return new ResponseEntity<>(histories, HttpStatus.OK);
    }
}
