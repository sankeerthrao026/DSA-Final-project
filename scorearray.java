import java.util.Scanner;

class scorearray {
    int gn,ps;
    score[] a; //this is the array which stores scores of each game
    void scoreops(Scanner sc){
        int scid=1;
       System.out.println("enter game number");
        gn = dsproject.getNextInt(sc);
        System.out.println("enter number of players");
          ps = dsproject.getNextInt(sc);
          while (ps <= 0) {
              System.out.println("Number of players must be positive. Enter again:");
              ps = dsproject.getNextInt(sc);
          }
             a=new score[ps];
         for (int i = 0; i<ps; i++) {
            a[i]=new score();
            System.out.println("enter player id");
             a[i].playerid = dsproject.getNextInt(sc);
            System.out.println("enter score of the player,game "+gn);
             a[i].sco = dsproject.getNextInt(sc);
             a[i].scoreid=scid;
             scid++;
         }
    }
    void selectionSortMax() {
    for (int i = 0; i < a.length - 1; i++) {
        int maxIndex = i;
        for (int j = i + 1; j < a.length; j++) {
            if (a[j].sco > a[maxIndex].sco) {
                maxIndex = j;
            }
        }
        score temp = a[i];
        a[i] = a[maxIndex];
        a[maxIndex] = temp;
    }
}
    void lsplayerid(int searchId) {
        boolean found = false;

        for (int i = 0; i < ps; i++) {
            if (a[i].playerid == searchId) {
                System.out.println("RECORD FOUND IN GAME " + gn);
                System.out.println("PLAYER ID: " + a[i].playerid +
                                   " SCORE: " + a[i].sco +
                                   " SCORE ID: " + a[i].scoreid);
                found = true;
            }
        }

        if (!found) {
            System.out.println("PLAYER ID NOT FOUND IN GAME " + gn);
        }
    }
}
