package ru.javarush.khryukin.cryptoanalizer;

import ru.javarush.khryukin.cryptoanalizer.commands.BruteForce;
import ru.javarush.khryukin.cryptoanalizer.commands.Decoder;
import ru.javarush.khryukin.cryptoanalizer.commands.Encoder;
import ru.javarush.khryukin.cryptoanalizer.constants.Constants;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        System.out.println("Данная программа работает в 3 режимах:");
        System.out.println("1 - кодирование исходного файла шифром Цезаря");
        System.out.println("2 - раскодирование исходного файла шифром Цезаря");
        System.out.println("3 - раскодирование исходного файла методом Brute Force");
        System.out.println("Выберите режим работы команды:");
        Scanner scanner = new Scanner(System.in);
        int num;
        while (true) {
            num = scanner.nextInt();
            if(num > 0 && num < 4){
                break;
            } else {
                System.out.println("Не верно выбран режим работы, повторите ввод:");
            }
        }
        System.out.println("Разместите исходный файл в папку text, которая находится в папке проекта.");
        System.out.println("Введите имя файла для работы. Например - test.txt");

        String fileName = "";
        int key = 0;
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))){
            fileName = bufferedReader.readLine();
            if(num == 1){
                System.out.println("Введите ключ для шифрования:");
                key = Integer.parseInt(bufferedReader.readLine());
            } else if(num == 2){
                System.out.println("Введите ключ для расшифрования:");
                key = Integer.parseInt(bufferedReader.readLine());
            }
        } catch (IOException e){
            e.printStackTrace();
        }



        if(num == 1) {
            Encoder.encodeFunction(Path.of("text\\" + fileName), key);
            System.out.println("Зашифрованный файл можно найти по пути: " + Encoder.ENCODE_FILE_PATH.toAbsolutePath());
        } else if(num == 2){
            Decoder.decodeFunction(Path.of("text\\" + fileName), key);
            System.out.println("Расшифрованный файл можно найти по пути: " + Decoder.DECODE_FILE_PATH.toAbsolutePath());
        } else {
            BruteForce.bruteForceDecoder(Path.of("text\\" + fileName));
            System.out.println("Расшифрованный файл можно найти по пути: " + BruteForce.BRUTEFORCE_FILE_PATH.toAbsolutePath());
        }

    }
}
