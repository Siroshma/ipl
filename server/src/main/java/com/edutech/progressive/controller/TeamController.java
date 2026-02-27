package com.edutech.progressive.controller;
import com.edutech.progressive.entity.Team;
import com.edutech.progressive.service.TeamService;
import com.edutech.progressive.service.impl.TeamServiceImplArraylist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import java.sql.SQLException;
import java.util.List;
 
 
@RestController
@RequestMapping("/team")
public class TeamController {
 
    private final TeamService teamServiceJpa;
 
    @Autowired(required = false)
    private TeamServiceImplArraylist teamServiceArraylist;
 
    public TeamController(TeamService teamServiceJpa) {
        this.teamServiceJpa = teamServiceJpa;
    }
    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() throws SQLException {
        return ResponseEntity.ok(teamServiceJpa.getAllTeams());
    }
    @GetMapping("/{teamId}")
    public ResponseEntity<Team> getTeamById(@PathVariable int teamId) throws SQLException {
        return ResponseEntity.ok(teamServiceJpa.getTeamById(teamId));
    }
    @PostMapping
    public ResponseEntity<Integer> addTeam(@RequestBody Team team) throws SQLException {
        int id = teamServiceJpa.addTeam(team);
        return ResponseEntity.status(HttpStatus.CREATED).body(id); // ✅ 201
    }
    @PutMapping("/{teamId}")
    public ResponseEntity<Void> updateTeam(@PathVariable int teamId, @RequestBody Team team) throws SQLException {
        team.setTeamId(teamId);
        teamServiceJpa.updateTeam(team);
        return ResponseEntity.ok().build(); // 200
    }
    @DeleteMapping("/{teamId}")
    public ResponseEntity<Void> deleteTeam(@PathVariable int teamId) throws SQLException {
        teamServiceJpa.deleteTeam(teamId);
        return ResponseEntity.noContent().build(); // 204
    }
 
 
    @GetMapping("/fromArrayList")
    public ResponseEntity<List<Team>> getAllTeamsFromArrayList() throws SQLException {
        return ResponseEntity.ok(teamServiceArraylist.getAllTeams());
    }
 
    @GetMapping("/fromArrayList/sorted")
    public ResponseEntity<List<Team>> getAllTeamsSortedByNameFromArrayList() throws SQLException {
        return ResponseEntity.ok(teamServiceArraylist.getAllTeamsSortedByName());
    }
 
    @PostMapping("/toArrayList")
    public ResponseEntity<Integer> addTeamToArrayList(@RequestBody Team team) throws SQLException {
        int size = teamServiceArraylist.addTeam(team);
        return ResponseEntity.status(HttpStatus.CREATED).body(size);
    }
}