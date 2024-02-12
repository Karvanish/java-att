import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserDataInput {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные: Фамилия Имя Отчество дата_рождения номер_телефона пол");
        String input = scanner.nextLine();
        
        try {
            validateAndWriteData(input);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void validateAndWriteData(String inputData) throws Exception {
        String[] parts = inputData.split(" ");
        
        if (parts.length != 6) {
            throw new IllegalArgumentException("Неверное количество данных. Ожидается 6 значений.");
        }
        
        String lastName = parts[0];
        String firstName = parts[1];
        String middleName = parts[2];
        String birthDate = parts[3];
        String phoneNumber = parts[4];
        String gender = parts[5];
        
        // Валидация даты рождения, номера телефона и пола
        if (!birthDate.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
            throw new IllegalArgumentException("Формат даты рождения неверен. Ожидается dd.mm.yyyy");
        }
        
        if (!phoneNumber.matches("\\d+")) {
            throw new IllegalArgumentException("Номер телефона должен содержать только цифры.");
        }
        
        if (!gender.matches("[fm]")) {
            throw new IllegalArgumentException("Пол должен быть указан как 'f' или 'm'.");
        }
        
        // Запись в файл
        writeToFile(lastName, String.join(" ", lastName, firstName, middleName, birthDate, phoneNumber, gender));
    }

    private static void writeToFile(String fileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName + ".txt", true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
