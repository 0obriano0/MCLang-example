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
    // DataBase.sendMessage(player, "You are holding: " + ChatColor.GREEN + Main.mclang.getItemTranslate(itemStack));
    // DataBase.sendMessage(player, "You are holding(ja_jp): " + ChatColor.GREEN + Main.mclang_ja_jp.getItemTranslate(itemStack));
    // DataBase.sendMessage(player, "You are holding{zh_tw}: " + ChatColor.GREEN + Main.mclang_zh_tw.getItemTranslate(itemStack));
    DataBase.sendMessage(player,  "You are holding: " + ChatColor.RESET + "\n" + 
                                  "   - en    : " + ChatColor.GREEN + Main.mclang.getItemTranslate(itemStack) + ChatColor.RESET + "\n" + 
                                  "   - ja_jp : " + ChatColor.GREEN + Main.mclang_ja_jp.getItemTranslate(itemStack) + ChatColor.RESET + "\n" + 
                                  "   - zh_tw : " + ChatColor.GREEN + Main.mclang_zh_tw.getItemTranslate(itemStack) + ChatColor.RESET
    );
  }
}
  
