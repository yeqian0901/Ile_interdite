import java.util.ArrayList;
import java.util.Random;

public class Paquet {
    private Carte[] cartes;
    private Random random;
    private ArrayList<Carte> defausse;

    public Paquet(int n){
        this.cartes = new Carte[n];
        this.defausse = new ArrayList<>();
    }

    public void melangerC(){
        for(int i=cartes.length;i>0;i--){
            int j = random.nextInt(i);
            Carte t = cartes[i];
            cartes[i] = cartes[j];
            cartes[j] = t;
        }
    }

    public Carte tirer(){
        Carte t = cartes[0];
        Carte[] c = new Carte[cartes.length-1];
        for(int i=0;i<cartes.length-2;i++){
            c[i] = cartes[i+1];
        }
        this.cartes = c;
        return t;
    }

    public void poseDefau(Carte carte){
        defausse.add(carte);
    }

    public void melangerD(){
        for(int i=defausse.size();i>0;i--){
            int j = random.nextInt(i);
            Carte t = defausse.get(i);
            defausse.set(i,defausse.get(j));
            defausse.set(j,t);
        }
    }
}
