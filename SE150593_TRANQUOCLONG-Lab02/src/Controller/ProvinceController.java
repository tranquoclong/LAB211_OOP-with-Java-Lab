package Controller;

import Model.Province;
import Model.Vaccine;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class ProvinceController extends ArrayList<Province> implements FileConnection<Province>{
    private final String FILENAME = "province.txt";
    private Scanner scanner = null;
    private FileWriter fileWriter = null;
    private BufferedWriter bufferedWriter = null;
    private PrintWriter printWriter = null;

    public ProvinceController() {
        getAll();
    }

    public Province get(String Province) {
        for (Province province : this) if (province.getName().equals(Province)) return province;
        return null;
    }

    public void getAll() {
        boolean readMode = true;
        open(readMode);
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            Province province = create(data);
            this.add(province);
        }
        close();
    }

    @Override
    public void open(boolean readMode) {
        if (readMode) {
            try {
                scanner = new Scanner(Paths.get(FILENAME), "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                fileWriter = new FileWriter(FILENAME, true);
                bufferedWriter = new BufferedWriter(fileWriter);
                printWriter = new PrintWriter(bufferedWriter);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Province create(String data) {
        String split[] = data.split("\\;");
        return new Province(split[0], split[1]);
    }

    @Override
    public void close() {
        if (scanner != null) scanner.close();
        if (printWriter != null && bufferedWriter != null && fileWriter != null)
            try {
                printWriter.close();
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
