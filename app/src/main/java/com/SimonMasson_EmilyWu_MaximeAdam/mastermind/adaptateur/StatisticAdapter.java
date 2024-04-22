package com.SimonMasson_EmilyWu_MaximeAdam.mastermind.adaptateur;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.R;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.modele.entite.Statistique;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.presentateur.PresentateurMastermind;
import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.presentateur.PresentateurStatistique;

public class StatisticAdapter extends ArrayAdapter<Statistique> {
    private final Context context;
    private int viewId;
    private final PresentateurMastermind presentateur;
    public StatisticAdapter(@NonNull Context context, int viewId, PresentateurMastermind presentateur){
        super(context, viewId);
        this.context = context;
        this.viewId = viewId;
        this.presentateur = presentateur;
    }
    @Override
    public int getCount(){return this.presentateur.getNbMastermind();}
    @NonNull
    @Override
    public View getView(int position, @Nullable View vView, @NonNull ViewGroup parent){
        View view = vView;
        if(view == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.
                    LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(this.viewId, parent, false);
        }
        if(presentateur.getMastermind(position) != null){
            final TextView textViewID = (TextView) view.findViewById(R.id.textViewID);
            final TextView textViewDate = (TextView) view.findViewById(R.id.textViewDate);

            textViewID.setText("ID : "+presentateur.getMastermind(position).getId());
            textViewDate.setText("Date : "+presentateur.getMastermind(position).getId());
        }
        return view;
    }
}
