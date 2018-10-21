package jhonjto.condorlabsskills.activities.allTeams.component;

import dagger.Component;
import jhonjto.condorlabsskills.activities.allTeams.activity.MainActivity;
import jhonjto.condorlabsskills.activities.allTeams.interactor.MainScreenModule;
import jhonjto.condorlabsskills.root.NetComponent;
import jhonjto.condorlabsskills.util.CustomScope;

@CustomScope
@Component(dependencies = NetComponent.class, modules = MainScreenModule.class)
public interface MainScreenComponent {
    void inject(MainActivity activity);
}
