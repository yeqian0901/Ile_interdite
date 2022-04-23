public class Case extends ZoneCliquable{
    private int x,y;
    private int flood;//0 normal, et apres niveau 1 inonder, et enfin 2 submerger
    private boolean helicoptere;
    private int element;//0 est rien, 1 est eau, 2 est terre, 3 est feu, 4 est air

    public Case(boolean heli, int x, int y, int element){
        super("",100,100);
        this.x=x;
        this.y=y;
        this.element = element;
        this.flood = 0;
        this.helicoptere = heli;
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

    public int getElement() {
        return element;
    }

    public boolean isHelicoptere() {
        return helicoptere;
    }

    public void setElement(int element) {
        if(element < 0){
            return;
        }
        this.element = element;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void clicGauche(){}
    public void clicDroit(){}
}
