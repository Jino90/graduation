package website.yoborisov.graduation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;

@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@NamedQueries({
        @NamedQuery(name = Menu.DELETE, query = "DELETE FROM Menu u WHERE u.id=:id"),
        @NamedQuery(name = Menu.ALL_SORTED, query = "SELECT u FROM Menu u"),
})
@Entity
@Table(name = "menu")
public class Menu extends AbstractBaseEntity {

    public static final String seqName = "global_seq";

    public static final String DELETE = "Menu.delete";
    public static final String ALL_SORTED = "Menu.getAllSorted";

    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    @OneToMany(mappedBy = "menu", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    //@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    //@BatchSize(size = 200)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Dish> dishes;

    public Restraunt getRestraunt() {
        return restraunt;
    }

    public void setRestraunt(Restraunt restraunt) {
        this.restraunt = restraunt;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restraunt_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Restraunt restraunt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User author;

    @Column(name = "votes", nullable = false, columnDefinition = "Votes value", updatable = true)
    private Integer votes = 0;

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }

    @Column(name = "publish_date", nullable = false, columnDefinition = "Publish date", updatable = true)
    @JsonIgnore
    private LocalDateTime publishDate;

    @Transient
    private String description;

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

    public void decreaseVotes(){
        this.votes--;
    }

    public Menu(Set<Dish> dishes) {
        this(null, dishes);
    }

    public Menu(Integer id, Set<Dish> dishes) {
        super(id);
        this.dishes = dishes;
        this.publishDate = LocalDateTime.now();
    }

    public void setAuthor(User user){
        this.author = user;
    }

    public User getAuthor(){
        return this.author;
    }

    public void setDishes(Set<Dish> dishes){
        this.dishes = dishes;
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void addToDishes(Dish dish){
        dish.setMenu(this);
        this.dishes.add(dish);
    }

    public String getDescription() {
        return String.format("Меню № %d", this.id);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                "dishes=" + dishes +
                "votes=" + votes +
                "author=" + author +
                '}';
    }
}
