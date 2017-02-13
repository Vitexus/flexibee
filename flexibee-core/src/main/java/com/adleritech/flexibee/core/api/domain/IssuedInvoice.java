package com.adleritech.flexibee.core.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.time.Instant;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IssuedInvoice {

    @Element(name="typDokl")
    private String documentType;

    @Element(name="firma")
    private String company;

    @Element(name = "datVyst", required = false)
    private Instant issued;

    @Element(name = "bezPolozek", required = false)
    private Boolean withoutItems;

    @Element(name = "sumDphZakl", required = false)
    private Double sumWithoutVat;

    @ElementList(name = "polozkyFaktury", required = false)
    private List<IssuedInvoiceItem> items;

}
