package website.yoborisov.graduation.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@NamedQueries({
        @NamedQuery(name = Menu.DELETE, query = "DELETE FROM Menu u WHERE u.id=:id"),
        @NamedQuery(name = Menu.ALL_SORTED, query = "SELECT u FROM Menu u ORDER BY u.name, u.price"),
})
@Entity
@Table(name = "menu")
public class Menu extends AbstractNamedEntity {

    public static final String DELETE = "Menu.delete";
    public static final String ALL_SORTED = "Menu.getAllSorted";

    @Column(name = "price", nullable = false, columnDefinition = "Price for dish", updatable = false)
    @NotNull
    private Float price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User author;

    public Menu() {
    }

    public Menu(Integer id, String name, Float price) {
        super(id, name);
        this.price = price;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setAuthor(User user){
        this.author = user;
    }

    public User getAuthor(){
        return this.author;
    }

}
