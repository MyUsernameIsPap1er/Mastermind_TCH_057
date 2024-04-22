package com.SimonMasson_EmilyWu_MaximeAdam.mastermind.modele.entite;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Mastermind {
    private String id;
    private Code secretCode;
    private List<Tentative> tentatives;
    private int maxTentatives;
    private String mail;
    //?Résultat de la partie (gagnée, perdue ou abancdonnée)
    private String resultat;
    private Date date;

    public String getId(){return id;};
    public void setId(String id){this.id = id;}

    public Code getSecretCode() {
        return secretCode;
    }

    public void setSecretCode(Code secretCode) {
        this.secretCode = secretCode;
    }

    public List<Tentative> getTentatives() {
        return tentatives;
    }

    public void setTentatives(List<Tentative> tentatives) {
        this.tentatives = tentatives;
    }

    public int getMaxTentatives() {
        return maxTentatives;
    }

    public void setMaxTentatives(int maxTentatives) {
        this.maxTentatives = maxTentatives;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    /**Constructeur Mastermind()-6
     * uniquement pour tester StatisticActivity
     * -
     * @param id
     * @param courriel
     * @param code
     * @param nb_coul
     * @param resultat
     * @param nb_tentative
     */
    public Mastermind(String id, String courriel,String[] code, int nb_coul, String resultat, int nb_tentative){
        this.secretCode = new Code(id,code,nb_coul);
        this.mail = courriel;
        this.date = new Date();
        this.tentatives = new List<Tentative>() {
            @Override
            public int size() {
                return nb_tentative;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(@Nullable Object o) {
                return false;
            }

            @NonNull
            @Override
            public Iterator<Tentative> iterator() {
                return null;
            }

            @NonNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @NonNull
            @Override
            public <T> T[] toArray(@NonNull T[] a) {
                return null;
            }

            @Override
            public boolean add(Tentative tentative) {
                return false;
            }

            @Override
            public boolean remove(@Nullable Object o) {
                return false;
            }

            @Override
            public boolean containsAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(@NonNull Collection<? extends Tentative> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, @NonNull Collection<? extends Tentative> c) {
                return false;
            }

            @Override
            public boolean removeAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(@NonNull Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Tentative get(int index) {
                return null;
            }

            @Override
            public Tentative set(int index, Tentative element) {
                return null;
            }

            @Override
            public void add(int index, Tentative element) {

            }

            @Override
            public Tentative remove(int index) {
                return null;
            }

            @Override
            public int indexOf(@Nullable Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(@Nullable Object o) {
                return 0;
            }

            @NonNull
            @Override
            public ListIterator<Tentative> listIterator() {
                return null;
            }

            @NonNull
            @Override
            public ListIterator<Tentative> listIterator(int index) {
                return null;
            }

            @NonNull
            @Override
            public List<Tentative> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
        this.resultat = resultat;
    }

    /**
     * Constructeur Simple de la partie de MasterMind
     * utiliser a l'initialisation d'une partie
     * -
     * @param maxTentatives tenative delimitante de la fin de partie
     * @param secretCode code delimitant la condition de victoire
     **/
    public Mastermind(Code secretCode, int maxTentatives) {
        this.secretCode = secretCode;
        this.maxTentatives = maxTentatives;
        this.tentatives = new ArrayList<>();
        this.date = new Date();

    }
    /**
     * Delimitte les actions permise par l'utilisateur pour la partie de MasterMind
     * on fait la gestion de chaque tour d'une partie,
     * le Constructeur Simple de MasterMind est utiliser avec cette fonction au
     * moment d'initialiser la partie
     * @param proposition le codeSecret de l'object MasterMind qui sera utiliser
     *                    pour la partie
     */
    //?delimite une ronde de MASTERMIND
    public void jouer(Code proposition) {
        //?check si il a perdue
        if (tentatives.size() >= maxTentatives) {
            resultat = "perdue";
            System.out.println("Max tentatives atteintes. Fin du jeu!");
            return;
        }
//?prepare la proposition
        Feedback feedback = secretCode.compareWith(proposition);
        Tentative tentative = new Tentative(proposition, feedback);
        tentatives.add(tentative);
//? compare la tentative au codeSecret
        if (feedback.getCorrectPosition() == secretCode.getColors().length) {
            resultat = "gagnée";
            System.out.println("Félicitations! Vous avez deviné le code.");
        } else {
            System.out.println("Proposition incorrecte. Réessayez.");
        }
    }
    //?regarde si la partie a ete abandonner
    public void checkAbandon() {
        if (resultat == null) {
            resultat = "abandonnée";
            System.out.println("Partie abandonnée.");
        }
    }
    //?regarde si la partie est completer
    public boolean checkComplete(){
        return resultat != null;
    }


}
