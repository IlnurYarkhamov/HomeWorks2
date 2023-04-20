import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) throws IOException {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();

        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

//        System.out.println("Список несовершеннолетних: ");
        countOfMinors(persons);
//        char ch = (char) System.in.read();
//        System.out.println("Список военнобязанных: ");
        militarians(persons);
//        ch = (char) System.in.read();
//        System.out.println("Список трудоспособных: ");
        employable(persons);
    }

    public static void countOfMinors(Collection<Person> persons) {
        long count = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println(count);
    }

    public static void militarians(Collection<Person> persons) {
        List<String> miles = persons.stream()
                .filter(x -> 27 > x.getAge() && x.getAge() > 18)
                .map(x -> x.getFamily().toString())
                .collect(Collectors.toList());
        miles.forEach(System.out::println);
    }


    public static void employable(Collection<Person> persons) {
        List<Person> peoples = persons.stream()
                .filter(x -> x.getEducation() == Education.HIGHER)
                .filter(x -> (60 > x.getAge() && x.getAge() > 18 && x.getSex() == Sex.WOMAN) || (65 > x.getAge() && x.getAge() > 18 && x.getSex() == Sex.MAN))
                .sorted(Comparator.comparing(Person::getFamily))
//                .collect(Collectors.toList());
//                 peoples.sort(Comparator.comparing(Person::getFamily));
                .collect(Collectors.toList());

//        for(int i = 0; i < 50; i++) {
//            System.out.println(peoples.get(i));

//         }

        peoples.forEach(System.out::println);
    }
}