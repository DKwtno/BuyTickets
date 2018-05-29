package com.sorahjy.buytickets.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import java.util.Objects;

@Entity
public class Girl {

    @Id
    // remove @GeneratedValue
    private int id;
    private String username;
    @Min(value=18,message = "未成年少女禁止入内")
    private String age;
    public Girl(final int id, final String username, final String age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }

    public Girl() {
    }

    public int getId() {

        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(final String age) {
        this.age = age;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Girl girl = (Girl) o;
        return id == girl.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Girl{" +
               "id=" + id +
               ", username='" + username + '\'' +
               ", age='" + age + '\'' +
               '}';
    }
}
