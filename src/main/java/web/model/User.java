package web.model;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name="firstName")
   // @NotBlank(message = "Don't forget to input name")
    //@Min(value = 3,message = "Input minimum 3 symbols")
    private String firstName;


    @Column(name= "surname")
   // @NotBlank(message = "Dont forget to input surname")
    private String surname;

   // @Size(min =0, message = "Age should be greater than 0")
    @Column(name="age")
    private Integer age;


    @Column(name="city")
   // @NotBlank(message = "city shouldn't be empty")
    private  String city;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public User(String firstName, String surname, Integer age, String city) {
        this.firstName = firstName;
        this.surname = surname;
        this.age = age;
        this.city = city;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", email='" + city + '\'' +
                '}';
    }
}
