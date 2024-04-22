package com.SimonMasson_EmilyWu_MaximeAdam.mastermind.dao.sql;

import java.util.Date;

public class MastermindContrat {
    public static final String DB_NAME="MASTERMIND.DB";
    public  String NOM_TABLE_PARTIE = "partie";
    public static final int DB_VERSION=1;
    public static final String NOM_TABLE = "Partie";

    public class colonnes{
        public static final  String COLONNE_ID_CODE = "ID";
        public static final String COLONNE_COURRIEL = "courriel";
        public static final String COLONNE_CODE = "code";
        public static final String COLONNE_NB_COULEUR = "nombre_de_couleur";
        public static final String COLONNE_RESULTAT = "resultat";
        public static final String COLONNE_NB_TENTATIVE = "nombre_de_tentative";
        public static final String COLONNE_DATE = "date_partie";

    }
}
