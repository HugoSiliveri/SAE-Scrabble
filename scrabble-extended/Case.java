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
        String[] bgColors = {Utils.RESET, Utils.LIGHT_BLUE_BG, Utils.DARK_BLUE_BG, Utils.PINK_BG, Utils.RED_BG};
        String[] fgColors = {Utils.RESET, Utils.LIGHT_BLUE, Utils.DARK_BLUE, Utils.PINK, Utils.RED};
        if (this.estRecouverte()) screen += Utils.RESET+" "+fgColors[this.couleur - 1]+Utils.BOLD+this.lettre+Utils.RESET+" ┃";
        else screen += Utils.RESET+" "+bgColors[this.couleur - 1]+Utils.BOLD+this.lettre+Utils.RESET+" ┃";
        return screen;
    }
}
