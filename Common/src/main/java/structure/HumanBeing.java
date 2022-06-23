package structure;

import utils.Const;
import work.with.structure.HumanBeingInput;

import java.io.Serializable;
import java.util.Date;
import java.util.Scanner;

/**
 * Data storage structure
 */
public class HumanBeing implements java.lang.Comparable, Serializable {
    private int id;
    private String name;
    private Coordinates coordinates;
    private Date creationDate;
    private float age;
    private Long impactSpeed;
    private Double distanceTravelled;
    private WeaponType weaponType;
    private String owner;


    public HumanBeing(int id, String name, Coordinates coordinates, float age, Long impactSpeed, Double distanceTravelled, WeaponType weaponType, String owner) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.age = age;
        this.impactSpeed = impactSpeed;
        this.distanceTravelled = distanceTravelled;
        this.weaponType = weaponType;
        this.owner = owner;
    }

    public HumanBeing(int id, String name, Coordinates coordinates, Date creationDate, float age, Long impactSpeed, Double distanceTravelled, WeaponType weaponType, String owner) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.age = age;
        this.impactSpeed = impactSpeed;
        this.distanceTravelled = distanceTravelled;
        this.weaponType = weaponType;
        this.owner = owner;
    }

    public HumanBeing() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws NullPointerException {
        if (name != null && !name.isEmpty())
            this.name = name;
        else throw new NullPointerException();
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public float getAge() {
        return age;
    }

    public void setAge(float enginePower) {
        this.age = enginePower;
    }

    public Long getImpactSpeed() {
        return impactSpeed;
    }

    public Double getDistanceTravelled() {
        return distanceTravelled;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public String getWeaponTypeAsString() {
        return weaponType.toString();
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setImpactSpeed(Long impactSpeed) {
        this.impactSpeed = impactSpeed;
    }

    public void setDistanceTravelled(Double distanceTravelled) {
        this.distanceTravelled = distanceTravelled;
    }

    public void setWeaponTypeType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public int compareTo(Object o) {
        if (this == o)
            return 0;
        if (o == null || getClass() != o.getClass())
            return -1;

        HumanBeing other = (HumanBeing) o;
        return (this.id - other.id);
    }

    @Override
    public String toString() {
        return "-----------------------------------------------------" + "\n" + "id=" + id + "\n" + "name=" + name + "\n" + "coordinates=" + coordinates + "\n" + "creationDate=" + Const.timeFormat.format(creationDate) + "\n" + "age=" + age + "\n" + "impactSpeed=" + impactSpeed + "\n" + "distanceTravelled=" + distanceTravelled + "\n" + "weaponType=" + weaponType + "\n" + "owner=" + owner + "\n" + "-----------------------------------------------------";
    }

    public static HumanBeing input(Scanner scanner, Boolean script) {
        HumanBeingInput input = new HumanBeingInput(scanner, script);
        HumanBeing humanBeing = input.resultElement(0);
        return humanBeing;
    }
}
