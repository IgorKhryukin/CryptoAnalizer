package ru.javarush.khryukin.cryptoanalizer.commands;

import ru.javarush.khryukin.cryptoanalizer.constants.Constants;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;


public class BruteForce {
    public static final Path BRUTEFORCE_FILE_PATH = Path.of("text\\decode.txt");

    public static void bruteForceDecoder(Path path){
        boolean flag = false;
        for (int i = 1; i < Constants.ALPHABET.length; i++) {
            Decoder.decodeFunction(path, i);
            try(BufferedReader bufferedReader = new BufferedReader(new FileReader(String.valueOf(BRUTEFORCE_FILE_PATH)))) {
                while(bufferedReader.ready()) {
                    String str = bufferedReader.readLine();
                    if (str.contains(" и ") || str.contains(" в ") || str.contains(" с ") || str.contains(" о ") || str.contains(" на ") || str.contains(". ") || str.contains("! ") || str.contains(", ")) {
                        if (str.contains(". ") || str.contains("! ") || str.contains(", ")) {
                            if(str.contains(" и ") || str.contains(" в ") || str.contains(" с ") || str.contains(" о ") || str.contains(" на ")) {
                                if(str.contains(" он ") || str.contains(" я ") || str.contains(" мы ") || str.contains("Вы ") || str.contains(" она ") || str.contains(" вы ") || str.contains(" они ")) {
                                    if(str.contains(", что ") || str.contains(", но ")) {
                                        System.out.println("Файл раскодирован");
                                        flag = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                if(flag){
                    break;
                }
            } catch (IOException e) {
                System.out.println("Файл не найден");
            }
        }
    }
}
