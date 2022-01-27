package leonardo.ezio.personal.entity;

import java.io.Serializable;

/**
 * @Description :
 * @Author : LeonardoEzio
 * @Date: 2021-09-30 16:54
 */
public class TestEntity implements Serializable {

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
