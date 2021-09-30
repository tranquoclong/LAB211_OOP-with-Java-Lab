package Model;

public class Province {
    private String province;
    private String name;

    public Province() {
    }

    public Province(String province,String name) {
        this.province = province;
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "province [name=" + name + ", province=" + province + "]";
    }
}
