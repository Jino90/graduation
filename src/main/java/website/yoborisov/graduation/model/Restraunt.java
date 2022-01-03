package website.yoborisov.graduation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import org.hibernate.mapping.Collection;
import website.yoborisov.graduation.HasId;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;

@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "restraunt")
public class Restraunt extends AbstractNamedEntity implements HasId {

    public Set<Menu> getMenuSet() {
        return menuSet;
    }

    public void setMenuSet(Set<Menu> menuSet) {
        this.menuSet = menuSet;
    }

    @Transient
    private String description;

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

    public String getDescription() {
        return String.format("Ресторан № %d", this.id);
    }

    @JsonIgnore
    public Menu getLastMenu(){
        return Collections.max(menuSet, Comparator.comparing(Menu::getPublishDate));
    }
}
