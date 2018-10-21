package jhonjto.condorlabsskills.activities.detailTeam.presenter;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import jhonjto.condorlabsskills.activities.detailTeam.view.DetailScreenContract;
import jhonjto.condorlabsskills.mapper.DetailResponse;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

import javax.inject.Inject;

public class DetailScreenPresenter implements DetailScreenContract.Presenter {
    public Retrofit retrofit;
    DetailScreenContract.View view;

    @Inject
    public DetailScreenPresenter(Retrofit retrofit, DetailScreenContract.View view) {
        this.retrofit = retrofit;
        this.view = view;
    }

    @Override
    public void loadTeams() {
        getObservable().subscribeWith(getObserver());
    }

    public Observable<DetailResponse> getObservable(){
        return retrofit.create(DetailScreenPresenter.CondorApi.class).getTeamDetails(view.idTeam())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<DetailResponse> getObserver(){
        return new DisposableObserver<DetailResponse>() {

            @Override
            public void onNext(DetailResponse teamsResponses) {
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

        @GET("lookupteam.php")
        Observable<DetailResponse> getTeamDetails(@Query("id") String idTeam);

    }

}
