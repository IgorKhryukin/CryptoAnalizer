package ru.javarush.khryukin.cryptoanalizer.commands;

import ru.javarush.khryukin.cryptoanalizer.constants.Constants;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class Decoder {
    public static final Path DECODE_FILE_PATH = Path.of("text\\decode.txt");

    public static void decodeFunction(Path filePath, int key){

        try(FileReader fileReader = new FileReader(String.valueOf(filePath));
            FileWriter fileWriter = new FileWriter(String.valueOf(DECODE_FILE_PATH))){
            while (fileReader.ready()){
                char temp = (char) fileReader.read();
                String s = String.valueOf(temp);
                if(s.equals("\n")){
                    fileWriter.write("\n");
                    continue;
                }
                if(!Constants.STRING_ALPHABET.contains(s)){
                    fileWriter.write(temp);
                    continue;
                }
                for (int i = 0; i < Constants.ALPHABET.length; i++) {
                    if(temp == Constants.ALPHABET[i]){
                        if(i - key < 0){
                            fileWriter.write(Constants.ALPHABET[i - key + Constants.ALPHABET.length]);
                        } else {
                            fileWriter.write(Constants.ALPHABET[i - key]);
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
