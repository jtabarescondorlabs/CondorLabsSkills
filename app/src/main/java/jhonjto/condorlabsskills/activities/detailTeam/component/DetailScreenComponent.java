package jhonjto.condorlabsskills.activities.detailTeam.component;

import dagger.Component;
import jhonjto.condorlabsskills.activities.detailTeam.activity.DetailActivity;
import jhonjto.condorlabsskills.activities.detailTeam.interactor.DetailScreenModule;
import jhonjto.condorlabsskills.root.NetComponent;
import jhonjto.condorlabsskills.util.CustomScope;

@CustomScope
@Component(dependencies = NetComponent.class, modules = DetailScreenModule.class)
public interface DetailScreenComponent {
    void inject(DetailActivity detailActivity);
}
