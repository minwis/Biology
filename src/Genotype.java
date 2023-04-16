public class Genotype {

    private static String pattern = "";
    private static int frequency = 1;

    Genotype(String s) {
        pattern = s;
    }

    void addFrequency() {
        frequency++;
    }
}
