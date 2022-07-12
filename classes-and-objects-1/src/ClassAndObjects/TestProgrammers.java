package ClassAndObjects;

public class TestProgrammers {
    public static void main(String[] args) {

        System.out.println("--- Creating Programmers ---");

        Programmer programmer1 , programmer2;

        programmer1 = new Programmer("Davide",28,false);
        programmer2 = new Programmer("Ray",29,true);

        programmer1.printDetails();

        System.out.println("-----------------");

        programmer2.printDetails();

        System.out.println("-----------------");

        System.out.println("--Important Question!--");

        programmer2.isWearsGlasses();

        System.out.println("--Break Time!--");

        programmer1.drinksCoffee();
    }

}
