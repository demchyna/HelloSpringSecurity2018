package com.softserve.academy.repository;

import com.softserve.academy.model.Group;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GroupRepository {
    private List<Group> groups = new ArrayList<>();

    public GroupRepository() {
        groups.add(new Group("PI-16-1", 3));
        groups.add(new Group("PI-17-1", 2));
        groups.add(new Group("PI-18-1", 1));
    }

    public Group getById(final int id) {
        return groups.stream()
                .filter(g -> g.getId() == id)
                .findAny().orElse(null);
    }

    public void create(Group group) {
        if (group != null)
            groups.add(group);
    }

    public void update(Group group) {
        if (group != null) {
            Group oldGroup = groups.stream()
                    .filter(g -> g.getId() == group.getId())
                    .findAny().orElse(null);
            if (oldGroup != null) {
                int index = groups.indexOf(oldGroup);
                groups.set(index, group);
            }
        }
    }

    public void delete(int id) {
        groups.stream()
                .filter(g -> g.getId() == id).findAny()
                .ifPresent(g -> groups.remove(g));
    }

    public List<Group> getAll() {
        return groups;
    }
}
