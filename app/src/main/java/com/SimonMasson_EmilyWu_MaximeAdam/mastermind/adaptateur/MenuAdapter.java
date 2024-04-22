package com.SimonMasson_EmilyWu_MaximeAdam.mastermind.adaptateur;

import android.content.Context;
import android.content.DialogInterface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import androidx.appcompat.app.AlertDialog;

import com.SimonMasson_EmilyWu_MaximeAdam.mastermind.R;

public class MenuAdapter {

    private Context context;
    private View anchor;
    private String email;
    private Runnable abandonAction;
    private Runnable newGameAction;
    private Runnable returnAction;


    public MenuAdapter(Context context, View anchor, String email, Runnable abandonAction, Runnable newGameAction, Runnable returnAction) {
        this.context = context;
        this.anchor = anchor;
        this.email = email;
        this.abandonAction = abandonAction;
        this.newGameAction = newGameAction;
        this.returnAction = returnAction;
    }
    public void showMenu() {
        PopupMenu menu = new PopupMenu(context, anchor);
        menu.getMenuInflater().inflate(R.menu.menu, menu.getMenu());

        for (int i = 0; i< menu.getMenu().size(); i++){
            MenuItem item = menu.getMenu().getItem(i);
            SpannableString spanString = new SpannableString(item.getTitle().toString());
            int end = spanString.length();
            spanString.setSpan(new AbsoluteSizeSpan(25), 0, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            item.setTitle(spanString);
        }

        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.abandonner) {
                    confirmAction(email,"abandonnez la partie", abandonAction);
                    return true;
                } else if (id == R.id.nouvelle_partie) {
                    confirmAction(email,"commencez une nouvelle partie", newGameAction);
                    return true;
                } else if (id == R.id.retour_menu) {
                    confirmAction(email,"retournez au Menu Principal", returnAction);
                    return true;
                } else if (id == R.id.cancel) {
                    menu.dismiss();
                    return true;
                } else {
                    return false;
                }
            }
        });
        menu.show();
    }
    /**
     * Alerte de confirmation du PopUpMenu
     * Ici on ne gere pas l'action choisi, on fait juste
     * @param email identifiant du joeur dans la partie, on s'addresse a ce joueur en particulier
     * @param actionName action selectionner
     * @param action ...
     */
    //?dialogue d'alerte pour confirmer le choix du joueur avec message dynamique par rapport a l'action choisi
    public void confirmAction(String email,String actionName, final Runnable action) {
        new AlertDialog.Builder(context)
                .setTitle("Agent "+email+", nous avons encore besoin de vos service...")
                .setMessage("Voulez-vous vraiement " + actionName + "?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        action.run();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
