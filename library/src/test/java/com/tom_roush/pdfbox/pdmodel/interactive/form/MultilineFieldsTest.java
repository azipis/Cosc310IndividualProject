/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tom_roush.pdfbox.pdmodel.interactive.form;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.cos.COSString;
import com.tom_roush.pdfbox.pdfparser.PDFStreamParser;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MultilineFieldsTest
{
   private static final File IN_DIR = new File("src/test/resources/pdfbox/com/tom_roush/pdfbox/pdmodel/interactive/form");

   // Test for PDFBOX-3812
   @Test
   public void testMultilineAuto() throws IOException
   {
      PDDocument document = PDDocument.load(new File(IN_DIR, "PDFBOX3812-acrobat-multiline-auto.pdf"));
      PDAcroForm acroForm = document.getDocumentCatalog().getAcroForm();

      // Get and store the field sizes in the original PDF
      PDTextField fieldMultiline = (PDTextField) acroForm.getField("Multiline");
      float fontSizeMultiline = getFontSizeFromAppearanceStream(fieldMultiline);

      PDTextField fieldSingleline = (PDTextField) acroForm.getField("Singleline");
      float fontSizeSingleline = getFontSizeFromAppearanceStream(fieldSingleline);

      PDTextField fieldMultilineAutoscale = (PDTextField) acroForm.getField("MultilineAutoscale");
      float fontSizeMultilineAutoscale = getFontSizeFromAppearanceStream(fieldMultilineAutoscale);

      PDTextField fieldSinglelineAutoscale = (PDTextField) acroForm.getField("SinglelineAutoscale");
      float fontSizeSinglelineAutoscale = getFontSizeFromAppearanceStream(fieldSinglelineAutoscale);

      fieldMultiline.setValue("Multiline - Fixed");
      fieldSingleline.setValue("Singleline - Fixed");
      fieldMultilineAutoscale.setValue("Multiline - auto");
      fieldSinglelineAutoscale.setValue("Singleline - auto");

      assertEquals(fontSizeMultiline, getFontSizeFromAppearanceStream(fieldMultiline), 0.001f);
      assertEquals(fontSizeSingleline, getFontSizeFromAppearanceStream(fieldSingleline), 0.001f);
      assertEquals(fontSizeMultilineAutoscale, getFontSizeFromAppearanceStream(fieldMultilineAutoscale), 0.001f);
      assertEquals(fontSizeSinglelineAutoscale, getFontSizeFromAppearanceStream(fieldSinglelineAutoscale), 0.025f);
   }

   // Test for PDFBOX-3812
   @Test
   public void testMultilineBreak() throws IOException
   {
      final String TEST_PDF = "PDFBOX-3835-input-acrobat-wrap.pdf";
      PDDocument document = PDDocument.load(new File(IN_DIR, TEST_PDF));
      PDAcroForm acroForm = document.getDocumentCatalog().getAcroForm();

      // Get and store the field sizes in the original PDF
      PDTextField fieldInput = (PDTextField) acroForm.getField("filled");
      String fieldValue = fieldInput.getValue();
      List<String> acrobatLines = getTextLinesFromAppearanceStream(fieldInput);
      fieldInput.setValue(fieldValue);
      List<String> pdfboxLines = getTextLinesFromAppearanceStream(fieldInput);
      assertEquals("Number of lines generated by PDFBox shall match Acrobat", acrobatLines.size(),pdfboxLines.size());
      for (int i = 0; i < acrobatLines.size(); i++)
      {
         assertEquals("Number of characters per lines generated by PDFBox shall match Acrobat", acrobatLines.get(i).length(), pdfboxLines.get(i).length());
      }
      document.close();
   }

   private float getFontSizeFromAppearanceStream(PDField field) throws IOException
   {
      PDAnnotationWidget widget = field.getWidgets().get(0);
      PDFStreamParser parser = new PDFStreamParser(widget.getNormalAppearanceStream());

      Object token = parser.parseNextToken();

      while (token != null)
      {
         if (token instanceof COSName && ((COSName) token).getName().equals("Helv"))
         {
            token = parser.parseNextToken();
            if (token != null && token instanceof COSNumber)
            {
               return ((COSNumber) token).floatValue();
            }
         }
         token = parser.parseNextToken();
      }
      return 0;
   }

   private List<String> getTextLinesFromAppearanceStream(PDField field) throws IOException
   {
      PDAnnotationWidget widget = field.getWidgets().get(0);
      PDFStreamParser parser = new PDFStreamParser(widget.getNormalAppearanceStream());

      Object token = parser.parseNextToken();

      List<String> lines = new ArrayList<String>();

      while (token != null)
      {
         if (token instanceof COSString)
         {
            lines.add(((COSString) token).getString());
         }
         token = parser.parseNextToken();
      }
      return lines;
   }

}
