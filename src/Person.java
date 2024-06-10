public class Person {
    private String name;
    private String surname;
    private String email;

    public Person(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public Object print_person_info(){
        System.out.println("Customer name is "+getName()+" "+ getSurname());
        System.out.println("Customer email is "+ getEmail());
        return null;
    }
}
