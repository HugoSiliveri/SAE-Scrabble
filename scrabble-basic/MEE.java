public class MEE {

  private int[] tabFreq;
  private int nbTotEx;

  /**
   * pré-requis : max >= 0
   * action : crée un multi-ensemble vide dont les éléments seront
   * inférieurs à max
   */
  public MEE(int max) {
    this.tabFreq = new int[max];
    this.nbTotEx = 0;
  }

  /**
   * pré-requis : les éléments de tab sont positifs ou nuls
   * action : crée un multi-ensemble dont le tableau de fréquences est
   * . une copie de tab
   */
  public MEE(int[] tab) {
    this.tabFreq = tab.clone();
    this.nbTotEx = 0;
    for (int i : tab) {
      this.nbTotEx += i;
    }
  }

  /**
   * constructeur par copie
   */
  public MEE(MEE e) {
    this.tabFreq = e.tabFreq.clone();
    this.nbTotEx = e.nbTotEx;
  }

  public String toString() {
    String strMEE = "\u001B[4CChevalet\n┏━━━━━━━━━━━━━━━┓\n┃"+"\u001B[36m"+"\u001B[1m";
    char[] letters = new char[this.nbTotEx];
    int k = 0;
    for (int i = 0; i < this.tabFreq.length; i++) {
      for (int j = 0; j < this.tabFreq[i]; j++) {
        letters[k] = Ut.indexToMaj(i);
        k++;
      }
    }
    k = 0;
    for (int i = 0; i < 15; i++) {
      if (i % 2 == 0 || k > letters.length-1) strMEE += " ";
      else {
        strMEE += letters[k]; 
        k++;
      }
    }
    strMEE += "\u001B[0m"+"┃\n┗━━━━━━━━━━━━━━━┛";
    return strMEE;
  }

  public int getFrequence(int i) {
    return this.tabFreq[i];
  }

  public int getNbTotEx() {
    return nbTotEx;
  }

  /**
   * résultat : vrai ssi cet ensemble est vide
   */
  public boolean estVide() {
    boolean isEmpty = true;
    int i = 0;
    while (isEmpty && i < this.tabFreq.length) {
      if (this.tabFreq[i] > 0)
        isEmpty = false;
      else
        i++;
    }
    return isEmpty;
  }

  /**
   * pré-requis : 0 <= i < tabFreq.length
   * action : ajoute un exemplaire de i à this
   */
  public void ajoute(int i) {
    this.tabFreq[i]++;
    this.nbTotEx++;
  }

  /**
   * pré-requis : 0 <= i < tabFreq.length
   * action/résultat : retire un exemplaire de i de this s’il en existe,
   * et retourne vrai ssi cette action a pu être effectuée
   */
  public boolean retire(int i) {
    if (this.tabFreq[i] == 0)
      return false;
    else {
      this.tabFreq[i]--;
      this.nbTotEx--;
      return true;
    }
  }

  /**
   * pré-requis : this est non vide
   * action/résultat : retire de this un exemplaire choisi aléatoirement
   * et le retourne
   */
  public int retireAleat() {
    int random = (int) (Math.random() * this.tabFreq.length);
    while (this.tabFreq[random] == 0) {
      random = (int) Math.random() * this.tabFreq.length;
    }
    this.retire(random);
    return random;
  }

  /**
   * pré-requis : 0 <= i < tabFreq.length
   * action/résultat : transfère un exemplaire de i de this vers e s’il
   * en existe, et retourne vrai ssi cette action a pu être effectuée
   */
  public boolean transfere(MEE e, int i) {
    if (this.tabFreq[i] == 0)
      return false;
    else {
      e.ajoute(i);
      return this.retire(i);
    }
  }

  /**
   * pré-requis : k >= 0
   * action : tranfère k exemplaires choisis aléatoirement de this vers e
   * dans la limite du contenu de this
   * résultat : le nombre d’exemplaires effectivement transférés
   */
  public int transfereAleat(MEE e, int k) {
    int transferredElt = 0;
    if (k > this.nbTotEx)
      k = this.nbTotEx;
    for (int i = 0; i < k; i++) {
      int random;
      do {
        random = (int) (Math.random() * this.tabFreq.length);
      } while (this.tabFreq[random] == 0);
      e.ajoute(random);
      this.retire(random);
      transferredElt++;
    }
    return transferredElt;
  }

  /**
   * pré-requis : tabFreq.length <= v.length
   * résultat : retourne la somme des valeurs des exemplaires des
   * éléments de this, la valeur d’un exemplaire d’un élément i
   * de this étant égale à v[i]
   */
  public int sommeValeurs(int[] v) {
    int sum = 0;
    for (int i = 0; i < this.tabFreq.length; i++) {
      sum += this.tabFreq[i] * v[i];
    }
    return sum;
  }
}
