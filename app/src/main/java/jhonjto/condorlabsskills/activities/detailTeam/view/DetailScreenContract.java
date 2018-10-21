package jhonjto.condorlabsskills.activities.detailTeam.view;

import jhonjto.condorlabsskills.mapper.DetailResponse;

public interface DetailScreenContract {

    interface View {
        String idTeam();

        void showTeams(DetailResponse teamsResponses);

        void showError(String message);

        void showComplete();
    }

    interface Presenter {
        void loadTeams();
    }

}
