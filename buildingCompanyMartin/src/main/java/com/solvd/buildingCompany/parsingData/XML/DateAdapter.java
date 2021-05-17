package com.solvd.buildingCompany.parsingData.XML;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter extends XmlAdapter<String, Date> {

    @Override
    public String marshal(Date v) throws Exception {
        return new SimpleDateFormat("MM/dd/yyyy").format(v);
    }

    @Override
    public Date unmarshal(String v) throws Exception {
        return new SimpleDateFormat("MM/dd/yyyy").parse(v);
    }

}