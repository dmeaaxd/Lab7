package app;

import dad.DataAccessDriver;
import structure.Coordinates;
import structure.HumanBeing;
import structure.WeaponType;
import utils.Const;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

public class CollectionManager {
    private final TreeSet<HumanBeing> collection;
    static Date collectionDate = new Date();

    public CollectionManager() {
        collection = new TreeSet();
        readCollectionFromDB();
    }

    private synchronized boolean readCollectionFromDB() {
        boolean success = false;
        collection.clear();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DataAccessDriver.getInstance().getConnection();
            pst = con.prepareStatement("SELECT \"id\", \"name\", \"creationDate\", \"weaponType\", \"x\", \"y\", \"age\", \"impactspeed\", \"distanceTravelled\", \"owner\" FROM \"humanbeings\"");
            rs = pst.executeQuery();
            HumanBeing humanBeing;
            while (rs.next()) {
                humanBeing = new HumanBeing(
                        rs.getInt("id"),
                        rs.getString("name"),
                        new Coordinates(rs.getFloat("x"), rs.getFloat("y")),
                        new Date(rs.getTimestamp("creationDate").getTime()),
                        rs.getFloat("age"),
                        rs.getLong("impactSpeed"),
                        rs.getDouble("distanceTravelled"),
                        WeaponType.valueOf(rs.getString("weaponType")),
                        rs.getString("owner")
                );
                collection.add(humanBeing);
            }
            success = true;
            rs.close();
            rs = null;
            pst.close();
            pst = null;
            con.close();
            con = null;
        } catch (Exception e) {
            System.out.println("Error while selecting humanBeings");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
                rs = null;
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception e) {
                }
                pst = null;
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                }
                con = null;
            }
        }
        return success;
    }

    private int getNextHumanBeingsIdFromDB() {
        int result = 0;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DataAccessDriver.getInstance().getConnection();
            pst = con.prepareStatement("SELECT NEXTVAL('humanbeings_id') AS id");
            rs = pst.executeQuery();
            if (rs.next()) {
                result = rs.getInt("id");
            }
            rs.close();
            rs = null;
            pst.close();
            pst = null;
            con.close();
            con = null;
        } catch (Exception e) {
            System.out.println("Error while selecting next humanBeings id");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
                rs = null;
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception e) {
                }
                pst = null;
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                }
                con = null;
            }
        }
        return result;
    }

    private boolean insertHumanBeingToDB(HumanBeing humanBeing) {
        boolean success = false;
        humanBeing.setId(getNextHumanBeingsIdFromDB());
        Instant instant = humanBeing.getCreationDate().toInstant();
        Timestamp ts = instant != null ? Timestamp.from(instant) : null;

        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DataAccessDriver.getInstance().getConnection();
            String sql = "INSERT INTO \"humanbeings\" (\"id\", \"name\", \"creationDate\", \"weaponType\", \"x\", \"y\", \"age\", \"impactspeed\", \"distanceTravelled\", \"owner\") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pst = con.prepareStatement(sql);
            pst.setInt(1, humanBeing.getId());
            pst.setString(2, humanBeing.getName());
            pst.setTimestamp(3, ts);
            pst.setString(4, humanBeing.getWeaponTypeAsString());
            pst.setFloat(5, humanBeing.getCoordinates().getX());
            pst.setFloat(6, humanBeing.getCoordinates().getY());
            pst.setFloat(7, humanBeing.getAge());
            pst.setLong(8, humanBeing.getImpactSpeed());
            pst.setDouble(9, humanBeing.getDistanceTravelled());
            pst.setString(10, humanBeing.getOwner());
            pst.executeUpdate();
            success = true;
            pst.close();
            pst = null;
            con.close();
            con = null;
        } catch (Exception e) {
            System.out.println("Error while adding humanBeings");
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception e) {
                }
                pst = null;
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                }
                con = null;
            }
        }
        return success;
    }

    private boolean updateHumanBeingInDB(int id, HumanBeing humanBeing) {
        boolean success = false;
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DataAccessDriver.getInstance().getConnection();
            String sql = "UPDATE \"humanbeings\" SET \"name\"=?, \"weaponType\"=?, \"x\"=?, \"y\"=?, \"age\"=?, \"impactSpeed\"=?, \"distanceTravelled\"=? WHERE \"id\"=?";
            pst = con.prepareStatement(sql);
            pst.setString(1, humanBeing.getName());
            pst.setString(2, humanBeing.getWeaponTypeAsString());
            pst.setFloat(3, humanBeing.getCoordinates().getX());
            pst.setFloat(4, humanBeing.getCoordinates().getY());
            pst.setFloat(5, humanBeing.getAge());
            pst.setLong(6, humanBeing.getImpactSpeed());
            pst.setDouble(7, humanBeing.getDistanceTravelled());
            pst.setInt(8, humanBeing.getId());
            pst.executeUpdate();
            success = true;
            pst.close();
            pst = null;
            con.close();
            con = null;
        } catch (Exception e) {
            System.out.println("Error while adding humanBeings");
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception e) {
                }
                pst = null;
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                }
                con = null;
            }
        }
        return success;
    }


    private boolean deleteByOwnerFromDB(String owner) {
        boolean success = false;
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DataAccessDriver.getInstance().getConnection();
            String sql = "DELETE FROM \"humanbeings\" WHERE \"owner\"=?";
            pst = con.prepareStatement(sql);
            pst.setString(1, owner);
            pst.executeUpdate();
            success = true;
            pst.close();
            pst = null;
            con.close();
            con = null;
        } catch (Exception e) {
            System.out.println("Error while deleting by owner");
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception e) {
                }
                pst = null;
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                }
                con = null;
            }
        }
        return success;
    }

    private boolean deleteByIdFromDB(int id, String owner) {
        boolean success = false;
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DataAccessDriver.getInstance().getConnection();
            String sql = "DELETE FROM \"humanbeings\" WHERE \"id\"=? AND \"owner\"=?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.setString(2, owner);
            pst.executeUpdate();
            success = true;
            pst.close();
            pst = null;
            con.close();
            con = null;
        } catch (Exception e) {
            System.out.println("Error while deleting by id");
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception e) {
                }
                pst = null;
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                }
                con = null;
            }
        }
        return success;
    }

    private boolean deleteGreaterFromDB(float age, Long impactSpeed, Double distanceTravelled, String owner) {
        boolean success = false;
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DataAccessDriver.getInstance().getConnection();
            String sql = "DELETE FROM \"humanBeings\" WHERE \"age\">? AND \"impactSpeed\">? AND \"distanceTravelled\">? AND \"owner\"=?";
            pst = con.prepareStatement(sql);
            pst.setFloat(1, age);
            pst.setLong(2, impactSpeed);
            pst.setDouble(3, distanceTravelled);
            pst.setString(4, owner);
            pst.executeUpdate();
            success = true;
            pst.close();
            pst = null;
            con.close();
            con = null;
        } catch (Exception e) {
            System.out.println("Error while deleting greater");
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception e) {
                }
                pst = null;
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                }
                con = null;
            }
        }
        return success;
    }


    private boolean deleteLowerFromDB(float enginePower, Long capacity, Double distanceTravelled, String owner) {
        boolean success = false;
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DataAccessDriver.getInstance().getConnection();
            String sql = "DELETE FROM \"vehicles\" WHERE \"enginePower\"<? AND \"capacity\"<? AND \"distanceTravelled\"<? AND \"owner\"=?";
            pst = con.prepareStatement(sql);
            pst.setFloat(1, enginePower);
            pst.setLong(2, capacity);
            pst.setDouble(3, distanceTravelled);
            pst.setString(4, owner);
            pst.executeUpdate();
            success = true;
            pst.close();
            pst = null;
            con.close();
            con = null;
        } catch (Exception e) {
            System.out.println("Error while deleting lower");
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception e) {
                }
                pst = null;
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                }
                con = null;
            }
        }
        return success;
    }


    /**
     * Найти элемент по его id
     */
    public synchronized HumanBeing getById(int id) {
        for (HumanBeing v : collection) {
            if (v.getId() == id) {
                return v;
            }
        }
        return null;
    }


    public synchronized TreeSet<HumanBeing> getCollection() {
        return collection;
    }


    public synchronized boolean add(HumanBeing humanBeing) {
        if (insertHumanBeingToDB(humanBeing)) {
            collection.add(humanBeing);
            return true;
        }
        return false;
    }

    public synchronized boolean clear(String owner) {
        if (deleteByOwnerFromDB(owner)) {
            readCollectionFromDB();
            return true;
        }
        return false;
    }

    public synchronized String FilterGreaterThanType(String weaponType) {
        if (collection.isEmpty()) {
            return "Collection is empty!\n";
        } else {
            int ord = WeaponType.valueOf(weaponType).ordinal();
            return collection
                    .stream()
                    .filter(vehicle -> vehicle.getWeaponType().ordinal() > ord)
                    .map(HumanBeing::toString)
                    .reduce("", (result, humanBeing) -> result + humanBeing + "\n");
        }
    }

    public synchronized String info() {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(Const.timeFormat.parse(String.valueOf(collectionDate)));
        } catch (Exception ignored) {
        }
        return "Type - " + collection.getClass() + "\n" +
                "Creation date - " + calendar.getTime() + "\n" +
                "Amount of elements - " + collection.size();
    }


    public synchronized HumanBeing getMaxById() {
        return collection
                .stream()
                .max(Comparator.comparing(HumanBeing::getId))
                .orElse(null);
    }


    public synchronized String printAscending() {
        if (collection.isEmpty()) {
            return "Collection is empty!\n";
        } else {
            return collection
                    .stream()
                    .sorted(Comparator.comparingInt(HumanBeing::getId))
                    .map(HumanBeing::toString)
                    .reduce("", (result, humanBeing) -> result + humanBeing + "\n");
        }
    }


    public synchronized boolean removeById(int id, String owner) {
        HumanBeing avehicle = getById(id);
        if (avehicle == null || !avehicle.getOwner().equals(owner)) {
            return false;
        }
        if (deleteByIdFromDB(id, owner)) {
            collection.removeIf(humanBeing -> humanBeing.getId() == id && humanBeing.getOwner().equals(owner));
            return true;
        }
        return false;
    }


    public synchronized boolean removeGreater(HumanBeing element, String owner) {
        if (deleteGreaterFromDB(element.getAge(), element.getImpactSpeed(), element.getDistanceTravelled(), owner)) {
            readCollectionFromDB();
            collection.removeIf(v -> v.getDistanceTravelled() > element.getDistanceTravelled() && v.getImpactSpeed() > element.getImpactSpeed() && v.getAge() > element.getAge());
            return true;
        }
        return false;
    }


    public synchronized boolean removeLower(HumanBeing element, String owner) {
        if (deleteLowerFromDB(element.getAge(), element.getImpactSpeed(), element.getDistanceTravelled(), owner)) {
            readCollectionFromDB();
            collection.removeIf(v -> v.getDistanceTravelled() < element.getDistanceTravelled() && v.getImpactSpeed() < element.getImpactSpeed() && v.getAge() < element.getAge());
            return true;
        }
        return false;
    }


    public synchronized String show() {
        if (collection.isEmpty()) {
            return "Collection is empty!\n";
        } else {
            return collection
                    .stream()
                    .map(HumanBeing::toString)
                    .reduce("", (result, humanBeing) -> result + humanBeing + "\n");
        }
    }


    public synchronized boolean updateElement(HumanBeing v1, HumanBeing v2, String owner) {
        if (!v1.getOwner().equals(owner)) {
            return false;
        }
        if (updateHumanBeingInDB(v1.getId(), v2)) {
            v1.setName(v2.getName());
            v1.setCoordinates(v2.getCoordinates());
            v1.setAge(v2.getAge());
            v1.setImpactSpeed(v2.getImpactSpeed());
            v1.setDistanceTravelled(v2.getDistanceTravelled());
            v1.setWeaponTypeType(v2.getWeaponType());
            return true;
        }
        return false;
    }
}
