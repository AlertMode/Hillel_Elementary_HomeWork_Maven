package edu.palmirov.task_41;

import edu.palmirov.task_41.enteties.CurrencyRate;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CurrencyRateServiceHandler extends DefaultHandler {
    private int r030;
    private String txt;
    private double rate;
    private String cc;
    private LocalDate exchangeDate;

    private List<CurrencyRate> currencyRateList = new ArrayList<>();

    private byte r030Lock = 0;
    private byte txtLock = 0;
    private byte rateLock = 0;
    private byte ccLock = 0;
    private byte exchangeDateLock = 0;

    public CurrencyRateServiceHandler(){ super(); }

    @Override
    public void startDocument(){
        System.out.println("Getting info about the NBU currency rate...");
    }

    @Override
    public void endDocument(){
        System.out.println("Stop getting about the NBU currency rate.");
    }

    @Override
    public void startElement(String uri, String name, String qName, Attributes atts){
        if(qName.equalsIgnoreCase("r030")){
            r030Lock = 1;
        }
        if(qName.equalsIgnoreCase("txt")){
            txtLock = 1;
        }
        if(qName.equalsIgnoreCase("rate")){
            rateLock = 1;
        }
        if(qName.equalsIgnoreCase("cc")){
            ccLock = 1;
        }
        if(qName.equalsIgnoreCase("exchangedate")){
            exchangeDateLock = 1;
        }
    }
    @Override
    public void characters(char ch[], int start, int length){
        if(r030Lock == 1){
            r030 = Integer.parseInt(new String(ch, start, length));
            System.out.println("R030: " + r030);
            r030Lock = 2;
        }
        if(txtLock == 1){
            txt = new String(ch, start, length);
            System.out.println("CurrencyRate: " + txt);
            txtLock = 2;
        }
        if(rateLock == 1){
            rate = Double.parseDouble(new String(ch, start, length));
            System.out.println("Rate: " + rate);
            rateLock = 2;
        }
        if(ccLock == 1){
            cc = new String(ch, start, length);
            System.out.println("Abbreviation: " + cc);
            ccLock = 2;
        }
        if(exchangeDateLock == 1){
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
            String date = new String(ch, start, length);
            exchangeDate = LocalDate.parse(date, dateTimeFormatter);
            System.out.println("Date: " + date);
            exchangeDateLock = 2;
            System.out.println("------------------------------------------");
        }
        if(r030Lock == 2 && txtLock == 2 && rateLock == 2 && ccLock == 2 && exchangeDateLock == 2){
            currencyRateList.add(new CurrencyRate(r030, txt, rate, cc, exchangeDate));
            r030 = 0;
            txtLock = 0;
            rateLock = 0;
            ccLock = 0;
            exchangeDateLock = 0;
        }
    }

    public List<CurrencyRate> getCurrencyRateList(){
        return currencyRateList == null ? Collections.EMPTY_LIST : currencyRateList;
    }
}