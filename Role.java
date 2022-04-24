public class Role {
    private String nom;//Pilote,Ingenieur,Explorateur,Navigateur,Plongeur,Messager
    private int skill;

    public Role(Joueur joueur, int skill){
        this.nom = joueur.getNom();
        this.skill = skill;
    }
}
