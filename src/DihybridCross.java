import java.util.*;

public class DihybridCross {

    public static int[] phenotypes;
    public static HashMap<String,Integer> genotypes=new HashMap<>();
    public static List<String> eggGametes = new ArrayList<>();
    public static List<String> spermGametes = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String egg = sc.nextLine();
        String sperm = sc.nextLine();

        String[][] eggAlleles = new String[egg.length()/4][2];
        String[][] spermAlleles = new String[sperm.length()/4][2];
        phenotypes = new int[egg.length() / 2 + 1];

        int j = 0;
        for ( int i = 1; i < egg.length(); i+=4 ) {
            eggAlleles[j] = new String[] {String.valueOf(egg.charAt(i) - '0'), String.valueOf(egg.charAt(i + 2) - '0') };
            spermAlleles[j] = (new String[] {String.valueOf(sperm.charAt(i) - '0'), String.valueOf(sperm.charAt(i + 2) - '0') } );
            j++;
        }

        long sum = 1;
        for ( int i = 0; i < egg.length() / 4; i++ ) { //maximum of egg.length()/4 is 31(thus 31 genes are the limitation).
            sum *= 4;
        }
        System.out.println("Number of possible combinations - " + "\n" + sum);

        GametesCombination(eggAlleles, eggGametes, 0, "");
        GametesCombination(spermAlleles, spermGametes, 0, "");

        Crossing();

        System.out.println("Phenotype Ratio - ");
        for ( int i = 0; i < phenotypes.length; i++ ) {
            System.out.println(i + ": " + phenotypes[i]);
        }

        System.out.println("Genotypic Ratio - ");
        for ( Map.Entry<String, Integer> entry : genotypes.entrySet() ) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

    }

    public static void GametesCombination(String[][] Alleles, List<String> Gametes, int index, String combination) {
        if ( index == Alleles.length ) {
            Gametes.add(combination);
            return;
        }

        GametesCombination(Alleles, Gametes,index+1,  combination + Alleles[index][0]);
        GametesCombination(Alleles, Gametes,index+1, combination + Alleles[index][1]);

    }

    public static void Crossing() {
        for ( int i = 0; i < eggGametes.size(); i++ ) {
            for ( int j = 0; j < spermGametes.size(); j++ ) {

                int length = eggGametes.get(0).length();
                String combination = "";
                //eggGametes.get(i) + spermGametes.get(j);
                for ( int I = 0; I < length; I++ ) {
                    if ( eggGametes.get(i).charAt(I) - '0' < spermGametes.get(j).charAt(I) - '0' ) {
                        combination +=
                                String.valueOf((spermGametes.get(j).charAt(I) - '0')) +
                                        String.valueOf((eggGametes.get(j).charAt(I) - '0'));
                    }
                    else {
                        combination +=
                                String.valueOf((eggGametes.get(i).charAt(I) - '0')) +
                                        String.valueOf((spermGametes.get(j).charAt(I) - '0'));
                    }
                }


                int a = 0;
                for ( int I = 0; I < combination.length(); I++) {
                    a += combination.charAt(I) - '0';
                }

                phenotypes[a]++;

                if ( !genotypes.containsKey(combination) ) {
                    genotypes.put(combination, 1);
                }
                else {
                    genotypes.merge(combination, 1, (oldValue, newValue) -> oldValue + newValue);

                    System.out.print("");
                }

            }
        }
    }

}
