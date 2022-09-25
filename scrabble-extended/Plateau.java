
public class Plateau {

    private Case[][] g = new Case[15][15];

    public Plateau() {
        int[][] plateau = {
                { 5, 1, 1, 2, 1, 1, 1, 5, 1, 1, 1, 2, 1, 1, 5 },
                { 1, 4, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 4, 1 },
                { 1, 1, 4, 1, 1, 1, 2, 1, 2, 1, 1, 1, 4, 1, 1 },
                { 2, 1, 1, 4, 1, 1, 1, 2, 1, 1, 1, 4, 1, 1, 2 },
                { 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1 },
                { 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1 },
                { 1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1 },
                { 5, 1, 1, 2, 1, 1, 1, 4, 1, 1, 1, 2, 1, 1, 5 },
                { 1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1 },
                { 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 3, 1 },
                { 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1 },
                { 2, 1, 1, 4, 1, 1, 1, 2, 1, 1, 1, 4, 1, 1, 2 },
                { 1, 1, 4, 1, 1, 1, 2, 1, 2, 1, 1, 1, 4, 1, 1 },
                { 1, 4, 1, 1, 1, 3, 1, 1, 1, 3, 1, 1, 1, 4, 1 },
                { 5, 1, 1, 2, 1, 1, 1, 5, 1, 1, 1, 2, 1, 1, 5 }
        };

        for (int i = 0; i != plateau.length; i++) {
            for (int j = 0; j != plateau[i].length; j++) { // parcours total de la matrice ci-dessus
                this.g[i][j] = new Case(plateau[i][j]);
            }
        }

    }

    public Plateau(Case[][] plateau) {
        this.g = plateau;
    }

    /**
     * résultat : chaîne décrivant ce Plateau
     */
    public String toString() {
        String screen = "";
        final String top = Utils.RESET + "┏━━━┳━━━┳━━━┳━━━┳━━━┳━━━┳━━━┳━━━┳━━━┳━━━┳━━━┳━━━┳━━━┳━━━┳━━━┓";
        final String bottom = Utils.RESET + "┗━━━┻━━━┻━━━┻━━━┻━━━┻━━━┻━━━┻━━━┻━━━┻━━━┻━━━┻━━━┻━━━┻━━━┻━━━┛";
        final String line = Utils.RESET + "   ┣━━━╋━━━╋━━━╋━━━╋━━━╋━━━╋━━━╋━━━╋━━━╋━━━╋━━━╋━━━╋━━━╋━━━╋━━━┫";
        final String colNum = Utils.RESET + "     1   2   3   4   5   6   7   8   9   10  11  12  13  14  15";
        final char[] ligLettre = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N', 'O'};
        screen += colNum;
        screen += "\n   " + top;

        for (int i = 0; i < this.g.length; i++) {
            screen += "\n " + ligLettre[i] + " ┃";
            for (int j = 0; j < this.g[i].length; j++) {
                screen += this.g[i][j].toString();
            }
            if (i < this.g.length - 1)
                screen += "\n" + line;
        }

        screen += "\n   " + bottom;
        screen += "\nLégende des couleurs: \u001B[1m\u001B[38;5;195mLettre x2\u001B[0m - \u001B[1m\u001B[38;5;19mLettre x3\u001B[0m - \u001B[1m\u001B[38;5;206mMot x2\u001B[0m - \u001B[1m\u001B[31mMot x3\u001B[0m";
        return screen;
    }

    /**
     * pré-requis : mot est un mot accepté par CapeloDico,
     * 0 <= numLig <= 14, 0 <= numCol <= 14, sens est un élément
     * de {’h’,’v’} et l’entier maximum prévu pour e est au moins 25
     * résultat : retourne vrai ssi le placement de mot sur this à partir
     * de la case (numLig, numCol) dans le sens donné par sens à l’aide
     * des jetons de e est valide.
     */
    public boolean placementValide(String mot, int numLig, int numCol, char sens, MEE e) {
        boolean estValide = true;
        if (!this.g[7][7].estRecouverte()) {
            if (mot.length() < 2) {
                System.out
                        .print("\u001B[31m" + "\u001B[1m" + "\nErreur: " + "\u001B[0m" + "Le mot doit faire au moins 2 caractères.");
                estValide = false;
            }
            if (!this.centerCrossed(mot, numLig, numCol, sens)) {
                System.out
                        .print("\u001B[31m" + "\u001B[1m" + "\nErreur: " + "\u001B[0m" + "Le mot doit passer sur la case centrale.");
                estValide = false;
            }
            if (!this.hasAllTokens(mot, e)) {
                System.out.print("\u001B[31m" + "\u001B[1m" + "\nErreur: " + "\u001B[0m"
                        + "Vous ne possédez pas tous les jetons requis pour écrire ce mot.");
                estValide = false;
            }
        } else if (this.g[7][7].estRecouverte()) {
            if (this.exceedGrid(mot, numLig, numCol, sens)) {
                System.out
                        .print("\u001B[31m" + "\u001B[1m" + "\nErreur: " + "\u001B[0m" + "La zone de placement dépasse du plateau.");
                estValide = false;
            } else {
                Case[] area = this.wordSpot(mot, numLig, numCol, sens);

                if (!this.freeCasesBeforeAndAfter(mot, numLig, numCol, sens)) {
                    System.out.print("\u001B[31m" + "\u001B[1m" + "\nErreur: " + "\u001B[0m"
                            + "Le mot ne doit être ni précédé, ni suivi d'une lettre.");
                    estValide = false;
                }
                if (!this.oneFreeCase(area)) {
                    System.out.print("\u001B[31m" + "\u001B[1m" + "\nErreur: " + "\u001B[0m"
                            + "La zone de placement doit contenir au moins une case libre.");
                    estValide = false;
                }
                if (!this.oneCoveredCase(area)) {
                    System.out.print("\u001B[31m" + "\u001B[1m" + "\nErreur: " + "\u001B[0m"
                            + "La zone de placement doit contenir au moins une case recouverte.");
                    estValide = false;
                }
                if (!this.matchingGridTokens(mot, area)) {
                    System.out.print("\u001B[31m" + "\u001B[1m" + "\nErreur: " + "\u001B[0m"
                            + "La/Les lettre(s) déjà placée(s) sur le plateau ne correspond(ent) pas avec celle(s) de votre mot.");
                    estValide = false;
                }
                if (!this.hasAllTokens(mot, area, e)) {
                    System.out.print("\u001B[31m" + "\u001B[1m" + "\nErreur: " + "\u001B[0m"
                            + "Vous ne possédez pas tous les jetons requis pour écrire ce mot.");
                    estValide = false;
                }
            }
        }
        return estValide;
    }

    private boolean centerCrossed(String mot, int numLig, int numCol, char sens) {
        boolean startOk = false;
        int i = 0;
        while (i < mot.length() && !startOk) {
            if (sens == 'h' && numLig == 8 && numCol + i == 8)
                startOk = true;
            else if (sens == 'v' && numLig + i == 8 && numCol == 8)
                startOk = true;
            else
                i++;
        }
        return startOk;
    }

    private Case[] wordSpot(String mot, int numLig, int numCol, char sens) {
        Case[] wordPlace = new Case[mot.length()];
        for (int i = 0; i < mot.length(); i++) {
            if (sens == 'h')
                wordPlace[i] = this.g[numLig - 1][numCol + i - 1];
            else
                wordPlace[i] = this.g[numLig + i - 1][numCol - 1];
        }
        return wordPlace;
    }

    private boolean hasAllTokens(String mot, MEE e) {
        MEE copieChevalet = new MEE(e);
        int i = 0;
        boolean ownedToken = true;
        while (i < mot.length() && ownedToken) {
            if (copieChevalet.getFrequence(Utils.majToIndex(mot.charAt(i))) > 0)
                copieChevalet.retire(Utils.majToIndex(mot.charAt(i)));
            else
                ownedToken = false;
            i++;
        }
        return ownedToken;
    }

    private boolean exceedGrid(String mot, int numLig, int numCol, char sens) {
        int i = 0;
        boolean isOkay = true;
        while (i < mot.length() && isOkay) {
            if (sens == 'h' && (numCol + i) > 15)
                isOkay = false;
            else if (sens == 'v' && (numLig + i) >= 15)
                isOkay = false;
            else
                i++;
        }
        return !isOkay;
    }

    private boolean freeCasesBeforeAndAfter(String mot, int numLig, int numCol, char sens) {
        return ((sens == 'h' && ((numCol - 1) == 0) || !this.g[numLig - 1][numCol - 2].estRecouverte())
                || (sens == 'v' && ((numLig - 1) == 0) || !this.g[numLig - 2][numCol - 1].estRecouverte()))
                && ((sens == 'h' && ((numCol + mot.length()) == 16)
                        || !this.g[numLig - 1][numCol + mot.length() - 1].estRecouverte())
                        || (sens == 'v' && ((numLig + mot.length()) == 16)
                                || !this.g[numLig + mot.length() - 1][numCol - 1].estRecouverte()));
    }

    private boolean oneFreeCase(Case[] zonePlacement) {
        int i = 0;
        boolean isCovered = true;
        while (i < zonePlacement.length && isCovered) {
            if (!zonePlacement[i].estRecouverte())
                isCovered = false;
            i++;
        }
        return !isCovered;
    }

    private boolean oneCoveredCase(Case[] zonePlacement) {
        int i = 0;
        boolean isFree = true;
        while (i < zonePlacement.length && isFree) {
            if (zonePlacement[i].estRecouverte())
                isFree = false;
            else
                i++;
        }
        return !isFree;
    }

    private boolean matchingGridTokens(String mot, Case[] zonePlacement) {
        int i = 0;
        boolean sameLetter = true;
        while (i < mot.length() && sameLetter) {
            if (zonePlacement[i].estRecouverte() && zonePlacement[i].getLettre() != mot.charAt(i))
                sameLetter = false;
            else
                i++;
        }
        return sameLetter;
    }

    private boolean hasAllTokens(String mot, Case[] zonePlacement, MEE e) {
        int i = 0;
        MEE copieChevalet = new MEE(e);
        boolean ownedToken = true;
        while (i < mot.length() && copieChevalet.getNbTotEx() > 0 && ownedToken) {
            if (!zonePlacement[i].estRecouverte()) {
                if (copieChevalet.getFrequence(Utils.majToIndex(mot.charAt(i))) > 0)
                    copieChevalet.retire(Utils.majToIndex(mot.charAt(i)));
                else
                    ownedToken = false;
            }
            i++;
        }
        return ownedToken;
    }

    /**
     * pré-requis : le placement de mot sur this à partir de la case
     * (numLig, numCol) dans le sens donné par sens est valide
     * résultat : retourne le nombre de points rapportés par ce placement, le
     * nombre de points de chaque jeton étant donné par le tableau nbPointsJet.
     */
    public int nbPointsPlacement(String mot, int numLig, int numCol,
            char sens, int[] nbPointsJet) {

        int score = 0;
        int[] bonusCase = new int[mot.length()];

        for (int i = 0; i < bonusCase.length; i++) {
            if (sens == 'h')
                bonusCase[i] = this.g[numLig - 1][numCol - 1 + i].getCouleur();
            else
                bonusCase[i] = this.g[numLig - 1 + i][numCol - 1].getCouleur();
        }

        for (int i = 0; i < mot.length(); i++) {
            if (bonusCase[i] < 4)
                score += nbPointsJet[Utils.majToIndex(mot.charAt(i))] * bonusCase[i];
            else
                score += nbPointsJet[Utils.majToIndex(mot.charAt(i))];
        }

        for (int i = 0; i < bonusCase.length; i++) {
            if (bonusCase[i] == 4)
                score *= 2;
            if (bonusCase[i] == 5)
                score *= 3;
        }

        if (mot.length() >= 7) {
            int countFreeCase = 0;
            int i = 0;
            while (i < bonusCase.length && countFreeCase < 7) {
                if (sens == 'h' & !this.g[numLig][numCol + i].estRecouverte())
                    countFreeCase++;
                if (sens == 'v' & !this.g[numLig + i][numCol].estRecouverte())
                    countFreeCase++;
                i++;
            }
            if (countFreeCase == 7)
                score += 50;
        }
        return score;
    }

    /**
     * pré-requis : le placement de mot sur this à partir de la case
     * (numLig, numCol) dans le sens donné par sens à l’aide des
     * jetons de e est valide.
     * action/résultat : effectue ce placement et retourne le
     * nombre de jetons retirés de e.
     */
    public int place(String mot, int numLig, int numCol, char sens, MEE e) {
        int nbJetonRetire = 0;
        int longueur_mot = mot.length();
        char[] lettres = new char[mot.length()];

        for (int chara = 0; chara < longueur_mot; chara++) {
            lettres[chara] += mot.charAt(chara);
        }

        for (int i = 0; i < longueur_mot; i++) {
            if (!this.g[numLig - 1][numCol - 1].estRecouverte()) {
                this.g[numLig - 1][numCol - 1].setLettre(lettres[i]);
                e.retire(Utils.majToIndex(lettres[i]));
                nbJetonRetire++;
            }
            if (sens == 'h') {
                numCol += 1;
            } else {
                numLig += 1;
            }
        }
        return nbJetonRetire;

    }
}