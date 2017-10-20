import java.io.Serializable;

/**
 * Created by ak on 10/19/17
 */

public class User implements Serializable{
    private String name;
    private String age;
    private String height;
    private String weight;
    private String gender;

    public User(String name, String age, String height, String weight, String gender){
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
    }

    @Override
    public String toString(){
        return name + age + height + weight + gender;
    }

}
