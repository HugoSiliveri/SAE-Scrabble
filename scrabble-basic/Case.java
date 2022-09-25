public class Case {
    private int couleur;
    private char lettre;

    /**
     * pré-requis : uneCouleur est un entier entre 1 et 5
     * action : constructeur de Case
     */
    public Case(int uneCouleur) {
        this.couleur = uneCouleur;
        this.lettre = ' ';
    }

    /**
     * résultat : la couleur de this, un nombre entre 1 et 5
     */
    public int getCouleur() {
        return this.couleur;
    }

    /**
     * pré-requis : cette case est recouverte
     */
    public char getLettre() {
        return this.lettre;
    }

    /**
     * pré-requis : let est une lettre majuscule
     */
    public void setLettre(char let) {
        this.lettre = let;
    }

    /**
     * résultat : vrai ssi la case est recouverte par une lettre
     */
    public boolean estRecouverte() {
        return this.lettre != ' ';
    }

    public String toString() {
        String screen = "";
        String[] bgColors = {"\u001B[0m", "\u001B[48;5;195m", "\u001B[48;5;19m", "\u001B[48;5;206m", "\u001B[41m"};
        String[] fgColors = {"\u001B[0m", "\u001B[38;5;195m", "\u001B[38;5;19m", "\u001B[38;5;206m", "\u001B[31m"};
        if (this.estRecouverte()) screen += "\u001B[0m"+" "+fgColors[this.couleur - 1]+"\u001B[1m"+this.lettre+"\u001B[0m"+" ┃";
        else screen += "\u001B[0m"+" "+bgColors[this.couleur - 1]+"\u001B[1m"+this.lettre+"\u001B[0m"+" ┃";
        return screen;
    }
}
