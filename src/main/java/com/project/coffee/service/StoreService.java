package   com.project.coffee.service;


import com.project.coffee.dto.StoreDTO;
import java.util.List;

public interface StoreService {
    StoreDTO createStore(StoreDTO storeDTO);
    StoreDTO getStoreById(Long id);
    List<StoreDTO> getAllStores();
    StoreDTO updateStore(Long id, StoreDTO storeDTO);
    void deleteStore(Long id);
}
