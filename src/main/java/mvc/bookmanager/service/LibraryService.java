package mvc.bookmanager.service;

import mvc.bookmanager.dto.DTOSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Oleg
 * 14.06.2019
 */
@Service
public class LibraryService {
    private DTOSearch dtoSearch;

    public DTOSearch getDtoSearch() {
        return dtoSearch;
    }
@Autowired
    public void setDtoSearch(DTOSearch dtoSearch) {
        this.dtoSearch = dtoSearch;
    }
}
