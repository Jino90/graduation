package website.yoborisov.graduation.model;

import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "restraunt")
public class Restraunt extends AbstractNamedEntity {

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @OneToOne(mappedBy = "restraunt")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @BatchSize(size = 200)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Menu menu;

    public Restraunt (){

    }

    public Restraunt(Integer id, String name, Menu menu){
        super(id, name);
        this.menu = menu;
    }
}
