package jhonjto.condorlabsskills.root;

import android.app.Application;
import jhonjto.condorlabsskills.http.NetModule;

public class App extends Application {

    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("https://www.thesportsdb.com/api/v1/json/1/"))
                .build();
    }

    public NetComponent  getNetComponent() {
        return mNetComponent;
    }
}
