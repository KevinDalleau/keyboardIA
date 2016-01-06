package donnees;

/*
Couple d'entiers
Servant d'index dans la classe Bigramme
*/
public class CoupleEntiers {
    private int i, j;
    protected CoupleEntiers(int i, int j){
       this.i = i;
       this.j = j;
    }
    
    @Override
    public boolean equals (Object o){
        CoupleEntiers a = (CoupleEntiers) o;
        return this.i == a.i && this.j == a.j;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.i;
        hash = 89 * hash + this.j;
        return hash;
    }
}
