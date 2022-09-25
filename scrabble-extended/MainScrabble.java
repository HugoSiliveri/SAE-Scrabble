public class MainScrabble {
        public static void main(String[] args) {
                System.out.println(Utils.PURPLE + "Scrabble" + Utils.RESET + " - " + Utils.CYAN + "Version avec Extensions"
                                + Utils.RESET + " - " + Utils.YELLOW + "By Hugo S. & Gabin B." + Utils.RESET);
                int nbJoueurs;
                do {
                        System.out.print("\n\nCombien de joueurs participent (2-4) ?\n> " + Utils.GREEN);
                        nbJoueurs = Utils.inputInt();
                        System.out.print(Utils.RESET);
                } while (nbJoueurs > 4 || nbJoueurs < 2);

                String[] names = new String[nbJoueurs];
                for (int i = 0; i < nbJoueurs; i++) {
                        System.out.print("\n\nEntrez le nom du joueur n°" + (i + 1) + ":\n> " + Utils.GREEN);
                        String name = Utils.inputString();
                        System.out.print(Utils.RESET);
                        names[i] = name;
                }

                Scrabble scrabble = new Scrabble(names);
                scrabble.partie();
        }
}
