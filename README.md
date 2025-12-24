## Отчет по лабораторной работе № 3

#### № группы: `ПМ-2501`

#### Выполнил: `Ушаков Игнат`

#### Вариант: `17`

### Cодержание:

- [Постановка задачи](#1-постановка-задачи)
- [Алгоритм](#3-алгоритм)
- [Программа](#4-программа)

 ### 1. Постановка задачи

> Разработать программу для управления номерами в гостинице с учётом правил размещения, включая возможность проживания с животными в чётных номерах. Реализовать функции добавления и выселения гостей или семей, а также распределения номеров в соответствии с заданными условиями.

Необходимо создать класс 'Hotel', именно там будут реализованы все соотвествующие функции:Создание гостиницы, Вывод списка номеров,Поселение человека в заданный номер, Поселение человека в первый свободный номер,Поселение группы людей, Поселение группы людей начиная с определённого номера, Выселение человека из номера,Поселение семьи в соседние номера, Выселение человека с учётом семьи,Поселение человека с животным в указанный номер,Поселение группы людей с животными,Поселение семьи с животными.

### 3. Алгоритм
#### Краткое описание методов:
  1. Конструктор Hotel(int n)
    Создаёт гостиницу с n номерами.
    Все номера изначально свободны, все идентификаторы семей равны нулю.
  2. printRooms()
    Выводит список свободных номеров и список занятых номеров с именами жильцов
  3. checkIn(String name, int room)
    Заселяет человека с указанным именем в заданный номер, если номер существует и свободен.
  4. checkInFirstFree(String name)
    Заселяет человека в первый найденный свободный номер.
  5. checkInGroup(String[] names)
    Размещает группу людей в любые свободные номера.Возвращает количество успешно размещённых людей.
  6. checkInGroupFrom(String[] names, int startRoom)
    Размещает группу людей в свободные номера, начиная с указанного номера.Возвращает количество размещённых людей.
  7. checkOut(int room)
    Выселяет человека из указанного номера и освобождает его.
  8. checkInFamily(String[] names)
    Размещает семью в соседние свободные номера.Всем членам семьи присваивается общий идентификатор семьи.Возвращает true, если размещение удалось, иначе false.
  9. checkOutWithFamily(String name)
    Выселяет человека из указанного номера.Если человек является частью семьи, выселяются все члены этой семьи.
  10. checkInWithAnimal(String name, int room)
    Заселяет человека с животным в указанный номер, если номер чётный и свободен.
  11. checkInGroupWithAnimals(String[] names)
    Размещает группу людей с животными в свободные чётные номера.Возвращает количество успешно размещённых людей.
  12. checkInFamilyWithAnimals(String[] names)
    Размещает семью с животными в соседние чётные номера.Возвращает true, если размещение возможно, иначе false.

### 4. Программа
```java
import java.io.PrintStream;
import java.util.Scanner;
class Hotel
{
    public static Scanner in = new Scanner(System.in);
    public static PrintStream out = System.out;
    private String[] rooms;
    private int[] onefamily;
    private int familydss = 1;
    public Hotel(int n) 
    {
        rooms=new String[n];
        onefamily=new int[n];
    }
    public void printRooms() 
    {
        out.println("Свободные номера:");
        for (int i =0; i<rooms.length;i++) {
            if (rooms[i]==null) {
                out.println("Номер "+(i+1));
            }
        }
        out.println("Занятые номера:");
        for (int i=0;i<rooms.length;i++) {
            if (rooms[i]!=null){
                out.println("Номер "+(i+1)+" — "+rooms[i]);
            }
        }
    }
    public void checkIn(String name, int room)
    {
        int i=room-1;
        if (i>=0&&i<rooms.length&&rooms[i]==null) {
            rooms[i]=name;
        }
    }
    public void checkInFirstFree(String name) 
    {
        for (int i=0; i<rooms.length; i++) {
            if (rooms[i]==null) {
                rooms[i]=name;
                break;
            }
        }
    }
    public int checkInGroup(String[] names)
    {
        int dss=0;
        for (int i=0; i<rooms.length&&dss<names.length; i++) {
            if (rooms[i]==null) {
                rooms[i]=names[dss];
                dss++;
            }
        }
        return dss;
    }
    public int checkInGroupFrom(String[] names, int startRoom) 
    {
        int dss = 0;
        for (int i=startRoom-1;i<rooms.length&&dss<names.length; i++) {
            if (rooms[i]==null) {
                rooms[i]=names[dss];
                dss++;
            }
        }
        return dss;
    }
    public void checkOut(int room) 
    {
        int i=room-1;
        if (i>=0&&i<rooms.length) {
            rooms[i]=null;
            onefamily[i]=0;
        }
    }
    public boolean checkInFamily(String[] names) 
    {
        for (int i=0; i<=rooms.length-names.length; i++) {
            boolean boo=true;
            for (int j=0;j<names.length;j++) {
                if (rooms[i+j]!=null) {
                    boo=false;
                    break;
                }
            }
            if (boo){
                for (int j= 0;j<names.length;j++) {
                    rooms[i+j]=names[j];
                    onefamily[i+j]=familydss;
                }
                familydss++;
                return true;
            }
        }
        return false;
    }
    public void checkOutWithFamily(String name)
    {
        int f = 0;
        for (int i = 0; i < rooms.length; i++) {
            if (name.equals(rooms[i])) {
                f = onefamily[i];
                break;
            }
        }
        for (int i = 0; i < rooms.length; i++) {
            if (onefamily[i] == f) {
                rooms[i] = null;
                onefamily[i] = 0;
            }
        }
    }
    public void checkInWithAnimal(String name,int room)
    {
        if (room%2==0) {
            checkIn(name,room);
        }
    }
    public int checkInGroupWithAnimals(String[] names)
    {
        int dss=0;
        for (int i=1; i<=rooms.length&&dss<names.length; i+=2) {
            if (rooms[i]==null) {
                rooms[i]=names[dss];
                dss++;
            }
        }
        return dss;
    }
    public boolean checkInFamilyWithAnimals(String[] names) 
    {
        for (int i = 1;i<=rooms.length-names.length; i+=2) {
            boolean boo=true;
            for (int j=0; j< names.length;j++) {
                if (rooms[i+j]!=null||(i+j+1)%2!=0) {
                    boo=false;
                    break;
                }
            }
            if (boo) {
                for (int j=0;j<names.length; j++) {
                    rooms[i+j]=names[j];
                    onefamily[i+j]=familydss;
                }
                familydss++;
                return true;
            }
        }
        return false;
    }
}
```
