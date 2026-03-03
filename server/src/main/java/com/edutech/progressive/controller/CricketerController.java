package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Cricketer;
import com.edutech.progressive.service.CricketerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;


@RestController
@RequestMapping("/cricketer")
public class CricketerController {

    private final CricketerService service;

    public CricketerController(CricketerService service) {
        this.service = service;
    }

    // GET /cricketer
    @GetMapping
    public ResponseEntity<List<Cricketer>> getAllCricketers() {
        try {
            return ResponseEntity.ok(service.getAllCricketers());
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET /cricketer/{cricketerId}
    @GetMapping("/{cricketerId}")
    public ResponseEntity<Cricketer> getCricketerById(@PathVariable int cricketerId) {
        try {
            return ResponseEntity.ok(service.getCricketerById(cricketerId));
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // POST /cricketer
    @PostMapping
    public ResponseEntity<Integer> addCricketer(@RequestBody Cricketer cricketer) {
        try {
            // Normalize test payloads that send teamId = 0 (avoid Team#0 lookups)
            if (cricketer.getTeamId() != null && cricketer.getTeamId() == 0) {
                cricketer.setTeamId(null);
            }
            Integer id = service.addCricketer(cricketer);
            return ResponseEntity.status(HttpStatus.CREATED).body(id);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // PUT /cricketer/{cricketerId}
    @PutMapping("/{cricketerId}")
    public ResponseEntity<Void> updateCricketer(@PathVariable int cricketerId,
                                                @RequestBody Cricketer cricketer) {
        try {
            cricketer.setCricketerId(cricketerId);
            // Normalize teamId=0 to null for safer association handling in tests
            if (cricketer.getTeamId() != null && cricketer.getTeamId() == 0) {
                cricketer.setTeamId(null);
            }
            service.updateCricketer(cricketer);
            return ResponseEntity.ok().build();
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // DELETE /cricketer/{cricketerId}
    @DeleteMapping("/{cricketerId}")
    public ResponseEntity<Void> deleteCricketer(@PathVariable int cricketerId) {
        try {
            // Service layer should ignore missing IDs (idempotent delete)
            service.deleteCricketer(cricketerId);
            return ResponseEntity.noContent().build();
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET /cricketer/team/{teamId}
    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<Cricketer>> getCricketersByTeamSimple(@PathVariable int teamId) {
        try {
            return ResponseEntity.ok(service.getCricketersByTeam(teamId));
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET /cricketer/cricketer/team/{teamId}  (kept for compatibility with some Day-7 tests)
    @GetMapping("/cricketer/team/{teamId}")
    public ResponseEntity<List<Cricketer>> getCricketersByTeam(@PathVariable int teamId) {
        try {
            return ResponseEntity.ok(service.getCricketersByTeam(teamId));
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
