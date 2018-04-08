package serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 序列化对象到文件
 * <p>
 * 反序列化的对象和序列化的对象equals和==都返回false
 * 但是这两个对象是完全一样的，也就是深克隆
 *
 * @author voctrals
 */
public class Try {

    public static void main(String[] args) {
        Address address = new Address();
        address.setProvince("辽宁省");
        address.setCity("大连市");
        address.setZone("甘井子区");
        address.setDescription("金柳东路301号");

        Person person = new Person();
        person.setName("Voctrals");
        person.setAge(32);
        person.setAddress(address);

        ObjectOutputStream objectOutputStream;
        ObjectInputStream objectInputStream;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream("voctrals"));
            objectOutputStream.writeObject(person);

            objectOutputStream.close();

            objectInputStream = new ObjectInputStream(new FileInputStream("voctrals"));
            Person serialPerson = (Person) objectInputStream.readObject();

            // false
            System.out.println(person.equals(serialPerson));
            // false
            System.out.println(person == serialPerson);

            objectInputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

/**
 * Person类实现序列化接口
 *
 * @author voctrals
 */
class Person implements Serializable {

    private static final long serialVersionUID = -9216225021055576806L;

    private String name;
    private Integer age;
    private Address address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address=" + address +
                '}';
    }
}

/**
 * Address类也要实现序列化接口，否则person无法序列化
 *
 * @author voctrals
 */
class Address implements Serializable {

    private static final long serialVersionUID = 1739345395654620128L;

    private String province;
    private String city;
    private String zone;
    private String description;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Address{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", zone='" + zone + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}