import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class CatalogFilter {
    public static void main(String[] args) {
        Laptops laptop1 = new Laptops("MSI B12VFK-463XRU", 32, 1024, "Windows", "Black");
        Laptops laptop2 = new Laptops("ASUS FA507NV-LP0233", 16, 512, "no_OS", "Grey");
        Laptops laptop3 = new Laptops("GIGABYTE GS MF", 16, 512, "no_OS", "Black");
        Laptops laptop4 = new Laptops("ASUS Vivobook E1504FA-BQ533", 16, 512, "Windows", "Black");
        Laptops laptop5 = new Laptops("AORUS 17 BSF", 16, 1000, "Windows", "Black");
        Laptops laptop6 = new Laptops("HONOR MagicBook 14", 16, 512, "Windows", "Silver");
        Laptops laptop7 = new Laptops("Honor MagicBook 14", 8, 256, "Windows", "Silver");
        Laptops laptop8 = new Laptops("HUAWEI MateBook D16", 16, 512, "no_OS", "Grey");
        Laptops laptop9 = new Laptops("Dell Inspiron 5510", 8, 512, "Windows", "Silver");
        Laptops laptop10 = new Laptops("Dell Vostro 3500", 4, 512, "no_OS", "Black");

        Set<Laptops> LaptopSet = new HashSet<>();
        LaptopSet.add(laptop1);
        LaptopSet.add(laptop2);
        LaptopSet.add(laptop3);
        LaptopSet.add(laptop4);
        LaptopSet.add(laptop5);
        LaptopSet.add(laptop6);
        LaptopSet.add(laptop7);
        LaptopSet.add(laptop8);
        LaptopSet.add(laptop9);
        LaptopSet.add(laptop10);

        Map<String, Object> filters = new HashMap<>();

        try (Scanner filterLaptop = new Scanner(System.in)) {
            System.out.println("Выберите критерии для фильтрации:");
            System.out.println("OS - Windows/no_OS; color - Black/Silver/Grey");
            System.out.println("1 - ОЗУ");
            System.out.println("2 - Объем ЖД");
            System.out.println("3 - Операционная система");
            System.out.println("4 - Цвет");
            System.out.println("0 - Завершить выбор");

            int choice;
            while (true) {
                choice = filterLaptop.nextInt();
                if (choice == 0)
                    break;
                switch (choice) {
                    case 1:
                        System.out.println("Минимальный объем ОЗУ:");
                        filters.put("ram", filterLaptop.nextInt());
                        break;
                    case 2:
                        System.out.println("Минимальный объем ЖД:");
                        filters.put("rom", filterLaptop.nextInt());
                        break;
                    case 3:
                        System.out.println("Операционная система:");
                        filters.put("os", filterLaptop.next());
                        break;
                    case 4:
                        System.out.println("Цвет:");
                        filters.put("color", filterLaptop.next());
                        break;
                    default:
                        System.out.println("Неверный выбор. Попробуйте еще раз.");
                        break;
                }

            }

        }

        Set<Laptops> filteredLaptops = LaptopSet.stream()
                .filter(laptop -> filters.getOrDefault("ram", 0) instanceof Integer
                        && laptop.ram >= (int) filters.getOrDefault("ram", 0))
                .filter(laptop -> filters.getOrDefault("rom", 0) instanceof Integer
                        && laptop.rom >= (int) filters.getOrDefault("rom", 0))
                .filter(laptop -> filters.getOrDefault("os", "").equals("")
                        || laptop.os.equalsIgnoreCase((String) filters.getOrDefault("os", "")))
                .filter(laptop -> filters.getOrDefault("color", "").equals("")
                        || laptop.color.equalsIgnoreCase((String) filters.getOrDefault("color", "")))
                .collect(Collectors.toSet());

        System.out.println("Найденные по запросу ноутбуки: ");
        for (Laptops laptop : filteredLaptops) {
            System.out.println(laptop);
        }

    }

    public static class Laptops {
        private String model;
        private int ram;
        private int rom;
        private String os;
        private String color;

        public Laptops(String model, int ram, int rom, String os, String color) {
            this.model = model;
            this.ram = ram;
            this.rom = rom;
            this.os = os;
            this.color = color;

        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public int getRam() {
            return ram;
        }

        public void setRam(int ram) {
            this.ram = ram;
        }

        public int getRom() {
            return rom;
        }

        public void setRom(int rom) {
            this.rom = rom;
        }

        public String getOs() {
            return os;
        }

        public void setOs(String os) {
            this.os = os;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return "Найдено по запросу: {" +
                    "модель: " + model + "; " +
                    "ОЗУ: " + ram + "; " +
                    "объем ЖД: " + rom + "; " +
                    "операционная система: " + os + "; " +
                    "цвет: " + color +
                    "}";
        }

        // @Override
        // public boolean equals(Object obj) {
        //     if (this == obj)
        //         return true;
        //     if (obj == null || getClass() != obj.getClass())
        //         return false;
        //     Laptops laptops = (Laptops) obj;
        //     return laptops.model.equals(model) &&
        //             laptops.ram == ram &&
        //             laptops.rom == rom &&
        //             laptops.os.equals(os) &&
        //             laptops.color.equals(color);
        // }

        @Override
        public int hashCode() {
            return Objects.hash(model, ram, rom, os, color);
        }
    }
}