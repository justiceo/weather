

public class Main {

    public static void main(String[] args) {

        //Warmup exercise - downloads and prints the assignment page
        Warmup cold = new Warmup();
        cold.turnUpTheHeat();

        //some pretty printing stuff
        System.out.println("\n\n===============================================================");
        System.out.println("=========================== PART TWO ==========================");
        System.out.println("===============================================================\n\n");

        //The actual assignment - uses the wunderground api to obtain weather info of a region and prints the hourly forecast
        Wunderground weather = new Wunderground();
        weather.weatherInfo();
    }
}
