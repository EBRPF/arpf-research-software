package org.rrcat.arpf.ui.api.model;



public class CustomerReg {

    String customerRegistrationNo;
    String nameOfOrganisation;
    String researchHeadName;
    String researchEmailId;
    String researchMobileNo;
    String officeAddress;

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getResearchEmailId() {
        return researchEmailId;
    }

    public void setResearchEmailId(String researchEmailId) {
        this.researchEmailId = researchEmailId;
    }

    public String getResearchMobileNo() {
        return researchMobileNo;
    }

    public void setResearchMobileNo(String researchMobileNo) {
        this.researchMobileNo = researchMobileNo;
    }

    public String getResearchHeadName() {
        return researchHeadName;
    }

    public void setResearchHeadName(String researchHeadName) {
        this.researchHeadName = researchHeadName;
    }

    public String getNameOfOrganisation() {
        return nameOfOrganisation;
    }

    public void setNameOfOrganisation(String nameOfOrganisation) {
        this.nameOfOrganisation = nameOfOrganisation;
    }






    public CustomerReg( ) {
    }

    public String getCustomerRegistrationNo() {
        return customerRegistrationNo;
    }

    public void setCustomerRegistrationNo(String customerRegistrationNo) {
        this.customerRegistrationNo = customerRegistrationNo;
    }
}
