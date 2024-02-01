package HW;

import HW.data.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        task4();
        System.out.println();
        task5();
    }

    /**
     * 4. Используйте библиотеку commons-lang3 для генерации методов toString, equals и hashCode.
     */
    private static void task4() {
        System.out.println("Task №4");
        String personString = ToStringBuilder.reflectionToString(new Person("Ivan", "Ivanov", 100));
        System.out.println(personString);
        System.out.println();

        Person person1 = new Person("Ivan", "Ivanov", 20);
        Person person2 = new Person("Stas", "Petrov", 30);
        Person person3 = new Person("Ivan", "Ivanov", 20);

        System.out.println(EqualsBuilder.reflectionEquals(person1, person2));
        System.out.println(EqualsBuilder.reflectionEquals(person1, person3));
        System.out.println();

        System.out.println(HashCodeBuilder.reflectionHashCode(person1));
        System.out.println(HashCodeBuilder.reflectionHashCode(person2));
        System.out.println(HashCodeBuilder.reflectionHashCode(person3));
    }

    /**
     * 5. Используйте библиотеку gson для сериализации и десериализации объектов класса Person в формат JSON.
     *
     * @throws IOException
     */
    private static void task5() throws IOException {
        System.out.println("Task №5\n----------------------------------------------");
        // сериализация
        Path path = Paths.get("person.json");
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Person person = new Person("Ivan", "Ivanov", 20);
        Files.write(path, gson.toJson(person).getBytes());

        //десериализация
        String personJSON = Files.readString(path);
        Person personFromJSON = gson.fromJson(personJSON, Person.class);
        System.out.println(ToStringBuilder.reflectionToString(personFromJSON));
    }
}
