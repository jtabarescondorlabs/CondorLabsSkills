package jhonjto.condorlabsskills.activities.detailTeam.interactor;

import dagger.Module;
import dagger.Provides;
import jhonjto.condorlabsskills.activities.detailTeam.view.DetailScreenContract;
import jhonjto.condorlabsskills.util.CustomScope;

@Module
public class DetailScreenModule {
    private final DetailScreenContract.View view;

    public DetailScreenModule(DetailScreenContract.View view) {
        this.view = view;
    }

    @Provides
    @CustomScope
    DetailScreenContract.View provideDetailScreenContractView(){
        return view;
    }
}
