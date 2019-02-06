package com.socks.api.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

public class PropertyUtil {

  public Properties readPropertyFromFile(String pathToFile) {
    FileInputStream fileInputStream = null;
    Properties property = new Properties();

    try {
      fileInputStream = new FileInputStream(pathToFile);
//            property.load(fileInputStream);
      property.load(new InputStreamReader(fileInputStream, Charset.forName("UTF-8")));
    } catch (IOException e) {
      System.err.println(String.format("File with path \"%s\" is not found", pathToFile));
    } finally {
      if (fileInputStream != null) {
        try {
          fileInputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return property;
  }
}