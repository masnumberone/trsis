/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.lab1.model;

import java.util.*;

/**
 *
 * @author Pavel.Stepanov
 */
public  class DataMap {

    private static DataMap instance;
    private Map<String, List<String>> data;

    private DataMap(){
        data = new HashMap<String, List<String>>();
    }

    public static DataMap getInstance() {
        if(instance==null) {
            instance = new DataMap();
        }
        return instance;
    }

    public void addData(String ip, String literature){
        if(data.containsKey(ip)) {
            data.get(ip).add(literature);
            // тут можно написать компаратор (?) для сортировки списка в алфавитном порядке
        }
        else {
//            data.put(ip, new ArrayList<String>(literature));
            List<String> list = new ArrayList<>();
            list.add(literature);
            data.put(ip, list);
        }
    }

    public void removeLiterature(String ip, int index){
        if(containsKey(ip) && index < data.get(ip).size()) {
            data.get(ip).remove(index);
            if(data.get(ip).size() == 0) {
                data.remove(ip);
            }
        }
        return;
    }

    public boolean containsKey(String ip){
        return data.containsKey(ip);
    }

    public List<String> getData(String ip) {
        return data.get(ip);
    }
}
