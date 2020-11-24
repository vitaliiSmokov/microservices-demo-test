package io.swagger.petstore.api.generators;

import com.devskiller.jfairy.producer.person.Person;
import com.github.javafaker.Faker;
import io.swagger.petstore.api.utils.FakerUtil;
import io.swagger.petstore.api.utils.RandomUtil;

public class BaseGenerator {
  protected static final Faker FAKER = FakerUtil.getFaker();
  protected static final Person PERSON = RandomUtil.getFairy().person();
}
