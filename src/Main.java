import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class Main {
    public static File read_file(Scanner scanner){
        System.out.print("Введите имя входного файла и его формат: ");
        String input_filename = scanner.nextLine();
        File input_file = new File(input_filename);
        if (!input_file.exists()){
            throw new IllegalArgumentException("Файл с таким именем не найден.");
        }
        return input_file;
    }
    public static Map<Character, Integer> scan_file(File input_file) throws IOException{
        Map<Character, Integer> frequency_map = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(input_file))){
            int curr;
            while ((curr = reader.read()) != -1){
                char ch = (char) curr;
                if (Character.isLetter(ch) && (ch >= 'A') && (ch <= 'z')) {
                    frequency_map.put(ch, frequency_map.getOrDefault(ch, 0) + 1);
                }
            }
        }
        return frequency_map;
    }
    public static void write_result(Map<Character, Integer> frequency_map,Scanner scanner) throws IOException{
        System.out.print("Введите имя выходного файла и его формат: ");
        String outputFileName = scanner.nextLine();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            for (Map.Entry<Character, Integer> entry : frequency_map.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }
            System.out.println("Результат работы программы записан в файл: \"" + outputFileName + "\"");
        }
    }
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)) {
            File input_file = read_file(scanner);
            Map<Character, Integer> dict = scan_file(input_file);
            write_result(dict,scanner);
        }
        catch (IllegalArgumentException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
        catch (IOException e) {
            System.err.println("Ошибка при работе с файлом: " + e.getMessage());
        }
    }
}