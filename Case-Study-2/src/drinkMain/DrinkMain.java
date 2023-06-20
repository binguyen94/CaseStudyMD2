package drinkMain;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DrinkMain {
    private final String NAME_DRINK1 = "coffee";
    private final String NAME_DRINK2 = "milk-tea";
    private final String NAME_DRINK3 = "best-seller";

    public void readToFile(String filePath, List<String> list) throws IOException {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(filePath);
            bufferedReader = new BufferedReader(fileReader);
            String str = "";
            while ((str = bufferedReader.readLine()) != null) {
                list.add(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            fileReader.close();
            bufferedReader.close();
        }
    }
    public void writeToFile(String path, List<String> list) throws IOException {
        try (
                Writer fos = new OutputStreamWriter(
                        new FileOutputStream(path), "UTF-8");
                BufferedWriter oos = new BufferedWriter(fos)
        ) {
            for (String s : list) {
                oos.write(s);
                oos.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
