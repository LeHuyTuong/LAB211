/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author USER
 */
public class Mountain {
    private String code;
    private String mountainName;
    private String province;
    private String description;
    
    public Mountain(){
    }

    public Mountain(String code, String mountainName, String province, String description) {
        this.code = code;
        this.mountainName = mountainName;
        this.province = province;
        this.description = description;
    }

    public String getCode() {
        return "MT"+ code ;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMountainName() {
        return mountainName;
    }

    public void setMountainName(String mountainName) {
        this.mountainName = mountainName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
