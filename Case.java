public class Case {
    private int x,y;
    private int flood;//0 normal, et apres niveau 1 inonder, et enfin 2 submerger
    private boolean helicoptere;
    private int element; //0 est rien, 1 est eau, 2 est terre, 3 est feu, 4 est air

    public Case(int x, int y, int element){
        this.x = x;
        this.y = y;
        this.element = element;
        this.flood = 0;
        this.helicoptere = false;
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

}
