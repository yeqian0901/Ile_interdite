public class Arete {
    private Case[] extremites;

    public Arete(Case s1, Case s2) {
        // On range les extrémité dans l'ordre donné par les paramètres
        // Variante : on les classe en fonction d'un ordre (pas utile ici)
        extremites = new Case[2];
        extremites[0] = s1;
        extremites[1] = s2;
        // On inscrit l'arête dans les listes d'incidence de ses extrémités
        if(s1.arete() && s2.arete()) {
            s1.ajouteArete(this);
            s2.ajouteArete(this);
        }
    }
}
