package config;

import java.util.List;

public interface PopulatableUIComponent<T> {
    /* T for example could be JComboBox or a JList or anything that can be filled */
    void populate(List<T> listObjects);
    void removeAllItems();
    void addToList(T object);
    void rePopulate(List<T> listObjects);
}
