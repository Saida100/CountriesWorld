package com.saida_aliyeva.countriesworld.model_class;

import java.util.List;

public class Countries {

    private String name;
    private String capital;
    private String region;
    private String nativeName;
    private String alpha2Code;
    private String flag;
    private String area;
    private String subregion;
    private List<String> topLevelDomain;
    private List<String> callingCodes;
    private List<Currency> currencies;
    private List<Language> languages;
    private List<Double> latlng;

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public List<String> getTopLevelDomain() {
        return topLevelDomain;
    }

    public void setTopLevelDomain(List<String> topLevelDomain) {
        this.topLevelDomain = topLevelDomain;
    }

    public List<String> getCallingCodes() {
        return callingCodes;
    }

    public void setCallingCodes(List<String> callingCodes) {
        this.callingCodes = callingCodes;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public List<Double> getLatlng() {
        return latlng;
    }

    public void setLatlng(List<Double> latlng) {
        this.latlng = latlng;
    }

    @Override
    public String toString() {
        return "Countries{" +
                "name='" + name + '\'' +
                ", capital='" + capital + '\'' +
                ", region='" + region + '\'' +
                ", nativeName='" + nativeName + '\'' +
                ", alpha2Code='" + alpha2Code + '\'' +
                ", flag='" + flag + '\'' +
                ", area='" + area + '\'' +
                ", subregion='" + subregion + '\'' +
                ", topLevelDomain=" + topLevelDomain +
                ", callingCodes=" + callingCodes +
                ", currencies=" + currencies +
                ", languages=" + languages +
                ", latlng=" + latlng +
                '}';
    }
}
