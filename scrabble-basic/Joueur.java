public class Joueur {

    private String nom;
    private MEE chevalet;
    private int score;

    public Joueur(String unNom) {
        this.nom = unNom;
        this.chevalet = new MEE(26);
        this.score = 0;
    }

    public String toString() {
        return "Joueur " + "\u001B[33m" + this.nom + "\u001B[0m" + "\n- Score: " + this.score + "\n\n"
                + this.chevalet.toString() + "\u001B[0m";
    }

    private void printChevalet() {
        System.out.print("\n\n" + this.chevalet);
    }

    public int getScore() {
        return this.score;
    }

    public void ajouteScore(int nb) {
        this.score += nb;
    }

    /**
     * pré-requis : nbPointsJet indique le nombre de points rapportés par
     * chaque jeton/lettre
     * résultat : le nombre de points total sur le chevalet de ce joueur
     * suggestion : bien relire la classe MEE !
     */
    public int nbPointsChevalet(int[] nbPointsJet) {
        return this.chevalet.sommeValeurs(nbPointsJet);
    }

    /**
     * pré-requis : les éléments de s sont inférieurs à 26
     * action : simule la prise de nbJetons jetons par this dans le sac s,
     * dans la limite de son contenu.
     */
    public void prendJetons(MEE s, int nbJetons) {
        if (nbJetons <= s.getNbTotEx()) {
            s.transfereAleat(this.chevalet, nbJetons);
        } else {
            s.transfereAleat(this.chevalet, s.getNbTotEx());
        }
    }

    /**
     * pré-requis : les éléments de s sont inférieurs à 26*et nbPointsJet.length >=
     * 26
     * action : simule le coup de this : this choisit de passer son tour,
     * d’échanger des jetons ou de placer un mot
     * résultat : -1 si this a passé son tour, 1 si son chevalet est vide,
     * et 0 sinon
     */
    public int joue(Plateau p, MEE s, int[] nbPointsJet) {
        System.out.print("\n\n" + p + "\n\n" + this);
        System.out.print("\u001B[33m" + "\n\nIndications de Jeu:" + "\u001B[0m"
                + "\n\n- [J]ouer un mot\n- [E]changer des lettres\n- [P]asser\n\n"
                + "\u001B[36m" + this.nom + "\u001B[0m" + ":\n\nC'est votre tour, que souhaitez-vous faire ?\n> "
                + "\u001B[32m");

        String input = "";
        try {
            input = Ut.saisirChaine();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        char choice = ' ';
        if (input.length() == 1)
            choice = Character.toUpperCase(input.charAt(0));
        System.out.print("\u001B[0m");

        if (choice == 'P')
            return -1;
        else if (choice == 'E') {
            if (this.chevalet.estVide())
                return 1;
            else {
                this.echangeJetons(s);
                return 0;
            }
        } else if (choice == 'J') {
            if (this.chevalet.estVide())
                return 1;
            else {
                boolean status = this.joueMot(p, s, nbPointsJet);
                if (!status) {
                    Ut.pause(3000);
                    System.out.println();
                    // System.out.println("\u001B[31m" + "\u001B[1m" + "\nErreur: " + "\u001B[0m" +
                    // "Le placement du mot a échoué.");
                    return this.joue(p, s, nbPointsJet);
                } else
                    return 0;
            }
        } else {
            Ut.clearConsole();
            System.out.print(p + "\n\n" + this);
            System.out.println("\u001B[31m" + "\u001B[1m" + "\n\nErreur: " + "\u001B[38;5;208m" + input + "\u001B[0m"
                    + " n'est pas un choix valide.\n\u001B[8CVeuillez recommencer" + "\n\n");
            Ut.pause(1300);
            return this.joue(p, s, nbPointsJet);
        }
    }

    /**
     * pré-requis : les éléments de s sont inférieurs à 26
     * et nbPointsJet.length >= 26
     * action : simule le placement d’un mot de this :
     * a) le mot, sa position sur le plateau et sa direction, sont saisis
     * au clavier
     * b) vérifie si le mot est valide
     * c) si le coup est valide, le mot est placé sur le plateau
     * résultat : vrai ssi ce coup est valide, c’est-à-dire accepté par
     * CapeloDico et satisfaisant les règles détaillées plus haut
     * stratégie : utilise la méthode joueMotAux
     */
    public boolean joueMot(Plateau p, MEE s, int[] nbPointsJet) {
        System.out.print(p);
        this.printChevalet();
        String mot = "";
        System.out.print("\n\nVeuillez saisir un mot\n> " + "\u001B[32m");
        try {
            mot = Ut.saisirChaine();
            System.out.print("\u001B[0m");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        int x = 0;
        System.out.print("\n\nVeuillez saisir la position du mot (N° de Ligne)\n> Ligne N°" + "\u001B[32m");
        try {
            x = Ut.saisirEntier();
            System.out.print("\u001B[0m");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        while (x < 1 || x > 15) {
            System.out.println("\u001B[31m" + "\u001B[1m" + "\nErreur: " + "\u001B[0m"
                    + "Numéro de ligne inconnu !\n\u001B[8CVeuillez réessayer avec un entier compris entre 1 et 15.");
            System.out.print("\nVeuillez saisir la position du mot (N° de Ligne)\n> Ligne N°" + "\u001B[32m");
            try {
                x = Ut.saisirEntier();
                System.out.print("\u001B[0m");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        int y = 0;

        System.out.print("\n\nVeuillez saisir la position du mot (N° de Colonne)\n> Colonne N°" + "\u001B[32m");
        try {
            y = Ut.saisirEntier();
            System.out.print("\u001B[0m");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        while (y < 1 || y > 15) {
            System.out.println("\u001B[31m" + "\u001B[1m" + "\nErreur: " + "\u001B[0m"
                    + "Numéro de colonne inconnu !\n\u001B[8CVeuillez réessayer avec un entier compris entre 1 et 15.");
            System.out.print("\nVeuillez saisir la position du mot (N° de Colonne)\n> Colonne N°" + "\u001B[32m");
            try {
                y = Ut.saisirEntier();
                System.out.print("\u001B[0m");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        char sens = ' ';
        System.out.print("\nVeuillez saisir le sens du mot (h/v)\n> " + "\u001B[32m");
        try {
            sens = Ut.saisirCaractere();
            System.out.print("\u001B[0m");
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        while (sens != 'h' && sens != 'v') {
            System.out.println(
                    "\u001B[31m" + "\u001B[1m" + "\nErreur: " + "\u001B[0m"
                            + "Sens inconnu !\n\u001B[8CVeuillez réessayer en tapant "
                            + "\u001B[34m" + "h" + "\u001B[0m" + " ou " + "\u001B[34m" + "v" + "\u001B[0m" + ".");
            System.out.print("\nVeuillez saisir le sens du mot (h/v)\n> " + "\u001B[32m");
            try {
                sens = Ut.saisirCaractere();
                System.out.print("\u001B[0m");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        char rep = ' ';
        System.out
                .print("\u001B[31m" + "\n\nM. CapeloDico" + "\u001B[0m" + ", acceptez-vous le mot " + "\u001B[34m"
                        + mot
                        + "\u001B[0m" + " (o/n) ? \n> " + "\u001B[32m");
        try {
            rep = Character.toUpperCase(Ut.saisirCaractere());
            System.out.print("\u001B[0m");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (rep == 'O' && p.placementValide(mot, x, y, sens, this.chevalet)) {
            this.joueMotAux(p, s, nbPointsJet, mot, x, y, sens);
            return true;
        } else
            return false;
    }

    /**
     * pré-requis : cf. joueMot et le placement de mot à partir de la case
     * (numLig, numCol) dans le sens donné par sens est valide
     * action : simule le placement d’un mot de this
     */
    public void joueMotAux(Plateau p, MEE s, int[] nbPointsJet, String mot,
            int numLig, int numCol, char sens) {
        int scoreToAdd = p.nbPointsPlacement(mot, numLig, numCol, sens, nbPointsJet);
        this.score += scoreToAdd;
        p.place(mot, numLig, numCol, sens, this.chevalet);
        this.prendJetons(s, 7 - this.chevalet.getNbTotEx());
        Ut.clearConsole();
        System.out.println(
                "\u001B[35m" + "\u001B[3ESuccès ! " + "\u001B[0m" + "Le mot " + "\u001B[34m" + mot + "\u001B[0m"
                        + " a correctement été placé sur le plateau.\nVous obtenez "
                        + "\u001B[33m" + scoreToAdd + "\u001B[0m" + " points.");
        this.printChevalet();
    }

    /**
     * pré-requis : sac peut contenir des entiers de 0 à 25
     * action : simule l’échange de jetons de ce joueur :
     * - saisie de la suite de lettres du chevalet à échanger
     * en vérifiant que la suite soit correcte
     * - échange de jetons entre le chevalet du joueur et le sac
     * stratégie : appelle les méthodes estCorrectPourEchange et echangeJetonsAux
     */
    public void echangeJetons(MEE sac) {
        System.out.print("\nVeuillez saisir les lettres que vous souhaitez échanger (Format: " + "\u001B[34m" + "ABC"
                + "\u001B[0m" + ")\n> " + "\u001B[32m");
        String selectedTokens = "";
        try {
            selectedTokens = Ut.saisirChaine();
            System.out.print("\u001B[0m");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        while (selectedTokens.length() < 1 || selectedTokens.length() > sac.getNbTotEx()) {
            System.out.println("\u001B[31m" + "\u001B[1m" + "\nErreur: " + "\u001B[0m" + "Il ne reste que "
                    + "\u001B[38;5;208m" + sac.getNbTotEx() + "\u001B[0m"
                    + " jetons dans le sac.\n\u001B[8CVeuillez réessayer en échangeant au maximum " + "\u001B[34m"
                    + sac.getNbTotEx() + "\u001B[0m" + " jetons.");
            System.out.print("\nVeuillez saisir les lettres que vous souhaitez échanger (Format: " + "\u001B[34m"
                    + "ABC" + "\u001B[0m" + ")\n> " + "\u001B[32m");
            try {
                selectedTokens = Ut.saisirChaine();
                System.out.print("\u001B[0m");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (this.estCorrectPourEchange(selectedTokens)) {
            this.echangeJetonsAux(sac, selectedTokens);
            String strExchangedJetons = "";
            for (int i = 0; i < selectedTokens.length(); i++) {
                strExchangedJetons += selectedTokens.charAt(i) + " ";
            }
            Ut.clearConsole();
            System.out.println("\u001B[35m" + "\u001B[3ESuccès ! " + "\u001B[0m" + "Les jetons " + "\u001B[36m"
                    + strExchangedJetons + "\u001B[0m"
                    + "ont été échangés.");
            this.printChevalet();
        } else {
            Ut.clearConsole();
            this.printChevalet();
            System.out.println("\u001B[31m" + "\u001B[1m" + "\n\nErreur: " + "\u001B[0m"
                    + "Les lettres doivent être en majuscules et vous devez posséder les jetons à échanger.\n\u001B[8CVeuillez réessayer en respectant les consignes ci-dessus.");
            this.echangeJetons(sac);
        }
    }

    /**
     * résultat : vrai ssi les caractères de mot correspondent tous à des
     * lettres majuscules et l’ensemble de ces caractères est un
     * sous-ensemble des jetons du chevalet de this
     */
    public boolean estCorrectPourEchange(String mot) {
        boolean isCorrect = true;
        int i = 0;
        while (i < mot.length() && isCorrect) {
            if (!Ut.estUneMajuscule(mot.charAt(i)))
                isCorrect = false;
            else if (this.chevalet.getFrequence(Ut.majToIndex(mot.charAt(i))) == 0)
                isCorrect = false;
            else
                i++;
        }
        return isCorrect;
    }

    /**
     * pré-requis : sac peut contenir des entiers de 0 à 25 et ensJetons
     * est un ensemble de jetons correct pour l’échange
     * action : simule l’échange de jetons de ensJetons avec des
     * jetons du sac tirés aléatoirement.
     */
    public void echangeJetonsAux(MEE sac, String ensJetons) {
        sac.transfereAleat(this.chevalet, ensJetons.length());
        for (int i = 0; i < ensJetons.length(); i++) {
            this.chevalet.transfere(sac, Ut.majToIndex(ensJetons.charAt(i)));
        }
    }
}
