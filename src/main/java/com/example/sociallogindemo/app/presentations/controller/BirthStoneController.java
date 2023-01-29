package com.example.sociallogindemo.app.presentations.controller;

import com.example.sociallogindemo.app.presentations.response.BirthStone;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
@Tag(name = "BirthStone", description = "誕生石のAPI")
public class BirthStoneController {

  @GetMapping("/birthstone")
  @ResponseBody
  @Operation(summary = "誕生石データを取得する",
          description = "誕生石データをサービスを通さずに取得するただのダミー")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "成功"),
          @ApiResponse(responseCode = "405", description = "入力チェックエラー")
  })
  public BirthStone getBirthStone() {
    BirthStone birthStone = new BirthStone();
    birthStone.setMonth("2月");
    birthStone.setName("アメジスト");
    birthStone.setColor("紫");
    return birthStone;
  }
}
