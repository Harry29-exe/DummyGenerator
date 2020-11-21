import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Dummy {
    FileWriter writer;

    /**Dobrze to napisane raczej nie jest ale w zupełności wystarczająco
     * w konstruktorze wpełnij producers, allProducedVaccines, pharmacies,
     * neededVaccines. Plik wyjściowy powinien się pojawić w folderze w tkórym w waszym ide jest src, out etc.
     * A i program raczej sie wywali jak będzie stosunkowo mało szczepionek**/
    public Dummy() throws IOException {
        File newFile = new File("newFile.txt");
        newFile.createNewFile();

        int producers = 1000;
        int allProducedVaccines = 18_000_000;
        int pharmacies = 10000;
        int neededVaccines = 10_000_000;

        writer = new FileWriter(newFile);
        writer.write("# ghfkdsjgbfkadrjehbgfadrg" +"\n");
        List<Integer> producedVaccines = printProducers(producers,allProducedVaccines);
        writer.write("# kldjjgbfdsjgbvkdsjgbfdskhjbhgfd" +"\n");
        printPharmacies(pharmacies, neededVaccines);
        writer.write("# aliuwjgfdklijghfdskjghvbfdsjk" +"\n");
        printContract(producers,pharmacies,producedVaccines);

        writer.close();
    }

    public static void main(String[] args) throws IOException {
        CompanyNames.init();

        Dummy dummy = new Dummy();
    }

    public void printPharmacies(int amount, int allVaccines) throws IOException {
        Random random = new Random();
        for(int i = 0; i< amount-1; i++) {
            int vaccinesNeeded = random.nextInt((allVaccines*2) / (amount-i) );
            vaccinesNeeded = vaccinesNeeded < allVaccines? vaccinesNeeded: allVaccines/2;
            allVaccines -= vaccinesNeeded;

            writer.write(i + " | " + CompanyNames.map.get(random.nextInt(1000)) + " | " + vaccinesNeeded +"\n");
        }
        writer.write(amount-1 + " | " + CompanyNames.map.get(random.nextInt(1000)) + " | " + allVaccines +"\n");
    }

    public List<Integer> printProducers(int amount, int allProducedVaccines) throws IOException {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();

        for(int i = 0; i< amount-1; i++) {
            int vaccinesProduced = random.nextInt((allProducedVaccines * 2) / (amount - i));
            vaccinesProduced = vaccinesProduced < allProducedVaccines ? vaccinesProduced : allProducedVaccines / 2;
            list.add(vaccinesProduced);

            allProducedVaccines -= vaccinesProduced;

            writer.write(i + " | " + CompanyNames.map.get(random.nextInt(1000)) + " | " + vaccinesProduced +"\n");
        }

        list.add(allProducedVaccines);
        writer.write(amount-1 + " | " + CompanyNames.map.get(random.nextInt(1000)) + " | " + allProducedVaccines +"\n");
        return list;
    }

    public void printContract(int producers, int pharmacies, List<Integer> maxVaccines) throws IOException {
        Random random = new Random();
        DecimalFormat decimalFormat = new DecimalFormat("#.##", DecimalFormatSymbols.getInstance(Locale.US));

        for (int pr = 0; pr < producers; pr++) {
            for(int ph = 0; ph< pharmacies; ph++) {
                try {
                    writer.write(pr + " | " + ph + " | " + (random.nextInt(maxVaccines.get(pr) / 2) + maxVaccines.get(pr) / 2) + " | " + decimalFormat.format(random.nextFloat() * 40 + 60) + "\n");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                if(pr == 99 && ph > 600) {
                    int i = 0;
                }
            }
        }
    }
}
