package jhonjto.condorlabsskills.mapper;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeamsResponse {

    @SerializedName("teams")
    @Expose
    private List<Teams> teams = null;

    public TeamsResponse() {
    }

    public TeamsResponse(List<Teams> teams) {
        super();
        this.teams = teams;
    }

    public List<Teams> getTeams() {
        return teams;
    }

    public void setTeams(List<Teams> teams) {
        this.teams = teams;
    }
}
