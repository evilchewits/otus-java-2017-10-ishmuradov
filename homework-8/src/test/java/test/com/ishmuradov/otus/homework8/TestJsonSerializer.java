package test.com.ishmuradov.otus.homework8;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ishmuradov.otus.homework8.JsonSerializer;

public class TestJsonSerializer {

  @Test
  public void test_serialize() throws IOException {
    List<String> strList = new ArrayList<String>();
    strList.add("str1");
    strList.add("str2");
    SampleBean nestedBean = new SampleBean(2, '\t', true, "MyString", new SampleBean(), new int[] { 1, 3 },
        new SampleBean[] { new SampleBean(), new SampleBean() }, strList);
    SampleBean bean = new SampleBean();
    bean.setObjVar(nestedBean);
    
    ObjectMapper mapper = new ObjectMapper();

    String jsonJackson = mapper.writeValueAsString(bean);
    String jsonOwnSerializer = JsonSerializer.serialize(bean).toString();
    
    assertEquals(mapper.readTree(jsonJackson), mapper.readTree(jsonOwnSerializer));
  }
}
