public class MainScrabble {
        public static void main(String[] args) {
                System.out.println("\u001B[35m" + "Scrabble" + "\u001B[0m" + " - " + "\u001B[36m" + "Version Basique"
                                + "\u001B[0m" + " - " + "\u001B[33m" + "By Hugo S. & Gabin B." + "\u001B[0m");
                int nbJoueurs = 0;
                do {
                        System.out.print("\n\nCombien de joueurs participent (2-4) ?\n> " + "\u001B[32m");
                        try {
                                nbJoueurs = Ut.saisirEntier();
                                System.out.print("\u001B[0m");
                        } catch (Exception ex) {
                                ex.printStackTrace();
                        }
                } while (nbJoueurs > 4 || nbJoueurs < 2);

                String[] names = new String[nbJoueurs];
                for (int i = 0; i < nbJoueurs; i++) {
                        System.out.print("\n\nEntrez le nom du joueur n°" + (i + 1) + ":\n> " + "\u001B[32m");
                        String name = "";
                        try {
                                name = Ut.saisirChaine();
                                System.out.print("\u001B[0m");
                        } catch (Exception ex) {
                                ex.printStackTrace();
                        }
                        names[i] = name;
                }

                Scrabble scrabble = new Scrabble(names);
                scrabble.partie();
        }
}
