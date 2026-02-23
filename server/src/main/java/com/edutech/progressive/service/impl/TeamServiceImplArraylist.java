package com.edutech.progressive.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.edutech.progressive.entity.Team;
import com.edutech.progressive.service.TeamService;

public class TeamServiceImplArraylist implements TeamService {

    private static List<Team> teamList = new ArrayList<>();

    @Override
    public List<Team> getAllTeams() {
        return teamList;
    }

    @Override
    public int addTeam(Team team) {
        teamList.add(team);
        return teamList.size();
    }

    @Override
    public List<Team> getAllTeamsSortedByName() {
        List<Team> sortedTeam = teamList;
        sortedTeam.sort(Comparator.comparing(Team::getTeamName));
        return sortedTeam;
    }

    @Override
    public void emptyArrayList() {
        teamList = new ArrayList<>();
    }

    


}