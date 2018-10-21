package jhonjto.condorlabsskills.activities.allTeams.presenter;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import jhonjto.condorlabsskills.activities.allTeams.view.MainScreenContract;
import jhonjto.condorlabsskills.mapper.TeamDetail;
import jhonjto.condorlabsskills.mapper.TeamsResponse;
import retrofit2.Retrofit;
import retrofit2.http.GET;

import javax.inject.Inject;

public class MainScreenPresenter implements MainScreenContract.Presenter {
    public Retrofit retrofit;
    MainScreenContract.View view;

    @Inject
    public MainScreenPresenter(Retrofit retrofit, MainScreenContract.View view) {
        this.retrofit = retrofit;
        this.view = view;
    }

    @Override
    public void loadTeams() {
        getObservable().subscribeWith(getObserver());
    }

    public Observable<TeamsResponse> getObservable(){
        return retrofit.create(CondorApi.class).getTeams()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<TeamsResponse> getObserver(){
        return new DisposableObserver<TeamsResponse>() {

            @Override
            public void onNext(TeamsResponse teamsResponses) {
                view.showTeams(teamsResponses);
            }

            @Override
            public void onError(Throwable e) {
                view.showError(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
    }

    public interface CondorApi {

        @GET("search_all_teams.php?s=Soccer&c=Spain")
        Observable<TeamsResponse> getTeams();

    }

}
