package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Food implements Comparable<Food> {
  private String ID;
  private String name;
  private String weight;
  private String type;
  private String place;
  private Date date;
  public static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

  public Food(String ID, String name, String weight, String type, String place, Date date) {
    this.ID = ID;
    this.name = name;
    this.weight = weight;
    this.type = type;
    this.place = place;
    this.date = date;
  }

  public String getID() {
    return ID;
  }

  public void setID(String ID) {
    this.ID = ID;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getWeight() {
    return weight;
  }

  public void setWeight(String weight) {
    this.weight = weight;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getPlace() {
    return place;
  }

  public void setPlace(String place) {
    this.place = place;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  @Override
  public String toString() {
    return ID + "," + name + "," + weight + "," + type + "," + place + "," + format.format(date);
  }

  @Override
  public int compareTo(Food f) {
    return f.getDate().compareTo(this.getDate());
  }
}
