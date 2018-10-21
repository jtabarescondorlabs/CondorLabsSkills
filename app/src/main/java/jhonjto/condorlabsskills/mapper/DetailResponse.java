package jhonjto.condorlabsskills.mapper;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailResponse {
    @SerializedName("teams")
    @Expose
    private List<TeamDetail> teams = null;

    public DetailResponse() {
    }

    public DetailResponse(List<TeamDetail> teams) {
        super();
        this.teams = teams;
    }

    public List<TeamDetail> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamDetail> teams) {
        this.teams = teams;
    }
}
