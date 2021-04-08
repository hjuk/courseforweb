package com.guli.edu.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChapterVo {
    private String id;

    private String title;

    private List<VideoVo> cd=new ArrayList<>();
}
