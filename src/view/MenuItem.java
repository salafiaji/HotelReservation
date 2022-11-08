package view;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class MenuItem {
    private String name;
    public MenuItem(String name) {
        this.name = name;
    }

    public void Run() {
        throw new NotImplementedException();
    }

    @Override
    public String toString() {
        return name;
    }
}