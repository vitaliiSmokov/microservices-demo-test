package io.swagger.petstore.api.generators;

import io.swagger.petstore.api.utils.FakerUtil;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@UtilityClass
public class DataSupplierGenerator {

  public List<String> getEmptyStrings() {
    return Arrays.asList("", null, "   ");
  }

  public List<String> getStrings(int min, int max) {
    return Arrays.asList(FakerUtil.getRandomCharacters(min), FakerUtil.getRandomCharacters(max));
  }

  public List<String> getStrings(int max, List<String> values) {
    List<String> strings = new ArrayList<>(values);
    strings.add(FakerUtil.getRandomCharacters(max));
    return strings;
  }

  public List<String> getStrings(int min, int max, List<String> values) {
    List<String> strings = new ArrayList<>(values);
    strings.addAll(
        Arrays.asList(FakerUtil.getRandomCharacters(min), FakerUtil.getRandomCharacters(max)));
    return strings;
  }

  public List<String> getStrings(List<String> first, List<String> last) {
    List<String> strings = new ArrayList<>(first);
    strings.addAll(last);
    return strings;
  }
}
