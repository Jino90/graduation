package website.yoborisov.graduation.model;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@NamedQueries({
        @NamedQuery(name = Menu.DELETE, query = "DELETE FROM Menu u WHERE u.id=:id"),
        @NamedQuery(name = Menu.ALL_SORTED, query = "SELECT u FROM Menu u ORDER BY u.name"),
})
@Entity
@Table(name = "menu")
public class Menu extends AbstractNamedEntity {

    public static final String DELETE = "Menu.delete";
    public static final String ALL_SORTED = "Menu.getAllSorted";

    @OneToMany(mappedBy = "menu")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @BatchSize(size = 200)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Dish> dishes;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restraunt_id", referencedColumnName = "id")
    private Restraunt restraunt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User author;

    @Column(name = "votes", nullable = false, columnDefinition = "Votes value", updatable = true)
    private int votes = 0;

    public Menu() {
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public void increaseVotes() {
        this.votes++;
    }

    public Menu(Integer id, String name, Set<Dish> dishes) {
        super(id, name);
        this.dishes = dishes;
    }

    public void setAuthor(User user){
        this.author = user;
    }

    public User getAuthor(){
        return this.author;
    }

}
