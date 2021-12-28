package website.yoborisov.graduation.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import website.yoborisov.graduation.model.Restraunt;
import website.yoborisov.graduation.repository.RestrauntRepository;

import java.util.List;

@Repository
public class DataJpaRestrauntRepository implements RestrauntRepository {
    private static final Sort SORT_NAME = Sort.by(Sort.Direction.ASC, "name");

    private final CrudRestrauntRepository crudRestrauntRepository;
    private final CrudMenuRepository crudMenuRepository;

    public DataJpaRestrauntRepository(CrudRestrauntRepository crudRestrauntRepository, CrudMenuRepository crudMenuRepository) {
        this.crudRestrauntRepository = crudRestrauntRepository;
        this.crudMenuRepository = crudMenuRepository;
    }

    @Override
    @Transactional
    public Restraunt save(Restraunt restraunt, int menuId) {
        if (!restraunt.isNew() && get(restraunt.id()) == null) {
            return null;
        }
        restraunt.setMenu(crudMenuRepository.getById(menuId));
        return crudRestrauntRepository.save(restraunt);
    }

    @Override
    public boolean delete(int id) {
        return crudRestrauntRepository.delete(id) != 0;
    }

    public Restraunt get(int id) {
        return crudRestrauntRepository.findById(id).orElse(null);
    }

    @Override
    public List<Restraunt> getAll() {
        return crudRestrauntRepository.findAll(SORT_NAME);
    }

    @Override
    public Restraunt setMenu(Restraunt restraunt, int menuId) {
        restraunt.setMenu(crudMenuRepository.getById(menuId));
        return crudRestrauntRepository.save(restraunt);
    }
}
