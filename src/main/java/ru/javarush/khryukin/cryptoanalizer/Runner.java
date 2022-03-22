package ru.javarush.khryukin.cryptoanalizer;

import ru.javarush.khryukin.cryptoanalizer.commands.BruteForce;
import ru.javarush.khryukin.cryptoanalizer.commands.Decoder;
import ru.javarush.khryukin.cryptoanalizer.commands.Encoder;
import ru.javarush.khryukin.cryptoanalizer.constants.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        int workingMode;
        String fileName = "";
        int key = 0;

        System.out.println("Данная программа работает в 3 режимах:");
        System.out.println("1 - Шифрование файла шифром Цезаря");
        System.out.println("2 - Расшифровывание файла шифром Цезаря");
        System.out.println("3 - Расшифровывание файла методом Brute Force");
        System.out.println("Выберите режим работы программы:");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            workingMode = scanner.nextInt();
            if(workingMode > 0 && workingMode < 4){
                break;
            } else {
                System.out.println("Не верно выбран режим работы, повторите ввод:");
            }
        }
        System.out.println("Разместите исходный файл в папку text, которая находится в папке проекта.");
        System.out.println("Введите имя файла для работы. Например - test.txt");

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))){
            while(true) {
                fileName = bufferedReader.readLine();
                if(!Files.exists(Path.of("text\\" + fileName))){
                    System.out.println("Файл с таким названием отсутсвует в папке text. " +
                            "Проверьте наличие файла и корректность имени и повторите ввод.");
                } else {
                    break;
                }
            }
            if(workingMode == 1){
                System.out.println("Введите ключ для шифрования:");
                while (true) {
                    key = Integer.parseInt(bufferedReader.readLine());
                    if(key < 0){
                        System.out.println("Ключ не может быть отрицательным, повторите ввод:");
                    } else {
                        break;
                    }
                }
            } else if(workingMode == 2){
                System.out.println("Введите ключ для расшифрования:");
                while (true) {
                    key = Integer.parseInt(bufferedReader.readLine());
                    if(key < 0){
                        System.out.println("Ключ не может быть отрицательным, повторите ввод:");
                    } else {
                        break;
                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }



        if(workingMode == 1) {
            Encoder.encodeFunction(Path.of("text\\" + fileName), key);
            System.out.println("Зашифрованный файл можно найти по пути: " + Encoder.ENCODE_FILE_PATH.toAbsolutePath());
        } else if(workingMode == 2){
            Decoder.decodeFunction(Path.of("text\\" + fileName), key);
            System.out.println("Расшифрованный файл можно найти по пути: " + Decoder.DECODE_FILE_PATH.toAbsolutePath());
        } else {
            BruteForce.bruteForceDecoder(Path.of("text\\" + fileName));
            System.out.println("Расшифрованный файл можно найти по пути: " + BruteForce.BRUTEFORCE_FILE_PATH.toAbsolutePath());
        }

    }
}
