import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static ArrayList<Integer> x_cord = new ArrayList<>();
    static ArrayList<Integer> y_cord = new ArrayList<>();
    static int konnyu = 8;
    static int kozepes = 20;
    static int nehez = 50;

    static int konnyu_tips = 12;
    static int kozepes_tips = 20;
    static int nehez_tips = 35;

    static int secretCord_x;
    static int secretCord_y;

    static int selectedHarness = 1;

    public static void main(String[] args) {

        selectHardness();

        if (selectedHarness == 1){
            generateMap(konnyu);
            System.out.println(secretCord_x +"--- " + secretCord_y);
            System.out.println("Könnyű pálya kiválasztva ("+konnyu+"x"+konnyu+")");
            tipp(konnyu, konnyu_tips);
        }
        else if (selectedHarness == 2){
            generateMap(kozepes);
            System.out.println("Közepes pálya kiválasztva ("+kozepes+"x"+kozepes+")");
            tipp(kozepes, kozepes_tips);
        }
        else if (selectedHarness == 3){
            generateMap(nehez);
            System.out.println("Nehéz pálya kiválasztva ("+nehez+"x"+nehez+")");
            tipp(nehez, nehez_tips);
        }
        else {
            System.out.println("Valami nem jó");
            System.exit(1);
        }

    }

    public static void selectHardness(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Válassz nehézséget: 1-Könnyű, 2-Közepes, 3-Nehéz\n> ");
        selectedHarness = scanner.nextInt();
    }

    public static void tipp(int maxSize, int maxTips){
        Scanner scanner = new Scanner(System.in);
        for (int i = 1; i <= maxTips; i++) {
            System.out.print(i+"/"+maxTips+". tipp - Add meg az x koordinátát ("+1+"-"+maxSize+")\n> ");
            int cord_x = scanner.nextInt();
            System.out.print("Add meg az y koordinátát ("+1+"-"+maxSize+")\n> ");
            int cord_y = scanner.nextInt();

            calcTavolsag(cord_x, cord_y, i, maxSize);

        }
        System.out.println("Vesztettél!!!");
        System.out.println("Az összes tipped felhasználtad ("+maxTips+"/"+maxTips+")");
        System.out.println("A kincs koordinátája: ");
        System.out.println("     X --> " + secretCord_x);
        System.out.println("     Y --> "+ secretCord_y);
    }

    public static void calcTavolsag(int tipp_x, int tipp_y, int tips, int maxTips){
        if (tipp_x > maxTips || tipp_x < 1 || tipp_y > maxTips || tipp_y < 1){
            System.out.println("Tanulj meg tippelni!!! ezért levonok egy tippet");
            return;
        }

        if (tipp_x == secretCord_x && tipp_y == secretCord_y){
            System.out.println("Nyertél!!!");
            System.out.println("Felhasznált tippjeid: " + tips + "/" + maxTips);
            System.exit(1);
        }
        else if (tipp_x == secretCord_x || tipp_y == secretCord_y){
            System.out.println("Nagyon meleg! Próbálj újra");
        }
        else if(tipp_x-1 == secretCord_x || tipp_x+1 == secretCord_x && tipp_y-1 == secretCord_x || tipp_y+1 == secretCord_y){
            System.out.println("Meleg! Próbálj újra");
        }
        else if(tipp_x-2 == secretCord_x || tipp_x+2 == secretCord_x && tipp_y-2 == secretCord_x || tipp_y+2 == secretCord_y){
            System.out.println("Langyos! Próbálj újra");
        }
        else if(tipp_x-3 == secretCord_x || tipp_x+3 == secretCord_x && tipp_y-3 == secretCord_x || tipp_y+3 == secretCord_y){
            System.out.println("Hideg! Próbálj újra");
        }
        else if(tipp_x-4 <= secretCord_x || tipp_x+4 >= secretCord_x && tipp_y-4 <= secretCord_x || tipp_y+4 >= secretCord_y){
            System.out.println("Jéghideg! Próbálj újra");
        }
    }

    public static void generateMap(int hardness){
        Random random = new Random();

        for (int i = 0; i < hardness; i++) {
            x_cord.add(i);
            y_cord.add(i);
        }
        secretCord_x = random.nextInt(hardness);
        secretCord_y = random.nextInt(hardness);
    }
}