package org.tsob.MCLangExample.Command.hand;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.tsob.MCLangExample.Main;
import org.tsob.MCLangExample.Command.mainCommandSystem;
import org.tsob.MCLangExample.DataBase.DataBase;
public class Commanditemname extends mainCommandSystem{
  public Commanditemname() {
    super(  "hand.itemname",
        DataBase.fileMessage.getString("Command.Help.hand_itemname"),
        new ArrayList<String>(Arrays.asList("mclangex.user.hand.itemname")));
  }

  @Override
  public void run(Player player, String commandLabel, Command command, String[] args) throws Exception {
    ItemStack itemStack = player.getInventory().getItemInMainHand();
    if (itemStack == null || itemStack.getType().toString().equals("AIR")) {
      player.sendMessage(DataBase.fileMessage.getString("Command.Hand.ItemName.NoItem"));
      return;
    }
    DataBase.sendMessage(player,"You are holding: " + ChatColor.GREEN + Main.mclang.getItemTranslate(itemStack));
  }
}
  
