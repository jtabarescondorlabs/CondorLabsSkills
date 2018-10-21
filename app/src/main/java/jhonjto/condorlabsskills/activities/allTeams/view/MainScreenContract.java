package jhonjto.condorlabsskills.activities.allTeams.view;

import jhonjto.condorlabsskills.mapper.TeamsResponse;

public interface MainScreenContract {

    interface View {
        void showTeams(TeamsResponse teamsResponses);

        void showError(String message);

        void showComplete();
    }

    interface Presenter {
        void loadTeams();
    }

}
