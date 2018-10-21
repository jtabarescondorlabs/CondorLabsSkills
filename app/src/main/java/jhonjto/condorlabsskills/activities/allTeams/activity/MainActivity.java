package jhonjto.condorlabsskills.activities.allTeams.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import jhonjto.condorlabsskills.R;
import jhonjto.condorlabsskills.activities.allTeams.component.DaggerMainScreenComponent;
import jhonjto.condorlabsskills.activities.allTeams.interactor.MainScreenModule;
import jhonjto.condorlabsskills.activities.allTeams.presenter.MainScreenPresenter;
import jhonjto.condorlabsskills.activities.allTeams.view.MainScreenContract;
import jhonjto.condorlabsskills.adapters.TeamsAdapter;
import jhonjto.condorlabsskills.mapper.TeamsResponse;
import jhonjto.condorlabsskills.root.App;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainScreenContract.View {
    private String TAG = "MainActivity";

    Context context;

    TeamsAdapter adapter;
    RecyclerView rvTeams;

    @BindView(R.id.clMain) protected ConstraintLayout clMain;
    @BindView(R.id.progressBar) protected ProgressBar progressBar;

    @Inject
    MainScreenPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvTeams = findViewById(R.id.rvTeams);

        ButterKnife.bind(this);
        // assign singleton instances to fields
        // We need to cast to `MyApp` in order to get the right method
        DaggerMainScreenComponent.builder()
                .netComponent(((App) getApplicationContext()).getNetComponent())
                .mainScreenModule(new MainScreenModule(this))
                .build().inject(this);

        context = this;

        getTeamList();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void getTeamList(){
        presenter.loadTeams();
    }

    @Override
    public void showTeams(TeamsResponse teams) {
        if (teams!=null) {
            rvTeams.setLayoutManager(new LinearLayoutManager(this));
            adapter = new TeamsAdapter(teams.getTeams(), MainActivity.this);
            rvTeams.setAdapter(adapter);
        } else {
            showError("No se pudieron cargar los datos");
        }
    }

    @Override
    public void showError(String message) {
        Snackbar.make(clMain, "Error" + message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showComplete() {
        Snackbar.make(clMain, "Complete", Snackbar.LENGTH_SHORT).show();
    }

}
