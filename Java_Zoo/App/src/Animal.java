public class Animal {
    private final String name;
    private final int age;
    private final String gender;
    private final String birthSeason;
    private final String color;
    private final double weight;
    private final String habitat;
    private final String uniqueID;
    private final String birthdate;

    // Constructor to initialize animal details
    public Animal(String name, int age, String gender, String birthSeason, String color, double weight, String habitat, String uniqueID, String birthdate) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.birthSeason = birthSeason;
        this.color = color;
        this.weight = weight;
        this.habitat = habitat;
        this.uniqueID = uniqueID;
        this.birthdate = birthdate;
    }

    // Getter methods to access the animal details
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthSeason() {
        return birthSeason;
    }

    public String getColor() {
        return color;
    }

    public double getWeight() {
        return weight;
    }

    public String getHabitat() {
        return habitat;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getSpecies() {
        return habitat;  // Assuming habitat is the species
    }
}




