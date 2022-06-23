package structure;

import java.io.Serializable;

/**
 * Weapon type
 */
public enum WeaponType implements Serializable {
    HAMMER,
    SHOTGUN,
    KNIFE,
    MACHINE_GUN;

    static WeaponType[] namesWeaponType = WeaponType.values();
}
