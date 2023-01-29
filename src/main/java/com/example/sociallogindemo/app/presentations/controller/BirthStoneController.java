package com.example.sociallogindemo.app.presentations.controller;

import com.example.sociallogindemo.app.presentations.response.BirthStone;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class BirthStoneController {

  @GetMapping("/birthstone")
  @ResponseBody
  public BirthStone getBirthStone() {
    BirthStone birthStone = new BirthStone();
    birthStone.setMonth("2月");
    birthStone.setName("アメジスト");
    birthStone.setColor("紫");
    return birthStone;
  }
}
