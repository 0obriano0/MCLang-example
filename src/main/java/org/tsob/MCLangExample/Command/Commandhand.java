package org.tsob.MCLangExample.Command;

import java.util.ArrayList;
import java.util.Arrays;

import org.tsob.MCLangExample.DataBase.DataBase;
public class Commandhand extends mainCommandSystem{
  public Commandhand() {
    super(  "hand",
        DataBase.fileMessage.getString("Command.Help.hand"),
        new ArrayList<String>(Arrays.asList("mclangex.user.hand")),
        "/hand");
  }
  
}
  
