package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InputUtilities {

    public static final BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

    public static String readLine() {
        try {
            return inputReader.readLine();
        } catch (IOException e) {
            e.printStackTrace(); // handle Exception
            return null;
        }
    }

    public static int readInt() {
        try {
            return Integer.parseInt(inputReader.readLine());
        } catch (NumberFormatException | IOException e) {
            System.err.println("Input tidak valid. masukkan Integer");
            return -1;// readInt(); // rekursif
        }
    }

    public static void closeReader() {
        try {
            inputReader.close();
        } catch (IOException e) {
            e.printStackTrace(); // handle Exception
        }
    }

    public static void cls() {
        // wont work on IDE
        try {
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void pressEnter() {
        System.out.println();
        System.out.print("Press ENTER to continue");
        try {
            InputUtilities.inputReader.readLine();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static Date getDateFromInput() {
        try {
            String inputDate = InputUtilities.inputReader.readLine();
            // Replace "/" with "-" in the input string
            inputDate = inputDate.replaceAll("/", "-");

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

            // Parse the formatted string to a Date object
            return dateFormat.parse(inputDate);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return null;
        } catch (ParseException e) {
            return null;
        }
    }

    public static LocalDateTime getDateTimeFromInput() {
        try {
            String inputDate = InputUtilities.inputReader.readLine();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

            // String inputString = "20-11-2024 13:30:00";
            return LocalDateTime.parse(inputDate, formatter);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;

        }
    }

    public static AppEnums.RentalInterval getRentalIntervalFromInput() {
        try {
            System.out.print("Masukkan Input [hour, day, week, month] : ");
            String inputStatus = InputUtilities.inputReader.readLine();
            return switch (inputStatus.toLowerCase()) {
                case "hour" -> AppEnums.RentalInterval.Hour;
                case "day" -> AppEnums.RentalInterval.Day;
                case "week" -> AppEnums.RentalInterval.Week;
                case "month" -> AppEnums.RentalInterval.Month;
                default -> null;
            };
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return null;
        }

    }
}
