package jhonjto.condorlabsskills.activities.detailTeam.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.robertsimoes.shareable.Shareable;
import jhonjto.condorlabsskills.R;
import jhonjto.condorlabsskills.activities.allTeams.activity.MainActivity;
import jhonjto.condorlabsskills.activities.detailTeam.component.DaggerDetailScreenComponent;
import jhonjto.condorlabsskills.activities.detailTeam.interactor.DetailScreenModule;
import jhonjto.condorlabsskills.activities.detailTeam.presenter.DetailScreenPresenter;
import jhonjto.condorlabsskills.activities.detailTeam.view.DetailScreenContract;
import jhonjto.condorlabsskills.mapper.DetailResponse;
import jhonjto.condorlabsskills.mapper.TeamDetail;
import jhonjto.condorlabsskills.root.App;

import javax.inject.Inject;
import java.util.List;

public class DetailActivity extends AppCompatActivity implements DetailScreenContract.View {
    private String TAG = "MainActivity";

    Context context;

    String idTeam;

    @BindView(R.id.clDetail) protected ConstraintLayout clDetail;
    @BindView(R.id.ivBadge) protected ImageView ivBadge;
    @BindView(R.id.ivJersey) protected ImageView ivJersey;
    @BindView(R.id.tvTeamName) protected TextView tvTeamName;
    @BindView(R.id.tvFundation) protected TextView tvFundation;
    @BindView(R.id.tvDescription) protected TextView tvDescription;
    @BindView(R.id.tvWebsite) protected TextView tvWebsite;
    @BindView(R.id.tvFacebook) protected TextView tvFacebook;
    @BindView(R.id.tvTwitter) protected TextView tvTwitter;
    @BindView(R.id.tvInstagram) protected TextView tvInstagram;
    @BindView(R.id.tvYoutube) protected TextView tvYoutube;

    @Inject
    DetailScreenPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);
        // assign singleton instances to fields
        // We need to cast to `MyApp` in order to get the right method
        DaggerDetailScreenComponent.builder()
                .netComponent(((App) getApplicationContext()).getNetComponent())
                .detailScreenModule(new DetailScreenModule(this))
                .build().inject(this);

        context = this;

        try {
            idTeam = getIntent().getStringExtra("idTeam");
            if (TextUtils.isEmpty(idTeam)) {
                Snackbar snackbar = Snackbar
                        .make(clDetail, "No podemos encontrar tu vehículo, Inicia Sesión!", Snackbar.LENGTH_INDEFINITE)
                        .setActionTextColor(getResources().getColor(R.color.colorAccent))
                        .setAction("Ir", view -> {
                            Intent intent = new Intent();
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.setClass(DetailActivity.this, MainActivity.class);
                            startActivity(intent);
                        });
                snackbar.show();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

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

    private void getTeamList() {
        presenter.loadTeams();
    }

    @Override
    public String idTeam() {
        return idTeam;
    }

    @Override
    public void showTeams(DetailResponse teamsResponses) {
        List<TeamDetail> teamDetails = teamsResponses.getTeams();
        int result = teamDetails.size();
        for (int i = 0; i < result; i++) {
            Glide.with(context).load(teamDetails.get(i).getStrTeamBadge()).into(ivBadge);
            Glide.with(context).load(teamDetails.get(i).getStrTeamJersey()).into(ivJersey);
            tvTeamName.setText(teamDetails.get(i).getStrAlternate());
            tvFundation.setText(teamDetails.get(i).getIntFormedYear());
            tvDescription.setText(teamDetails.get(i).getStrDescriptionES());
            int finalI = i;
            tvWebsite.setOnClickListener(view -> {
                String url = "https://"+teamDetails.get(finalI).getStrWebsite();
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            });
            tvFacebook.setOnClickListener(view -> {
                Shareable shareable = new Shareable.Builder(this)
                        .url(teamDetails.get(finalI).getStrFacebook())
                        .socialChannel(Shareable.Builder.FACEBOOK)
                        .build();
                shareable.share();
            });
            tvTwitter.setOnClickListener(view -> {
                Shareable shareable = new Shareable.Builder(this)
                        .url(teamDetails.get(finalI).getStrTwitter())
                        .socialChannel(Shareable.Builder.TWITTER)
                        .build();
                shareable.share();
            });
            tvInstagram.setOnClickListener(view -> {
                Shareable shareable = new Shareable.Builder(this)
                        .url(teamDetails.get(finalI).getStrInstagram())
                        .socialChannel(Shareable.Builder.ANY)
                        .build();
                shareable.share();
            });
            tvYoutube.setOnClickListener(view -> {
                Shareable shareable = new Shareable.Builder(this)
                        .url(teamDetails.get(finalI).getStrYoutube())
                        .socialChannel(Shareable.Builder.ANY)
                        .build();
                shareable.share();
            });
        }
    }

    @Override
    public void showError(String message) {
        Snackbar.make(clDetail, "Error" + message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showComplete() {
        Snackbar.make(clDetail, "Complete", Snackbar.LENGTH_SHORT).show();
    }
}
