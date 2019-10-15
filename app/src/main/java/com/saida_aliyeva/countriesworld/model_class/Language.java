package com.saida_aliyeva.countriesworld.model_class;

public class Language {

    private String name;
    private String nativeName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    @Override
    public String toString() {
        return "Language{" +
                "name='" + name + '\'' +
                ", nativeName='" + nativeName + '\'' +
                '}';
    }
}
