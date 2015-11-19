
package com.mss.msp.acc.projectsdata;

/**
 *
 * @author Riza Erbas
 * POJO's for the org_rel table objects
 */
public class Org_RelVTO {
    
    private Integer id;
    private Integer orgID;
    private Integer relatedOrgID;
    private String orgStatus;
    private String typeOfRelation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrgID() {
        return orgID;
    }

    public void setOrgID(Integer orgID) {
        this.orgID = orgID;
    }

    public Integer getRelatedOrgID() {
        return relatedOrgID;
    }

    public void setRelatedOrgID(Integer relatedOrgID) {
        this.relatedOrgID = relatedOrgID;
    }

    public String getOrgStatus() {
        return orgStatus;
    }

    public void setOrgStatus(String orgStatus) {
        this.orgStatus = orgStatus;
    }

    public String getTypeOfRelation() {
        return typeOfRelation;
    }

    public void setTypeOfRelation(String typeOfRelation) {
        this.typeOfRelation = typeOfRelation;
    }

    @Override
    public String toString() {
        return "Org_RelVTO{" + "id=" + id + ", orgID=" + orgID + ", relatedOrgID=" + relatedOrgID + ", orgStatus=" + orgStatus + ", typeOfRelation=" + typeOfRelation + '}';
    }

  
}
