/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.recruitment;

/**
 *
 * @author MIRACLE
 */
public class ConsultantListVTO {

    private String attachement_file_name;
    private String attachment_type;
    private String location;
    private String date_of_attachment;
    private String download_link;
    private int object_id;
    private int acc_attachment_id;
    private String status;
    private int reqId;
    private String req_name;
    private String req_skills;
    private String createdDate;

    public String getAttachement_file_name() {
        return attachement_file_name;
    }

    public void setAttachement_file_name(String attachement_file_name) {
        this.attachement_file_name = attachement_file_name;
    }

    public String getAttachment_type() {
        return attachment_type;
    }

    public void setAttachment_type(String attachment_type) {
        this.attachment_type = attachment_type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate_of_attachment() {
        return date_of_attachment;
    }

    public void setDate_of_attachment(String date_of_attachment) {
        this.date_of_attachment = date_of_attachment;
    }

    public String getDownload_link() {
        return download_link;
    }

    public void setDownload_link(String download_link) {
        this.download_link = download_link;
    }

    public int getObject_id() {
        return object_id;
    }

    public void setObject_id(int object_id) {
        this.object_id = object_id;
    }

    public int getAcc_attachment_id() {
        return acc_attachment_id;
    }

    public void setAcc_attachment_id(int acc_attachment_id) {
        this.acc_attachment_id = acc_attachment_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getReqId() {
        return reqId;
    }

    public void setReqId(int reqId) {
        this.reqId = reqId;
    }

    public String getReq_name() {
        return req_name;
    }

    public void setReq_name(String req_name) {
        this.req_name = req_name;
    }

    public String getReq_skills() {
        return req_skills;
    }

    public void setReq_skills(String req_skills) {
        this.req_skills = req_skills;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}