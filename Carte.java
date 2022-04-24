public class Carte {
    private String nom;
    private int skill; //0 est sac de sable, 1 est helicoptere, 2 est inondé

    public Carte(String nom,int skill){
        this.nom = nom;
        this.skill = skill;
    }

    public int getSkill() {
        return skill;
    }
}
