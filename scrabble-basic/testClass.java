public class testClass {
    public static void main (String[] args) {

        String[] names = { "Gabin", "Hugo" };
        Scrabble s = new Scrabble(names);

        s.partie();

        /*
        Plateau p = new Plateau();
        int[] letters = { 100, 100, 100, 1, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100};
        MEE c = new MEE(letters);

        String[] words = {"CAR", "B", "DAD"}; //{"MAISON", "TATA", "NON", "PANNEAU"};
        int[] linesPos = {4, 8, 7}; //{8, 7, 8, 10};
        int[] columnsPos = {4, 8, 8}; //{5, 6, 10, 5};
        char[] sens = {'h', 'h', 'v'}; //{'h', 'v', 'v', 'h'};


        //System.out.println(p);

        for (int i = 0; i < words.length; i++) {
            boolean correct = p.placementValide(words[i], linesPos[i], columnsPos[i], sens[i], c);
            System.out.println("\u001B[33m"+words[i]+"\u001B[0m"+" -> "+correct);
            //if (correct) p.place(words[i], linesPos[i], columnsPos[i], sens[i], c);
        }
        System.out.println(p);
        */
    }
}
