package com.adleritech.flexibee.core.api.domain;

import com.adleritech.flexibee.core.api.transformers.Factory;
import org.junit.Test;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class InternalDocumentTest {

    @Test
    public void parseFromXmlToObject() throws Exception {
        String xml = "<winstrom version=\"1.0\">\n" +
            "    <interni-doklad>\n" +
            "        <typDokl>code:ID</typDokl>\n" +
            "        <datVyst>2011-01-01</datVyst>\n" +
            "        <varSym>123</varSym><!-- účet MD -->\n" +
            "    </interni-doklad>\n" +
            "</winstrom>";


        Serializer serializer = new Persister(Factory.matchers());

        WinstromRequest example = serializer.read(WinstromRequest.class, xml);

        assertThat(example.getInternalDocument()).isNotNull();
        assertThat(example.getInternalDocument().getIssued()).isEqualTo("2011-01-01");
        assertThat(example.getInternalDocument().getVariableSymbol()).isEqualTo("123");
    }

    @Test
    public void serializeToXml() throws Exception {
        String xml = "<winstrom version=\"1.0\">\n" +
            "    <interni-doklad>\n" +
            "        <typDokl>code:ID</typDokl>\n" +
            "        <firma>code:PBENDA</firma>\n" +
            "        <datVyst>2017-10-03</datVyst>\n" +
            "        <varSym>123</varSym>\n" +
            "    </interni-doklad>\n" +
            "</winstrom>";

        WinstromRequest request = WinstromRequest.builder()
                .internalDocument(
                        InternalDocument.builder()
                                .company("code:PBENDA")
                                .documentType("code:ID")
                                .issued(LocalDate.parse("2017-10-03"))
                                .variableSymbol("123")
                                .build())
                .build();

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        Serializer serializer = new Persister(Factory.matchers());
        serializer.write(request, result);

        assertThat(result.toString()).isXmlEqualTo(xml);
    }
}
