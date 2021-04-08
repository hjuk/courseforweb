package com.guli.edu.entity.subjectvo;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Subjectone {
    private String           id;
    private String           title;
    private List<Subjecttwo> subjecttwoList =new ArrayList<>();
}
