/**
 * This file was generated by the Jeddict
 */
package domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * @author Daniel Lindkvist
 */
@Entity
public class Mediatype implements Comparable<Mediatype> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;

    @Basic
    private String type;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int compareTo(Mediatype o) {
        return this.getType().compareTo(o.getType());
    }

    @Override
    public String toString() {
        return type;
    }
    

}