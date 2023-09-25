package com.example.kommuner.model;

import jakarta.persistence.*;

@Entity
public class Kommune {

    @Id
    @Column(length = 4)
    private String kode;
    private String navn;
    private String href;
    private String hrefPicture;

    @ManyToOne
    @JoinColumn(name = "region", referencedColumnName = "kode")
    Region region;

    public void setHrefPicture(String hrefPicture) {
        this.hrefPicture = hrefPicture;
    }

    public String getHrefPicture() {
        return hrefPicture;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
