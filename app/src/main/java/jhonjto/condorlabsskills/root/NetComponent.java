package jhonjto.condorlabsskills.root;

import dagger.Component;
import jhonjto.condorlabsskills.http.NetModule;
import retrofit2.Retrofit;

import javax.inject.Singleton;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {

    Retrofit retrofit();

}
