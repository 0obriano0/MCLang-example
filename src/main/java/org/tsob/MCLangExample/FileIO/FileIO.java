package org.tsob.MCLangExample.FileIO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.tsob.MCLangExample.AnsiColor;
import org.tsob.MCLangExample.Main;
import org.tsob.MCLangExample.DataBase.DataBase;

/**
 * 檔案操作的核心檔案
 * @author Brian
 *
 */
public class FileIO implements IFileIO{
  
  private transient String FileName;
  private transient String URL = null;
  
  protected FileConfiguration data = null;
  
  public FileIO(@Nonnull String FileName){
    this.FileName = FileName;
  }
  
  public FileIO(String URL,@Nonnull String FileName){
    this.FileName = FileName;
    this.URL = URL;
  }
  
  @Override
  public String getFileName() {
    return FileName;
  }
  
  protected void setFileName(String FileName) throws NullPointerException, IllegalArgumentException{
    this.FileName = FileName;
    readFile();
  }
  
  @Override
  public String getPath() {
    String full_url = FileName;
    
    if(URL.equals(null))
      full_url = "./" + Main.plugin.getDataFolder().toString().replace("\\", "/") + "/" + FileName;
    else
      full_url = "./" + Main.plugin.getDataFolder().toString().replace("\\", "/") + "/" + URL + "/" + FileName;
    
    return full_url;
  }
  
  @Override
  public FileConfiguration getFileforYML() {
    if(data == null) reloadFile();
    return data;
  }
  
  @Override
  public String getString(String path) {
    try {
      String getMsg = this.getFileforYML().getString(path);
      if (getMsg == null) {
        printCmd("§cError: path not found: §e" + path + "§c in file: §e" + getPath());
        getMsg = path;
      } else {
        getMsg = getMsg.replaceAll("&", "§");
      }
      return getMsg;
    }catch(NullPointerException e){
      
      String full_url = FileName;
      try {
        if(!URL.equals(null)) full_url = URL + "\\" + FileName;
      }catch(NullPointerException ee) {
        return "system error [file load error \" " + FileName + " \"]";
      }
      
      Main.plugin.saveResource(full_url, true);
      
      if(reloadFile())
        try {
          return this.getFileforYML().getString(path).replaceAll("&", "§");
        }catch(Exception ee){
          ee.printStackTrace();
          return "system error [file load error \" " + FileName + " \"]";
        }
      else {
        e.printStackTrace();
        return "system error [file load error \" " + FileName + " \"]";
      }
      
    }
    
  }
  
  private List<String> formateStringlist(@Nonnull List<String> data){
    List<String> finaldata = new ArrayList<String>();
    for(String Data : data) {
      if (Data == null || Data.isEmpty()) continue;
      finaldata.add(Data.replaceAll("&", "§"));
    }
    return finaldata;
  }
  
  @Override
  public List<String> getStringList(String path) {
    try {
      return formateStringlist(this.getFileforYML().getStringList(path));
    }catch(NullPointerException e){
      String full_url = FileName;
      
      if(!(URL == null || URL.isEmpty())) full_url = URL + "\\" + FileName;
          
      Main.plugin.saveResource(full_url, true);
      
      if(reloadFile())
        try {
          return formateStringlist(this.getFileforYML().getStringList(path));
        }catch(NullPointerException ee){
          ee.printStackTrace();
          //Arrays.asList("system error [file load error \" " + FileName + " \"]","please tell admin")
          return new ArrayList<String>();
        }
      else {
        e.printStackTrace();
        //Arrays.asList("system error [file load error \" " + FileName + " \"]","please tell admin")
        return new ArrayList<String>();
      }
      
    }
    
  }
  
  protected void readFile() throws NullPointerException, IllegalArgumentException {
    File File_load = null;
    String full_url = FileName;
    
    if(URL == null || URL.isEmpty())
      File_load = new File(Main.plugin.getDataFolder(), FileName);
    else {
      File_load = new File("./" + Main.plugin.getDataFolder().toString() + "/" + URL + "/" + FileName);
      full_url = URL + "\\" + FileName;
    }
    
    if (!File_load.exists()) Main.plugin.saveResource(full_url, true);
    data = YamlConfiguration.loadConfiguration(File_load);
  }
  
  @Override
  public boolean reloadFile() throws NullPointerException, IllegalArgumentException{
    readFile();
    if(!reloadcmd()) return false;
    return true;
  }

  @Override
  public boolean reloadcmd() {
    return true;
  }
  
  private void printCmd(String msg) {
    String title = AnsiColor.minecraftToAnsiColor("§b[FileIO] ");
    msg = title + AnsiColor.WHITE + AnsiColor.minecraftToAnsiColor(msg);
    DataBase.Print(msg);
  }
}
