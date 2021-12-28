package website.yoborisov.graduation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "dish")
public class Dish extends AbstractNamedEntity{

    @Column(name = "price", nullable = false, columnDefinition = "Price for dish", updatable = false)
    @NotNull
    private Integer price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="menu_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Menu menu;

    public Dish(){

    }

    public Dish(String name, Integer price){
        this(null, name, price);
    }

    public Dish(Integer id, String name, Integer price){
        super(id, name);
        this.price = price;
    }

    public Dish(Integer id, String name, Integer price, Menu menu){
        super(id, name);
        this.price = price;
        this.menu = menu;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Menu getMenu() {
        return null;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
