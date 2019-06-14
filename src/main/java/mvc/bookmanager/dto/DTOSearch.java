package mvc.bookmanager.dto;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Oleg
 * 13.06.2019
 */
@Component
public class DTOSearch {
    private Map<String,String> findMap = new LinkedHashMap<String, String>();
    private String text;
    private String find;

    public DTOSearch() {
    }

    public String getFind() {
        return find;
    }

    public void setFind(String find) {
        this.find = find;
    }

    public Map<String, String> getFindMap() {
        return findMap;
    }

    public void setFindMap(Map<String, String> findMap) {
        this.findMap = findMap;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
