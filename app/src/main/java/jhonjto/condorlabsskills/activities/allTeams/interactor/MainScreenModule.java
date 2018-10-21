package jhonjto.condorlabsskills.activities.allTeams.interactor;

import dagger.Module;
import dagger.Provides;
import jhonjto.condorlabsskills.activities.allTeams.view.MainScreenContract;
import jhonjto.condorlabsskills.util.CustomScope;

@Module
public class MainScreenModule {
    private final MainScreenContract.View mView;

    public MainScreenModule(MainScreenContract.View mView) {
        this.mView = mView;
    }

    @Provides
    @CustomScope
    MainScreenContract.View providesMainScreenContractView() {
        return mView;
    }

}
