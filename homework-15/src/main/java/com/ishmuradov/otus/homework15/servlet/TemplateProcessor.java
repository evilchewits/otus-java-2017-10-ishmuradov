package com.ishmuradov.otus.homework15.servlet;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

public class TemplateProcessor {
  private static final Version freeMarkerVersion = Configuration.VERSION_2_3_23;
  private static final String TMPL_DIR = "/tmpl/";
  private static TemplateProcessor instance = new TemplateProcessor();
  private final Configuration configuration;

  private TemplateProcessor() {
    configuration = new Configuration(freeMarkerVersion);
    configuration.setClassForTemplateLoading(this.getClass(), TMPL_DIR);
  }

  public static TemplateProcessor instance() {
    return instance;
  }

  public String getPage(String filename, Map<String, Object> data) throws IOException {
    try (Writer stream = new StringWriter()) {
      Template template = configuration.getTemplate(filename);
      template.process(data, stream);
      return stream.toString();
    } catch (TemplateException e) {
      throw new IOException(e);
    }
  }
}