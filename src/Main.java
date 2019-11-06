import java.util.Random;

public class Main {
    public static int bossHealth = 1200;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int[] heroesHealths = {250, 250, 250, 250};
    public static int[] heroesDamages = {20, 20, 20, 10};
    public static String[] heroesAttackTypes = {"Physical", "Magical", "Kinetic", "Medical"};

    public static void changeBossDefence() {
        Random r = new Random();
        int randomIndex = r.nextInt(heroesAttackTypes.length);
        bossDefenceType = heroesAttackTypes[randomIndex];
    }

    public static void round() {

        changeBossDefence();
        heroesHit();
        if (bossHealth > 0) {
            bossHit();
            manTreat();
        }
        printStatistics();
    }

    public static void printStatistics() {
        System.out.println("_______________");
        System.out.println("Boss health: " + bossHealth);
//        System.out.println("Warrior health: " + heroesHealths[0]);
//        System.out.println("Magic health: " + heroesHealths[1]);
//        System.out.println("Kinetic health: " + heroesHealths[2]);
        for (int i = 0; i < heroesAttackTypes.length; i++) {
            if (heroesHealths[i] < 0) {
                heroesHealths[i] = 0;
            }
            System.out.println(heroesAttackTypes[i] + " health: " + heroesHealths[i]);
        }
        System.out.println("_______________");
    }

    public static void manTreat() {
        for (int i = 0; i < heroesHealths.length; i++) {
            if (heroesHealths[3] < 0) {
                heroesHealths[3] = 0;
            }
            if (heroesHealths[3] > 0) {
                heroesHealths[i] = heroesHealths[i] + heroesDamages[3];
                if (heroesHealths[i] == heroesHealths[3]) {
                    heroesHealths[3] = heroesHealths[3] - heroesDamages[3];
                }
            } else if (heroesHealths[i] < 0) {
                heroesHealths[i] = heroesHealths[i];
            } else {
                heroesHealths[i] = heroesHealths[i];
            }

        }

        // if( heroesHealths>0 ){}
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamages.length; i++) {
            if (!heroesAttackTypes[i].equals(bossDefenceType)) { // heroesAttackTypes[i] != bossDefenceType
                Random r = new Random();
                int coefficient = r.nextInt(3) + 2; //0,1,2,3,4
                bossHealth = bossHealth - heroesDamages[i] * coefficient;
                System.out.println(heroesAttackTypes[i] + " critical attack "
                        + heroesDamages[i] * coefficient);
            } else {
                bossHealth = bossHealth - heroesDamages[i];
            }
        }
        /*for (int tempDamage : heroesDamages) {
            bossHealth = bossHealth - tempDamage;
        }*/
    }

    public static void bossHit() {
        for (int i = 0; i < heroesHealths.length; i++) {
            heroesHealths[i] = heroesHealths[i] - bossDamage;
        }
    }

    public static void main(String[] args) {
        printStatistics();
        while (!isFinished()) {
            round();
        }
    }

    public static boolean isFinished() {
        /*if (bossHealth <= 0 && heroesHealths[0] <= 0
                && heroesHealths[1] <= 0 && heroesHealths[2] <= 0) {
            System.out.println("Draw!!!");
            return true;
        }*/
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        if (heroesHealths[0] <= 0 && heroesHealths[1] <= 0 && heroesHealths[2] <= 0 && heroesHealths[3] <= 0) {
            System.out.println("Boss won!!!");

            return true;
        }
        return false;
    }

}
