package donnees;

/*
Couple d'entiers ordonnés pour faciliter leur comparaison
Servent d'index dans la classe Bigramme
*/
public class CoupleEntiers {
    private int min, max;
    protected CoupleEntiers(int i, int j){
        if(i < j){
            this.min = i;
            this.max = j;
        } else {
            this.min = j;
            this.max = i;
        }
    }
    public boolean equals (Object o){
        CoupleEntiers a = (CoupleEntiers) o;
        return this.min == a.min && this.max == a.max;
    }
}
