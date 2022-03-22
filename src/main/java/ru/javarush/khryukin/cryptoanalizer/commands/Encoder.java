package ru.javarush.khryukin.cryptoanalizer.commands;

import ru.javarush.khryukin.cryptoanalizer.constants.Constants;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class Encoder {
    public static final Path ENCODE_FILE_PATH = Path.of("text\\encode.txt");

    public static void encodeFunction(Path filePath, int key){
        key = key % Constants.ALPHABET.length;

        try(FileReader fileReader = new FileReader(String.valueOf(filePath));
            FileWriter fileWriter = new FileWriter(String.valueOf(ENCODE_FILE_PATH))){
            while(fileReader.ready()){
                char nextChar = (char) fileReader.read();
                String charToString = String.valueOf(nextChar);
                if(charToString.equals("\n")){
                    fileWriter.write("\n");
                    continue;
                }
                if(!Constants.STRING_ALPHABET.contains(charToString)){
                    fileWriter.write(nextChar);
                    continue;
                }
                for (int i = 0; i < Constants.ALPHABET.length; i++) {
                    if (nextChar == Constants.ALPHABET[i]) {
                        if (i + key >= Constants.ALPHABET.length) {
                            fileWriter.write(Constants.ALPHABET[i + key - Constants.ALPHABET.length]);
                        } else {
                            fileWriter.write(Constants.ALPHABET[i + key]);
                        }
                        break;
                    }
                }
            }
        } catch (IOException e){
            System.out.println("Файл не найден");
        }
    }
}
