package com.example.social.login.demo.app.presentations.response;

import com.example.social.login.demo.auth.systems.modelmapper.Mappable;
import lombok.Data;

@Data
public class BirthStone implements Mappable {

  /** 月 */
  private String month;

  /** 名前 */
  private String name;

  /** 色 */
  private String color;
}
