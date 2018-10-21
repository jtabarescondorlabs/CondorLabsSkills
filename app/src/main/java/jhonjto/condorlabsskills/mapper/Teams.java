package jhonjto.condorlabsskills.mapper;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Teams {

    @SerializedName("idTeam")
    @Expose
    private String idTeam;
    @SerializedName("strAlternate")
    @Expose
    private String strAlternate;
    @SerializedName("strStadium")
    @Expose
    private String strStadium;
    @SerializedName("strTeamBadge")
    @Expose
    private String strTeamBadge;

    public Teams() {
    }

    public Teams(String idTeam, String strAlternate, String strStadium, String strTeamBadge) {
        super();
        this.idTeam = idTeam;
        this.strAlternate = strAlternate;
        this.strStadium = strStadium;
        this.strTeamBadge = strTeamBadge;
    }

    public String getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(String idTeam) {
        this.idTeam = idTeam;
    }

    public String getStrAlternate() {
        return strAlternate;
    }

    public void setStrAlternate(String strAlternate) {
        this.strAlternate = strAlternate;
    }

    public String getStrStadium() {
        return strStadium;
    }

    public void setStrStadium(String strStadium) {
        this.strStadium = strStadium;
    }

    public String getStrStadiumThumb() {
        return strTeamBadge;
    }

    public void setStrStadiumThumb(String strStadiumThumb) {
        this.strTeamBadge = strStadiumThumb;
    }
}
