package work.with.structure;

import structure.*;

import java.util.Scanner;


/**
 * The class in which the collection item is created
 */
public class HumanBeingInput {
    private String name;
    private WeaponType weaponType;
    private Float x;
    private Float y;
    private Float age;
    private Long impactSpeed;
    private Double distanceTravelled;

    private Scanner input;
    private boolean script;

    public HumanBeingInput(Scanner input, boolean silent) {
        this.input = input;
        this.script = silent;
    }

    public boolean nameInput() {
        while (true) {
            if (!script)
                System.out.print("Enter name: ");
            name = input.nextLine();
            if (name.trim().length() > 0) break;
            if (!script)
                System.out.println("Invalid input. The string must not be empty!");
            else {
                return false;
            }
        }
        return true;
    }

    public boolean xInput() {
        boolean stop = false;
        while (!stop) {
            if (!script)
                System.out.print("Enter x coordinate: ");
            String line = input.nextLine();
            try {
                x = Float.parseFloat(line);
                stop = true;
            } catch (Exception e) {
                if (!script) {
                    System.out.println("Please enter a Float number!");
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean yInput() {
        boolean stop = false;
        while (!stop) {
            if (!script)
                System.out.print("Enter y coordinate: ");
            String line = input.nextLine();
            try {
                y = Float.parseFloat(line);
                stop = true;
            } catch (Exception e) {
                if (!script) {
                    System.out.println("Please enter a Float number!");
                } else {
                    return false;
                }
            }
        }
        return true;
    }


    public boolean ageInput() {
        boolean stop = false;
        while (!stop) {
            if (!script)
                System.out.print("Enter age: ");
            String line = input.nextLine();
            try {
                age = Float.parseFloat(line);
                if (age <= 0) throw new Exception();
                stop = true;
            } catch (Exception e) {
                if (!script) {
                    System.out.println("Please enter a positive Float number!");
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean impactSpeedInput() {
        boolean stop = false;
        while (!stop) {
            if (!script)
                System.out.print("Enter impactSpeed: ");
            String line = input.nextLine();
            try {
                impactSpeed = Long.parseLong(line);
                if (impactSpeed <= 0) throw new Exception();
                stop = true;
            } catch (Exception e) {
                if (!script) {
                    System.out.println("Please enter a positive Long number!");
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean distanceTravelledInput() {
        boolean stop = false;
        while (!stop) {
            if (!script)
                System.out.print("Enter distance travelled: ");
            String line = input.nextLine();
            try {
                distanceTravelled = Double.parseDouble(line);
                if (distanceTravelled <= 0) throw new Exception();
                stop = true;
            } catch (Exception e) {
                if (!script) {
                    System.out.println("Please enter a positive Double number!");
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean weaponTypeInput() {
        boolean stop = false;
        while (!stop) {
            if (!script)
                System.out.print("Enter type (HAMMER, SHOTGUN, KNIFE, MACHINE_GUN): ");
            String line = input.nextLine();
            try {
                weaponType = WeaponType.valueOf(line);
                stop = true;
            } catch (Exception e) {
                if (!script) {
                    System.out.println("Enter a word from the suggested list!");
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public HumanBeing createElement(Integer id) {
        return new HumanBeing(id, name, new Coordinates(x, y), age, impactSpeed, distanceTravelled, weaponType, null);
    }

    public HumanBeing resultElement(Integer id) {
        if (nameInput()
                && xInput()
                && yInput()
                && ageInput()
                && impactSpeedInput()
                && distanceTravelledInput()
                && weaponTypeInput()
        ) {
            return createElement(id);
        } else {
            return null;
        }
    }
}