package website.yoborisov.graduation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "restraunt")
public class Restraunt extends AbstractNamedEntity {

    public Set<Menu> getMenuSet() {
        return menuSet;
    }

    public void setMenuSet(Set<Menu> menuSet) {
        this.menuSet = menuSet;
    }

    @OneToMany(mappedBy = "restraunt", fetch = FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @BatchSize(size = 200)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Menu> menuSet;

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    @Column(name = "votes", nullable = false, columnDefinition = "Votes value", updatable = true)
    @JsonIgnore
    private Integer votes = 0;

    public Restraunt (){
    }

    public Restraunt(String name, Set<Menu> menuSet){
        this(null, name, menuSet);
    }

    public Restraunt(Integer id, String name, Set<Menu> menuSet){
        super(id, name);
        this.menuSet = menuSet;
    }

    public void increaseVotes() {
        this.votes++;
    }
}
