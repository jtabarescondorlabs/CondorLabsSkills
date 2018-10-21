package jhonjto.condorlabsskills.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import jhonjto.condorlabsskills.R;
import jhonjto.condorlabsskills.activities.detailTeam.activity.DetailActivity;
import jhonjto.condorlabsskills.mapper.Teams;

import java.util.List;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.TeamsHolder> {

    List<Teams> teamsList;
    Context context;

    public TeamsAdapter(List<Teams> teamsList, Context context) {
        this.teamsList = teamsList;
        this.context = context;
    }

    @Override
    public TeamsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_teams,parent,false);
        TeamsHolder mh = new TeamsHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(TeamsHolder holder, int position) {
        holder.tvTeamName.setText(teamsList.get(position).getStrAlternate());
        holder.tvNameStadium.setText(teamsList.get(position).getStrStadium());
        Glide.with(context).load(teamsList.get(position).getStrStadiumThumb()).into(holder.ivTeam);
    }

    @Override
    public int getItemCount() {
        return teamsList.size();
    }

    public class TeamsHolder extends RecyclerView.ViewHolder {

        ConstraintLayout clTeams;
        TextView tvTeamName,tvNameStadium;
        ImageView ivTeam;

        public TeamsHolder(View v) {
            super(v);
            clTeams = v.findViewById(R.id.clTeams);
            tvTeamName = v.findViewById(R.id.tvTeamName);
            tvNameStadium = v.findViewById(R.id.tvNameStadium);
            ivTeam = v.findViewById(R.id.ivTeam);

            clTeams.setOnClickListener(view -> {
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("idTeam", teamsList.get(getAdapterPosition()).getIdTeam());
                intent.setClass(context.getApplicationContext(), DetailActivity.class);
                context.startActivity(intent);
            });
        }
    }

}
