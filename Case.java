import java.util.Set;
import java.util.HashSet;

public class Case extends IG.ZoneCliquable{
    private String nom;
    private int x,y;
    private int flood;//0 normal, et apres niveau 1 inonder, et enfin 2 submerger
    private boolean helicoptere;
    private int element;//0 est rien, 1 est eau, 2 est terre, 3 est feu, 4 est air
    private Set<Arete> aretes;

    public Case(String nom,int x, int y, int element){
        super(nom,100,100);
        this.nom = nom;
        this.x=x;
        this.y=y;
        this.element = element;
        this.flood = 0;
        this.helicoptere = false;
        this.aretes = new HashSet<Arete>();
    }

    public void addHelicop(){
        this.helicoptere = true;
    }

    public void addflood(){
        if(this.flood == 0 || this.flood == 1){
            this.flood++;
        }
    }

    public void reduceflood(){
       if(this.flood == 1){
           this.flood--;
       }
    }

    public boolean arete(){
        if(aretes.size()>4){
            return false;
        }
        return true;
    }

    public void ajouteArete(Arete a){
            aretes.add(a);
    }

    public void setCase(int x,int y){
        this.x = x;
        this.y = y;
    }

    public void clicGauche(){}
    public void clicDroit(){}
}
