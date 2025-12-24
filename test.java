import java.io.PrintStream;
import java.util.Scanner;
public class Test
{
    public static Scanner in = new Scanner(System.in);
    public static PrintStream out = System.out;
    public static void main(String[] args) {
        out.println("ТЕСТ 1: Создание гостиницы");
        Hotel hotel = new Hotel(10);
        hotel.printRooms();

        out.println("\n ТЕСТ 2: Заселение в конкретный номер");
        hotel.checkIn("Иван", 1);
        hotel.checkIn("Пётр", 3);
        hotel.printRooms();

        out.println("\nТЕСТ 3: Заселение в первый свободный");
        hotel.checkInFirstFree("Анна");
        hotel.printRooms();

        out.println("\nТЕСТ 4: Заселение группы");
        String[] group1 = {"X", "Y", "Z"};
        out.println("Размещено: "+hotel.checkInGroup(group1));
        hotel.printRooms();

        out.println("\nТЕСТ 5: Выселение из номера");
        hotel.checkOut(3);
        hotel.printRooms();

        out.println("\nТЕСТ 6: Заселение семьи");
        String[] family1 = {"Папа1", "Папа2", "Ребёнок"};
        out.println("Семья размещена: "+hotel.checkInFamily(family1));
        hotel.printRooms();

        out.println("\nТЕСТ 7: Выселение человека с учётом семьи");
        hotel.checkOutWithFamily("Папа1");
        hotel.printRooms();

        out.println("\n ТЕСТ 8: Заселение с животным");
        hotel.checkInWithAnimal("Олег", 8);
        hotel.checkInWithAnimal("Макс", 7);
        hotel.printRooms();

        out.println("\nТЕСТ 9: Группа с животными");
        String[] animalsGroup = {"Собачник", "Кошатник", "Лера"};
        out.println("Размещено с животными: "+hotel.checkInGroupWithAnimals(animalsGroup));
        hotel.printRooms();

        out.println("\nТЕСТ 10: Семья с животными");
        String[] familyAnimals = {"Станислав", "Катя","СОБАКАА"};
        boolean famAnimalsPlaced = hotel.checkInFamilyWithAnimals(familyAnimals);
        out.println("Семья с животными размещена: "+famAnimalsPlaced);
        hotel.printRooms();

        out.println("\nТЕСТ 11: Попытка заселения при отсутствии мест");
        String[] bigFamily = {"Дмитрий", "Иаков", "Йосиф", "Магомед", "Степан"};
        out.println("Большая семья размещена: "+hotel.checkInFamily(bigFamily));
        hotel.printRooms();

    }
}
