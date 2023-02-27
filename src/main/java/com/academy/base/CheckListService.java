package com.academy.base;

import com.academy.model.Information;

import java.util.ArrayList;
import java.util.List;

public class CheckListService {
    public List<Information> getCheck() {
        List<Information> check = new ArrayList<>();
        Information check1 = new Information("Look", 25);
        Information check2 = new Information("Look1", 124);
        Information check3 = new Information("Look2", 634);
        check.add(check1);
        check.add(check2);
        check.add(check3);

        return check;
    }
}
